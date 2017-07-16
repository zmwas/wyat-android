package com.wyat.wyat.events.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.wyat.entities.Venue;
import com.wyat.wyat.R;
import com.wyat.wyat.application.AppComponent;
import com.wyat.wyat.application.WyatApplication;
import com.wyat.wyat.events.dependency_injection.components.DaggerEventComponent;
import com.wyat.wyat.events.dependency_injection.modules.EventModule;
import com.wyat.wyat.events.presenters.PostEventPresenter;

import org.codepond.wizardroid.WizardStep;
import org.codepond.wizardroid.persistence.ContextVariable;

import java.io.File;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.zelory.compressor.Compressor;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;

/**
 * Created by zack on 27/01/17.
 */

public class PostEventFragment2 extends WizardStep {


    @Bind(R.id.upload_pic)
    Button upload;

    @Bind(R.id.preview_pic)
    ImageView preview;

    @Bind(R.id.age)
    RadioGroup age;

    @Bind(R.id.free)
    RadioGroup free;

    @Bind(R.id.manage)
    RadioGroup manage;

    @Bind(R.id.yes_age)
    RadioButton yes_age;

    @Bind(R.id.no_age)
    RadioButton no_age;

    @Bind(R.id.yes_free)
    RadioButton yes_free;

    @Bind(R.id.no_free)
    RadioButton no_free;

    @Bind(R.id.yes_manage)
    RadioButton yes_manage;

    @Bind(R.id.no_manage)
    RadioButton no_manage;

    @Bind(R.id.post_event_button)
    Button post;

    @Bind(R.id.ticket_text)
    TextView ticket_text;

    @Bind(R.id.ticket_price)
    EditText ticket_price;

    @Bind(R.id.invite)
    RadioGroup invite;

    @Bind(R.id.yes_invite)
    RadioButton yes_invite;

    @Bind(R.id.no_invite)
    RadioButton no_invite;


    @Inject
    PostEventPresenter postEventPresenter;

    @ContextVariable
    String name;
    @ContextVariable
    String date_time;
    @ContextVariable
    String venue_name;
    @ContextVariable
    String description;
    @ContextVariable
    String type;

    String path;

    String inviteOnly;

    String ageRestricted;

    String isfree;

    String ticketPrice;


    Venue venue;

    File actualImage;


    Uri selectedImageUri;


    public PostEventFragment2() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppComponent appComponent = ((WyatApplication) getActivity().getApplication()).getAppComponent();

        DaggerEventComponent.builder().appComponent(appComponent).eventModule(new EventModule()).build().Inject(this);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.event_post_2, container, false);

        ButterKnife.bind(this, v);

        return v;
    }


    @OnClick(R.id.upload_pic)
    public void upload() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), 1);


    }

    @OnClick({

            R.id.yes_free,
            R.id.no_free

    })
    public void freeClicked(RadioButton radioButton) {

        switch (radioButton.getId()) {
            case R.id.yes_free:
                isfree = "true";
                ticket_text.setVisibility(View.GONE);
                ticket_text.setVisibility(View.GONE);

            case R.id.no_free:
                isfree = "false";
                ticket_text.setVisibility(View.VISIBLE);
                ticket_price.setVisibility(View.VISIBLE);


        }


    }

    @OnClick({

            R.id.yes_invite,
            R.id.no_invite

    })
    public void inviteClicked(RadioButton radioButton) {

        switch (radioButton.getId()) {
            case R.id.yes_invite:
                inviteOnly = "true";

            case R.id.no_invite:
                inviteOnly = "false";

        }


    }

    @OnClick({

            R.id.yes_age,
            R.id.no_age

    })
    public void ageClicked(RadioButton radioButton) {

        switch (radioButton.getId()) {
            case R.id.yes_age:
                ageRestricted = "true";

            case R.id.no_age:
                ageRestricted = "false";

        }


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            selectedImageUri = data.getData();

            path = selectedImageUri.getPath();
            preview.setVisibility(View.VISIBLE);
            preview.setImageURI(selectedImageUri);
            actualImage = new File(path);


            Compressor.getDefault(getActivity())
                    .compressToFileAsObservable(actualImage)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<File>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(File file) {
                    path = file.getPath();



                }
            });
        }


    }


    @OnClick(R.id.post_event_button)
    public void postEvent() {
        ticketPrice = ticket_price.getText().toString();

        Log.v(ageRestricted, ageRestricted);


        venue = new Venue();
        venue.setName(venue_name);
        venue.setAddress("Komarock");
        venue.setCity("Nairobi");
        venue.setRating(1.1);


        postEventPresenter.setAgeRestricted(ageRestricted);
        postEventPresenter.setTime(date_time);
        postEventPresenter.setFree(isfree);
        postEventPresenter.setDescription(description);
        postEventPresenter.setInvite(inviteOnly);
        postEventPresenter.setName(name);
        postEventPresenter.setTicketPrice(ticketPrice);
        postEventPresenter.setType(type);
        postEventPresenter.setPath(path);
        postEventPresenter.setVenue(venue);
        postEventPresenter.postVenue();


    }


}
