package com.example.anutree;

import static com.example.anutree.R.layout.user_holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter <UserAdapter.UserHolder> {

    private ArrayList <User> users; //List to store Users
    private Context context;
    private OnClickListener onClickListener;

    interface OnClickListener { // Place to have a onlick listener
        void onUserClicked (int position); //passes the possition of the user that is clicked for the msging app
    }

    public UserAdapter(ArrayList<User> users, Context context,OnClickListener onClickListener) { //constructor
        this.users = users;
        this.context = context;
        this.onClickListener = onClickListener;
    }


    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(user_holder,parent,false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        holder.textUsername.setText(users.get(position).getuID());  //.UID = .getUsername //Add glide after
        Glide.with(context).load(users.get(position).getPfp()).error(R.drawable.account_image).placeholder(R.drawable.account_image).into(holder.imgView); //puts into Image box
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserHolder extends RecyclerView.ViewHolder {
        TextView textUID;
        TextView textUsername;
        ImageView imgView;

        public UserHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onUserClicked(getAdapterPosition());
                }
            });

            textUID = itemView.findViewById(R.id.txtUID);
            textUsername = itemView.findViewById(R.id.txtUsername);
            imgView = itemView.findViewById(R.id.profile_img);


        }
    }
}
