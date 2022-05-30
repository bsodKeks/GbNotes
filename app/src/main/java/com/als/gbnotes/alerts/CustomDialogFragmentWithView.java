package com.als.gbnotes.alerts;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.als.gbnotes.R;

public class CustomDialogFragmentWithView extends DialogFragment {
    public static final String TAG = "CustomDialogFragmentWithViewTag";


    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View customView = getLayoutInflater().inflate(R.layout.dialog_custom, null);
        EditText dialogEditText = customView.findViewById(R.id.editText_custom_view);
        customView.findViewById(R.id.button_custom_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(requireContext(), dialogEditText.getText().toString(), Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        return new AlertDialog.Builder(requireContext())
                .setTitle("Title")
                .setView(customView)
                .setPositiveButton("Yes", (dialogInterface, i) -> Toast.makeText(requireContext(), "Yes!", Toast.LENGTH_SHORT).show())
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(requireContext(), "NO!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("Cancel", null)
                .create();
    }
}
