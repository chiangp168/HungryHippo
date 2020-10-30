package edu.northeastern.cs5500.delivery.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.bson.types.ObjectId;

import lombok.Data;


/**
 * This is a class representing a review object. Users should be able to retrieve any fields in this
 * class or create a new review object.
 */
@Data
public class Review implements Model{
    private ObjectId id;
    private String userName;
    private Integer restaurantId;
    private LocalDate createdTime;
    private String comment;
    private Double rating;

    /** Constructor for a Review object */
    public Review(
            ObjectId id,
            String userName,
            Integer restaurantId, // Should this be ObjectID?
            LocalDate createdTime,
            String comment,
            Double rating) {
        this.id = id;
        this.userName = userName;
        this.restaurantId = restaurantId;
        this.createdTime = createdTime;
        this.comment = comment;
        this.rating = rating;
    }

    /** @return true if this Review is valid */
    @JsonIgnore
    public boolean isValid() {
        return !userName.isEmpty()
                && !comment.isEmpty()
                && rating >= 0.0
                && restaurantId != null;
    }

}
