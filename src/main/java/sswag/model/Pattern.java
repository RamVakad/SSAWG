package sswag.model;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Pattern {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private User author;

    @Size(min = 4, max = 255, message = "Minimum pattern name length: 4 characters, Max: 255")
    @Column(nullable = false)
    private String name;

    @Column
    @Lob
    private String prolog;

    @Column
    @Lob
    private String problem;

    @Column
    @Lob
    private String forces;

    @Column
    @Lob
    private String solution;

    @Column
    @Lob
    private String examples;

    @Column
    @Enumerated(EnumType.STRING)
    private Classification classification;

    @Column
    private String classOther;

    @Column
    @Enumerated(EnumType.STRING)
    private SecurityDomain securityDomain;

    @Column
    private String securityOther;

    @Column
    private String category;

    @Column
    @Lob
    private String languages;

    @Column
    @Lob
    private String resultingContext;

    @Column
    @Lob
    private String codeRepository;

    @Column
    @Lob
    private String howTo;

    @Column
    @Lob
    private String threatModel;

    @Column
    @Lob
    private String testCases;

    @Column
    @Lob
    private String testScripts;

    @OneToOne
    private User approver;

    @Column
    @Lob
    private String approverComments;

    @Column
    @Enumerated(EnumType.STRING)
    private PatternStatus status;

    @OneToMany
    private List<Attachment> attachments;

    @Column
    @Lob
    private String attachmentsTemp;

    public Integer getPatternID() {
        return id;
    }

    public void setPatternID(Integer id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
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

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getAttachmentsTemp() {
        return attachmentsTemp;
    }

    public void setAttachmentsTemp(String attachmentsTemp) {
        this.attachmentsTemp = attachmentsTemp;
    }

}
