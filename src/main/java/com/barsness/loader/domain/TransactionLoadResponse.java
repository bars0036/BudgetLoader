package com.barsness.loader.domain;

import com.barsness.loader.domain.Transaction;

import java.util.List;

/**
 * Created by matt.barsness on 10/5/17.
 */
public class TransactionLoadResponse {

    private String response;
    private int transLoaded;
    private int transFailed;
    private int transSkipped;
    private List<Transaction> duplicateTransactions;
    private String originalFileName;
    private long fileSize;

    public TransactionLoadResponse(String response, int transLoaded, int transFailed, int transSkipped, List<Transaction> duplicateTransactions) {
        this.response = response;
        this.transLoaded = transLoaded;
        this.transFailed = transFailed;
        this.transSkipped = transSkipped;
        this.duplicateTransactions = duplicateTransactions;
    }

    public TransactionLoadResponse() {
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getTransLoaded() {
        return transLoaded;
    }

    public void setTransLoaded(int transLoaded) {
        this.transLoaded = transLoaded;
    }

    public int getTransFailed() {
        return transFailed;
    }

    public void setTransFailed(int transFailed) {
        this.transFailed = transFailed;
    }

    public int getTransSkipped() {
        return transSkipped;
    }

    public void setTransSkipped(int transSkipped) {
        this.transSkipped = transSkipped;
    }

    public List<Transaction> getDuplicateTransactions() {
        return duplicateTransactions;
    }

    public void setDuplicateTransactions(List<Transaction> duplicateTransactions) {
        this.duplicateTransactions = duplicateTransactions;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
}
