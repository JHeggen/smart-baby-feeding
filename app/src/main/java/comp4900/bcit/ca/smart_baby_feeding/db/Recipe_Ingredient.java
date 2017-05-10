package comp4900.bcit.ca.smart_baby_feeding.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Pkao on 2017-05-10.
 */

@Entity
public class Recipe_Ingredient{
    @Id
    private Long order;
    private Long ingre_id_FK;
    private Long recipe_id_FK;
    @Generated(hash = 829206232)
    public Recipe_Ingredient(Long order, Long ingre_id_FK, Long recipe_id_FK) {
        this.order = order;
        this.ingre_id_FK = ingre_id_FK;
        this.recipe_id_FK = recipe_id_FK;
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
}