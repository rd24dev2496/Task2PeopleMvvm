package com.example.task3peoplemvvm.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.task3peoplemvvm.R;
import com.example.task3peoplemvvm.databinding.PeopleDetailActivityBinding;
import com.example.task3peoplemvvm.model.People;
import com.example.task3peoplemvvm.viewmodel.PeopleDetailViewModel;

public class PeopleDetailActivity extends AppCompatActivity {
    private static final String EXTRA_PEOPLE = "EXTRA_PEOPLE";

    private PeopleDetailActivityBinding binding;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.people_detail_activity);
        //setSupportActionBar(binding.toolbar);
        displayHomeAsUpEnabled();
        getExtrasFromIntent();
    }

    public static Intent launchDetail(Context context, People people) {
        Intent intent = new Intent(context, PeopleDetailActivity.class);
        intent.putExtra(EXTRA_PEOPLE, people);
        return intent;
    }

    private void displayHomeAsUpEnabled() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void getExtrasFromIntent() {
        People people = (People) getIntent().getSerializableExtra(EXTRA_PEOPLE);
        PeopleDetailViewModel peopleDetailViewModel = new PeopleDetailViewModel(people);
        binding.setPeopleDetailViewModel(peopleDetailViewModel);
        setTitle(people.getName().getTitle() + "." + people.getName().getFirst() + " " + people.getName().getLast());
    }
}
