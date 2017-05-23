package a00907981.comp3717.bcit.ca.tabtest.RecipeList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import a00907981.comp3717.bcit.ca.tabtest.TabActivity;

/**
 * Created by Pkao on 2017-05-23.
 */

public class Make_or_Use_dialog extends DialogFragment {
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
