package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import exercise.model.Contact;
import exercise.repository.ContactRepository;
import exercise.dto.ContactDTO;
import exercise.dto.ContactCreateDTO;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    // BEGIN
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO postNew(@RequestBody ContactCreateDTO contactCreateDTO) {
        Contact contact = toContact(contactCreateDTO);
        contactRepository.save(contact);

        return toDTO(contact);
    }

    private Contact toContact (ContactCreateDTO contactCreateDTO) {
        Contact currContact = new Contact();

        currContact.setFirstName(contactCreateDTO.getFirstName());
        currContact.setLastName(contactCreateDTO.getLastName());
        currContact.setPhone(contactCreateDTO.getPhone());

        return currContact;
    }

    private ContactCreateDTO toCreateDTO (Contact contact) {
        ContactCreateDTO result = new ContactCreateDTO();

        result.setFirstName(contact.getFirstName());
        result.setLastName(contact.getLastName());
        result.setPhone(contact.getPhone());

        return result;
    }

    private ContactDTO toDTO (Contact contact) {
        ContactDTO result = new ContactDTO();

        result.setId(contact.getId());
        result.setPhone(contact.getPhone());
        result.setFirstName(contact.getFirstName());
        result.setLastName(contact.getLastName());
        result.setCreatedAt(contact.getCreatedAt());
        result.setUpdatedAt(contact.getUpdatedAt());

        return result;
    }
    // END
}
