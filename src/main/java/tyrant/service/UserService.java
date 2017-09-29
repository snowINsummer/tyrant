package tyrant.service;

import tyrant.body.UserWorkTime;
import tyrant.entity.UserWorkTimeR;

/**
 * Created by zhangli on 16/6/2017.
 */

public interface UserService {
    UserWorkTimeR getUserWorkTime(UserWorkTime userWorkTime) throws Exception;
}
