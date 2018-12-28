package sswag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sswag.model.Attachment;

import javax.transaction.Transactional;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {

    boolean existsById(Integer id);

    Attachment findByIdEquals(Integer id);

    @Transactional
    void deleteById(Integer id);
}
