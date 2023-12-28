package harjoitustyo.controller;

import java.sql.Date;
import java.util.List;

import org.apache.regexp.recompile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("events", this.eventRepository.findAll());
        return "index";
    }


    @PostMapping("/create")
    public String create(@RequestParam String eventTitle,
                         @RequestParam Date eventDate) {
        // Parse the date, create event, associate categories, and save to the repository
        Events newEvent = new Events();
        newEvent.setEventTitle(eventTitle);
        newEvent.setEventDate(eventDate);

        // Set other attributes as needed

        this.eventRepository.save(newEvent);
        return "redirect:/events/";
    }

    @GetMapping("/edit/{id}")
    public String editEvent(@PathVariable Long id, Model model) {
        // Retrieve existing event using the id
        Events event = eventRepository.findById(id).orElse(null);
    
        // Add the event data to the model for the edit form
        if (event != null) {
            model.addAttribute("eventId", event.getId());
            model.addAttribute("eventTitle", event.getEventTitle());
            model.addAttribute("eventDate", event.getEventDate());
            // Add other properties as needed
        }
    
        // Return the edit form view
        return "edit";
    }

    
    @PostMapping("/edit/{id}")
    public String editEvent(@PathVariable Long id, @RequestParam String eventTitle, @RequestParam Date eventDate) {
        // Retrieve the existing event by ID
        Events existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event ID: " + id));

        // Update the event with new information
        existingEvent.setEventTitle(eventTitle);
        existingEvent.setEventDate(eventDate);

        // Save the updated event
        eventRepository.save(existingEvent);

        return "redirect:/events/";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        this.eventRepository.deleteById(id);
        return "redirect:/events/";
    }

}
