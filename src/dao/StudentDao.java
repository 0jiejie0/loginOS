package dao;

import bean.Page;
import bean.Student;
import bean.User;

import java.util.List;

public interface StudentDao {
    /**
     * 在student表中新增一条记录，在user表中关联增加一条记录
     *
     * @param student 新增学生的信息
     * @param userDao
     * @param user 设有密码
     * @return 1 成功新增一条记录，0 失败
     */
    int add(Student student, UserDao userDao, User user);
    int add(Student student,User user);

    /**
     * 有id按id删除一条记录，否则按其他信息删除一条
     *
     * @param student
     * @return
     */
    int delete(Student student, UserDao userDao);
    int delete(Student student);

    /**
     * 将list中的学生批量删除
     *
     * @param list
     * @return
     */
    int delete(List<Student> list, UserDao userDao);
    int delete(List<Student> list);

    /**
     * 更改学生信息
     *
     * @param student
     * @return
     */
    int update(Student student, UserDao userDao);
    int update(Student student);

    /**
     * 分页查询
     *
     * @param page    页面信息(含查询条件)
     * @return
     */
    Page find(Page page);

    /**
     * 用作查找一个学生的信息，有id按id查询，否则按其他信息查询
     *
     * @param student
     * @return 查到的所有字段信息
     */
    Student find(Student student);
}