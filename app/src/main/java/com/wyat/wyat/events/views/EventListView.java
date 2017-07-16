package com.wyat.wyat.events.views;

import com.wyat.entities.Event;
import com.wyat.wyat.common.View;

import java.util.List;

/**
 * Created by zack on 16/01/17.
 */

public interface EventListView extends View {

    void bindEventList(List<Event> events);

    void updateEventList(int charactersAdded);

    void showDetailScreen();


}
