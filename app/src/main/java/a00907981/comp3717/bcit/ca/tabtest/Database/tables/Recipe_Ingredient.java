package a00907981.comp3717.bcit.ca.tabtest.Database.tables;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Pkao on 2017-05-10.
 */

@Entity
public class Recipe_Ingredient{
    @Id
    private Long order;
    private Long ingre_id_FK;
    private Long recipe_id_FK;
    private double weight_g;
    private double weight_oz;
    @Generated(hash = 364755730)
    public Recipe_Ingredient(Long order, Long ingre_id_FK, Long recipe_id_FK,
            double weight_g, double weight_oz) {
        this.order = order;
        this.ingre_id_FK = ingre_id_FK;
        this.recipe_id_FK = recipe_id_FK;
        this.weight_g = weight_g;
        this.weight_oz = weight_oz;
    }
    @Generated(hash = 1132476556)
    public Recipe_Ingredient() {
    }
    public Long getOrder() {
        return this.order;
    }
    public void setOrder(Long order) {
        this.order = order;
    }
    public Long getIngre_id_FK() {
        return this.ingre_id_FK;
    }
    public void setIngre_id_FK(Long ingre_id_FK) {
        this.ingre_id_FK = ingre_id_FK;
    }
    public Long getRecipe_id_FK() {
        return this.recipe_id_FK;
    }
    public void setRecipe_id_FK(Long recipe_id_FK) {
        this.recipe_id_FK = recipe_id_FK;
    }
    public double getWeight_g() {
        return this.weight_g;
    }
    public void setWeight_g(double weight_g) {
        this.weight_g = weight_g;
    }
    public double getWeight_oz() {
        return this.weight_oz;
    }
    public void setWeight_oz(double weight_oz) {
        this.weight_oz = weight_oz;
    }
   
}