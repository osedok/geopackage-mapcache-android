package mil.nga.mapcache.view.detail;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

import mil.nga.mapcache.R;
import mil.nga.mapcache.listeners.OnDialogButtonClickListener;

/**
 * Util class to launch dialogs and return click listeners for the action buttons in the GeoPackage
 * detail header view and Layer detail view delete button
 */
public class DetailActionUtil {
    /**
     * Context of the activity
     */
    private Context mContext;

    /**
     * Constructor
     * @param context activity context
     */
    public DetailActionUtil(Context context){
        mContext = context;
    }


    /**
     * Return to the activity to open a Detail GP view
     * @param context Context for opening dialog
     * @param gpName GeoPackage name
     * @param listener Click listener to callback to the mapfragment
     */
    public void openDetailDialog(Context context, String gpName,
                                 final OnDialogButtonClickListener listener){
        listener.onDetailGP(gpName);
    }


    /**
     * Open a rename GeoPackage dialog view
     * @param context Context for opening dialog
     * @param gpName GeoPackage name
     * @param listener Click listener to callback to the mapfragment
     */
    public void openRenameDialog(Context context, String gpName,
                                 final OnDialogButtonClickListener listener){
        // Create Alert window with basic input text layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View alertView = inflater.inflate(R.layout.basic_edit_alert, null);
        // Logo and title
        ImageView alertLogo = (ImageView) alertView.findViewById(R.id.alert_logo);
        alertLogo.setBackgroundResource(R.drawable.material_edit);
        TextView titleText = (TextView) alertView.findViewById(R.id.alert_title);
        titleText.setText("Rename GeoPackage");
        // GeoPackage name
        final TextInputEditText inputName = (TextInputEditText) alertView.findViewById(R.id.edit_text_input);
        inputName.setHint(gpName);
        inputName.setText(gpName);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        dialogBuilder.setView(alertView);
        dialogBuilder.setPositiveButton("Rename", (dialog, which)->{
            String newName = inputName.getText().toString();
            if (newName != null && !newName.isEmpty() && !newName.equals(gpName)) {
                dialog.dismiss();
                listener.onRenameGP(gpName, newName);
            }
        });
        dialogBuilder.setNegativeButton(context.getString(R.string.button_cancel_label), (dialog, which)->{
            dialog.dismiss();
        });
        AlertDialog alertDialog = dialogBuilder.create();

        // Validate the input before allowing the rename to happen
        inputName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String givenName = inputName.getText().toString();
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);

