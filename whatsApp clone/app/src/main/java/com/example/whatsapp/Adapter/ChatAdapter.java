package com.example.whatsapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp.ChatDetailActivity;
import com.example.whatsapp.Model.MessageModel;
import com.example.whatsapp.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.logging.Handler;

public class ChatAdapter extends RecyclerView.Adapter {


      ArrayList<MessageModel> messageModels;
      Context context;
      int SENDER_VIEW_TYPE = 1;
    int RECIVER_VIEW_TYPE = 2;

    public ChatAdapter (ArrayList<MessageModel> messageModels, Context context) {

        this.messageModels= messageModels;
        this.context= context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           if(viewType == SENDER_VIEW_TYPE ){
               View view= LayoutInflater.from(context).inflate(R.layout.sample_sender, parent,false);
               return new SenderViewVolder(view);
           }
           else {
               View view= LayoutInflater.from(context).inflate(R.layout.sample_reciever, parent,false);
               return new RecieveViewHolder(view);
           }


    }
     @Override
     public  int getItemViewType(int position){
        if(messageModels.get(position).getUId().equals(FirebaseAuth.getInstance().getUid())){
            return SENDER_VIEW_TYPE;
        }
        else {
            return RECIVER_VIEW_TYPE;
        }

     }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       MessageModel messageModel = messageModels.get(position);
       if(holder.getClass()== SenderViewVolder.class){
           ((SenderViewVolder)holder).sendertxt.setText(messageModel.getMessage());
       }
       else {
           ((RecieveViewHolder)holder).recievertxt.setText(messageModel.getMessage());
       }

    }

    @Override
    public int getItemCount() {
        return messageModels.size();
    }

    public class RecieveViewHolder extends RecyclerView.ViewHolder{

        TextView recievertxt , recievertime;

        public RecieveViewHolder(@NonNull View itemView) {
            super(itemView);
            recievertxt = itemView.findViewById(R.id.recievertxt);
            recievertime = itemView.findViewById(R.id.recievertime);
        }
    }
    public class SenderViewVolder extends RecyclerView.ViewHolder{

        TextView sendertxt , sendertime;

        public SenderViewVolder(@NonNull View itemView) {
            super(itemView);
            sendertxt = itemView.findViewById(R.id.sendertxt);
            sendertime = itemView.findViewById(R.id.sendertime);
        }
    }



}
