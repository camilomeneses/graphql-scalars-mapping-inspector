package dev.camilo.graphqlmappinginspection.event;

import dev.camilo.graphqlmappinginspection.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EventController {

  private static final Logger log = LoggerFactory.getLogger(EventController.class);

  private final List<Event> events = new ArrayList<>();

  public EventController() {
    var event = new Event(
        1,
        "SpringOne VMware Explore",
        "Event description",
        LocalDate.of(2023,8,21),
        LocalDate.of(2023,8,24),
        LocalDate.now().minusDays(180),
        LocalDate.now().minusDays(90),
        "Las Vegas, NV",
        "https://springone.io"
    );
    events.add(event);
  }

  @QueryMapping
  public List<Event> events(){
    return events;
  }

  /* importante nombrar el metodo igual como en el schema para poder ser llamado*/
  @SchemaMapping
  public List<Session> sessions(Event event){
    log.info("Fetching Sessions for Event: " + event.toString());
    var session1 = new Session(1, "test 1", "test 1 desc");
    return List.of(session1);
  }
}