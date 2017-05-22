package a00907981.comp3717.bcit.ca.tabtest.RecipeList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import a00907981.comp3717.bcit.ca.tabtest.R;

/**
 * Created by Pkao on 2017-05-22.
 */

public class Ingredient_Amount extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceStance){
        AlertDialog.Builder builder  = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater  = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.ingred_amount_dialog, null));
        builder.setTitle("Enter amount");

        builder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity().getBaseContext(),"Enter Button", Toast.LENGTH_SHORT).show();
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
}
