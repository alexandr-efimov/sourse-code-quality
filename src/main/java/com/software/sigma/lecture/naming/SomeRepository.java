package com.software.sigma.lecture.naming;

import lombok.Value;

public interface SomeRepository {

    void save(User user);

    void create(Task task);

    void persist(Car car);

    void store(SomeEntity someEntity);

    User getById();

    User findByFirstName();

    User searchByLastName();

    User retrieveByEmail();
}

@Value
class User {
    String id;
    String firstName;
    String lastName;
    String email;

    Integer birthYear;
}

@Value
class Car {
}

@Value
class Task {
}

@Value
class SomeEntity {
}