package sswag.dto;


import io.swagger.annotations.ApiModelProperty;
import sswag.model.*;

import java.util.List;

public class PatternFullDTO {

    @ApiModelProperty(position = 0)
    private Integer id;

    @ApiModelProperty(position = 1)
    private UserFullDTO author;

    @ApiModelProperty(position = 2)
    private String name;

    @ApiModelProperty(position = 3)
    private String prolog;

    @ApiModelProperty(position = 4)
    private String problem;

    @ApiModelProperty(position = 5)
    private String forces;

    @ApiModelProperty(position = 6)
    private String solution;

    @ApiModelProperty(position = 7)
    private String examples;

    @ApiModelProperty(position = 8)
    private Classification classification;

    @ApiModelProperty(position = 9)
    private String classOther;

    @ApiModelProperty(position = 10)
    private SecurityDomain securityDomain;

    @ApiModelProperty(position = 11)
    private String securityOther;

    @ApiModelProperty(position = 12)
    private String category;

    @ApiModelProperty(position = 13)
    private String languages;

    @ApiModelProperty(position = 14)
    private String resultingContext;

    @ApiModelProperty(position = 15)
    private String codeRepository;

    @ApiModelProperty(position = 16)
    private String howTo;

    @ApiModelProperty(position = 17)
    private String threatModel;

    @ApiModelProperty(position = 18)
    private String testCases;

    @ApiModelProperty(position = 19)
    private String testScripts;

    @ApiModelProperty(position = 20)
    private User approver;

    @ApiModelProperty(position = 21)
    private String approverComments;

    @ApiModelProperty(position = 22)
    private PatternStatus status;

    @ApiModelProperty(position = 23)
    private List<AttachmentMiniDTO> attachments;

    @ApiModelProperty(position = 24)
    private String attachmentsTemp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserFullDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserFullDTO author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProlog() {
        return prolog;
    }

    public void setProlog(String prolog) {
        this.prolog = prolog;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getForces() {
        return forces;
    }

    public void setForces(String forces) {
        this.forces = forces;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getExamples() {
        return examples;
    }

    public void setExamples(String examples) {
        this.examples = examples;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public String getClassOther() {
        return classOther;
    }

    public void setClassOther(String classOther) {
        this.classOther = classOther;
    }

    public SecurityDomain getSecurityDomain() {
        return securityDomain;
    }

    public void setSecurityDomain(SecurityDomain securityDomain) {
        this.securityDomain = securityDomain;
    }

    public String getSecurityOther() {
        return securityOther;
    }

    public void setSecurityOther(String securityOther) {
        this.securityOther = securityOther;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getResultingContext() {
        return resultingContext;
    }

    public void setResultingContext(String resultingContext) {
        this.resultingContext = resultingContext;
    }

    public String getCodeRepository() {
        return codeRepository;
    }

    public void setCodeRepository(String codeRepository) {
        this.codeRepository = codeRepository;
    }

    public String getHowTo() {
        return howTo;
    }

    public void setHowTo(String howTo) {
        this.howTo = howTo;
    }

    public String getThreatModel() {
        return threatModel;
    }

    public void setThreatModel(String threatModel) {
        this.threatModel = threatModel;
    }

    public String getTestCases() {
        return testCases;
    }

    public void setTestCases(String testCases) {
        this.testCases = testCases;
    }

    public String getTestScripts() {
        return testScripts;
    }

    public void setTestScripts(String testScripts) {
        this.testScripts = testScripts;
    }

    public User getApprover() {
        return approver;
    }

    public void setApprover(User approver) {
        this.approver = approver;
    }

    public String getApproverComments() {
        return approverComments;
    }

    public void setApproverComments(String approverComments) {
        this.approverComments = approverComments;
    }

    public PatternStatus getStatus() {
        return status;
    }

    public void setStatus(PatternStatus status) {
        this.status = status;
    }

    public List<AttachmentMiniDTO> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentMiniDTO> attachments) {
        this.attachments = attachments;
    }

    public String getAttachmentsTemp() {
        return attachmentsTemp;
    }

    public void setAttachmentsTemp(String attachmentsTemp) {
        this.attachmentsTemp = attachmentsTemp;
    }

}
