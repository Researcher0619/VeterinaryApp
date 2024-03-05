package dev.patika.VeterinaryApp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest  {

    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
}
