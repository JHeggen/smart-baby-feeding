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

import a00907981.comp3717.bcit.ca.tabtest.Database.dao.App;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.DaoSession;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.Ingredient;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.IngredientDao;
import a00907981.comp3717.bcit.ca.tabtest.R;

/**
 * Created by Getry on 5/15/2017.
 */

public class IngredCreator extends DialogFragment {
    AlertDialog.Builder builder;
    EditText name;
    EditText id;
    String ingName;
    IngredientDao ingDao;

    public static IngredCreator newInstance(String name) {
        IngredCreator temp = new IngredCreator();
        temp.setName(name);
        return temp;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.ingred_create_frag, new RelativeLayout(getActivity()));
        name = (EditText) view.findViewById(R.id.ingredName_edit);
        id = (EditText) view.findViewById(R.id.ingredUnit);
        if(ingName != "") {
            name.setText(ingName);
        }
        builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        Button dismiss = (Button) view.findViewById(R.id.ingred_submit);
        dismiss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (name.getText().toString().trim().equals("")) {
                    name.setError("Ingrediant Name is Required!");
                    name.setHint("Please enter Ingrediant Name");
                } else if (id.getText().toString().trim().equals("")) {
                    id.setError("Unit Messurement is Required!");
                    id.setHint("Please enter Unit Messurement");

                } else {
                    DaoSession daoSession = ((App)getActivity().getApplication()).getDaoSession();
                    ingDao = daoSession.getIngredientDao();

                    Query<Ingredient> ingredQuery = ingDao.queryBuilder().where(IngredientDao.Properties.Ingredient_name.eq(name.getText().toString())).build();

                    if(ingredQuery.list().isEmpty()){
                        Ingredient ingred = new Ingredient();
                        ingred.setIngredient_name(name.getText().toString());
                        ingred.setMosm_kg(Double.parseDouble(id.getText().toString()));
                        ingDao.insert(ingred);

                        FragmentManager fm = getFragmentManager();
                        Ingred_List recipes = (Ingred_List) fm.findFragmentByTag("fragment");
                        recipes.queryDB();
                        recipes.setupListRecyclerView();

                        dismiss();
                    } else {
                        name.setError("Ingredient Name is Already in Use");
                        name.setHint("Please Enter a Different Ingredient Name");
                    }
            }
        }});
        return builder.show();
    }
    public void setName(String rName) {
        ingName = rName;

    }
}
