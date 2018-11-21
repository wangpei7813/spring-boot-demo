package com.wang.dao.slave;

import com.wang.model.UserInfo;


/**
 * @Author wp
 * @date 2018/11/12 10:23
 */
public interface UserSlaveMapper {
    /**
     * 新增用户
     * @param user
     * @return
     */
    int save(UserInfo user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int update(UserInfo user);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteById(Long id);


}
