package com.example.testfirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UsersAdapter> {
    List<User> userList;
    public UserAdapter(List<User> userList){
        this.userList = userList;

    }
    @NonNull
    @Override
    public UsersAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout,parent,false);
        return new UsersAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter holder, int position) {
        User users = userList.get(position);
        holder.nameView.setText(users.getName());
    }

    @Override
    public int getItemCount() {
        if (userList != null){
            return userList.size();
        }
        return 0;
    }

    public static class UsersAdapter extends RecyclerView.ViewHolder{
        private final TextView nameView;
        public UsersAdapter(@NonNull View itemView) {
            super(itemView);
            nameView =itemView.findViewById(R.id.nameView);
        }
    }
}
