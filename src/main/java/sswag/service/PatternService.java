package sswag.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import sswag.exception.CustomException;
import sswag.model.Pattern;
import sswag.model.PatternStatus;
import sswag.model.User;
import sswag.repository.PatternRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PatternService {

    @Autowired
    private PatternRepository patternRepository;

    public List<Pattern> getAllPatterns() {
        List<Pattern> approved = patternRepository.findByStatusEquals(PatternStatus.STATUS_APPROVED);
        List<Pattern> pending = patternRepository.findByStatusEquals(PatternStatus.STATUS_PENDING);
        List<Pattern> rejected = patternRepository.findByStatusEquals(PatternStatus.STATUS_REJECTED);
        List<Pattern> result = Stream.of(approved, pending, rejected).flatMap(Collection::stream).collect(Collectors.toList());
        return result;
    }

    public List<Pattern> getApprovedPatterns() {
        return patternRepository.findByStatusEquals(PatternStatus.STATUS_APPROVED);
    }

    public List<Pattern> getPendingPatterns() {
        return patternRepository.findByStatusEquals(PatternStatus.STATUS_PENDING);
    }

    public List<Pattern> getPatternsByUser(User user) {
        return patternRepository.findByAuthorEquals(user);
    }

    public List<Pattern> getRejectedPatterns() {
        return patternRepository.findByStatusEquals(PatternStatus.STATUS_REJECTED);
    }

    public Pattern getPatternById(Integer id) {
        if (patternRepository.existsById(id)) {
            return patternRepository.findByIdEquals(id);
        } else {
            throw new CustomException("Pattern with ID: " + id + " does not exist.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void delete(Integer id) {
        patternRepository.deleteById(id);
    }

    public Pattern savePattern(Pattern p) {
        return patternRepository.save(p);
    }

}
