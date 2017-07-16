package com.wyat;

import com.wyat.entities.Event;
import com.wyat.repository.WyatRepository;

import java.util.List;

import rx.Observable;

/**
 * Created by zack on 31/12/16.
 */

public class GetEventListUseCase extends UseCase<List<Event>> {

    private WyatRepository wyatRepository;


    public GetEventListUseCase(WyatRepository repository) {
        wyatRepository=repository;
    }




    @Override
    public Observable<List<Event>> buildObservable() {
        return wyatRepository.getEvents();
    }
}
