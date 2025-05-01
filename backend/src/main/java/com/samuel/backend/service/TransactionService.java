package com.samuel.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samuel.backend.entity.Status;
import com.samuel.backend.entity.Transaction;
import com.samuel.backend.repository.StatusRepository;
import com.samuel.backend.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private StatusRepository statusRepository;

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        System.out.println("Transactions fetched: " + transactions);
        return transactions;
    }

    public List<Status> getAllStatuses() {
        List<Status> statuses = statusRepository.findAll();
        System.out.println("Statuses fetched: " + statuses);
        return statuses;
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
    }

    public Transaction createTransaction(Transaction transaction) {
        Long maxId = transactionRepository.findAll().stream()
                .map(Transaction::getId)
                .max(Long::compareTo)
                .orElse(0L);
        transaction.setId(maxId + 1);
        transaction.setCreateBy(transaction.getCustomerName());
        transaction.setCreateOn(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Long id, Transaction transaction) {
        Transaction existingTransaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
        existingTransaction.setProductid(transaction.getProductid());
        existingTransaction.setProductName(transaction.getProductName());
        existingTransaction.setAmount(transaction.getAmount());
        existingTransaction.setCustomerName(transaction.getCustomerName());
        existingTransaction.setStatus(transaction.getStatus());
        existingTransaction.setTransactionDate(transaction.getTransactionDate());
        return transactionRepository.save(existingTransaction);
    }
}