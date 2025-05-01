package com.samuel.backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samuel.backend.entity.Transaction;
import com.samuel.backend.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE) // Pastikan Content-Type adalah application/json
    public ResponseEntity<Map<String, Object>> getAllTransactions() {
        try {
            System.out.println("Fetching all transactions...");
            Map<String, Object> response = new HashMap<>();
            response.put("data", transactionService.getAllTransactions());
            System.out.println("Transactions fetched successfully");
            response.put("status", transactionService.getAllStatuses());
            System.out.println("Statuses fetched successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("Error in getAllTransactions: " + e.getMessage());
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to fetch transactions: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getTransactionById(@PathVariable Long id) {
        try {
            System.out.println("Fetching transaction with id: " + id);
            Transaction transaction = transactionService.getTransactionById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("data", transaction);
            System.out.println("Transaction fetched successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("Error in getTransactionById: " + e.getMessage());
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to fetch transaction: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> createTransaction(@RequestBody Transaction transaction) {
        try {
            System.out.println("Creating new transaction: " + transaction);
            Transaction newTransaction = transactionService.createTransaction(transaction);
            Map<String, Object> response = new HashMap<>();
            response.put("data", newTransaction);
            System.out.println("Transaction created successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("Error in createTransaction: " + e.getMessage());
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to create transaction: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        try {
            System.out.println("Updating transaction with id: " + id);
            Transaction updatedTransaction = transactionService.updateTransaction(id, transaction);
            Map<String, Object> response = new HashMap<>();
            response.put("data", updatedTransaction);
            System.out.println("Transaction updated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("Error in updateTransaction: " + e.getMessage());
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to update transaction: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}