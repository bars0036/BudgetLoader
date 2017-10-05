package com.barsness.loader.service;

import com.barsness.loader.domain.TransactionLoadResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by matt.barsness on 10/5/17.
 */

@Service
public class LoaderServiceImpl implements LoaderService {


    public TransactionLoadResponse loadTransactions(MultipartFile file){

        return new TransactionLoadResponse();
    }

    public TransactionLoadResponse loadTransactions(String file){

        return new TransactionLoadResponse();
    }
}
