package tyrant.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by zhangli on 9/5/2017.
 */
@Entity
public class TestcaseData {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private Integer testcaseModuleId;
    @Column(nullable = false)
    private String excelNo;
    private String caseInfo;
    private String description;
    private Date createTime;
    private Date updateTime;
    private Integer isActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTestcaseModuleId() {
        return testcaseModuleId;
    }

    public void setTestcaseModuleId(Integer testcaseModuleId) {
        this.testcaseModuleId = testcaseModuleId;
    }

    public String getExcelNo() {
        return excelNo;
    }

    public void setExcelNo(String excelNo) {
        this.excelNo = excelNo;
    }

    public String getCaseInfo() {
        return caseInfo;
    }

    public void setCaseInfo(String caseInfo) {
        this.caseInfo = caseInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
}
