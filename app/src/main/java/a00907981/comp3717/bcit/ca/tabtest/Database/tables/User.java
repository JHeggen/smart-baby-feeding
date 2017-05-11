package a00907981.comp3717.bcit.ca.tabtest.Database.tables;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import a00907981.comp3717.bcit.ca.tabtest.db.DaoSession;
import a00907981.comp3717.bcit.ca.tabtest.db.InfantDao;
import a00907981.comp3717.bcit.ca.tabtest.db.UserDao;

/**
 * Created by Pkao on 2017-05-10.
 */

@Entity
public class User {
    @Id
    private Long user_id;

    @ToMany
    @JoinEntity(
            entity = User_Infant.class,
            sourceProperty = "user_id",
            targetProperty = "infant_id"
    )
    private List<Infant> infantsWithUser;

    private String f_name;

    private String l_name;

    private String description;

    private String role;

    private String username;

    private String password;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1507654846)
    private transient UserDao myDao;

    @Generated(hash = 1373284761)
    public User(Long user_id, String f_name, String l_name, String description,
            String role, String username, String password) {
        this.user_id = user_id;
        this.f_name = f_name;
        this.l_name = l_name;
        this.description = description;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getUser_id() {
        return this.user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getF_name() {
        return this.f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return this.l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 773859254)
    public List<Infant> getInfantsWithUser() {
        if (infantsWithUser == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            InfantDao targetDao = daoSession.getInfantDao();
            List<Infant> infantsWithUserNew = targetDao
                    ._queryUser_InfantsWithUser(user_id);
            synchronized (this) {
                if (infantsWithUser == null) {
                    infantsWithUser = infantsWithUserNew;
                }
            }
        }
        return infantsWithUser;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1948534703)
    public synchronized void resetInfantsWithUser() {
        infantsWithUser = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2059241980)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserDao() : null;
    }
}
