package comp4900.bcit.ca.smart_baby_feeding.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

import java.sql.Time;
import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by Pkao on 2017-05-10.
 */
@Entity
public class History {
    @Id
    private Long history_id;

    private Long recipe_ID_FK;

    @ToOne(joinProperty = "recipe_ID_FK")
    private Recipe recipe;

    private String date;

    private String time;

    private String net_energy;

    private String net_prot;

    private String net_cho;

    private String net_fat;

    private String net_na;

    private String net_k;

    private String net_cl;

    private String net_ca;

    private String net_po;

    private String net_mg;

    private String net_iron;

    private String net_vit_a;

    private String net_vit_d;

    private String net_folic_acid;

    private String net_mosm_l;

    private String net_mosm_kg;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1462128466)
    private transient HistoryDao myDao;

    @Generated(hash = 1956150650)
    public History(Long history_id, Long recipe_ID_FK, String date, String time,
            String net_energy, String net_prot, String net_cho, String net_fat,
            String net_na, String net_k, String net_cl, String net_ca,
            String net_po, String net_mg, String net_iron, String net_vit_a,
            String net_vit_d, String net_folic_acid, String net_mosm_l,
            String net_mosm_kg) {
        this.history_id = history_id;
        this.recipe_ID_FK = recipe_ID_FK;
        this.date = date;
        this.time = time;
        this.net_energy = net_energy;
        this.net_prot = net_prot;
        this.net_cho = net_cho;
        this.net_fat = net_fat;
        this.net_na = net_na;
        this.net_k = net_k;
        this.net_cl = net_cl;
        this.net_ca = net_ca;
        this.net_po = net_po;
        this.net_mg = net_mg;
        this.net_iron = net_iron;
        this.net_vit_a = net_vit_a;
        this.net_vit_d = net_vit_d;
        this.net_folic_acid = net_folic_acid;
        this.net_mosm_l = net_mosm_l;
        this.net_mosm_kg = net_mosm_kg;
    }

    @Generated(hash = 869423138)
    public History() {
    }

    public Long getHistory_id() {
        return this.history_id;
    }

    public void setHistory_id(Long history_id) {
        this.history_id = history_id;
    }

    public Long getRecipe_ID_FK() {
        return this.recipe_ID_FK;
    }

    public void setRecipe_ID_FK(Long recipe_ID_FK) {
        this.recipe_ID_FK = recipe_ID_FK;
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

    public String getNet_energy() {
        return this.net_energy;
    }

    public void setNet_energy(String net_energy) {
        this.net_energy = net_energy;
    }

    public String getNet_prot() {
        return this.net_prot;
    }

    public void setNet_prot(String net_prot) {
        this.net_prot = net_prot;
    }

    public String getNet_cho() {
        return this.net_cho;
    }

    public void setNet_cho(String net_cho) {
        this.net_cho = net_cho;
    }

    public String getNet_fat() {
        return this.net_fat;
    }

    public void setNet_fat(String net_fat) {
        this.net_fat = net_fat;
    }

    public String getNet_na() {
        return this.net_na;
    }

    public void setNet_na(String net_na) {
        this.net_na = net_na;
    }

    public String getNet_k() {
        return this.net_k;
    }

    public void setNet_k(String net_k) {
        this.net_k = net_k;
    }

    public String getNet_cl() {
        return this.net_cl;
    }

    public void setNet_cl(String net_cl) {
        this.net_cl = net_cl;
    }

    public String getNet_ca() {
        return this.net_ca;
    }

    public void setNet_ca(String net_ca) {
        this.net_ca = net_ca;
    }

    public String getNet_po() {
        return this.net_po;
    }

    public void setNet_po(String net_po) {
        this.net_po = net_po;
    }

    public String getNet_mg() {
        return this.net_mg;
    }

    public void setNet_mg(String net_mg) {
        this.net_mg = net_mg;
    }

    public String getNet_iron() {
        return this.net_iron;
    }

    public void setNet_iron(String net_iron) {
        this.net_iron = net_iron;
    }

    public String getNet_vit_a() {
        return this.net_vit_a;
    }

    public void setNet_vit_a(String net_vit_a) {
        this.net_vit_a = net_vit_a;
    }

    public String getNet_vit_d() {
        return this.net_vit_d;
    }

    public void setNet_vit_d(String net_vit_d) {
        this.net_vit_d = net_vit_d;
    }

    public String getNet_folic_acid() {
        return this.net_folic_acid;
    }

    public void setNet_folic_acid(String net_folic_acid) {
        this.net_folic_acid = net_folic_acid;
    }

    public String getNet_mosm_l() {
        return this.net_mosm_l;
    }

    public void setNet_mosm_l(String net_mosm_l) {
        this.net_mosm_l = net_mosm_l;
    }

    public String getNet_mosm_kg() {
        return this.net_mosm_kg;
    }

    public void setNet_mosm_kg(String net_mosm_kg) {
        this.net_mosm_kg = net_mosm_kg;
    }

    @Generated(hash = 1482429547)
    private transient Long recipe__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 239926760)
    public Recipe getRecipe() {
        Long __key = this.recipe_ID_FK;
        if (recipe__resolvedKey == null || !recipe__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RecipeDao targetDao = daoSession.getRecipeDao();
            Recipe recipeNew = targetDao.load(__key);
            synchronized (this) {
                recipe = recipeNew;
                recipe__resolvedKey = __key;
            }
        }
        return recipe;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2057472935)
    public void setRecipe(Recipe recipe) {
        synchronized (this) {
            this.recipe = recipe;
            recipe_ID_FK = recipe == null ? null : recipe.getRecipe_id();
            recipe__resolvedKey = recipe_ID_FK;
        }
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
    @Generated(hash = 851899508)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getHistoryDao() : null;
    }

}
