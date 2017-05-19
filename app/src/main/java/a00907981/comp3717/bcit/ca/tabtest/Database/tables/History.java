package a00907981.comp3717.bcit.ca.tabtest.Database.tables;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
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

    private double date;

    private double time;

    private double net_energy;

    private double net_prot;

    private double net_cho;

    private double net_fat;

    private double net_na;

    private double net_k;

    private double net_cl;

    private double net_ca;

    private double net_po;

    private double net_mg;

    private double net_iron;

    private double net_vit_a;

    private double net_vit_d;

    private double net_folic_acid;

    private double net_mosm_l;

    private double net_mosm_kg;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1462128466)
    private transient HistoryDao myDao;

    @Generated(hash = 523456461)
    public History(Long history_id, Long recipe_ID_FK, double date, double time,
            double net_energy, double net_prot, double net_cho, double net_fat,
            double net_na, double net_k, double net_cl, double net_ca,
            double net_po, double net_mg, double net_iron, double net_vit_a,
            double net_vit_d, double net_folic_acid, double net_mosm_l,
            double net_mosm_kg) {
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

    public double getDate() {
        return this.date;
    }

    public void setDate(double date) {
        this.date = date;
    }

    public double getTime() {
        return this.time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getNet_energy() {
        return this.net_energy;
    }

    public void setNet_energy(double net_energy) {
        this.net_energy = net_energy;
    }

    public double getNet_prot() {
        return this.net_prot;
    }

    public void setNet_prot(double net_prot) {
        this.net_prot = net_prot;
    }

    public double getNet_cho() {
        return this.net_cho;
    }

    public void setNet_cho(double net_cho) {
        this.net_cho = net_cho;
    }

    public double getNet_fat() {
        return this.net_fat;
    }

    public void setNet_fat(double net_fat) {
        this.net_fat = net_fat;
    }

    public double getNet_na() {
        return this.net_na;
    }

    public void setNet_na(double net_na) {
        this.net_na = net_na;
    }

    public double getNet_k() {
        return this.net_k;
    }

    public void setNet_k(double net_k) {
        this.net_k = net_k;
    }

    public double getNet_cl() {
        return this.net_cl;
    }

    public void setNet_cl(double net_cl) {
        this.net_cl = net_cl;
    }

    public double getNet_ca() {
        return this.net_ca;
    }

    public void setNet_ca(double net_ca) {
        this.net_ca = net_ca;
    }

    public double getNet_po() {
        return this.net_po;
    }

    public void setNet_po(double net_po) {
        this.net_po = net_po;
    }

    public double getNet_mg() {
        return this.net_mg;
    }

    public void setNet_mg(double net_mg) {
        this.net_mg = net_mg;
    }

    public double getNet_iron() {
        return this.net_iron;
    }

    public void setNet_iron(double net_iron) {
        this.net_iron = net_iron;
    }

    public double getNet_vit_a() {
        return this.net_vit_a;
    }

    public void setNet_vit_a(double net_vit_a) {
        this.net_vit_a = net_vit_a;
    }

    public double getNet_vit_d() {
        return this.net_vit_d;
    }

    public void setNet_vit_d(double net_vit_d) {
        this.net_vit_d = net_vit_d;
    }

    public double getNet_folic_acid() {
        return this.net_folic_acid;
    }

    public void setNet_folic_acid(double net_folic_acid) {
        this.net_folic_acid = net_folic_acid;
    }

    public double getNet_mosm_l() {
        return this.net_mosm_l;
    }

    public void setNet_mosm_l(double net_mosm_l) {
        this.net_mosm_l = net_mosm_l;
    }

    public double getNet_mosm_kg() {
        return this.net_mosm_kg;
    }

    public void setNet_mosm_kg(double net_mosm_kg) {
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
