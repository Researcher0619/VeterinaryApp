package dev.patika.VeterinaryApp.service;

import dev.patika.VeterinaryApp.core.exception.DuplicationException;
import dev.patika.VeterinaryApp.core.exception.NotFoundException;
import dev.patika.VeterinaryApp.core.utilities.Message;
import dev.patika.VeterinaryApp.dto.request.CustomerRequest;
import dev.patika.VeterinaryApp.dto.response.CustomerResponse;
import dev.patika.VeterinaryApp.entity.Customer;
import dev.patika.VeterinaryApp.core.mapper.CustomerMapper;
import dev.patika.VeterinaryApp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public List<CustomerResponse> findAll() {
        return customerMapper.asOutput(customerRepository.findAll());
    }

    public CustomerResponse getById(Long id) {
        return customerMapper.asOutput(customerRepository.findById(id).orElseThrow(() ->
                new NotFoundException(Message.NOT_FOUND + " Animal owner with id " + id + " not found!")));
    }

    public CustomerResponse create(CustomerRequest request) {
        Optional<Customer> isCustomerExist = customerRepository.findByMail(request.getMail());
        if (isCustomerExist.isEmpty()) {
            Customer customerSaved = customerRepository.save(customerMapper.asEntity(request));
            return customerMapper.asOutput(customerSaved);
        }
        throw new DuplicationException(Message.ALREADY_EXIST + " This animal owner has already been registered in the system!");
    }

    public CustomerResponse update(Long id, CustomerRequest request) {
        Optional<Customer> customerFromDb = customerRepository.findById(id);
        Optional<Customer> isCustomerExist = customerRepository.findByMail(request.getMail());

        if (customerFromDb.isEmpty()) {
            throw new NotFoundException(Message.NOT_FOUND + " The pet owner with the ID " + id + " that you are trying to update could not be found in the system!");
        }
        if (isCustomerExist.isPresent()) {
            throw new DuplicationException(Message.ALREADY_EXIST + " This email has already been registered in the system.");
        }
        Customer customer = customerFromDb.get();
        customerMapper.update(customer, request);
        return customerMapper.asOutput((customerRepository.save(customer)));
    }

    public void deleteById(Long id) {
        Optional<Customer> customerFromDb = customerRepository.findById(id);
        if (customerFromDb.isPresent()) {
            customerRepository.delete(customerFromDb.get());
        } else {
            throw new NotFoundException(Message.NOT_FOUND + " Animal owner with ID " + id + " could not be found in the system!");
        }
    }

    public List<CustomerResponse> findByName(String name) {
        return customerMapper.asOutput(customerRepository.findByName(name));
    }
}
