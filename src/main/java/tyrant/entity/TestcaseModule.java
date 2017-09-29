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
public class TestcaseModule {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private Integer testcaseId;
    @Column(nullable = false)
    private String testcaseModuleName;
    private Date createTime;
    private Date updateTime;
    private Integer isActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTestcaseId() {
        return testcaseId;
    }

    public void setTestcaseId(Integer testcaseId) {
        this.testcaseId = testcaseId;
    }

    public String getTestcaseModuleName() {
        return testcaseModuleName;
    }

    public void setTestcaseModuleName(String testcaseModuleName) {
        this.testcaseModuleName = testcaseModuleName;
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
