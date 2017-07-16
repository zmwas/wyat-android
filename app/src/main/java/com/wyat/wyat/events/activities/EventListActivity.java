package com.wyat.wyat.events.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wyat.entities.Event;
import com.wyat.wyat.R;
import com.wyat.wyat.application.AppComponent;
import com.wyat.wyat.application.WyatApplication;
import com.wyat.wyat.events.adapter.EventListAdapter;
import com.wyat.wyat.events.adapter.RecyclerClickListener;
import com.wyat.wyat.events.dependency_injection.components.DaggerEventComponent;
import com.wyat.wyat.events.dependency_injection.modules.EventModule;
import com.wyat.wyat.events.fragments.EventDetailFragment;
import com.wyat.wyat.events.presenters.EventListPresenter;
import com.wyat.wyat.events.views.EventListView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zack on 13/01/17.
 */


public class EventListActivity extends AppCompatActivity implements EventListView, RecyclerClickListener {

    @Inject
    EventListPresenter eventListPresenter;
    @Bind(R.id.event_list)
    RecyclerView recyclerView;

    EventDetailFragment eventDetailFragment;


    private EventListAdapter eventListAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_list_layout);

        ButterKnife.bind(this);
        AppComponent appComponent = ((WyatApplication) getApplication()).getAppComponent();


        DaggerEventComponent.builder()
                .appComponent(appComponent)
                .eventModule(new EventModule())
                .build().Inject(this);

        eventListPresenter.attachView(this);
        eventListPresenter.onCreate();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public void bindEventList(List<Event> events) {
        eventListAdapter = new EventListAdapter(events, this, this);
        recyclerView.setAdapter(eventListAdapter);
    }

    @Override
    public void updateEventList(int charactersAdded) {

        eventListAdapter.notifyItemRangeChanged(eventListAdapter.getItemCount(), eventListAdapter.getItemCount() + charactersAdded);


    }

    @Override
    public void showDetailScreen() {
        Intent intent = new Intent(this, EventDetailActivity.class);

        startActivity(intent);

    }

    @Override
    public void OnItemClicked(int Position, Event event, View v) {


      //  FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

     //   eventDetailFragment = new EventDetailFragment();

        Intent intent = new Intent(this,EventDetailActivity.class);

        Bundle bundle = new Bundle();

        bundle.putSerializable("event", event);

        intent.putExtras(bundle);

        startActivity(intent);

       // eventDetailFragment.setArguments(bundle);

       // fragmentTransaction.replace(R.id.fragment_layout, eventDetailFragment).commit();


    }
}
