package comp4900.bcit.ca.smart_baby_feeding;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Pkao on 2017-05-02.
 */
@Entity
public class Recipe {
    @Id
    private long id;

    private String name;
}
