package harjoitustyo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import harjoitustyo.entity.Events;
import harjoitustyo.repository.EventRepository;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Events> getAllEvents() {
        return eventRepository.findAll();
    }

    public Events getEventById(Long eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }

    public void saveEvent(Events event) {
        eventRepository.save(event);
    }

    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }
}
