package harjoitustyo.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import harjoitustyo.entity.Events;
import harjoitustyo.repository.EventRepository;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;


    // Haetaan eventit 
    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("events", this.eventRepository.findAllByOrderByEventDateAsc());
        return "events";
    }

    @GetMapping("/{id}")
    public String showEventDetails(@PathVariable Long id, Model model) {
        // Retrieve the event details based on the provided ID
        Events event = eventRepository.findById(id).orElse(null);

        // Add the event to the model
        model.addAttribute("event", event);

        // Return the template name for the individual event page
        return "eventdetails";
    }


    // Luodaan uusi eventti, title ja päiväys pakolliset
    @PostMapping("/create")
    public String create(@RequestParam String eventTitle,
                         @RequestParam Date eventDate) {
 
        Events newEvent = new Events();
        newEvent.setEventTitle(eventTitle);
        newEvent.setEventDate(eventDate);

        // Tänne pitäisi saada vielä kategoria

        this.eventRepository.save(newEvent);
        return "redirect:/events/";
    }


    // Muokkaustilaan
    @GetMapping("/edit/{id}")
    public String editEvent(@PathVariable Long id, Model model) {
        // Retrieve existing event using the id
        Events event = eventRepository.findById(id).orElse(null);
    
        // Add the event data to the model for the edit form
        if (event != null) {
            model.addAttribute("eventId", event.getId());
            model.addAttribute("eventTitle", event.getEventTitle());
            model.addAttribute("eventDate", event.getEventDate());
        }

        return "edit";
    }

    
    // Tallennetaan muutokset
    @PostMapping("/edit/{id}")
    public String editEvent(
        @PathVariable Long id, 
        @RequestParam String eventTitle, 
        @RequestParam Date eventDate,
        @RequestParam(required = false) String eventDescription) {
        // Retrieve the existing event by ID
        Events existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event ID: " + id));

        existingEvent.setEventTitle(eventTitle);
        existingEvent.setEventDate(eventDate);
        existingEvent.setEventDescription(eventDescription);

        eventRepository.save(existingEvent);

        return "redirect:/events/";
    }


    // Poista tapahtuma
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        this.eventRepository.deleteById(id);
        return "redirect:/events/";
    }

}
