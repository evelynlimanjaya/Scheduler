package model;

/**
 * This class represents the contact.
 */
public class Contact {
    /**
     * Contact ID
     */
    private int contactID;
    /**
     * Contact name
     */
    private String contactName;
    /**
     * Contact email
     */
    private String email;

    /**
     * This is the constructor method of the contact object.
     * @param contactID Contact ID
     * @param contactName Contact Name
     * @param email Contact email
     */
    public Contact(int contactID, String contactName, String email){

        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * This method gets the contact ID.
     * @return Contact ID
     */
    public int getContactID() {
        return contactID;
    }

    /**This method gets the contact name.
     * @return Contact name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * This method gets the contact email.
     * @return Contact email
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method overrides the toString method to return contact name when this method is called.
     * @return Contact name
     */
    @Override
    public String toString(){
        return (contactName);
    }
}
