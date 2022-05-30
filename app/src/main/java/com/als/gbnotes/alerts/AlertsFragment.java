package com.als.gbnotes.alerts;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.als.gbnotes.R;
import com.google.android.material.snackbar.Snackbar;

public class AlertsFragment extends Fragment implements CustomDialogListener {
    private static final String CHANNEL_ID = "CHANNEL_ID";
    private int chosen = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_alerts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.button_toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(requireContext(), "I'm TOAST", Toast.LENGTH_SHORT).show();
                Toast.makeText(requireContext(), R.string.longText, Toast.LENGTH_LONG).show();
            }
        });

        view.findViewById(R.id.button_toast_short).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(requireContext(), "I'm TOAST", Toast.LENGTH_SHORT).show();
            }
        });

        view.findViewById(R.id.button_snack_bar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view.findViewById(R.id.button_snack_bar), R.string.longText, Snackbar.LENGTH_SHORT).show();
            }
        });

        view.findViewById(R.id.button_dialog_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialogFragment dialog = new CustomDialogFragment();
                dialog.setListener(AlertsFragment.this);
                dialog.show(requireActivity().getSupportFragmentManager(), CustomDialogFragment.TAG);;
            }
        });

        view.findViewById(R.id.button_snack_bar_with_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,
                                "Error!",
                                Snackbar.LENGTH_INDEFINITE)
                        .setAction("RELOAD", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                showToast("RELOADED");
                            }
                        })
                        .show();
            }
        });

        view.findViewById(R.id.button_alert_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });

        view.findViewById(R.id.button_alert_dialog_with_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialogWithView();
            }
        });

        view.findViewById(R.id.button_dialog_fragment_custom_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CustomDialogFragmentWithView().show(requireActivity().getSupportFragmentManager(), CustomDialogFragmentWithView.TAG);
            }
        });

        view.findViewById(R.id.button_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotification();
            }
        });

        view.findViewById(R.id.alert_dialog_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showListDialog();
            }
        });

        view.findViewById(R.id.alert_dialog_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showListDialog();
            }
        });
        view.findViewById(R.id.alert_dialog_list_single).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showListDialogSingle();
            }
        });
        view.findViewById(R.id.alert_dialog_list_multi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showListDialogMulti();
            }
        });

    }

    private void showListDialogMulti() {
        String[] items = getResources().getStringArray(R.array.cities);
        final boolean[] chosen = new boolean[items.length];
        for (int index = 0; index<chosen.length; index++){
            chosen[index] = false;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("List")
                .setMultiChoiceItems(items, chosen, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        chosen[i] = b;
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for (int index = 0; index<chosen.length; index++){
                            if (chosen[index]){
                                showToast(items[index]);
                            }
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showToast("negative action");
                    }
                });
        AlertDialog alert = builder.create();

        alert.show();
    }

    private void showListDialogSingle() {
        String[] items = getResources().getStringArray(R.array.cities);
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("List")
                .setSingleChoiceItems(items, chosen,  new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        chosen = i;
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (chosen == -1) {
                            showToast("no chose item");
                            return;
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showToast("negative action");
                    }
                });
        AlertDialog alert = builder.create();

        alert.show();
    }

    private void showListDialog() {
        String[] items = getResources().getStringArray(R.array.cities);
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("List")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(requireContext(), "Выбран город:" + items[i], Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void showNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String name = "Name";
            String descriptionText = "Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(descriptionText);

            // Регистрируем канал в системе
            NotificationManager notificationManager =
                    (NotificationManager) requireActivity().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireActivity(), CHANNEL_ID);
        // Все цветные иконки отображаются только в оттенках серого
        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Hello")
                .setContentText("GeekBrains")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat.from(requireActivity()).notify(42, builder.build());
    }

    private void showAlertDialogWithView() {
        final View customView = getLayoutInflater().inflate(R.layout.dialog_custom, null);
        final AlertDialog dialog = new AlertDialog.Builder(requireContext())
                .setTitle("Custom title")
                .setView(customView)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showToast("Positive action");
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showToast("negative action");
                    }
                })
                .setNeutralButton("Cancel", null)
                .create();
        final EditText dialogEditText = customView.findViewById(R.id.editText_custom_view);
        customView.findViewById(R.id.button_custom_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast(dialogEditText.getText().toString());
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    private void showAlertDialog() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Title")
                .setMessage(R.string.longText)
                //.setIcon(R.drawable.ebrg)
                //.setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showToast("Positive action");
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showToast("negative action");
                    }
                })
                //.setNeutralButton("Cancel", null)
                .show();
    }

    private void showToast(String msg) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onOk() {
        showToast("OK LISTENER");
    }

    @Override
    public void onNo() {
        showToast("NO LISTENER");
    }
}
