package dao;

import bean.User;

public interface UserDao {
    /**
     * 调用getInfo()能查到信息说明登录成功
     *
     * @param user
     * @return
     */
    boolean login(User user);

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    int save(User user);

    /**
     * 1.根据id删除
     * 2.根据其它信息删除
     *
     * @param user
     * @return
     */
    int delete(User user);

    /**
     * 根据user查找用户信息
     *
     * @param user(username,usertype)
     * @return test.user(username, usertype, id), 失败返回null
     */
    User getInfo(User user);
    User getInfo();

    /**
     * 根据id修改用户名
     *
     * @param user
     * @return
     */
    int update(User user);
}