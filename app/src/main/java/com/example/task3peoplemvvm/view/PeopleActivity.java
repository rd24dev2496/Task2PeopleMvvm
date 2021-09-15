package com.example.task3peoplemvvm.view;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.task3peoplemvvm.R;
import com.example.task3peoplemvvm.data.PeopleFactory;
import com.example.task3peoplemvvm.databinding.PeopleActivityBinding;
import com.example.task3peoplemvvm.viewmodel.PeopleViewModel;
import java.util.Observable;
import java.util.Observer;

public class PeopleActivity extends AppCompatActivity implements Observer {
    private PeopleViewModel peopleViewModel;
    private PeopleActivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
     // setSupportActionBar(binding.toolbar);
        setupListPeopleView(binding.recyclerPeople);
        setupObserver(peopleViewModel);
    }
    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.people_activity);
        peopleViewModel = new PeopleViewModel(this);
        binding.setMainViewModel(peopleViewModel);
        peopleViewModel.fetchPeopleList();
    }
    private void setupListPeopleView(RecyclerView recyclerPeople) {
        PeopleAdapter adapter = new PeopleAdapter();
        recyclerPeople.setAdapter(adapter);
        recyclerPeople.setHasFixedSize(true);
    }
    public void setupObserver(PeopleViewModel observable) {
        observable.addObserver(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        peopleViewModel.reset();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_github) {
            startActivityActionView();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void startActivityActionView() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(PeopleFactory.PROJECT_URL)));
    }
    @Override
    public void update(Observable observable, Object data) {
        PeopleAdapter peopleAdapter = (PeopleAdapter) binding.recyclerPeople.getAdapter();
        PeopleViewModel peopleViewModel = (PeopleViewModel) observable;
        if (peopleAdapter != null) {
            peopleAdapter.setPeopleList(peopleViewModel.getPeopleList());
        }
    }
}