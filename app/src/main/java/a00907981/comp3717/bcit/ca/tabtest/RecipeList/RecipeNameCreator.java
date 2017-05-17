package a00907981.comp3717.bcit.ca.tabtest.RecipeList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import org.greenrobot.greendao.query.Query;

import java.util.List;

import a00907981.comp3717.bcit.ca.tabtest.Database.dao.App;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.DaoSession;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.Recipe;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.RecipeDao;
import a00907981.comp3717.bcit.ca.tabtest.R;

/**
 * Created by Getry on 5/15/2017.
 */

public class RecipeNameCreator extends DialogFragment{

    AlertDialog.Builder builder;
    EditText name;

    private RecipeDao recipeDao;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.recipe_name_frag, new RelativeLayout(getActivity()));

        name = (EditText) view.findViewById(R.id.recipe_name_edit);

        builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);

        Button dismiss = (Button) view.findViewById(R.id.recipe_submit);

        dismiss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(name.getText().toString().trim().equals("")){
                    name.setError("Recipe Name is Required!");
                    name.setHint("Please Enter Recipe Name");
                } else {
                    /**
                     * We will call the edittext here and quarry into the database for the name. If it exists we will return an error
                     * and have them input again. Otherwise we will input it into the database.
                     */

                    DaoSession daoSession = ((App)getActivity().getApplication()).getDaoSession();
                    recipeDao = daoSession.getRecipeDao();

                    Query<Recipe> recipeQuery = recipeDao.queryBuilder().where(RecipeDao.Properties.Recipe_name.eq(name.getText().toString())).build();

                    if(recipeQuery.list().isEmpty()){
                        Recipe recipe = new Recipe();
                        recipe.setRecipe_name(name.getText().toString());
                        recipeDao.insert(recipe);

                        FragmentManager fm = getFragmentManager();
                        Recipes recipes = (Recipes) fm.findFragmentByTag("fragment");
                        recipes.queryDB();
                        recipes.setupListRecyclerView();

                        dismiss();
                    } else {
                        name.setError("Recipe Name is Already in Use");
                        name.setHint("Please Enter a Different Recipe Name");
                    }
                }
        }});
        return builder.show();
    }
}

