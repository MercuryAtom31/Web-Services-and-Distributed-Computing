package com.hichembenzair.soccerjerseysapplication.customersubdomain.businesslayer;

import com.hichembenzair.soccerjerseysapplication.customersubdomain.dataaccesslayer.Customer;
import com.hichembenzair.soccerjerseysapplication.customersubdomain.dataaccesslayer.CustomerRepository;
import com.hichembenzair.soccerjerseysapplication.customersubdomain.mapperlayer.CustomerRequestMapper;
import com.hichembenzair.soccerjerseysapplication.customersubdomain.mapperlayer.CustomerResponseMapper;
import com.hichembenzair.soccerjerseysapplication.customersubdomain.presentationlayer.CustomerRequestModel;
import com.hichembenzair.soccerjerseysapplication.customersubdomain.presentationlayer.CustomerResponseModel;
import com.hichembenzair.soccerjerseysapplication.utils.exceptions.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    private final CustomerRequestMapper requestMapper;
    private final CustomerResponseMapper responseMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               CustomerRequestMapper requestMapper,
                               CustomerResponseMapper responseMapper) {
        this.customerRepository = customerRepository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @Override
    public List<CustomerResponseModel> getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();

        List<CustomerResponseModel> customerResponseModels = new ArrayList<>();

        for (Customer customer : customerList) {
            CustomerResponseModel customerResponseModel = new CustomerResponseModel();
            BeanUtils.copyProperties(customer, customerResponseModel);

            customerResponseModel.setCustomerId(customer.getCustomerIdentifier().getCustomerIdentifier());
            customerResponseModel.setCustomerFirstName(customer.getCustomerFirstName());
            customerResponseModel.setCustomerLastName(customer.getCustomerLastName());

            customerResponseModels.add(customerResponseModel);
        }
        return customerResponseModels;
    }

    @Override
    public CustomerResponseModel getCustomerByCustomerId(String customerId) {
        // Find the customer by customerId from the repository
        Customer customer = customerRepository.findByCustomerIdentifier_CustomerId(customerId);

        // Check if the customer is not found, throw NotFoundException
        if (customer == null) {
            throw new NotFoundException("Customer not found with ID: " + customerId);
        }

        // Map the Customer entity to CustomerResponseModel using the responseMapper
        return responseMapper.entityToResponseModel(customer);
    }

    @Override
    public CustomerResponseModel addCustomer(CustomerRequestModel customerRequestModel) {
        // Validate customerRequestModel if necessary

        // Map the CustomerRequestModel to Customer entity using the requestMapper
        Customer customer = requestMapper.requestModelToEntity(customerRequestModel);

        // Save the customer entity in the repository
        Customer savedCustomer = customerRepository.save(customer);

        // Map the saved Customer entity to CustomerResponseModel using the responseMapper
        return responseMapper.entityToResponseModel(savedCustomer);
    }

    @Override
    public CustomerResponseModel updateCustomer(CustomerRequestModel customerRequestModel, String customerId) {
        // Find the existing customer by customerId from the repository
        Customer existingCustomer = customerRepository.findByCustomerIdentifier_CustomerId(customerId);

        // Check if the customer is not found, throw NotFoundException
        if (existingCustomer == null) {
            throw new NotFoundException("Customer not found with ID: " + customerId);
        }

        // Update the existingCustomer fields using customerRequestModel
        // You can use your own logic to update the customer fields

        // Save the updated customer entity in the repository
        Customer updatedCustomer = customerRepository.save(existingCustomer);

        // Map the updated Customer entity to CustomerResponseModel using the responseMapper
        return responseMapper.entityToResponseModel(updatedCustomer);
    }

    @Override
    public void removeCustomer(String customerId) {

        // Find the existing customer by customerId from the repository
        Customer existingCustomer = customerRepository.findByCustomerIdentifier_CustomerId(customerId);

        // Check if the customer is not found, throw NotFoundException
        if (existingCustomer == null) {
            throw new NotFoundException("Customer not found with ID: " + customerId);
        }

        // Remove the customer from the repository
        customerRepository.delete(existingCustomer);
    }
}
