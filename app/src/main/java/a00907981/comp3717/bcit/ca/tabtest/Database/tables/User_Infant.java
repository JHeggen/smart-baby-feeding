package a00907981.comp3717.bcit.ca.tabtest.Database.tables;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Pkao on 2017-05-10.
 */

@Entity
public class User_Infant {
    @Id
    private Long U_I_id;

    private Long user_id;

    private Long infant_id;

    @Generated(hash = 364130657)
    public User_Infant(Long U_I_id, Long user_id, Long infant_id) {
        this.U_I_id = U_I_id;
        this.user_id = user_id;
        this.infant_id = infant_id;
    }
    @Generated(hash = 1959825881)
    public User_Infant() {
    }
    public Long getU_I_id() {
        return this.U_I_id;
    }
    public void setU_I_id(Long U_I_id) {
        this.U_I_id = U_I_id;
    }
    public Long getUser_id() {
        return this.user_id;
    }
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    public Long getInfant_id() {
        return this.infant_id;
    }
    public void setInfant_id(Long infant_id) {
        this.infant_id = infant_id;
    }
}
