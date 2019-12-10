package app.controller;

import app.entity.Course;
import app.entity.Subscriber;
import app.repository.CourseRepository;
import app.repository.SubscriberRepository;
import app.repository.UserRepository;
import com.fasterxml.jackson.core.util.RequestPayload;
import org.springframework.web.bind.annotation.*;

import javax.validation.Payload;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class SubscribeController {
    SubscriberRepository subscriberRepository;
    UserRepository userRepository;
    CourseRepository courseRepository;

    public SubscribeController(
            SubscriberRepository subscriberRepository,
            UserRepository userRepository,
            CourseRepository courseRepository
    ) {
        this.subscriberRepository = subscriberRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @PostMapping("/subscribe")
    public Subscriber subscribe(@RequestBody Map<String, Integer> body) {
        Subscriber subscriber = new Subscriber();
        Course course = this.courseRepository.findOne(body.get("courseId"));

        subscriber.setCourse(course);
        subscriber.setUser(this.userRepository.findOne(body.get("userId")));
        subscriber.setFirstName("Test");
        subscriber.setLastName("TestL");
        this.subscriberRepository.saveAndFlush(subscriber);

        return subscriber;
    }

    @GetMapping("/subscribe/{id}")
    public Subscriber get(@PathVariable int id) {
        return this.subscriberRepository.findOne(id);
    }
}
