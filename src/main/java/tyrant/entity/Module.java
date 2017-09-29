package tyrant.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangli on 9/5/2017.
 */
@Entity
//@Data // 自动生成set get方法
//@NoArgsConstructor // 无参构造函数
public class Module {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private Integer projectId;
    @Column(nullable = false)
    private String moduleName;
    private Date createTime;
    private Date updateTime;
    private Integer isActive;

    @Transient
    private List<Testcase> testcaseList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
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

    public List<Testcase> getTestcaseList() {
        return testcaseList;
    }

    public void setTestcaseList(List<Testcase> testcaseList) {
        this.testcaseList = testcaseList;
    }
}
