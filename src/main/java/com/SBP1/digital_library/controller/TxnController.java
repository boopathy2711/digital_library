package com.SBP1.digital_library.controller;

import com.SBP1.digital_library.dto.request.BookReturnRequest;
import com.SBP1.digital_library.dto.request.TxnRequest;
import com.SBP1.digital_library.exceptions.BookException;
import com.SBP1.digital_library.exceptions.TxnException;
import com.SBP1.digital_library.exceptions.UserException;
import com.SBP1.digital_library.service.TxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/txn")
@Validated
public class TxnController {

    @Autowired
    private TxnService txnService;
    @PostMapping("/issue")
    public String issueBook(@RequestBody TxnRequest txnRequest) throws BookException, UserException {
        return txnService.issueBook(txnRequest);
    }

    @PutMapping("/return")
    public int returnBook(@RequestBody BookReturnRequest bookReturnRequest) throws BookException, UserException, TxnException {
        return txnService.returnBook(bookReturnRequest);
    }





}
