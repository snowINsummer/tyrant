package tyrant.service;

import tyrant.body.QueryLastFiveDaysResult;
import tyrant.common.DataTransformObject.ChartModel;
import tyrant.body.WSResult;
import tyrant.common.DataTransformObject.LastFiveDaysResult;

import java.util.List;

/**
 * Created by zhangli on 9/5/2017.
 */

public interface TestcaseResultService {

    void saveResult(WSResult wsResult);

    ChartModel queryLastFiveDaysResult(QueryLastFiveDaysResult queryLastFiveDaysResult);

}
