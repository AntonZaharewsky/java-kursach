package app.repository;

import app.entity.Course;
import app.entity.Subscriber;
import app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriberRepository extends JpaRepository<Subscriber, Integer> {
    public List<Subscriber> findByUser(User user);
    public void deleteByUserAndCourse(User user, Course course);
}
