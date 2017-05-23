package a00907981.comp3717.bcit.ca.tabtest.RecipeList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.Pair;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;

import a00907981.comp3717.bcit.ca.tabtest.Database.dao.App;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.DaoSession;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.Ingredient;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.IngredientDao;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.Recipe;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.RecipeDao;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.Recipe_Ingredient;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.Recipe_IngredientDao;
import a00907981.comp3717.bcit.ca.tabtest.TabActivity;

/**
 * Created by Pkao on 2017-05-23.
 */

public class Make_or_Use_dialog extends DialogFragment {
    public String recipeName;
    private IngredientDao ingredientDao;
    private ArrayList<Pair<Long, String>> mItemArray;
    private DaoSession daoSession;
    private RecipeDao recipeDao;
    private long recipePK;

    public static Make_or_Use_dialog newInstance(String name) {
        Make_or_Use_dialog temp = new Make_or_Use_dialog();
        temp.setName(name);
        return temp;
    }

    public void setName(String rName) {
        recipeName = rName;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstance){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Would you like to make or use the recipe?");

        builder.setPositiveButton("Use recipe", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Bundle args = getArguments();
                TabActivity tb = (TabActivity) getActivity();

                int spinnerpos = args.getInt("pos");

                tb.setSpinnerFocus(spinnerpos);

                tb.switchTabs(0);

                dismiss();
            }
        });

        builder.setNegativeButton("Make the recipe", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FragmentManager fm = getActivity().getSupportFragmentManager();


                Ingred_Weigh iWeight = new Ingred_Weigh();
                iWeight.show(fm,"Dialog");
                dismiss();
            }
        });

        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        return builder.create();
    }
    public void queryDB(){


        mItemArray = new ArrayList<>();

        DaoSession daoSession = ((App)getActivity().getApplication()).getDaoSession();
        ingredientDao = daoSession.getIngredientDao();
        Recipe_IngredientDao recipe_ingredient = daoSession.getRecipe_IngredientDao();

        QueryBuilder<Ingredient> ingredientQuery = ingredientDao.queryBuilder();
        ingredientQuery.join(Recipe_Ingredient.class, Recipe_IngredientDao.Properties.Ingre_id_FK).
                where(Recipe_IngredientDao.Properties.Recipe_id_FK.eq(recipePK));

        long i = 0;

        for(Ingredient ingredient : ingredientQuery.list()){
            mItemArray.add(new Pair<Long, String>(i ++, ingredient.getIngredient_name()));
        }
    }
    public void setRecipePK() {

        recipeDao = daoSession.getRecipeDao();

        Query<Recipe> recipeQuery = recipeDao.queryBuilder().where(RecipeDao.Properties.Recipe_name.eq(recipeName.toString())).build();

        Recipe recipe = recipeQuery.unique();
        recipePK = recipe.getRecipe_id();
        //Toast.makeText(getContext(), "IN RecipeQuesry pk is " + recipePK, Toast.LENGTH_SHORT).show();
    }
}
