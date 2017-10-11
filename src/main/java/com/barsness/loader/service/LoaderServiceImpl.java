package com.barsness.loader.service;

import com.barsness.loader.domain.Transaction;
import com.barsness.loader.domain.TransactionLoadResponse;
import com.barsness.loader.helper.ParseTransactionFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matt.barsness on 10/5/17.
 */

@Service
public class LoaderServiceImpl implements LoaderService {

    @Value("${budget.service.url}")
    private String budgetAppUrl;

    @Value("${budget.budgetloader.sendTransactions}")
    private boolean sendTransactions;

    public TransactionLoadResponse loadTransactions(MultipartFile file){
        TransactionLoadResponse response = new TransactionLoadResponse();
        try {
            List<Transaction> trans = ParseTransactionFile.parseTransactionCSV(file.getInputStream());
            response = sendTransactions(trans);
            response.setResponse("Success");
        }
        catch(Exception e){
            System.out.println("Error reading file: " +e.getMessage());
            e.printStackTrace();
            response.setResponse("Failed");
        }
        response.setOriginalFileName(file.getOriginalFilename());
        response.setFileSize(file.getSize());
        return response;
    }

    public TransactionLoadResponse loadTransactions(String file){

        return new TransactionLoadResponse();
    }

    private TransactionLoadResponse sendTransactions(List<Transaction> trans){
        TransactionLoadResponse response = new TransactionLoadResponse();
        RestTemplate restTemplate = new RestTemplate();
        int transLoaded = 0;
        int transFailed = 0;
        int transSkipped = 0;
        List<Transaction> duplicateTrans = new ArrayList<>();
        if(sendTransactions) {
            for (Transaction tran : trans) {
                ResponseEntity<Transaction[]> checkExistsResponse = restTemplate.getForEntity(budgetAppUrl + "/transaction/find?transDate={transDate}&description={description}&value={value}", Transaction[].class, tran.getTransactionDate().toString(), tran.getDescription(), tran.getValue());
                if (checkExistsResponse.getBody().length == 0) {
                    HttpEntity<Transaction> request = new HttpEntity<>(tran);
                    ResponseEntity<Transaction> forEntity = restTemplate.exchange(budgetAppUrl + "/transaction/add", HttpMethod.POST, request, Transaction.class);
                    if (forEntity.getStatusCode() == HttpStatus.OK) {
                        transLoaded++;
                    } else {
                        transFailed++;
                    }
                } else {
                    duplicateTrans.add(tran);
                    transSkipped++;
                    System.out.println("Transaction Not Loaded: " + tran.toString());
                }
            }
        }
        else{
            transLoaded = trans.size();
        }
        response.setTransLoaded(transLoaded);
        response.setTransFailed(transFailed);
        response.setTransSkipped(transSkipped);
        response.setDuplicateTransactions(duplicateTrans);
        return response;
    }
}
