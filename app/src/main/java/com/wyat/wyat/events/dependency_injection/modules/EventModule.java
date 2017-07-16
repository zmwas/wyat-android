package com.wyat.wyat.events.dependency_injection.modules;

import com.wyat.GetEventDetailUseCase;
import com.wyat.GetEventListUseCase;
import com.wyat.PostEventUseCase;
import com.wyat.PostVenueUseCase;
import com.wyat.repository.WyatRepository;
import com.wyat.wyat.common.Activity;
import com.wyat.wyat.events.presenters.EventDetailPresenter;
import com.wyat.wyat.events.presenters.EventListPresenter;
import com.wyat.wyat.events.presenters.PostEventPresenter;
import com.wyat.wyat.events.views.EventDetailView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zack on 16/01/17.
 */
@Module
public class EventModule {

    @Provides @Activity
    GetEventListUseCase providesGetEventListUseCase(WyatRepository repository){
        return  new GetEventListUseCase(repository);
    }


    @Provides @Activity
    EventListPresenter providesEventListPresenter(GetEventListUseCase getEventListUseCase){

        return  new EventListPresenter(getEventListUseCase );
    }

    @Provides @Activity
    PostEventUseCase providesPostEventUseCase(WyatRepository repository){
        return new PostEventUseCase(repository);
    }


    @Provides @Activity
    PostEventPresenter providesPostEventPresenter(PostEventUseCase postEventUseCase,PostVenueUseCase postVenueUseCase){
        return new PostEventPresenter(postEventUseCase,postVenueUseCase);
    }

    @Provides @Activity
    PostVenueUseCase providesPostVenueUseCase(WyatRepository wyatRepository){

        return new PostVenueUseCase(wyatRepository);
    }


}
