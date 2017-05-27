package tyrant.service;

import qa.exception.HTTPException;
import qa.exception.RunException;
import qa.httpClient.ResponseInfo;
import tyrant.common.entity.WSDataVo;

/**
 * Created by zhangli on 24/5/2017.
 */
public interface WSService {

    ResponseInfo sendMessage(WSDataVo wsResult) throws HTTPException, RunException;
}
