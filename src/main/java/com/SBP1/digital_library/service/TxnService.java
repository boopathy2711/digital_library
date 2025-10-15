package com.SBP1.digital_library.service;

import com.SBP1.digital_library.dto.request.BookReturnRequest;
import com.SBP1.digital_library.dto.request.TxnRequest;
import com.SBP1.digital_library.enums.TxnStatus;
import com.SBP1.digital_library.exceptions.BookException;
import com.SBP1.digital_library.exceptions.TxnException;
import com.SBP1.digital_library.exceptions.UserException;
import com.SBP1.digital_library.model.Books;
import com.SBP1.digital_library.model.LibUser;
import com.SBP1.digital_library.model.Txn;
import com.SBP1.digital_library.repository.TxnRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TxnService {

    @Autowired
    private TxnRepository txnRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Value("${user.valid.days}")
    private int validNoDays;

    @Value("${user.delayed.finePerDay}")
    private int finePerDay;

    @Transactional
    public String issueBook(TxnRequest txnRequest) throws BookException, UserException {
        LibUser userFromDB = userService.checkUser(txnRequest.getUserEmail());
        if(userFromDB==null){
            throw new UserException("User is not registered with the library");
        }
        Books booksFromDB = bookService.checkBookIsValid(txnRequest.getBookNo());
        if(booksFromDB==null){
            throw new BookException("Request book not available or Invalid Book No");
        }
        if(booksFromDB.getLibUser()!=null){
            throw new BookException("Book is issued to another student");
        }
        String txnID = UUID.randomUUID().toString();
        Txn txn = Txn.builder().txnId(txnID).
                txnStatus(TxnStatus.ISSUED).libUser(userFromDB).books(booksFromDB).
                issuedDate(new Date()).build();
        txnRepository.save(txn);
        bookService.markBookUnavailable(booksFromDB, userFromDB);
        return txnID;
    }

    @Transactional
    public int returnBook(BookReturnRequest bookReturnRequest) throws UserException, BookException, TxnException {
        LibUser userFromDB = userService.checkUser(bookReturnRequest.getEmail());
        if(userFromDB==null){
            throw new UserException("User is not registered with the library");
        }
        Books booksFromDB = bookService.checkBookIsValid(bookReturnRequest.getBookNo());
        if(booksFromDB==null){
            throw new BookException("Request book not available or Invalid Book No");
        }
        if(booksFromDB.getLibUser()!=null && booksFromDB.getLibUser().equals(userFromDB)){
            Txn txnfromDB = txnRepository.findByTxnId(bookReturnRequest.getTxnId());
            if(txnfromDB ==null){
                throw new TxnException("No transaction found for the given txnId");
            }
            int amount = calculateSettlementAmount(txnfromDB, booksFromDB);
            if(amount==booksFromDB.getSecurityAmount()){
                txnfromDB.setTxnStatus(TxnStatus.RETURNED);
            }
            else{
                txnfromDB.setTxnStatus(TxnStatus.FINED);
            }
            booksFromDB.setLibUser(null);
            txnfromDB.setSubmittedDate(new Date());
            txnRepository.save(txnfromDB);
            return amount;
        }
        else {
            throw new TxnException("Book is assigned to someone else or not at all assigned");
        }
    }

    private int calculateSettlementAmount(Txn txnfromDB, Books booksFromDB){
        Long issueTime = txnfromDB.getIssuedDate().getTime();
        Long submitTime = System.currentTimeMillis();
        Long diff = submitTime - issueTime;
        int daysPassed = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        if(daysPassed>validNoDays){
            int fine = (daysPassed - validNoDays) * finePerDay;
            return booksFromDB.getSecurityAmount() - fine;
        }
        return booksFromDB.getSecurityAmount();
    }
}

//without @Transactional the first query will only be executed which is Txn
//by using @Transactional all the queries will be executed as either it will do both or dont do anything.