                if(givenName.isEmpty()){
                    inputName.setError("Name is required");
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }
//                else {
//                    boolean allowed = Pattern.matches("[a-zA-Z_0-9]+", givenName);
//                    if (!allowed) {
//                        inputName.setError("Names must be alphanumeric only");
//                        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
//                    }
//                }
            }
        });

        alertDialog.show();
    }

    /**
     * Open a dialog for renaming a Layer inside a GeoPackage
     * @param context Context for opening dialog
     * @param gpName GeoPackage name
     * @param layerName Current layer name
     * @param listener Click listener to callback to the mapfragment
     */
    public void openRenameLayerDialog(Context context, String gpName, String layerName,
                                 final OnDialogButtonClickListener listener){
        // Create Alert window with basic input text layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View alertView = inflater.inflate(R.layout.basic_edit_alert, null);
        // Logo and title
        ImageView alertLogo = (ImageView) alertView.findViewById(R.id.alert_logo);
        alertLogo.setBackgroundResource(R.drawable.material_edit);
        TextView titleText = (TextView) alertView.findViewById(R.id.alert_title);
        titleText.setText("Rename Layer");
        // Current Layer name
        final TextInputEditText inputName = (TextInputEditText) alertView.findViewById(R.id.edit_text_input);
        inputName.setHint(layerName);
        inputName.setText(layerName);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        dialogBuilder.setView(alertView);
        dialogBuilder.setPositiveButton("Rename", (dialog, which)->{
            String newName = inputName.getText().toString();
            if (newName != null && !newName.isEmpty() && !newName.equals(layerName)) {
                dialog.dismiss();
                listener.onRenameLayer(gpName, layerName, newName);
            }
        });
        dialogBuilder.setNegativeButton(context.getString(R.string.button_cancel_label), (dialog, which)->{
            dialog.dismiss();
        });
        AlertDialog alertDialog = dialogBuilder.create();

        // Validate the input before allowing the rename to happen
        inputName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String givenName = inputName.getText().toString();
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);

                if(givenName.isEmpty()){
                    inputName.setError("Name is required");
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                } else {
                    boolean allowed = Pattern.matches("[a-zA-Z_0-9]+", givenName);
                    if (!allowed) {
                        inputName.setError("Names must be alphanumeric only");
                        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    }
                }
            }
        });

        alertDialog.show();
    }


    /**
     * return to a share dialog action (no dialog needed)
     * @param context Context for running the share task
     * @param gpName GeoPackage name
     * @param listener Click listener to callback to the mapfragment
     */
    public void openShareDialog(Context context, String gpName,
                                 final OnDialogButtonClickListener listener){
        listener.onShareGP(gpName);
    }


    /**
     * Open a copy GeoPackage dialog view
     * @param context Context for opening dialog
     * @param gpName GeoPackage name
     * @param listener Click listener to callback to the mapfragment
     */
    public void openCopyDialog(Context context, String gpName,
                                 final OnDialogButtonClickListener listener){
        // Create Alert window with basic input text layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View alertView = inflater.inflate(R.layout.basic_edit_alert, null);
        // Logo and title
        ImageView alertLogo = (ImageView) alertView.findViewById(R.id.alert_logo);
        alertLogo.setBackgroundResource(R.drawable.material_copy);
        TextView titleText = (TextView) alertView.findViewById(R.id.alert_title);
        titleText.setText("Copy GeoPackage");

        final TextInputEditText inputName = (TextInputEditText) alertView.findViewById(R.id.edit_text_input);
        inputName.setText(gpName + context.getString(R.string.geopackage_copy_suffix));
        inputName.setHint("GeoPackage Name");

        AlertDialog.Builder copyDialogBuilder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle)
                .setView(alertView)
                .setPositiveButton("Copy", (dialog, which)->{
                            String newName = inputName.getText().toString();
                            if (newName != null && !newName.isEmpty()
                                    && !newName.equals(gpName)) {
                                dialog.dismiss();
                                listener.onCopyGP(gpName, newName);
                            }
                })

                .setNegativeButton(context.getString(R.string.button_cancel_label),
                        (dialog, which)->{
                            dialog.dismiss();
                });
        AlertDialog alertDialog = copyDialogBuilder.create();

