package tyrant.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by zhangli on 10/5/2017.
 */

@Entity
public class BatchNo {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer testcaseId;
    @Column(nullable = false)
    private String batchNoStr;
    private Date createTime;
    private Date updateTime;

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

    public String getBatchNoStr() {
        return batchNoStr;
    }

    public void setBatchNoStr(String batchNoStr) {
        this.batchNoStr = batchNoStr;
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
}
