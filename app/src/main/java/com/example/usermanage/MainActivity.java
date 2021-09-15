package com.example.usermanage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usermanage.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView main_recycler;
    private FloatingActionButton button_add;
    private Intent intent;
    private TextView main_nodata;

    public static ArrayList<User> listUser = new ArrayList<>();
    public static UserRVAdapter adapter = new UserRVAdapter(listUser);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //addDummyData(); //add dummy data, for testing purposes
        setupRecyclerView();

        if(listUser.size() > 0){
            main_nodata.setVisibility(View.INVISIBLE);
            adapter.notifyDataSetChanged();
        }else{
            main_nodata.setVisibility(View.VISIBLE);
        }

        button_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                intent = new Intent(getBaseContext(), AddModifyActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initView(){
        main_recycler = findViewById(R.id.main_recycler);
        main_nodata = findViewById(R.id.main_nodata);
        button_add = findViewById(R.id.button_add);
    }

    private void setupRecyclerView(){
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getBaseContext());
        main_recycler.setLayoutManager(manager);
        main_recycler.setAdapter(adapter);
    }

    private void addDummyData(){ //add dummy data, for testing purposes
        listUser.add(new User("Test", 69, "420 St., Nice, France"));
        adapter.notifyDataSetChanged();
    }

}