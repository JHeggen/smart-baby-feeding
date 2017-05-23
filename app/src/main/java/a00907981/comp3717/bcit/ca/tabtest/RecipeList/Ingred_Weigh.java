package a00907981.comp3717.bcit.ca.tabtest.RecipeList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

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
import a00907981.comp3717.bcit.ca.tabtest.R;

/**
 * Created by a00980198 on 5/22/2017.
 */

public class Ingred_Weigh extends DialogFragment {
    AlertDialog.Builder builder;
    EditText ingredientWeight;
    private String recipeName;
    private IngredientDao ingredientDao;
    private ArrayList<String> mItemArray;
    private DaoSession daoSession;
    private RecipeDao recipeDao;
    private long recipePK;
    private ListView lv;


    public static Ingred_Weigh newInstance(String name) {
        Ingred_Weigh temp = new Ingred_Weigh();
        temp.setName(name);
        return temp;
    }

    public void setName(String rName) {
        recipeName = rName;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.weigh_ingred_frag, new RelativeLayout(getActivity()));
        lv = (ListView) view.findViewById(R.id.ingredChecklist);

        ingredientWeight = (EditText) view.findViewById(R.id.ingredWeight);

        builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        setRecipePK();
        queryDB();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this.getActivity(),
                android.R.layout.simple_list_item_1,
                mItemArray );

        lv.setAdapter(arrayAdapter);



        return builder.show();
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
            mItemArray.add(ingredient.getIngredient_name() + " Add ");
        }
    }
    public void setRecipePK() {
        DaoSession daoSession = ((App)getActivity().getApplication()).getDaoSession();

        recipeDao = daoSession.getRecipeDao();

        Query<Recipe> recipeQuery = recipeDao.queryBuilder().where(RecipeDao.Properties.Recipe_name.eq(recipeName.toString())).build();

        Recipe recipe = recipeQuery.unique();
        recipePK = recipe.getRecipe_id();
        //Toast.makeText(getContext(), "IN RecipeQuesry pk is " + recipePK, Toast.LENGTH_SHORT).show();
    }
}
