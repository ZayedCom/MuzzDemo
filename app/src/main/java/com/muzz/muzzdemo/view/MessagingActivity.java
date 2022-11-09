package com.muzz.muzzdemo.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.muzz.muzzdemo.R;
import com.muzz.muzzdemo.databinding.ActivityMessagingBinding;
import com.muzz.muzzdemo.utility.Database;
import com.muzz.muzzdemo.viewmodel.MessagingViewModel;

import java.util.Objects;

public class MessagingActivity extends AppCompatActivity {

    public static final String myPreferences = "myExchangeRates";

    public static SharedPreferences sharedPreferences;

    public Database database;

    public SQLiteDatabase dbSQL;

    private static Bundle bundleRecyclerViewState;

    private MessagingViewModel messagingViewModel;

    private ActivityMessagingBinding binding;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = new Database(MessagingActivity.this);
        dbSQL = database.getWritableDatabase();
        database.close();

        sharedPreferences = getSharedPreferences(myPreferences, Context.MODE_PRIVATE);

        messagingViewModel = new ViewModelProvider(this).get(MessagingViewModel.class);

        binding = DataBindingUtil.setContentView(MessagingActivity.this, R.layout.activity_messaging);

        binding.setLifecycleOwner(this);

        binding.setMessagingViewModel(messagingViewModel);

        binding = ActivityMessagingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toolbar = binding.toolbar;
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // save RecyclerView state when changing UI orientation
        /*
        bundleRecyclerViewState = new Bundle();
        if (Objects.requireNonNull(binding.messagesRecyclerView.getLayoutManager()).getChildAt(0) != null) {
            Parcelable listState = binding.messagesRecyclerView.getLayoutManager().onSaveInstanceState();
            bundleRecyclerViewState.putParcelable(myPreferences, listState);
        */
    }

    @Override
    protected void onResume() {
        super.onResume();

        // restore RecyclerView state when changing UI orientation
        /*
        if (!database.getDBMessages().isEmpty()) {
            if (Objects.requireNonNull(binding.messagesRecyclerView.getLayoutManager()).getChildAt(0) != null) {
                Parcelable listState = bundleRecyclerViewState.getParcelable(myPreferences);
                binding.messagesRecyclerView.getLayoutManager().onRestoreInstanceState(listState);
            }
        }
         */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_options) {
            Toast.makeText(this, "Clicked More Options..", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}