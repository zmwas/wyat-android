package com.wyat.wyat.events.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.wyat.wyat.R;

import org.codepond.wizardroid.WizardStep;
import org.codepond.wizardroid.persistence.ContextVariable;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zack on 27/01/17.
 */

public class PostEventFragment1 extends WizardStep {


    Calendar date;
    @Bind(R.id.event_name)
    EditText event_name;
    @Bind(R.id.show_date_time)
    EditText show_date_time;
    @Bind(R.id.pick_date_time)
    Button pick_date_time;
    @Bind(R.id.event_venue)
    EditText event_venue;
    @Bind(R.id.event_type)
    Spinner event_type;
    @Bind(R.id.event_description)
    EditText event_description;

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

    public PostEventFragment1() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.event_post_1, container, false);

        ButterKnife.bind(this, v);


        return v;
    }


    private void bindDataFields() {

        name = event_name.getText().toString();

        venue_name = event_venue.getText().toString();

        date_time = show_date_time.getText().toString();

        description = event_description.getText().toString();

        type = event_type.getSelectedItem().toString();


    }

    @OnClick(R.id.pick_date_time)
    public void pick_date() {


        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();
        new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                date.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        date.set(Calendar.MINUTE, minute);
                        Log.v("TAG", "The choosen one " + date.getTime());


                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        show_date_time.setText(simpleDateFormat.format(date.getTime()));

                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();

    }

    @Override
    public void onExit(int exitCode) {

        switch (exitCode) {
            case (WizardStep.EXIT_NEXT):
                bindDataFields();
            case (WizardStep.EXIT_PREVIOUS):
                break;

        }

        super.onExit(exitCode);
    }
}
