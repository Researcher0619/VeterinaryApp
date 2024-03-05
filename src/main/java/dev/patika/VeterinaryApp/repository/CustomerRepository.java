package dev.patika.VeterinaryApp.repository;

import dev.patika.VeterinaryApp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByMail (String mail);

    List<Customer> findByName(String name);
}
