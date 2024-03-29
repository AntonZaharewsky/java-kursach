package app.repository;

import app.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByNameContainingOrDescriptionContaining(String text, String textAgain);
}
