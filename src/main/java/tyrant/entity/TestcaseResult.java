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
public class TestcaseResult {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private Integer batchNoId;
    @Column(nullable = false)
    private Integer testcaseDataId;
    @Column(nullable = false)
    private String status;
    private Date startTime;
    private Date finishTime;
    private String duration;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBatchNoId() {
        return batchNoId;
    }

    public void setBatchNoId(Integer batchNoId) {
        this.batchNoId = batchNoId;
    }

    public Integer getTestcaseDataId() {
        return testcaseDataId;
    }

    public void setTestcaseDataId(Integer testcaseDataId) {
        this.testcaseDataId = testcaseDataId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

}
