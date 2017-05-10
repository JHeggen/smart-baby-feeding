package comp4900.bcit.ca.smart_baby_feeding.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import comp4900.bcit.ca.smart_baby_feeding.DaoSession;
import comp4900.bcit.ca.smart_baby_feeding.IngredientDao;
import comp4900.bcit.ca.smart_baby_feeding.RecipeDao;

/**
 * Created by Pkao on 2017-05-10.
 */

@Entity
public class Ingredient {
    @Id
    private Long ingre_id;

    @ToMany
    @JoinEntity(
            entity = Recipe_Ingredient.class,
            sourceProperty = "ingre_id_FK",
            targetProperty = "recipe_id_FK"
    )
    private List<Recipe> recipesWithIngredient;

    private String ingredient_name;

    private String ingredient_unit;

    private String ingredient_phy_type;

    private String ingredient_baby_type;

    private String energy;

    private String prot;

    private String cho;

    private String fat;

    private String na_mmol_l;

    private String k_mmol_l;

    private String cl_mmol_l;

    private String ca_mmol_l;

    private String po_mmol_l;

    private String mg_mmol_l;

    private String iron_mg;

    private String vit_a_ug;

    private String vit_d_ug;

    private String folic_acid_ug;

    private String mosm_l;

    private String mosm_kg;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 942581853)
    private transient IngredientDao myDao;

    @Generated(hash = 1035309046)
    public Ingredient(Long ingre_id, String ingredient_name, String ingredient_unit,
            String ingredient_phy_type, String ingredient_baby_type, String energy,
            String prot, String cho, String fat, String na_mmol_l, String k_mmol_l,
            String cl_mmol_l, String ca_mmol_l, String po_mmol_l, String mg_mmol_l,
            String iron_mg, String vit_a_ug, String vit_d_ug, String folic_acid_ug,
            String mosm_l, String mosm_kg) {
        this.ingre_id = ingre_id;
        this.ingredient_name = ingredient_name;
        this.ingredient_unit = ingredient_unit;
        this.ingredient_phy_type = ingredient_phy_type;
        this.ingredient_baby_type = ingredient_baby_type;
        this.energy = energy;
        this.prot = prot;
        this.cho = cho;
        this.fat = fat;
        this.na_mmol_l = na_mmol_l;
        this.k_mmol_l = k_mmol_l;
        this.cl_mmol_l = cl_mmol_l;
        this.ca_mmol_l = ca_mmol_l;
        this.po_mmol_l = po_mmol_l;
        this.mg_mmol_l = mg_mmol_l;
        this.iron_mg = iron_mg;
        this.vit_a_ug = vit_a_ug;
        this.vit_d_ug = vit_d_ug;
        this.folic_acid_ug = folic_acid_ug;
        this.mosm_l = mosm_l;
        this.mosm_kg = mosm_kg;
    }

    @Generated(hash = 1584798654)
    public Ingredient() {
    }

    public Long getIngre_id() {
        return this.ingre_id;
    }

    public void setIngre_id(Long ingre_id) {
        this.ingre_id = ingre_id;
    }

    public String getIngredient_name() {
        return this.ingredient_name;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    public String getIngredient_unit() {
        return this.ingredient_unit;
    }

    public void setIngredient_unit(String ingredient_unit) {
        this.ingredient_unit = ingredient_unit;
    }

    public String getIngredient_phy_type() {
        return this.ingredient_phy_type;
    }

    public void setIngredient_phy_type(String ingredient_phy_type) {
        this.ingredient_phy_type = ingredient_phy_type;
    }

    public String getIngredient_baby_type() {
        return this.ingredient_baby_type;
    }

    public void setIngredient_baby_type(String ingredient_baby_type) {
        this.ingredient_baby_type = ingredient_baby_type;
    }

    public String getEnergy() {
        return this.energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public String getProt() {
        return this.prot;
    }

    public void setProt(String prot) {
        this.prot = prot;
    }

    public String getCho() {
        return this.cho;
    }

    public void setCho(String cho) {
        this.cho = cho;
    }

    public String getFat() {
        return this.fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getNa_mmol_l() {
        return this.na_mmol_l;
    }

    public void setNa_mmol_l(String na_mmol_l) {
        this.na_mmol_l = na_mmol_l;
    }

    public String getK_mmol_l() {
        return this.k_mmol_l;
    }

    public void setK_mmol_l(String k_mmol_l) {
        this.k_mmol_l = k_mmol_l;
    }

    public String getCl_mmol_l() {
        return this.cl_mmol_l;
    }

    public void setCl_mmol_l(String cl_mmol_l) {
        this.cl_mmol_l = cl_mmol_l;
    }

    public String getCa_mmol_l() {
        return this.ca_mmol_l;
    }

    public void setCa_mmol_l(String ca_mmol_l) {
        this.ca_mmol_l = ca_mmol_l;
    }

    public String getPo_mmol_l() {
        return this.po_mmol_l;
    }

    public void setPo_mmol_l(String po_mmol_l) {
        this.po_mmol_l = po_mmol_l;
    }

    public String getMg_mmol_l() {
        return this.mg_mmol_l;
    }

    public void setMg_mmol_l(String mg_mmol_l) {
        this.mg_mmol_l = mg_mmol_l;
    }

    public String getIron_mg() {
        return this.iron_mg;
    }

    public void setIron_mg(String iron_mg) {
        this.iron_mg = iron_mg;
    }

    public String getVit_a_ug() {
        return this.vit_a_ug;
    }

    public void setVit_a_ug(String vit_a_ug) {
        this.vit_a_ug = vit_a_ug;
    }

    public String getVit_d_ug() {
        return this.vit_d_ug;
    }

    public void setVit_d_ug(String vit_d_ug) {
        this.vit_d_ug = vit_d_ug;
    }

    public String getFolic_acid_ug() {
        return this.folic_acid_ug;
    }

    public void setFolic_acid_ug(String folic_acid_ug) {
        this.folic_acid_ug = folic_acid_ug;
    }

    public String getMosm_l() {
        return this.mosm_l;
    }

    public void setMosm_l(String mosm_l) {
        this.mosm_l = mosm_l;
    }

    public String getMosm_kg() {
        return this.mosm_kg;
    }

    public void setMosm_kg(String mosm_kg) {
        this.mosm_kg = mosm_kg;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 851050717)
    public List<Recipe> getRecipesWithIngredient() {
        if (recipesWithIngredient == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RecipeDao targetDao = daoSession.getRecipeDao();
            List<Recipe> recipesWithIngredientNew = targetDao
                    ._queryIngredient_RecipesWithIngredient(ingre_id);
            synchronized (this) {
                if (recipesWithIngredient == null) {
                    recipesWithIngredient = recipesWithIngredientNew;
                }
            }
        }
        return recipesWithIngredient;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 537280190)
    public synchronized void resetRecipesWithIngredient() {
        recipesWithIngredient = null;
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
    @Generated(hash = 1386056592)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getIngredientDao() : null;
    }

}
