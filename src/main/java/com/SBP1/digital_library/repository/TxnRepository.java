package com.SBP1.digital_library.repository;

import com.SBP1.digital_library.model.Txn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxnRepository extends JpaRepository<Txn, Integer> {
    Txn findByTxnId(String value);
}
