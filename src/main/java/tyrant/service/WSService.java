package tyrant.service;

import qa.httpClient.ResponseInfo;
import tyrant.body.WSDataVo;

/**
 * Created by zhangli on 24/5/2017.
 */
public interface WSService {

    ResponseInfo sendMessage(WSDataVo wsResult) throws Exception;
}
