package com.example.usermanage;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.usermanage.model.User;
import java.util.ArrayList;

public class UserRVAdapter extends RecyclerView.Adapter<UserRVAdapter.TempViewHolder>{
    private ArrayList<User> listUser;
    //private static final String TAG = "UserRVAdapter";

    public UserRVAdapter(ArrayList<User> listTemp){
        this.listUser = listTemp;
    }

    @NonNull
    @Override
    public TempViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_user, parent, false);
        return new TempViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull TempViewHolder holder, int position) {
        holder.user_Name.setText(listUser.get(position).getName());
        holder.user_Age.setText(String.valueOf(listUser.get(position).getAge()));
        holder.user_Address.setText(listUser.get(position).getAddress());
        holder.user_ID.setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class TempViewHolder extends RecyclerView.ViewHolder{
        private TextView user_Name, user_Age, user_Address, user_ID;
        private CardView user_CardView;

        public TempViewHolder(@NonNull View itemView){
            super(itemView);
            user_Name = itemView.findViewById(R.id.user_Name);
            user_Age = itemView.findViewById(R.id.user_Age);
            user_Address = itemView.findViewById(R.id.user_Address);
            user_ID = itemView.findViewById(R.id.user_ID);
            user_CardView = itemView.findViewById(R.id.user_CardView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent (view.getContext(), UserActivity.class);
                    intent.putExtra("IDName", user_Name.getText());
                    intent.putExtra("IDAge", String.valueOf(user_Age.getText()));
                    intent.putExtra("IDAddress", user_Address.getText());
                    intent.putExtra("IDPosition", user_ID.getText());

//                    Log.d(TAG, "User name is: " + user_Name.getText());
//                    Log.d(TAG, "Age is: " + user_Age.getText());
//                    Log.d(TAG, "Address is: " + user_Address.getText());
//                    Log.d(TAG, "Position is: " + user_ID.getText());
                    view.getContext().startActivity(intent);
                }
            });

        }
    }

}
