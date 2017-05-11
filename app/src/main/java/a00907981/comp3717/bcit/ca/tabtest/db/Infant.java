package a00907981.comp3717.bcit.ca.tabtest.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by Pkao on 2017-05-10.
 */
@Entity
public class Infant {
    @Id
    private Long infant_id;

    @ToMany(referencedJoinProperty = "history_id")
    @OrderBy("date ASC")
    private List<History> historyList;

    @ToMany(referencedJoinProperty = "reminder_id")
    @OrderBy("date ASC")
    private List<Reminders> remindersList;

    private String f_name;

    private String l_name;

    private int age;

    private String actual_DOB;

    private String expected_DOB;

    private String description;

    private String Weight;

    private String height;

    private String locale;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1361238733)
    private transient InfantDao myDao;

    @Generated(hash = 274747653)
    public Infant(Long infant_id, String f_name, String l_name, int age,
            String actual_DOB, String expected_DOB, String description,
            String Weight, String height, String locale) {
        this.infant_id = infant_id;
        this.f_name = f_name;
        this.l_name = l_name;
        this.age = age;
        this.actual_DOB = actual_DOB;
        this.expected_DOB = expected_DOB;
        this.description = description;
        this.Weight = Weight;
        this.height = height;
        this.locale = locale;
    }

    @Generated(hash = 1758206772)
    public Infant() {
    }

    public Long getInfant_id() {
        return this.infant_id;
    }

    public void setInfant_id(Long infant_id) {
        this.infant_id = infant_id;
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

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getActual_DOB() {
        return this.actual_DOB;
    }

    public void setActual_DOB(String actual_DOB) {
        this.actual_DOB = actual_DOB;
    }

    public String getExpected_DOB() {
        return this.expected_DOB;
    }

    public void setExpected_DOB(String expected_DOB) {
        this.expected_DOB = expected_DOB;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeight() {
        return this.Weight;
    }

    public void setWeight(String Weight) {
        this.Weight = Weight;
    }

    public String getHeight() {
        return this.height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getLocale() {
        return this.locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 624639498)
    public List<History> getHistoryList() {
        if (historyList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HistoryDao targetDao = daoSession.getHistoryDao();
            List<History> historyListNew = targetDao
                    ._queryInfant_HistoryList(infant_id);
            synchronized (this) {
                if (historyList == null) {
                    historyList = historyListNew;
                }
            }
        }
        return historyList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 466944887)
    public synchronized void resetHistoryList() {
        historyList = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 826688213)
    public List<Reminders> getRemindersList() {
        if (remindersList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RemindersDao targetDao = daoSession.getRemindersDao();
            List<Reminders> remindersListNew = targetDao
                    ._queryInfant_RemindersList(infant_id);
            synchronized (this) {
                if (remindersList == null) {
                    remindersList = remindersListNew;
                }
            }
        }
        return remindersList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1944644623)
    public synchronized void resetRemindersList() {
        remindersList = null;
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
    @Generated(hash = 1795837271)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getInfantDao() : null;
    }
}
