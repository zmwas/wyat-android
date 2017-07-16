package com.wyat.wyat.events.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wyat.entities.Event;
import com.wyat.wyat.R;
import com.wyat.wyat.events.presenters.EventDetailPresenter;
import com.wyat.wyat.events.views.EventDetailView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zack on 15/03/17.
 */

public class EventDetailFragment extends Fragment implements EventDetailView {
    @Bind(R.id.detail_image_view)
    ImageView detail_image;

    @Bind(R.id.textview_venue)
    TextView venue;

    @Bind(R.id.description)
    TextView description;


    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

     @Bind(R.id.appbar)
    AppBarLayout appBarLayout;

    @Inject
    EventDetailPresenter eventDetailPresenter;

    private Event event;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.event_detail_layout, container, false);
        ButterKnife.bind(this, v);


        return v;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        event = (Event) getActivity().getIntent().getSerializableExtra("event");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(event.getName());

        venue.setText(event.getVenue().getName());

        description.setText(event.getDescription());

        Picasso.with(getActivity()).load(event.getEventPicUrl()).fit().into(detail_image);
        detail_image.setDrawingCacheEnabled(true);

        Bitmap bitmap = detail_image.getDrawingCache();


    }
}
