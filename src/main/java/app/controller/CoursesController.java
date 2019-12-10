package app.controller;

import app.entity.Course;
import app.entity.Subscriber;
import app.entity.User;
import app.repository.CourseRepository;
import app.repository.SubscriberRepository;
import app.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class CoursesController {
    private CourseRepository courseRepository;
    private UserRepository userRepository;
    private SubscriberRepository subscriberRepository;

    public CoursesController(
            CourseRepository courseRepository,
            UserRepository userRepository,
            SubscriberRepository subscriberRepository
    ) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.subscriberRepository = subscriberRepository;
    }

    @GetMapping("/course")
    public List<Course> findAll() {
        return this.courseRepository.findAll();
    }

    @GetMapping("/course/{id}")
    public Course find(@PathVariable int id) {
        return this.courseRepository.findOne(id);
    }

    @GetMapping("/coursebyemail")
    public List<Course> findByEmail(@RequestParam String email) {
        User user = this.userRepository.findByEmail(email);
        List<Subscriber> subscribers = this.subscriberRepository.findByUser(user);

        ArrayList<Course> courses = new ArrayList<Course>();
        subscribers.forEach((subscriber) -> courses.add(subscriber.getCourse()));
        return courses;
    }

    @GetMapping("/unsubscribe")
    @Transactional
    public void unsubscribe(@RequestParam String email, @RequestParam int courseId) {
        this.subscriberRepository.deleteByUserAndCourse(
                this.userRepository.findByEmail(email),
                this.courseRepository.findOne(courseId)
        );
        this.subscriberRepository.flush();
    }

    @PostMapping("/course")
    public Course create(@RequestBody Map<String, String> body) {
        Course course = new Course(
                body.get("name"),
                body.get("description"),
                Float.parseFloat(body.get("price"))
        );
        this.courseRepository.saveAndFlush(course);

        return course;
    }

    @DeleteMapping("/course/{id}")
    public void remove(@PathVariable int id) {
        this.courseRepository.delete(id);
    }
}
