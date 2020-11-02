package edu.northeastern.cs5500.delivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;

/**
 * This is a class representing a delivery driver object. A driver should be able to view the order
 * and set pick up and delivered time. A driver inherits from a Person class.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DeliveryDriver extends Person {
    private ObjectId Id;
    private ObjectId OrderId;

    /** Constructor for DeliveryDriver */
    public DeliveryDriver(
            String username,
            String password,
            String firstName,
            String lastName,
            String email,
            Integer phoneNumber,
            ObjectId Id,
            ObjectId OrderId)
            {
        super(username, password, firstName, lastName, email, phoneNumber);
        this.Id = Id;
        this.OrderId = OrderId;
    }

    /** @return true if this User is valid */
    @JsonIgnore
    public boolean isValid() {
        return !username.isEmpty()
                && !firstName.isEmpty()
                && !lastName.isEmpty()
                && Integer.toString(phoneNumber).length() == 10
                && !email.isEmpty();
    }
}
