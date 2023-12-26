package newtables;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Event extends AbstractPersistable<Long> {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "event_title", nullable = false)
    private String eventTitle;

    @Column(name = "event_description", columnDefinition = "TEXT")
    private String eventDescription;

    @Column(name = "event_date")
    private Date eventDate;

    @Column(name = "event_time")
    private Time eventTime;

    @Column(name = "due_date")
    private Date dueDate;
}
