package com.wyat;

import com.wyat.entities.Venue;
import com.wyat.repository.WyatRepository;

import rx.Observable;

/**
 * Created by zack on 22/02/17.
 */

public class PostVenueUseCase extends UseCase<Venue> {

    private WyatRepository wyatRepository;
    private Venue venue;

    public PostVenueUseCase(WyatRepository wyatRepository) {
        this.wyatRepository = wyatRepository;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    @Override
    public Observable<Venue> buildObservable() {
        return wyatRepository.postVenue(venue);
    }
}
