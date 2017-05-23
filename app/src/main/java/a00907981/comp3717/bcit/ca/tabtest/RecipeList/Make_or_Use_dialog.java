package a00907981.comp3717.bcit.ca.tabtest.RecipeList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

import a00907981.comp3717.bcit.ca.tabtest.TabActivity;

/**
 * Created by Pkao on 2017-05-23.
 */

public class Make_or_Use_dialog extends DialogFragment {
    public String recipeName;


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


                Ingred_Weigh iWeight = Ingred_Weigh.newInstance(recipeName);
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

}
