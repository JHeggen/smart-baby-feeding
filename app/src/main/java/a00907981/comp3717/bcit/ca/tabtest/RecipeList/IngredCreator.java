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



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.ingred_create_frag, new RelativeLayout(getActivity()));

        builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        Button dismiss = (Button) view.findViewById(R.id.ingred_submit);
        dismiss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return builder.show();
    }
}
