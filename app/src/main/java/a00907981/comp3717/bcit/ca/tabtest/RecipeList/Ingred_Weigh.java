package a00907981.comp3717.bcit.ca.tabtest.RecipeList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import a00907981.comp3717.bcit.ca.tabtest.R;

/**
 * Created by a00980198 on 5/22/2017.
 */

public class Ingred_Weigh extends DialogFragment {
    AlertDialog.Builder builder;
    EditText ingredientWeight;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.ingred_create_frag, new RelativeLayout(getActivity()));
        ingredientWeight = (EditText) view.findViewById(R.id.ingredWeight);

        builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);


        return builder.show();
    }
}
