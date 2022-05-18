package com.example.anutree;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {

    private ArrayList<Message> messages;
    private String senderImg,recieverImg;
    private Context context;

    public MessageAdapter(ArrayList<Message> messages,String senderImg, String recieverMsg,Context context) {
        this.messages = messages;
        this.senderImg = senderImg;
        this.recieverImg = recieverMsg;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.message_holder,parent,false); //inflates msg
        return new MessageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        holder.txtmessage.setText((messages).get(position).getContentOfMsg());  //gets msgs by position and sets it as the txtmessage to be displayed

        ConstraintLayout constraintLayout = holder.x;
        if(messages.get(position).getSender().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())) {  //Might be UID???? check this
            Glide.with(context).load(senderImg).error(R.drawable.account_image).placeholder(R.drawable.account_image).into(holder.pfp); //if Pfp missing
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);  //get info in that moment
            constraintSet.clear((R.id.messageCardview),ConstraintSet.LEFT);  // Clears left side of constraints so msg shows on right side as IF user sent it
            constraintSet.clear((R.id.textmsg),ConstraintSet.LEFT); //clear left side of text that shows up
            constraintSet.connect((R.id.messageCardview),(ConstraintSet.RIGHT),R.id.ccLayout,ConstraintSet.RIGHT,0);//link to right side so it shows up as if u said it
            constraintSet.connect((R.id.textmsg),(ConstraintSet.RIGHT),R.id.messageCardview,ConstraintSet.LEFT,0);
            //same as above for pfp
            constraintSet.applyTo(constraintLayout);
        } else {
            Glide.with(context).load(recieverImg).error(R.drawable.account_image).placeholder(R.drawable.account_image).into(holder.pfp);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);  //get info in that moment
            constraintSet.clear((R.id.messageCardview),ConstraintSet.RIGHT);  // Clears left side of constraints so msg shows on right side as IF user sent it
            constraintSet.clear((R.id.textmsg),ConstraintSet.RIGHT); //clear left side of text that shows up
            constraintSet.connect((R.id.messageCardview),(ConstraintSet.LEFT),R.id.ccLayout,ConstraintSet.LEFT,0);//link to right side so it shows up as if u said it
            constraintSet.connect((R.id.textmsg),(ConstraintSet.LEFT),R.id.messageCardview,ConstraintSet.RIGHT,0);
            //same as above for pfp
            constraintSet.applyTo(constraintLayout);
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MessageHolder extends RecyclerView.ViewHolder{
        ConstraintLayout x;
        TextView txtmessage;
        ImageView pfp;

        public MessageHolder(@NonNull View itemView) {
            super(itemView);

            x = itemView.findViewById(R.id.ccLayout);
            txtmessage = itemView.findViewById(R.id.textmsg);
            pfp = itemView.findViewById(R.id.smallPfp);



        }
    }
}
