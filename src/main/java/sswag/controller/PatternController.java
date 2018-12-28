package sswag.controller;

import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import sswag.dto.NewPatternDTO;
import sswag.dto.PatternFullDTO;
import sswag.dto.PatternNameDTO;
import sswag.model.Pattern;
import sswag.model.PatternStatus;
import sswag.model.User;
import sswag.service.PatternService;
import sswag.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/patterns")
@Api(tags = "patterns")
public class PatternController {

    @Autowired
    private UserService userService;

    @Autowired
    private PatternService patternService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${PatternController.all}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<PatternFullDTO> getAllPatterns() {
        List<Pattern> allPatterns = patternService.getAllPatterns();
        java.lang.reflect.Type listType = new TypeToken<List<PatternFullDTO>>() {}.getType();
        return modelMapper.map(allPatterns, listType);
    }

    @GetMapping(value = "/approved")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${PatternController.approved}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<PatternFullDTO> getApprovedPatterns() {
        List<Pattern> approved = patternService.getApprovedPatterns();
        java.lang.reflect.Type listType = new TypeToken<List<PatternFullDTO>>() {}.getType();
        return modelMapper.map(approved, listType);
    }

    @GetMapping(value = "/approvedNames")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${PatternController.approvedNames}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<PatternNameDTO> getApprovedNames() {
        List<Pattern> approved = patternService.getApprovedPatterns();
        java.lang.reflect.Type listType = new TypeToken<List<PatternNameDTO>>() {}.getType();
        return modelMapper.map(approved, listType);
    }

    @GetMapping(value = "/pending")
    @PreAuthorize("hasRole('ROLE_APPROVER') or hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${PatternController.pending}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<PatternFullDTO> getPendingPatterns() {
        List<Pattern> pending = patternService.getPendingPatterns();
        java.lang.reflect.Type listType = new TypeToken<List<PatternFullDTO>>() {}.getType();
        return modelMapper.map(pending, listType);
    }

    @GetMapping(value = "/mypending")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${PatternController.mypending}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<PatternFullDTO> getMyPendingPatterns(HttpServletRequest req) {
        User u = userService.whois(req);
        List<Pattern> patterns = patternService.getPatternsByUser(u);
        List<Pattern> ret = new ArrayList<Pattern>();
        for (Pattern p : patterns) {
            if (p.getStatus() == PatternStatus.STATUS_PENDING) ret.add(p);
        }
        java.lang.reflect.Type listType = new TypeToken<List<PatternFullDTO>>() {}.getType();
        return modelMapper.map(ret, listType);
    }

    @GetMapping(value = "/rejected")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_APPROVER')")
    @ApiOperation(value = "${PatternController.rejected}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<PatternFullDTO> getRejectedPatterns() {
        List<Pattern> rejected = patternService.getRejectedPatterns();
        java.lang.reflect.Type listType = new TypeToken<List<PatternFullDTO>>() {}.getType();
        return modelMapper.map(rejected, listType);
    }

    @GetMapping(value = "/id/{id}")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${PatternController.id}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The pattern doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public PatternFullDTO search(HttpServletRequest req, @ApiParam("PatternID") @PathVariable Integer id) {
        Pattern p = patternService.getPatternById(id);
        if ( (p.getStatus() == PatternStatus.STATUS_APPROVED || req.isUserInRole("ROLE_ADMIN") || req.isUserInRole("ROLE_APPROVER")) ) {
            return modelMapper.map(p, PatternFullDTO.class);
        } else if (p.getAuthor().getUserID() == userService.whois(req).getUserID()) {
            return modelMapper.map(p, PatternFullDTO.class);
        } else {
            return modelMapper.map(new Pattern(), PatternFullDTO.class); //TODO: Throw Exception
        }
    }

    @PostMapping(value = "/new")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${PatternController.new}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 422, message = "Username is already in use"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token"),
            @ApiResponse(code = 200, message = "Upload of new pattern Successful.")})
    public String newPattern(HttpServletRequest req, @ApiParam("New Pattern") @RequestBody NewPatternDTO newPatternDTO) {//
        Pattern p = modelMapper.map(newPatternDTO, Pattern.class);
        p.setAuthor(userService.whois(req));
        p.setStatus(PatternStatus.STATUS_PENDING);
        patternService.savePattern(p);
        return "Success";
    }

    @PostMapping(value = "/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${PatternController.save}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 422, message = "Username is already in use"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token"),
            @ApiResponse(code = 200, message = "Saving of pattern Successful.")})
    public String savePattern(HttpServletRequest req, @ApiParam("New Pattern") @RequestBody PatternFullDTO patternDTO) {//
        Pattern p = modelMapper.map(patternDTO, Pattern.class);
        patternService.savePattern(p);
        return "Success";
    }

    @PostMapping(value = "/approve/{id}")
    @PreAuthorize("hasRole('ROLE_APPROVER')")
    @ApiOperation(value = "${PatternController.save}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 422, message = "Username is already in use"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token"),
            @ApiResponse(code = 200, message = "Pattern Approved.")})
    public String approvePattern(HttpServletRequest req, @ApiParam("PatternID") @PathVariable Integer id, @ApiParam("Comments") @RequestBody String comments) {
        Pattern p = patternService.getPatternById(id);
        p.setStatus(PatternStatus.STATUS_APPROVED);
        p.setApprover(userService.whois(req));
        p.setApproverComments(comments);
        patternService.savePattern(p);
        return "Success";
    }

    @PostMapping(value = "/reject/{id}")
    @PreAuthorize("hasRole('ROLE_APPROVER')")
    @ApiOperation(value = "${PatternController.reject}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 422, message = "Username is already in use"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token"),
            @ApiResponse(code = 200, message = "Pattern rejected.")})
    public String rejectPattern(HttpServletRequest req, @ApiParam("PatternID") @PathVariable Integer id, @ApiParam("Comments") @RequestBody String comments) {
        Pattern p = patternService.getPatternById(id);
        p.setStatus(PatternStatus.STATUS_REJECTED);
        p.setApprover(userService.whois(req));
        p.setApproverComments(comments);
        patternService.savePattern(p);
        return "Success";
    }

}
