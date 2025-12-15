import java.util.HashMap;
import java.util.Map;

/**
 * ContactService
 * 
 * Requirements:
 *  - The contact service shall be able to add contacts with unique ID.
 *  - The contact service shall be able to delete contacts per contactId.
 *  - The contact service shall be able to update contact fields per contactId:
 *      firstName, lastName, phoneNumber, address.
 */
public class ContactService {

    // In-memory data structure to store contacts (no database)
    private final Map<String, Contact> contacts = new HashMap<>();

    /**
     * Adds a new contact with a unique ID.
     * 
     * @param contact Contact to add
     * @throws IllegalArgumentException if contact is null or contact ID already exists
     */
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null");
        }

        String contactId = contact.getContactID();
        if (contactId == null) {
            throw new IllegalArgumentException("Contact ID cannot be null");
        }

        if (contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("Contact ID must be unique");
        }

        contacts.put(contactId, contact);
    }

    /**
     * Deletes a contact per contactId.
     * 
     * @param contactId ID of the contact to delete
     * @throws IllegalArgumentException if contactId is null or does not exist
     */
    public void deleteContact(String contactId) {
        if (contactId == null) {
            throw new IllegalArgumentException("Contact ID cannot be null");
        }

        if (!contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("Contact ID not found");
        }

        contacts.remove(contactId);
    }

    /**
     * Updates the first name for the contact with the given ID.
     *
     * @param contactId   ID of the contact
     * @param newFirstName new first name (Contact class enforces null/length checks)
     * @throws IllegalArgumentException if the contactId does not exist or is null
     */
    public void updateFirstName(String contactId, String newFirstName) {
        Contact contact = getExistingContact(contactId);
        contact.setFirstName(newFirstName);
    }

    /**
     * Updates the last name for the contact with the given ID.
     *
     * @param contactId   ID of the contact
     * @param newLastName new last name (Contact class enforces null/length checks)
     * @throws IllegalArgumentException if the contactId does not exist or is null
     */
    public void updateLastName(String contactId, String newLastName) {
        Contact contact = getExistingContact(contactId);
        contact.setLastName(newLastName);
    }

    /**
     * Updates the phone number for the contact with the given ID.
     *
     * @param contactId  ID of the contact
     * @param newPhone   new phone value (Contact class enforces null/format checks)
     * @throws IllegalArgumentException if the contactId does not exist or is null
     */
    public void updatePhone(String contactId, String newPhone) {
        Contact contact = getExistingContact(contactId);
        contact.setPhone(newPhone);
    }

    /**
     * Updates the address for the contact with the given ID.
     *
     * @param contactId    ID of the contact
     * @param newAddress   new address (Contact class enforces null/length checks)
     * @throws IllegalArgumentException if the contactId does not exist or is null
     */
    public void updateAddress(String contactId, String newAddress) {
        Contact contact = getExistingContact(contactId);
        contact.setAddress(newAddress);
    }

    /**
     * Helper method to retrieve an existing contact or throw if not found.
     */
    private Contact getExistingContact(String contactId) {
        if (contactId == null) {
            throw new IllegalArgumentException("Contact ID cannot be null");
        }

        Contact contact = contacts.get(contactId);
        if (contact == null) {
            throw new IllegalArgumentException("Contact ID not found");
        }

        return contact;
    }

    /**
     * Optional helper for tests or other uses
     */
    public Contact getContact(String contactId) {
        return contacts.get(contactId);
    }
}
