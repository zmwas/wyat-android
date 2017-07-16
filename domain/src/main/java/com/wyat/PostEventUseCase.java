package com.wyat;

import com.wyat.entities.Event;
import com.wyat.entities.Venue;
import com.wyat.repository.WyatRepository;

import java.io.File;
import java.util.Map;

import rx.Observable;

/**
 * Created by zack on 31/12/16.
 */

public class PostEventUseCase extends UseCase<Event> {


    private final WyatRepository wyatRepository;

    private String  path,  name,  time,  type,  description, invite, ageRestricted, free, ticketPrice,venue_id;





    public PostEventUseCase(WyatRepository wyatRepository) {
        this.wyatRepository = wyatRepository;
    }


    public void setAgeRestricted(String ageRestricted) {
        this.ageRestricted = ageRestricted;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public void setInvite(String invite) {
        this.invite = invite;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
    }

    @Override

    public Observable<Event> buildObservable() {

        return wyatRepository.postEvent(path,venue_id, name,  time,  type,  description, invite, ageRestricted, free, ticketPrice);
    }
}
