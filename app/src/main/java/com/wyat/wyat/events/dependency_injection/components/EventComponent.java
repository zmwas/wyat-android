package com.wyat.wyat.events.dependency_injection.components;

import com.wyat.GetEventListUseCase;
import com.wyat.PostEventUseCase;
import com.wyat.PostVenueUseCase;
import com.wyat.wyat.accounts.dependency_injection.Modules.ActivityModule;
import com.wyat.wyat.application.AppComponent;
import com.wyat.wyat.common.Activity;
import com.wyat.wyat.events.activities.EventDetailActivity;
import com.wyat.wyat.events.activities.EventListActivity;
import com.wyat.wyat.events.dependency_injection.modules.EventModule;
import com.wyat.wyat.events.fragments.PostEventFragment2;

import dagger.Component;

/**
 * Created by zack on 16/01/17.
 */
@Activity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, EventModule.class})
public interface EventComponent {

    GetEventListUseCase getEventListUseCase();
    PostEventUseCase postEventUseCase();
    PostVenueUseCase postVenueUseCase();

    void Inject(EventListActivity eventList);
    void Inject(PostEventFragment2 postEventFragment2);





}
