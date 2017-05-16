package tyrant.service;

import tyrant.entity.BatchNo;
import tyrant.entity.Testcase;

/**
 * Created by zhangli on 10/5/2017.
 */
public interface BatchNoService {
    BatchNo saveAndQuery(String batchNoStr);

    void update(BatchNo batchNo, Testcase testcase);
}
