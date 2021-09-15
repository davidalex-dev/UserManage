package com.example.usermanage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usermanage.model.User;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    private Toolbar detail_toolbar;
    private TextView detail_name, detail_age, detail_address;
    private ImageButton detail_delete, detail_edit;
    private Intent intent;

    //private static final String TAG = "UserActivity";
    private boolean edit = AddModifyActivity.edit;
    private ArrayList<User> listUser = MainActivity.listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        InitView();

        intent = getIntent();
//        String id = intent.getStringExtra("IDPosition");
//        String name = intent.getStringExtra("IDName");
//        String age = intent.getStringExtra("IDAge");
//        String address = intent.getStringExtra("IDAddress");

        String tempPosition = intent.getStringExtra("IDPosition");
        String tempName = intent.getStringExtra("IDName");
        String tempAge = intent.getStringExtra("IDAge");
        String tempAddress = intent.getStringExtra("IDAddress");

//        Log.d(TAG, "[at position: " + tempPosition + "]");
//        Log.d(TAG, "Name received: " + tempName);
//        Log.d(TAG, "Age: " + tempAge);
//        Log.d(TAG, "Address received: " + tempAddress);

        detail_name.setText(tempName);
        detail_age.setText(tempAge);
        detail_address.setText(tempAddress);


        detail_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        detail_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //appear delete dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);

                builder.setMessage("Are you sure to delete this user?");
                builder.setTitle("Delete User");
                builder.setCancelable(false);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.listUser.remove(Integer.parseInt(tempPosition)); //for remove
                        Toast.makeText(getApplicationContext(), "User is deleted", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(intent);
                        finish();


                    }
                    });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        detail_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "Edit button pressed", Toast.LENGTH_SHORT).show();
                AddModifyActivity.edit = true;
                Intent intent = new Intent (getBaseContext(), AddModifyActivity.class);
                intent.putExtra("editName", tempName);
                intent.putExtra("editAge", tempAge);
                intent.putExtra("editAddress", tempAddress);
                intent.putExtra("editID", tempPosition);
//                Log.d(TAG, "Going to edit name: " + tempName);
//                Log.d(TAG, "Going to edit age: " + tempAge);
//                Log.d(TAG, "Going to edit address: " + tempAddress);
//                Log.d(TAG, "At position: " + tempPosition);
                startActivity(intent);
            }
        });

    }

    private void InitView(){
        detail_toolbar = findViewById(R.id.detail_toolbar);
        detail_name = findViewById(R.id.detail_name);
        detail_age = findViewById(R.id.detail_age);
        detail_address = findViewById(R.id.detail_address);
        detail_delete = findViewById(R.id.detail_delete);
        detail_edit = findViewById(R.id.detail_edit);
    }
}