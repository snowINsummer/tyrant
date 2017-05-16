package tyrant.common.entity;

import tyrant.entity.*;

/**
 * Created by zhangli on 11/5/2017.
 */
public class SaveResultVo {

    private Integer caseType;
    private Product product;
    private Project project;
    private String projectName;
    private Module module;
    private String moduleName;
    private Testcase testcase;
    private String testcaseName = null;
    private TestcaseModule testcaseModule;
    private String testcaseModuleName;
    private TestcaseData testcaseData;
    private String excelNo;

    public Integer getCaseType() {
        return caseType;
    }

    public void setCaseType(Integer caseType) {
        this.caseType = caseType;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Testcase getTestcase() {
        return testcase;
    }

    public void setTestcase(Testcase testcase) {
        this.testcase = testcase;
    }

    public String getTestcaseName() {
        return testcaseName;
    }

    public void setTestcaseName(String testcaseName) {
        this.testcaseName = testcaseName;
    }

    public TestcaseModule getTestcaseModule() {
        return testcaseModule;
    }

    public void setTestcaseModule(TestcaseModule testcaseModule) {
        this.testcaseModule = testcaseModule;
    }

    public String getTestcaseModuleName() {
        return testcaseModuleName;
    }

    public void setTestcaseModuleName(String testcaseModuleName) {
        this.testcaseModuleName = testcaseModuleName;
    }

    public TestcaseData getTestcaseData() {
        return testcaseData;
    }

    public void setTestcaseData(TestcaseData testcaseData) {
        this.testcaseData = testcaseData;
    }

    public String getExcelNo() {
        return excelNo;
    }

    public void setExcelNo(String excelNo) {
        this.excelNo = excelNo;
    }
}
