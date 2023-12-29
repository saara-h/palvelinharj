package harjoitustyo.controller;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import harjoitustyo.entity.Categories;
import harjoitustyo.entity.Events;
import harjoitustyo.repository.CategoryRepository;
import harjoitustyo.repository.EventRepository;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    // Haetaan eventit ja categoriat
    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("events", this.eventRepository.findAllByOrderByEventDateAsc());
        model.addAttribute("categories", this.categoryRepository.findAll());
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
                         @RequestParam Date eventDate,
                         @RequestParam List<Long> categoryId) {
 
        Events newEvent = new Events();
        newEvent.setEventTitle(eventTitle);
        newEvent.setEventDate(eventDate);

        // Retrieve selected categories from the repository
        List<Categories> categories = categoryRepository.findAllById(categoryId);
        // Set the categories for the event
        newEvent.setCategories(new HashSet<>(categories));

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

            // Include categories in the model for the edit form
        List<Categories> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);

        return "edit";
    }

    
    // Tallennetaan muutokset
    @PostMapping("/edit/{id}")
    public String editEvent(
        @PathVariable Long id, 
        @RequestParam String eventTitle, 
        @RequestParam Date eventDate,
        @RequestParam(required = false) String eventDescription,
        @RequestParam List<Long> categoryId) {
        // Retrieve the existing event by ID
        Events existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event ID: " + id));

        existingEvent.setEventTitle(eventTitle);
        existingEvent.setEventDate(eventDate);
        existingEvent.setEventDescription(eventDescription);

        // Retrieve existing category by ID
        List<Categories> categories = categoryRepository.findAllById(categoryId);

        // Set the category for the event
        existingEvent.setCategories(new HashSet<>(categories));

        eventRepository.save(existingEvent);

        return "redirect:/events/";
    }


    // Poista tapahtuma
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        this.eventRepository.deleteById(id);
        return "redirect:/events/";
    }


    // katsellaan eventtejä kategorioittain
    @GetMapping("/eventsbycategory")
    public String eventsByCategory(@RequestParam(required = false) Long categoryId, Model model) {
    List<Events> events;

    if (categoryId != null) {
        Categories selectedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + categoryId));
        events = eventRepository.findByCategories(selectedCategory);
    } else {
        events = eventRepository.findAll();
    }

    model.addAttribute("events", events);
    model.addAttribute("categories", categoryRepository.findAll());

    return "eventsbycategory";
}


}
