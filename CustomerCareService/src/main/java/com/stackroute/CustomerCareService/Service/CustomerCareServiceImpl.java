package com.stackroute.CustomerCareService.Service;


import com.stackroute.CustomerCareService.Model.Customer;
import com.stackroute.CustomerCareService.Repository.CustomerCareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerCareServiceImpl implements CustomerCareService{
        private CustomerCareRepository customerCareRepository;
        @Autowired
        public  CustomerCareServiceImpl(CustomerCareRepository customerCareRepository){
            this.customerCareRepository = customerCareRepository;
        }

        @Override
        public List<Customer> getAllQueryByUserId(int userID) {
            return customerCareRepository.findAllQueryByUserID(userID);
        }



    @Override
    public Customer addQuery(Customer customerCare) {
        Customer r = customerCareRepository.findAllQueryByUserIDAndCustomerCareId(customerCare.getUserID(), customerCare.getCustomerCareId());
        if(r==null)
            return customerCareRepository.save(customerCare);
        return null;
    }

    @Override
        public List<Customer> getAllQuery() {
            return customerCareRepository.findAll();
        }


        @Override
        public Customer updateQuery(Customer customerCare, int userID, int customerCareId) {
            Customer r = customerCareRepository.findAllQueryByUserIDAndCustomerCareId(userID, customerCareId);
            if(r!=null) {
                r.setUserID(customerCare.getUserID());
                r.setCustomerCareId(customerCareId);
                r.setUserEmail(customerCare.getUserEmail());
                r.setName(customerCare.getName());
                r.setContact(customerCare.getContact());
                r.setIssue(customerCare.getIssue());
                r.setStatus(customerCare.getStatus());
                return customerCareRepository.save(r);
            }
            return null;
        }
}
