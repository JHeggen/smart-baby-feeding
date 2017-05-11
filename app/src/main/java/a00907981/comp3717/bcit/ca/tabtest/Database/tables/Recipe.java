package a00907981.comp3717.bcit.ca.tabtest.Database.tables;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Pkao on 2017-05-10.
 */

@Entity
class Recipe {
    @Id
    private Long recipe_id;

    private String recipe_name;

    private String energy_total;

    private String prot_total;

    private String cho_total;

    private String fat_total;

    private String na_total;

    private String k_total;

    private String cl_total;

    private String ca_total;

    private String po_total;

    private String mg_total;

    private String iron_total;

    private String vit_a_total;

    private String vit_d_total;

    private String folic_acid_total;

    private String mosm_l_total;

    private String mosm_kg_total;

    @Generated(hash = 471506081)
    public Recipe(Long recipe_id, String recipe_name, String energy_total,
            String prot_total, String cho_total, String fat_total, String na_total,
            String k_total, String cl_total, String ca_total, String po_total,
            String mg_total, String iron_total, String vit_a_total,
            String vit_d_total, String folic_acid_total, String mosm_l_total,
            String mosm_kg_total) {
        this.recipe_id = recipe_id;
        this.recipe_name = recipe_name;
        this.energy_total = energy_total;
        this.prot_total = prot_total;
        this.cho_total = cho_total;
        this.fat_total = fat_total;
        this.na_total = na_total;
        this.k_total = k_total;
        this.cl_total = cl_total;
        this.ca_total = ca_total;
        this.po_total = po_total;
        this.mg_total = mg_total;
        this.iron_total = iron_total;
        this.vit_a_total = vit_a_total;
        this.vit_d_total = vit_d_total;
        this.folic_acid_total = folic_acid_total;
        this.mosm_l_total = mosm_l_total;
        this.mosm_kg_total = mosm_kg_total;
    }

    @Generated(hash = 829032493)
    public Recipe() {
    }

    public Long getRecipe_id() {
        return this.recipe_id;
    }

    public void setRecipe_id(Long recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getRecipe_name() {
        return this.recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public String getEnergy_total() {
        return this.energy_total;
    }

    public void setEnergy_total(String energy_total) {
        this.energy_total = energy_total;
    }

    public String getProt_total() {
        return this.prot_total;
    }

    public void setProt_total(String prot_total) {
        this.prot_total = prot_total;
    }

    public String getCho_total() {
        return this.cho_total;
    }

    public void setCho_total(String cho_total) {
        this.cho_total = cho_total;
    }

    public String getFat_total() {
        return this.fat_total;
    }

    public void setFat_total(String fat_total) {
        this.fat_total = fat_total;
    }

    public String getNa_total() {
        return this.na_total;
    }

    public void setNa_total(String na_total) {
        this.na_total = na_total;
    }

    public String getK_total() {
        return this.k_total;
    }

    public void setK_total(String k_total) {
        this.k_total = k_total;
    }

    public String getCl_total() {
        return this.cl_total;
    }

    public void setCl_total(String cl_total) {
        this.cl_total = cl_total;
    }

    public String getCa_total() {
        return this.ca_total;
    }

    public void setCa_total(String ca_total) {
        this.ca_total = ca_total;
    }

    public String getPo_total() {
        return this.po_total;
    }

    public void setPo_total(String po_total) {
        this.po_total = po_total;
    }

    public String getMg_total() {
        return this.mg_total;
    }

    public void setMg_total(String mg_total) {
        this.mg_total = mg_total;
    }

    public String getIron_total() {
        return this.iron_total;
    }

    public void setIron_total(String iron_total) {
        this.iron_total = iron_total;
    }

    public String getVit_a_total() {
        return this.vit_a_total;
    }

    public void setVit_a_total(String vit_a_total) {
        this.vit_a_total = vit_a_total;
    }

    public String getVit_d_total() {
        return this.vit_d_total;
    }

    public void setVit_d_total(String vit_d_total) {
        this.vit_d_total = vit_d_total;
    }

    public String getFolic_acid_total() {
        return this.folic_acid_total;
    }

    public void setFolic_acid_total(String folic_acid_total) {
        this.folic_acid_total = folic_acid_total;
    }

    public String getMosm_l_total() {
        return this.mosm_l_total;
    }

    public void setMosm_l_total(String mosm_l_total) {
        this.mosm_l_total = mosm_l_total;
    }

    public String getMosm_kg_total() {
        return this.mosm_kg_total;
    }

    public void setMosm_kg_total(String mosm_kg_total) {
        this.mosm_kg_total = mosm_kg_total;
    }
}