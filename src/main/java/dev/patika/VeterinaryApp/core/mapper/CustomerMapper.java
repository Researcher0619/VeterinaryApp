package dev.patika.VeterinaryApp.core.mapper;

import dev.patika.VeterinaryApp.dto.request.CustomerRequest;
import dev.patika.VeterinaryApp.dto.response.CustomerResponse;
import dev.patika.VeterinaryApp.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface CustomerMapper {

    Customer asEntity(CustomerRequest customerRequest); // asEntity method converts from Customer Request class to Customer class
    CustomerResponse asOutput(Customer customer); // The asOutput method converts from the Customer class to the CustomerResponse class.
    List<CustomerResponse> asOutput(List<Customer> customerList);
    void update(@MappingTarget Customer entity, CustomerRequest request); // updates only.


}
























/*
MapStruct kutuphanesi kullanildiginda mapper ile nesne donusumleri gerceklestirilir.
Bu metotlar, DTO(Data Transfer Object) ile Entity arasindaki donusumleri saglar.
MapStruck bu metotlarin gercek implementasyonlarini otomatik olarak olusturur.
*/






