package sswag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sswag.model.Pattern;
import sswag.model.PatternStatus;
import sswag.model.User;

import javax.transaction.Transactional;
import java.util.List;

public interface PatternRepository extends JpaRepository<Pattern, Integer> {

    boolean existsById(Integer id);

    Pattern findByIdEquals(Integer id);

    List<Pattern> findByAuthorEquals(User user);

    List<Pattern> findByStatusEquals(PatternStatus status);

    @Transactional
    void deleteById(Integer id);
}
