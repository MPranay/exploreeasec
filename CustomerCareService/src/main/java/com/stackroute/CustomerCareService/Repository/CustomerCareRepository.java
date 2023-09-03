package com.stackroute.CustomerCareService.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.stackroute.CustomerCareService.Model.Customer;

import java.util.List;

public interface CustomerCareRepository extends MongoRepository<Customer, Integer> {

    List<Customer> findAllQueryByUserID(int userID);

    Customer findAllQueryByUserIDAndCustomerCareId(int userID, int customerCareId);

}
