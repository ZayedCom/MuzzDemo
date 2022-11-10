package com.muzz.muzzdemo.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.muzz.muzzdemo.R;
import com.muzz.muzzdemo.databinding.ActivityMessagingBinding;
import com.muzz.muzzdemo.utility.Database;
import com.muzz.muzzdemo.utility.MessageListAdapter;
import com.muzz.muzzdemo.viewmodel.MessagingViewModel;

public class MessagingActivity extends AppCompatActivity {

    //sharedPreferences key
    public static final String myPreferences = "muzz";

    public static SharedPreferences sharedPreferences;

    public static Database database;

    public SQLiteDatabase dbSQL;

    private MessagingViewModel messagingViewModel;

    private ActivityMessagingBinding binding;

    private MessageListAdapter messageAdapter;

    boolean switchUser = false;

    //Loading messages from database when available.
    public void retrieveMessages() {
        messagingViewModel.getMessageList().observe(this, messageList -> {
            messageAdapter = new MessageListAdapter(messageList);

            LinearLayoutManager messageLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            messageLayoutManager.setStackFromEnd(true);

            binding.messagesRecyclerView.setLayoutManager(messageLayoutManager);
            binding.messagesRecyclerView.setAdapter(messageAdapter);
        });

        messagingViewModel.fetchMessages();
    }

    //Taking user text input from the editView and sending the message.
    public void sendMessage(View view) {
        if (!switchUser) {
            messagingViewModel.sendMessage("Zaid K. Al Qassar", binding.editMessage.getText().toString());
        } else {
            messagingViewModel.sendMessage("Shahzad Younas", binding.editMessage.getText().toString());
        }

        messagingViewModel.getMessageList().observe(this, messageList -> {
            messageAdapter = new MessageListAdapter(messageList);

            LinearLayoutManager messageLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            messageLayoutManager.setStackFromEnd(true);

            binding.messagesRecyclerView.setLayoutManager(messageLayoutManager);
            binding.messagesRecyclerView.setAdapter(messageAdapter);
        });

        binding.editMessage.getText().clear();
    }

    //Switching user and reply to message
    public void replyMessage(View view) {
        if (switchUser) {
            messagingViewModel.setMessageDeliveryStatus("Shahzad Younas");
            binding.username.setText(R.string.user_1);
            switchUser = false;
        } else {
            messagingViewModel.setMessageDeliveryStatus("Zaid K. Al Qassar");
            binding.username.setText(R.string.user_2);
            switchUser = true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Loading up data from database.
        database = new Database(MessagingActivity.this);
        dbSQL = database.getWritableDatabase();
        database.close();

        //Loading up sharedPreferences.
        sharedPreferences = getSharedPreferences(myPreferences, Context.MODE_PRIVATE);

        //Initializing the viewModel.
        messagingViewModel = new ViewModelProvider(this).get(MessagingViewModel.class);

        binding = DataBindingUtil.setContentView(MessagingActivity.this, R.layout.activity_messaging);

        binding.setLifecycleOwner(this);

        binding.setMessagingViewModel(messagingViewModel);

        binding = ActivityMessagingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Setting up the toolbar.
        Toolbar toolbar = binding.toolbar;
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        binding.username.setText(R.string.user_1);

        //Retrieving messages from SQL database.
        retrieveMessages();

        //Toast message observer
        messagingViewModel.getToastObserver().observe(this, message -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_options) {
            messagingViewModel.setToastMessage("You Clicked More Options..");
        }
        return super.onOptionsItemSelected(item);
    }
}