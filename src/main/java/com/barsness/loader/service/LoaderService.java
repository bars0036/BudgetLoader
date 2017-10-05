package com.barsness.loader.service;

import com.barsness.loader.domain.TransactionLoadResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by matt.barsness on 10/5/17.
 */
public interface LoaderService {

    public TransactionLoadResponse loadTransactions(MultipartFile file);
    public TransactionLoadResponse loadTransactions(String file);

}
