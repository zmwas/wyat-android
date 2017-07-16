package com.wyat.wyat.events.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.wyat.wyat.R;
import com.wyat.wyat.events.fragments.EventDetailFragment;
import com.wyat.wyat.events.views.EventDetailView;

/**
 * Created by zack on 16/01/17.
 */

public class EventDetailActivity extends AppCompatActivity implements EventDetailView {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_detail_main);
/**
 Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
@Override public void onGenerated(Palette palette) {
collapsingToolbarLayout.setContentScrimColor(palette.getVibrantColor(getResources().getColor(R.color.colorPrimary)));
}
});
 **/

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_layout, new EventDetailFragment());
        ft.commit();

    }


}

