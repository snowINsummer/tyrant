package tyrant.service;

import tyrant.common.entity.UserWorkTime;
import tyrant.entity.UserWorkTimeR;

/**
 * Created by zhangli on 16/6/2017.
 */

public interface UserService {
    UserWorkTimeR getUserWorkTime(UserWorkTime userWorkTime) throws Exception;
}
