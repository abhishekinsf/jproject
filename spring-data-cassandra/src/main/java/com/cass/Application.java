package com.cass;

import com.cass.cassandra.Person;
import com.cass.cassandra.PersonKey;
import com.cass.cassandra.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
public class Application implements CommandLineRunner {

  @Autowired private PersonRepository personRepository;

  public static void main(final String args[]) {
    SpringApplication.run(Application.class);
  }

  @Override
  public void run(String... args) throws Exception {
    final PersonKey key = new PersonKey("Akash", LocalDateTime.now(), UUID.randomUUID());
    final Person p = new Person(key, "Kumar", 1000);
    personRepository.insert(p);

    System.out.println("find by first name");
    personRepository.findByKeyFirstName("Akash").forEach(System.out::println);

    System.out.println("find by first name and date of birth greater than date");
    personRepository
        .findByKeyFirstNameAndKeyDateOfBirthGreaterThan("Akash", LocalDateTime.now().minusDays(1))
        .forEach(System.out::println);

    System.out.println("find by last name");
    personRepository.findByLastName("Kumar").forEach(System.out::println);
  }
}
