package sswag.controller;


import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sswag.dto.AttachmentFullDTO;
import sswag.dto.AttachmentMiniDTO;
import sswag.dto.NewAttachmentDTO;
import sswag.model.Attachment;
import sswag.service.AttachmentService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/attachments")
@Api(tags = "attachments")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/get/{id}")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${AttachmentController.get}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token"),
            @ApiResponse(code = 200, message = "Attachment retreived.")})
    public AttachmentFullDTO getAttachment(@ApiParam("AttachmentID") @PathVariable Integer id) {
        Attachment a = attachmentService.getAttachmentById(id);
        return modelMapper.map(a, AttachmentFullDTO.class);
    }

    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${AttachmentController.delete}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token"),
            @ApiResponse(code = 200, message = "Attachment deleted.")})
    public String deleteAttachment(@ApiParam("AttachmentID") @PathVariable Integer id) {
        attachmentService.delete(id);
        return "Success";
    }

    @PostMapping(value = "/upload")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${AttachmentController.upload}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token"),
            @ApiResponse(code = 200, message = "Upload of new attachment Successful.")})
    public AttachmentMiniDTO upload(@ApiParam("New Attachment") @RequestBody NewAttachmentDTO newAttachmentDTO) {//
        Attachment a = modelMapper.map(newAttachmentDTO, Attachment.class);
        System.out.println(a.getBitsInBytes());
        a = attachmentService.saveAttachment(a);
        return modelMapper.map(a, AttachmentMiniDTO.class);
    }

}
