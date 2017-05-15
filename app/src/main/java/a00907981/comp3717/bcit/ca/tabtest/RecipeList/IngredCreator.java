package a00907981.comp3717.bcit.ca.tabtest.RecipeList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import a00907981.comp3717.bcit.ca.tabtest.R;

/**
 * Created by Getry on 5/15/2017.
 */

public class IngredCreator extends DialogFragment {
    AlertDialog.Builder builder;
    EditText name;
    EditText id;
    String ingName;

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
                    /**
                     * We will call the edittext here and quarry into the database for the name. If it exists we will return an error
                     * and have them input again. Otherwise we will input it into the database.
                     */
                    dismiss();
                }
            }
        });
        return builder.show();
    }
    public void setName(String rName) {
        ingName = rName;

    }
}
