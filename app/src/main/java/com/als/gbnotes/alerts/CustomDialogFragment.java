package com.als.gbnotes.alerts;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.als.gbnotes.R;

public class CustomDialogFragment extends DialogFragment {
    public static final String TAG = "CustomDialogTag";

    private CustomDialogListener listener;

    public void setListener(CustomDialogListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setTitle("Title")
                .setMessage(R.string.longText)
                //.setIcon(R.drawable.ebrg)
                //.setCancelable(false)
                .setPositiveButton("Yes", (dialogInterface, i) -> listener.onOk())
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onNo();
                    }
                })
                .setNeutralButton("Cancel", null)
                .create();
    }
}
