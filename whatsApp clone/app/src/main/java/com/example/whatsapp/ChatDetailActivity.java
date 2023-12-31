package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.whatsapp.Adapter.ChatAdapter;
import com.example.whatsapp.Fragment.ChatFragment;
import com.example.whatsapp.Model.MessageModel;
import com.example.whatsapp.databinding.ActivityChatDetailBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class ChatDetailActivity extends AppCompatActivity {
            ActivityChatDetailBinding binding;

            FirebaseDatabase database;
            FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityChatDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
  getSupportActionBar().hide();
        database= FirebaseDatabase.getInstance();
        auth= FirebaseAuth.getInstance();



        final String senderId = auth.getUid();
        String recievedId = getIntent().getStringExtra("userId");
        String userName = getIntent().getStringExtra("userName");
        String profile = getIntent().getStringExtra("profile");
        binding.userName.setText(userName);
        Picasso.get().load(profile).placeholder(R.drawable.user).into(binding.profileImage);

        binding.backtochat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChatDetailActivity.this, ChatFragment.class));
            }
        });


        final ArrayList<MessageModel> messageModels = new ArrayList<>();
        final ChatAdapter chatAdapter= new ChatAdapter(messageModels, this);
        binding.chatrecyclerview.setAdapter(chatAdapter);

        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        binding.chatrecyclerview.setLayoutManager(layoutManager);

        final  String senderRoom = senderId + recievedId;
        final  String reciverRoom = recievedId + senderId;

        database.getReference().child("chats")
                        .child(senderRoom)
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        messageModels.clear();
                                        for (DataSnapshot snapshot1 : snapshot.getChildren()){
                                            MessageModel model= snapshot.getValue(MessageModel.class);
                                            messageModels.add(model);
                                        }
                                        chatAdapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });



        binding.sendmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = binding.etdmsg.getText().toString();
                final  MessageModel model = new MessageModel(senderId, message);
                model.setTimestamp(new Date().getTime());

                binding.etdmsg.setText("");
                database.getReference().child("chats").child(senderRoom)
                        .push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                database.getReference().child("chats").child(reciverRoom)
                                        .push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {

                                            }
                                        });
                            }
                        });
            }
        });




    }

}