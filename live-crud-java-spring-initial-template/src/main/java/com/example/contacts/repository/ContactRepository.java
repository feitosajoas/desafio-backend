package com.example.contacts.repository;

import com.example.contacts.model.Contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    boolean existsByPhoneNumber(String phoneNumber);

    @Query("SELECT c FROM Contact c ORDER BY c.contato_id")
    List<Contact> findAllByOrderedId();
}
