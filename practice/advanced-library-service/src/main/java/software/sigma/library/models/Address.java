package software.sigma.library.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class Address {

    String county;
    String city;
    String house;
    String apartment;
    String postal_index;

    public Address(String string, String string2, String string3, String string4, String string5) {
        this.county = string;
        this.city = string2;
        this.house = string3;
        this.apartment = string4;
        this.postal_index = string5;
    }
}
