package comp4900.bcit.ca.smart_baby_feeding;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Getry on 5/2/2017.
 */

public class MyDialogFragment extends DialogFragment {
    AlertDialog.Builder builder;
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_sample_dialog, null);
        builder = new AlertDialog.Builder(getActivity());
        builder.setView(R.layout.fragment_sample_dialog);
        Button dismiss = (Button) view.findViewById(R.id.dismiss);
        final AlertDialog dialog = builder.create();
        dismiss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        return builder.show();
    }
}
