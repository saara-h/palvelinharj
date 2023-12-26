package harjoitustyo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import harjoitustyo.entity.Events;
import harjoitustyo.repository.EventRepository;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/")
    public String getAllEvents(Model model) {
        // 1. Retrieve a list of events
        List<Events> events = eventRepository.findAll();
    
        // 2. Add the list of events to the Thymeleaf model with the attribute name "events".
        model.addAttribute("events", events);
    
        // 3. Return the logical view name "index" to Spring MVC.
        return "index";
    }
    

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("events", new Events());
        return "create";
    }

    @PostMapping("/create")
    public String createEvent(@ModelAttribute("events") Events events) {
        eventRepository.save(events);
        return "redirect:/";
    }

    // Add methods for edit and delete as needed

}
