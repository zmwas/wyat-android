package com.wyat.wyat.events.adapter;

import android.view.View;
import android.widget.ImageView;

import com.wyat.entities.Event;

/**
 * Created by zack on 27/02/17.
 */

public interface RecyclerClickListener {

    void OnItemClicked(int Position, Event event,View v);

}
