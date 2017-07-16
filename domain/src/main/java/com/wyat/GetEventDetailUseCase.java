package com.wyat;

import com.wyat.entities.Event;
import com.wyat.repository.WyatRepository;

import rx.Observable;

/**
 * Created by zack on 31/12/16.
 */

public class GetEventDetailUseCase extends UseCase<Event> {

    private WyatRepository wyatRepository;
    private int eventId;


    public GetEventDetailUseCase(int eventId,WyatRepository wyatRepository) {
        this.wyatRepository = wyatRepository;
        this.eventId= eventId;
    }

    @Override
    public Observable<Event> buildObservable() {
        return wyatRepository.getEvent(eventId);
    }
}
