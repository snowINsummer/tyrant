package tyrant.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhangli on 8/5/2017.
 */

@Entity
//@Table(name = "PRODUCT")
public class Product implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private String productName;
    private Date createTime;
    private Date updateTime;
    private Integer isActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
