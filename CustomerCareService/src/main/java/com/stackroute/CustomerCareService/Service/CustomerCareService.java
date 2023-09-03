package com.stackroute.CustomerCareService.Service;

import com.stackroute.CustomerCareService.Model.Customer;

import java.util.List;

public interface CustomerCareService {
    List<Customer> getAllQuery();
    List<Customer> getAllQueryByUserId(int userID);

    Customer addQuery(Customer customerCare);
    Customer updateQuery(Customer customerCare, int userID, int customerCareId);


}
