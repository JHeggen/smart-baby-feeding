package comp4900.bcit.ca.smart_baby_feeding.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Pkao on 2017-05-10.
 */
@Entity
public class Reminders {
    @Id
    private Long reminder_id;

    private String date;

    private String time;

    private String message;

    private String phone_number;

    private String email;

    private String type;

    private String severity;

    @Generated(hash = 677265759)
    public Reminders(Long reminder_id, String date, String time, String message,
            String phone_number, String email, String type, String severity) {
        this.reminder_id = reminder_id;
        this.date = date;
        this.time = time;
        this.message = message;
        this.phone_number = phone_number;
        this.email = email;
        this.type = type;
        this.severity = severity;
    }

    @Generated(hash = 1693877174)
    public Reminders() {
    }

    public Long getReminder_id() {
        return this.reminder_id;
    }

    public void setReminder_id(Long reminder_id) {
        this.reminder_id = reminder_id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhone_number() {
        return this.phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeverity() {
        return this.severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
}
