package comp4900.bcit.ca.smart_baby_feeding;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Pkao on 2017-05-02.
 */
@Entity
public class Recipe {
    @Id
    private long id;

    private String name;

    @Generated(hash = 1709081986)
    public Recipe(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 829032493)
    public Recipe() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
