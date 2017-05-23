package a00907981.comp3717.bcit.ca.tabtest.RecipeList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.greendao.query.Query;

import a00907981.comp3717.bcit.ca.tabtest.Database.dao.App;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.DaoSession;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.Ingredient;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.IngredientDao;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.Recipe;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.RecipeDao;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.Recipe_Ingredient;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.Recipe_IngredientDao;
import a00907981.comp3717.bcit.ca.tabtest.R;
import a00907981.comp3717.bcit.ca.tabtest.SettingsActivity;

/**
 * Created by Pkao on 2017-05-22.
 */

public class Ingredient_Amount extends DialogFragment {
    private static final double mToi = 0.035274;
    private static final double iTom = 28.3495;

    private DaoSession daoSession;
    private Recipe_IngredientDao recipeIngDao;
    private RecipeDao recipeDao;
    private IngredientDao ingredientDao;

    private EditText amount;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceStance){

        daoSession = ((App)getActivity().getApplication()).getDaoSession();

        AlertDialog.Builder builder  = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater  = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.ingred_amount_dialog, null);

        builder.setView(view);
        builder.setTitle("Enter amount");

        amount = (EditText) view.findViewById(R.id.ingred_amount);

        setMessage();

        builder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Ingred_List ingred_list = (Ingred_List)fm.findFragmentByTag("ingre_list");

                long recipePK = ingred_list.getRecipePK();
                long ingPK = ingred_list.getIngPK();

                recipeIngDao = daoSession.getRecipe_IngredientDao();
                Recipe_Ingredient ingred = new Recipe_Ingredient();
                ingred.setIngre_id_FK(ingPK);
                ingred.setRecipe_id_FK(recipePK);

                double amt = Double.parseDouble(amount.getText().toString());
                double metric;
                double imperial;

                if(SettingsActivity.isMetric){
                    metric = amt;
                    imperial = amt * mToi;
                } else {
                    metric = amt * iTom;
                    imperial = amt;
                }

                ingred.setWeight_g(metric);
                ingred.setWeight_oz(imperial);

                recipeIngDao.insert(ingred);

                try{
                    updateRecipe(ingPK, recipePK, metric);
                } catch (Exception e) {
                    Log.d("MyTag", "ingPK: " + ingPK);
                }

                ingred_list.queryDB();
                ingred_list.setupListRecyclerView();
                fm.popBackStackImmediate();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        return builder.create();

    }

    public void setMessage(){
        if(SettingsActivity.isMetric){
            amount.setHint("Enter the amount in grams");
        } else {
            amount.setHint("Enter the amount in oz");
        }

    }

    public void updateRecipe(long rpk, long ipk, double mass){

        recipeDao = daoSession.getRecipeDao();
        ingredientDao = daoSession.getIngredientDao();

        Recipe recipe = recipeDao.queryBuilder().where(RecipeDao.Properties.Recipe_id.eq(rpk)).build().unique();
        Ingredient ingredient = ingredientDao.queryBuilder().where(IngredientDao.Properties.Ingre_id.eq(ipk)).build().unique();

        double energy_o = recipe.getEnergy_total();
        double prot_o = recipe.getProt_total();
        double cho_o = recipe.getCho_total();
        double fat_o = recipe.getFat_total();
        double na_o = recipe.getNa_total();
        double k_o = recipe.getK_total();
        double cl_o = recipe.getCl_total();
        double ca_o = recipe.getCa_total();
        double po_o = recipe.getPo_total();
        double mg_o = recipe.getMg_total();
        double iron_o = recipe.getIron_total();
        double vit_a_o = recipe.getVit_a_total();
        double vit_d_o = recipe.getVit_d_total();
        double folic_acid_o = recipe.getFolic_acid_total();

        double energy_n = energy_o + (ingredient.getEnergy()/100);
        double prot_n = prot_o + (ingredient.getProt()/100);
        double cho_n = cho_o + (ingredient.getCho()/100);
        double fat_n = fat_o + (ingredient.getFat()/100);
        double na_n = na_o + (ingredient.getNa_mmol_l()/100);
        double k_n = k_o + (ingredient.getK_mmol_l()/100);
        double cl_n = cl_o + (ingredient.getCl_mmol_l()/100);
        double ca_n = ca_o + (ingredient.getCa_mmol_l()/100);
        double po_n = po_o + (ingredient.getPo_mmol_l()/100);
        double mg_n = mg_o + (ingredient.getMg_mmol_l()/100);
        double iron_n = iron_o + (ingredient.getIron_mg()/100);
        double vit_a_n = vit_a_o + (ingredient.getVit_a_ug()/100);
        double vit_d_n = vit_d_o + (ingredient.getVit_d_ug()/100);
        double folic_acid_n = folic_acid_o + (ingredient.getFolic_acid_ug()/100);

        Recipe updatedRecipe = recipeDao.load(rpk);

        updatedRecipe.setEnergy_total(energy_n);
        updatedRecipe.setProt_total(prot_n);
        updatedRecipe.setCho_total(cho_n);
        updatedRecipe.setFat_total(fat_n);
        updatedRecipe.setNa_total(na_n);
        updatedRecipe.setK_total(k_n);
        updatedRecipe.setCl_total(cl_n);
        updatedRecipe.setCa_total(ca_n);
        updatedRecipe.setPo_total(po_n);
        updatedRecipe.setMg_total(mg_n);
        updatedRecipe.setIron_total(iron_n);
        updatedRecipe.setVit_a_total(vit_a_n);
        updatedRecipe.setVit_d_total(vit_d_n);
        updatedRecipe.setFolic_acid_total(folic_acid_n);

        recipeDao.update(updatedRecipe);
    }
}
