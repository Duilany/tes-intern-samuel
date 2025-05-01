package com.samuel.backend.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "productid")
    private String productid;

    @Column(name = "productname") // Ubah ke huruf kecil
    private String productName;

    @Column(name = "amount")
    private String amount;

    @Column(name = "customername") // Ubah ke huruf kecil
    private String customerName;

    @Column(name = "status")
    private int status;

    @Column(name = "transactiondate") // Ubah ke huruf kecil
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime transactionDate;

    @Column(name = "createby") // Ubah ke huruf kecil
    private String createBy;

    @Column(name = "createon") // Ubah ke huruf kecil
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createOn;

    // Getters and Setters with Logging
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProductid() { return productid; }
    public void setProductid(String productid) { this.productid = productid; }

    public String getProductName() { 
        System.out.println("Getting productName: " + productName);
        return productName; 
    }
    public void setProductName(String productName) { this.productName = productName; }

    public String getAmount() { return amount; }
    public void setAmount(String amount) { this.amount = amount; }

    public String getCustomerName() { 
        System.out.println("Getting customerName: " + customerName);
        return customerName; 
    }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public LocalDateTime getTransactionDate() { 
        System.out.println("Getting transactionDate: " + transactionDate);
        return transactionDate; 
    }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }

    public String getCreateBy() { 
        System.out.println("Getting createBy: " + createBy);
        return createBy; 
    }
    public void setCreateBy(String createBy) { this.createBy = createBy; }

    public LocalDateTime getCreateOn() { 
        System.out.println("Getting createOn: " + createOn);
        return createOn; 
    }
    public void setCreateOn(LocalDateTime createOn) { this.createOn = createOn; }
}