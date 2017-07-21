package tyrant.service;

import tyrant.body.QueryLastFiveDaysResult;
import tyrant.common.DataTransformObject.ChartModel;
import tyrant.body.WSResult;

/**
 * Created by zhangli on 9/5/2017.
 */

public interface TestcaseResultService {

    void saveResult(WSResult wsResult);

    ChartModel queryLastFiveDaysResult(QueryLastFiveDaysResult queryLastFiveDaysResult);
}
