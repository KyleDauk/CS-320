import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests for ContactService.
 *
 * 
 *  - "The contact service shall be able to add contacts with unique ID."
 *      -> testAddContactSuccessfully, testAddContactWithDuplicateIdThrowsException
 *  - "The contact service shall be able to delete contacts per contactId."
 *      -> testDeleteExistingContact, testDeleteNonExistingContactThrowsException
 *  - "The contact service shall be able to update contact fields per contactId."
 *      -> testUpdateFirstName, testUpdateLastName,
 *         testUpdatePhone, testUpdateAddress
 */
public class ContactServiceTest {

    private ContactService service;

    @BeforeEach
    public void setUp() {
        service = new ContactService();
    }

    private Contact createSampleContact() {
        return new Contact(
                "12345",
                "John",
                "Doe",
                "1234567890",
                "123 Main Street"
        );
    }

    // ----- ADD CONTACT REQUIREMENT -----

    @Test
    public void testAddContactSuccessfully() {
        Contact contact = createSampleContact();

        service.addContact(contact);
        Contact stored = service.getContact("12345");

        assertNotNull(stored);
        assertEquals("John", stored.getFirstName());
        assertEquals("Doe", stored.getLastName());
        assertEquals("1234567890", stored.getPhone());
        assertEquals("123 Main Street", stored.getAddress());
    }

    @Test
    public void testAddContactWithDuplicateIdThrowsException() {
        Contact contact1 = createSampleContact();
        Contact contact2 = new Contact(
                "12345",    // same ID as contact1
                "Jane",
                "Smith",
                "0987654321",
                "456 Oak Road"
        );

        service.addContact(contact1);
        assertThrows(IllegalArgumentException.class, () -> service.addContact(contact2));
    }

    // ----- DELETE CONTACT REQUIREMENT -----

    @Test
    public void testDeleteExistingContact() {
        Contact contact = createSampleContact();
        service.addContact(contact);

        // Ensure it exists
        assertNotNull(service.getContact("12345"));

        // Delete it
        service.deleteContact("12345");

        // Now it should be gone
        assertNull(service.getContact("12345"));
    }

    @Test
    public void testDeleteNonExistingContactThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("doesNotExist"));
    }

    // ----- UPDATE CONTACT FIELDS REQUIREMENT -----

    @Test
    public void testUpdateFirstName() {
        Contact contact = createSampleContact();
        service.addContact(contact);

        service.updateFirstName("12345", "Mike");

        Contact updated = service.getContact("12345");
        assertEquals("Mike", updated.getFirstName());
    }

    @Test
    public void testUpdateLastName() {
        Contact contact = createSampleContact();
        service.addContact(contact);

        service.updateLastName("12345", "Smith");

        Contact updated = service.getContact("12345");
        assertEquals("Smith", updated.getLastName());
    }

    @Test
    public void testUpdatePhone() {
        Contact contact = createSampleContact();
        service.addContact(contact);

        service.updatePhone("12345", "5555555555");

        Contact updated = service.getContact("12345");
        assertEquals("5555555555", updated.getPhone());
    }

    @Test
    public void testUpdateAddress() {
        Contact contact = createSampleContact();
        service.addContact(contact);

        service.updateAddress("12345", "999 New Address Blvd");

        Contact updated = service.getContact("12345");
        assertEquals("999 New Address Blvd", updated.getAddress());
    }

    @Test
    public void testUpdateNonExistingContactThrowsException() {
        // No contacts added
        assertThrows(IllegalArgumentException.class,
                () -> service.updateFirstName("nope", "Name"));
        assertThrows(IllegalArgumentException.class,
                () -> service.updateLastName("nope", "Last"));
        assertThrows(IllegalArgumentException.class,
                () -> service.updatePhone("nope", "1234567890"));
        assertThrows(IllegalArgumentException.class,
                () -> service.updateAddress("nope", "Somewhere"));
    }
}
