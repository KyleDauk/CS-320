import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ContactTest {

    @Test
    public void testContactCreationValid() {
        Contact c = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main Street Apt 4");
        assertEquals("1234567890", c.getContactID());
        assertEquals("John", c.getFirstName());
        assertEquals("Doe", c.getLastName());
        assertEquals("1234567890", c.getPhone());
        assertEquals("123 Main Street Apt 4", c.getAddress());
    }

    @Test
    public void testContactIdNull() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact(null, "John", "Doe", "1234567890", "123 Main St"));
    }

    @Test
    public void testContactIdTooLong() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("12345678901", "John", "Doe", "1234567890", "123 Main St"));
    }

    @Test
    public void testFirstNameNull() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("123", null, "Doe", "1234567890", "123 Main St"));
    }

    @Test
    public void testFirstNameTooLong() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("123", "Johnnnnnnnnn", "Doe", "1234567890", "123 Main St"));
    }

    @Test
    public void testLastNameTooLong() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("123", "John", "Doeeeeeeeeee", "1234567890", "123 Main St"));
    }

    @Test
    public void testPhoneNotTenDigits() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("123", "John", "Doe", "12345", "123 Main St"));
    }

    @Test
    public void testPhoneContainsLetters() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("123", "John", "Doe", "123456789a", "123 Main St"));
    }

    @Test
    public void testAddressTooLong() {
        String longAddr = "This address is way longer than thirty characters and should fail";
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("123", "John", "Doe", "1234567890", longAddr));
    }
}