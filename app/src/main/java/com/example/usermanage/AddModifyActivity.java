package com.example.usermanage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;

import com.example.usermanage.model.User;

import java.util.ArrayList;

public class AddModifyActivity extends AppCompatActivity {
    private Button addmodify_button;
    private TextInputLayout text_Name, text_Age, text_Address;
    private Toolbar addmodify_toolbar;
    private Intent intent;

    //private static final String TAG = "AddModifyActivity";

    public static boolean edit = false;

    private ArrayList<User> listUser = MainActivity.listUser;


    @Override
    public void onBackPressed() {
        if(edit == true){
            edit = false;
            finish();
        }else{
            end();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmodify);
        InitView();

        //Log.d(TAG, "Edit boolean is: " + edit);
        if(edit == true){
            addmodify_toolbar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    edit = false;
                    finish();
                }
            });

            intent = getIntent();
//            Log.d(TAG, "editName: " + intent.getStringExtra("editName"));
//            Log.d(TAG, "editAge: " + intent.getStringExtra("editAge"));
//            Log.d(TAG, "editAddress: " + intent.getStringExtra("editAddress"));
//            Log.d(TAG, "editID: " + intent.getStringExtra("editID"));

            addmodify_toolbar.setTitle("Edit User");
            text_Name.getEditText().setText(intent.getStringExtra("editName"));
            text_Age.getEditText().setText(intent.getStringExtra("editAge"));
            text_Address.getEditText().setText(intent.getStringExtra("editAddress"));

            addmodify_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkEmpty();
                    if(text_Name.getEditText().getText().toString().trim().length() > 0 &&
                            text_Age.getEditText().getText().toString().trim().length() > 0 &&
                            text_Address.getEditText().getText().toString().trim().length() > 0){

//                        Log.d(TAG, "Edited name: " + text_Name);
//                        Log.d(TAG, "Edited age: " + String.valueOf(text_Age));
//                        Log.d(TAG, "Edited address: " + text_Address);
//                        Log.d(TAG, "At position: " + intent.getStringExtra("editID"));


                        MainActivity.listUser.get(Integer.valueOf(intent.getStringExtra("editID"))).setName(text_Name.getEditText().getText().toString().trim());
                        MainActivity.listUser.get(Integer.valueOf(intent.getStringExtra("editID"))).setAge(Integer.parseInt(text_Age.getEditText().getText().toString().trim()));
                        MainActivity.listUser.get(Integer.valueOf(intent.getStringExtra("editID"))).setAddress(text_Address.getEditText().getText().toString().trim());

//                        Log.d(TAG, "Edited user");
                        Toast.makeText(getApplicationContext(), "User is edited", Toast.LENGTH_SHORT).show();
                        edit = false;
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }
            });






            //




        }else{
            addmodify_toolbar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    end();
                }
            });

            addmodify_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkEmpty();

                    if(text_Name.getEditText().getText().toString().trim().length() > 0 &&
                            text_Age.getEditText().getText().toString().trim().length() > 0 &&
                            text_Address.getEditText().getText().toString().trim().length() > 0){

                        String name = text_Name.getEditText().getText().toString().trim();
                        int age = Integer.parseInt(text_Age.getEditText().getText().toString().trim());
                        String address = text_Address.getEditText().getText().toString().trim();

//                        Log.d(TAG, "New name: " + name);
//                        Log.d(TAG, "New age: " + String.valueOf(age));
//                        Log.d(TAG, "New address: " + address);

                        User user = new User(name,age,address); //make new user
                        MainActivity.listUser.add(user);

//                        Log.d(TAG, "User is created");
                        Toast.makeText(getApplicationContext(), "User is created", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }

    }

    private void InitView(){
        addmodify_button = findViewById(R.id.addmodify_button);
        text_Name = findViewById(R.id.text_Name);
        text_Age = findViewById(R.id.text_Age);
        text_Address = findViewById(R.id.text_Address);
        addmodify_toolbar = findViewById(R.id.addmodify_toolbar);
    }

    private void end(){
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void checkEmpty(){
        //check if empty or not
        if(text_Name.getEditText().getText().toString().trim().length() == 0){
            text_Name.setError("Please fill the name!");
        }

        if(text_Age.getEditText().getText().toString().trim().length() == 0){
            text_Age.setError("Please fill out the age!");
        }

        if(text_Address.getEditText().getText().toString().trim().length() == 0){
            text_Address.setError("Please fill out the address!");
        }
    }

}