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

    private double energy_total;

    private double prot_total;

    private double cho_total;

    private double fat_total;

    private double na_total;

    private double k_total;

    private double cl_total;

    private double ca_total;

    private double po_total;

    private double mg_total;

    private double iron_total;

    private double vit_a_total;

    private double vit_d_total;

    private double folic_acid_total;

    private double mosm_l_total;

    private double mosm_kg_total;

    @Generated(hash = 1105725554)
    public Recipe(Long recipe_id, String recipe_name, double energy_total,
            double prot_total, double cho_total, double fat_total, double na_total,
            double k_total, double cl_total, double ca_total, double po_total,
            double mg_total, double iron_total, double vit_a_total,
            double vit_d_total, double folic_acid_total, double mosm_l_total,
            double mosm_kg_total) {
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

    public double getEnergy_total() {
        return this.energy_total;
    }

    public void setEnergy_total(double energy_total) {
        this.energy_total = energy_total;
    }

    public double getProt_total() {
        return this.prot_total;
    }

    public void setProt_total(double prot_total) {
        this.prot_total = prot_total;
    }

    public double getCho_total() {
        return this.cho_total;
    }

    public void setCho_total(double cho_total) {
        this.cho_total = cho_total;
    }

    public double getFat_total() {
        return this.fat_total;
    }

    public void setFat_total(double fat_total) {
        this.fat_total = fat_total;
    }

    public double getNa_total() {
        return this.na_total;
    }

    public void setNa_total(double na_total) {
        this.na_total = na_total;
    }

    public double getK_total() {
        return this.k_total;
    }

    public void setK_total(double k_total) {
        this.k_total = k_total;
    }

    public double getCl_total() {
        return this.cl_total;
    }

    public void setCl_total(double cl_total) {
        this.cl_total = cl_total;
    }

    public double getCa_total() {
        return this.ca_total;
    }

    public void setCa_total(double ca_total) {
        this.ca_total = ca_total;
    }

    public double getPo_total() {
        return this.po_total;
    }

    public void setPo_total(double po_total) {
        this.po_total = po_total;
    }

    public double getMg_total() {
        return this.mg_total;
    }

    public void setMg_total(double mg_total) {
        this.mg_total = mg_total;
    }

    public double getIron_total() {
        return this.iron_total;
    }

    public void setIron_total(double iron_total) {
        this.iron_total = iron_total;
    }

    public double getVit_a_total() {
        return this.vit_a_total;
    }

    public void setVit_a_total(double vit_a_total) {
        this.vit_a_total = vit_a_total;
    }

    public double getVit_d_total() {
        return this.vit_d_total;
    }

    public void setVit_d_total(double vit_d_total) {
        this.vit_d_total = vit_d_total;
    }

    public double getFolic_acid_total() {
        return this.folic_acid_total;
    }

    public void setFolic_acid_total(double folic_acid_total) {
        this.folic_acid_total = folic_acid_total;
    }

    public double getMosm_l_total() {
        return this.mosm_l_total;
    }

    public void setMosm_l_total(double mosm_l_total) {
        this.mosm_l_total = mosm_l_total;
    }

    public double getMosm_kg_total() {
        return this.mosm_kg_total;
    }

    public void setMosm_kg_total(double mosm_kg_total) {
        this.mosm_kg_total = mosm_kg_total;
    }


}