//        // Validate the input before allowing the rename to happen
//        inputName.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
//            @Override
//            public void afterTextChanged(Editable editable) {}
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                String givenName = inputName.getText().toString();
//                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
//
//                if(givenName.isEmpty()){
//                    inputName.setError("Name is required");
//                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
//                } else {
//                    boolean allowed = Pattern.matches("[a-zA-Z_0-9]+", givenName);
//                    if (!allowed) {
//                        inputName.setError("Names must be alphanumeric only");
//                        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
//                    }
//                }
//            }
//        });
        alertDialog.show();
    }

    /**
     * Open a dialog for copying a Layer inside a geopackage
     * @param context Context for opening dialog
     * @param gpName GeoPackage name
     * @param layerName Layer to copy
     * @param listener Click listener to callback to the mapfragment
     */
    public void openCopyLayerDialog(Context context, String gpName, String layerName,
                               final OnDialogButtonClickListener listener){
        // Create Alert window with basic input text layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View alertView = inflater.inflate(R.layout.basic_edit_alert, null);
        // Logo and title
        ImageView alertLogo = (ImageView) alertView.findViewById(R.id.alert_logo);
        alertLogo.setBackgroundResource(R.drawable.material_copy);
        TextView titleText = (TextView) alertView.findViewById(R.id.alert_title);
        titleText.setText("Copy Layer");

        final TextInputEditText input = (TextInputEditText) alertView.findViewById(R.id.edit_text_input);
        input.setText(layerName + context.getString(R.string.geopackage_copy_suffix));
        input.setHint("New layer name");

        AlertDialog.Builder copyDialog = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle)
                .setView(alertView)
                .setPositiveButton("Copy", (dialog, which)->{
                    String newName = input.getText().toString();
                    if (newName != null && !newName.isEmpty()
                            && !newName.equals(gpName)) {
                        dialog.dismiss();
                        listener.onCopyLayer(gpName, layerName, newName);
                    }
                })

                .setNegativeButton(context.getString(R.string.button_cancel_label),
                        (dialog, which)->{
                            dialog.dismiss();
                        });
        AlertDialog alertDialog = copyDialog.create();


        // Validate the input before allowing the rename to happen
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String givenName = input.getText().toString();
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);

                if(givenName.isEmpty()){
                    input.setError("Name is required");
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                } else {
                    boolean allowed = Pattern.matches("[a-zA-Z_0-9]+", givenName);
                    if (!allowed) {
                        input.setError("Names must be alphanumeric only");
                        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    }
                }
            }
        });
        alertDialog.show();
    }

    /**
     * Open a Delete GeoPackage dialog view
     * @param context Context for opening dialog
     * @param gpName GeoPackage name
     * @param listener Click listener to callback to the mapfragment
     */
    public void openDeleteDialog(Context context, String gpName,
                                 final OnDialogButtonClickListener listener){
        // Create Alert window with basic input text layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View alertView = inflater.inflate(R.layout.basic_label_alert, null);
        // Logo and title
        ImageView alertLogo = (ImageView) alertView.findViewById(R.id.alert_logo);
        alertLogo.setBackgroundResource(R.drawable.material_delete);
        TextView titleText = (TextView) alertView.findViewById(R.id.alert_title);
        titleText.setText("Delete this GeoPackage?");
        TextView actionLabel = (TextView) alertView.findViewById(R.id.action_label);
        actionLabel.setText(gpName);
        actionLabel.setVisibility(View.INVISIBLE);

        AlertDialog deleteDialog = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle)
                .setView(alertView)
                .setIcon(context.getResources().getDrawable(R.drawable.material_delete))
                .setPositiveButton("Delete", (dialog, which)->{
                    dialog.dismiss();
                    listener.onDeleteGP(gpName);
                })

                .setNegativeButton(context.getString(R.string.button_cancel_label),
                        (dialog, which)->{
                            dialog.dismiss();
                            listener.onCancelButtonClicked();
                        }).create();

        deleteDialog.show();
    }

    /**
     * A Delete dialog for deleting a layer from a GeoPackage (called from the Layer Detail page)
     * @param context Context for opening dialog
     * @param gpName GeoPackage name
     * @param layerName Layer name to delete
     * @param listener Click listener to callback to the mapfragment
     */
    public void openDeleteLayerDialog(Context context, String gpName, String layerName,
                                      final OnDialogButtonClickListener listener){
        // Create Alert window with basic input text layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View alertView = inflater.inflate(R.layout.basic_label_alert, null);
        // Logo and title
        ImageView alertLogo = (ImageView) alertView.findViewById(R.id.alert_logo);
        alertLogo.setBackgroundResource(R.drawable.material_delete);
        TextView titleText = (TextView) alertView.findViewById(R.id.alert_title);
        titleText.setText("Delete this Layer?");
        TextView actionLabel = (TextView) alertView.findViewById(R.id.action_label);
        actionLabel.setText(layerName);
        actionLabel.setVisibility(View.INVISIBLE);

        AlertDialog deleteDialog = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle)
                .setView(alertView)
                .setIcon(context.getResources().getDrawable(R.drawable.material_delete))
                .setPositiveButton("Delete", (dialog, which)->{
                    dialog.dismiss();
                    listener.onDeleteLayer(gpName, layerName);
                })

                .setNegativeButton(context.getString(R.string.button_cancel_label),
                        (dialog, which)->{
                            dialog.dismiss();
                            listener.onCancelButtonClicked();
                        }).create();

        deleteDialog.show();
    }


}
