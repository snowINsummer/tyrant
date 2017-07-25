package tyrant.service;

import tyrant.common.DataTransformObject.LastFiveDaysResult;

import java.util.List;

/**
 * Created by zhangli on 24/7/2017.
 */
public interface JdbcTemplateService {

    List<LastFiveDaysResult> queryLastFiveDaysResult(Integer testcaseId);

}
