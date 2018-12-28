package sswag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sswag.model.Attachment;
import sswag.model.Pattern;
import sswag.repository.AttachmentRepository;


@Service
public class AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    public Attachment getAttachmentById(Integer id) {
        return attachmentRepository.findByIdEquals(id);
    }

    public void delete(Integer id) {
        attachmentRepository.deleteById(id);
    }

    public Attachment saveAttachment(Attachment a) {
        return attachmentRepository.save(a);
    }

}
