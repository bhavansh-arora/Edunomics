package com.example.edunomics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.midi.MidiOutputPort;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class chat extends AppCompatActivity {


    List<MessageChatModel> messageChatModelList =  new ArrayList<>();
    RecyclerView recyclerView;
    MessageChatAdapter adapter ;
    String time,hours,minutes;
    int hour;
    EditText messageET;
    ImageView sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getSupportActionBar().hide();

        messageET = (EditText)findViewById(R.id.messageET);
        sendBtn = (ImageView) findViewById(R.id.sendBtn);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(chat.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);



        MessageChatModel model1 = new MessageChatModel(
                "Hello. How are you today?",
                "10:00 PM",
                0
        );
        MessageChatModel model2 = new MessageChatModel(
                "Hey! I'm fine. Thanks for asking!",
                "10:00 PM",
                1
        );
        MessageChatModel model3 = new MessageChatModel(
                "Sweet! So, what do you wanna do today?",
                "10:00 PM",
                0
        );
        MessageChatModel model4 = new MessageChatModel(
                "Nah, I dunno. Play soccer.. or learn more coding perhaps?",
                "10:00 PM",
                1
        );


        messageChatModelList.add(model1);
        messageChatModelList.add(model2);
        messageChatModelList.add(model3);
        messageChatModelList.add(model4);
        messageChatModelList.add(model1);
        messageChatModelList.add(model2);
        messageChatModelList.add(model3);
        messageChatModelList.add(model4);
        messageChatModelList.add(model1);
        messageChatModelList.add(model2);
        messageChatModelList.add(model3);
        messageChatModelList.add(model4);

        recyclerView.smoothScrollToPosition(messageChatModelList.size());
        adapter = new MessageChatAdapter(messageChatModelList, chat.this );
        recyclerView.setAdapter(adapter);


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar=Calendar.getInstance();
                final SimpleDateFormat format=new SimpleDateFormat("HH");
                final SimpleDateFormat formatm=new SimpleDateFormat("mm");


                String msg = messageET.getText().toString();
                hours=format.format(calendar.getTime());
                hour=Integer.parseInt(hours);
                minutes=formatm.format(calendar.getTime());


                if(hour>12){
                    hour=hour-12;
                    time=hour+":"+minutes+" PM";
                }
                else if(hour==0){
                    hour=12;
                    time=hour+":"+minutes+" AM";


                }
                else{
                    time=hour+":"+minutes+" AM";
                }



                MessageChatModel model = new MessageChatModel(
                        msg,
                        time,
                        0
                );
                messageChatModelList.add(model);
                recyclerView.smoothScrollToPosition(messageChatModelList.size());
                adapter.notifyDataSetChanged();
                messageET.setText("");


            }
        });

    }
}
