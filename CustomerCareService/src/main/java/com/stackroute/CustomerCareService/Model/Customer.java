package com.stackroute.CustomerCareService.Model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document

public class Customer {

    public int getCustomerCareId() {
        return customerCareId;
    }

    public void setCustomerCareId(int customerCareId) {
        this.customerCareId = customerCareId;
    }

    @Id
    int customerCareId;
    int userID;

    String userEmail;

    String name;
    Long contact;
    String issue;
    String status;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }



    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerCareId='" + customerCareId + '\'' +
                ", userID=" + userID +
                ", userEmail='" + userEmail + '\'' +
                ", name='" + name + '\'' +
                ", contact=" + contact +
                ", issue='" + issue + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public Customer(int customerCareId, int userID, String name, String userEmail, long contact, String status) {
        this.customerCareId = customerCareId;
        this.userID = userID;

        this.name = name;
        this.userEmail = userEmail;
        this.contact = contact;
        this.status = status;
    }

}
