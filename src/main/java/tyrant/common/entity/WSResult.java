package tyrant.common.entity;

import java.util.List;

/**
 * Created by zhangli on 10/5/2017.
 */
public class WSResult {

    private Integer caseType;

    private String productName;

    private String batchNo;

    private List<WSResultItem> itemList;

    public Integer getCaseType() {
        return caseType;
    }

    public void setCaseType(Integer caseType) {
        this.caseType = caseType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public List<WSResultItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<WSResultItem> itemList) {
        this.itemList = itemList;
    }
}
