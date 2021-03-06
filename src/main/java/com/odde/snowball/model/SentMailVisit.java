package com.odde.snowball.model;

import com.odde.snowball.model.base.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SentMailVisit extends Entity<SentMailVisit> {
    private String emailAddress;
    private int readCount;
    private ObjectId sentMailId;

    public String toJSON() {
        return "{\"email\": \"" + getEmailAddress() + "\", \"open_count\": " + getReadCount() + "}";
    }

    public void updateViewCount() {
        setReadCount(getReadCount() + 1);
        save();
    }
}
