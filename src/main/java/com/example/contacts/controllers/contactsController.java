package com.example.contacts.controllers;

import com.example.contacts.exception.ContactExistException;
import com.example.contacts.model.Contact;
import com.example.contacts.service.ContactService;
import jakarta.servlet.Servlet;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/contacts")
public class contactsController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<Contact> addContact(@RequestBody @Valid Contact contact) throws ContactExistException {
        contactService.SaveContact(contact);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(contact.getContato_id()).toUri();
        return ResponseEntity.created(uri).body(contact);
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts = contactService.GetAllContacts();
        return ResponseEntity.ok().body(contacts);
    }

    @GetMapping("{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable int id) {
        Contact contact = contactService.GetContactById(id);
        return ResponseEntity.ok().body(contact);
    }

    @PutMapping("{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable int id, @RequestBody @Valid Contact contact) {
        var contactExists = contactService.GetContactById(id);
        if (contactExists != null) {
            contactService.UpdateContact(contactExists.getContato_id(), contact);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(contact.getContato_id()).toUri();
            return ResponseEntity.created(uri).body(contact);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Contact> deleteContact(@PathVariable int id) {
        var contactExists = contactService.GetContactById(id);
        if (contactExists != null) {
            contactService.DeleteContactById(contactExists.getContato_id());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
