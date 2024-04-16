package com.example.contacts.service;

import com.example.contacts.exception.ContactExistException;
import com.example.contacts.model.Contact;
import com.example.contacts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;


    public void SaveContact(Contact contact) throws ContactExistException {
            boolean isTrue = contactRepository.existsByPhoneNumber(contact.getPhoneNumber());
            if (isTrue)
                throw new ContactExistException("Ja existe um contato com o número: " + contact.getPhoneNumber());
        contactRepository.save(contact);
    }

    public List<Contact> GetAllContacts() {
        return contactRepository.findAllByOrderedId();
    }

    public Contact GetContactById(long id) {
        Optional<Contact> contact = contactRepository.findById(id);
        return contact.orElse(null);
    }

    public void DeleteContactById(long id) {
        contactRepository.deleteById(id);
    }

    public Contact UpdateContact(long id, Contact contactUpdate) {
        Contact contact = contactRepository.getReferenceById(id);
        updateData(contact, contactUpdate);
        return contactRepository.save(contact);
    }

    private void updateData(Contact data, Contact obj) {
        data.setPhoneNumber(obj.getPhoneNumber());
        data.setContato_email(obj.getContato_email());
        data.setContato_nome(obj.getContato_nome());
        data.setContato_telefone(obj.getContato_telefone());
        data.setContato_sn_ativo(obj.getContato_sn_ativo());
        data.setContato_sn_favorito(obj.getContato_sn_favorito());
    }
}
