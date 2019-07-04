package daoImpl;

import bean.Page;
import bean.Student;
import bean.User;
import dao.StudentDao;
import dao.UserDao;
import util.DbUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public int add(Student student, User user) {
        return add(student, DaoFactory.getUserDaoImpl(), user);
    }

    @Override
    public int add(Student student, UserDao userDao, User user) {
        if (find(student).getId() != 0) {//要增加的记录已经存在，不再新增记录（由于新增时刷新页面造成重复提交表单，产生bug：学生表与用户表记录不一致，同理改进update方法）
            return 0;
        }
        String sql = "insert into userdb.student(name, gender, major, department) VALUES (?,?,?,?)";
        int res = 0;
        try {
            PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
            prepare(preparedStatement, student);
            res = preparedStatement.executeUpdate();
            DbUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (res == 0) {
            return res;
        }
        student = find(student);//取出新增学生的全部信息
        user.setId(student.getId());
        user.setUsername(student.getName());
        user.setUsertype("学生");
        res = userDao.save(user);
        return res;
    }

    @Override
    public int delete(Student student) {
        return delete(student, DaoFactory.getUserDaoImpl());
    }

    @Override
    public int delete(Student student, UserDao userDao) {
        student = find(student);//转换，得到id
        String sql = "delete from userdb.student where id=?";
        int result = 0;
        //先删除引用，然后才能删除主记录
        //删除引用
        User user = new User();
        user.setId(student.getId());
        result = userDao.delete(user);
        if (result == 0) {
            return result;
        }
        //删除主记录
        try {
            PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, student.getId());
            result = preparedStatement.executeUpdate();
            DbUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(List<Student> list) {
        return delete(list, DaoFactory.getUserDaoImpl());
    }

    @Override
    public int delete(List<Student> list, UserDao userDao) {
        int result = 0;
        for (Student student :
                list) {
            result += delete(student, userDao);
        }
        return result;
    }

    @Override
    public int update(Student student) {
        return update(student, DaoFactory.getUserDaoImpl());
    }

    @Override
    public int update(Student student, UserDao userDao) {
        int id = student.getId();//暂存ID，屏蔽find中按id查询（find优先按id查找）
        student.setId(0);
        if (find(student).getId() != 0) {//要修改的信息已经存在，则不用进行修改
            return 0;
        }
        student.setId(id);
        String sql = "update userdb.student set name=?,gender=?,major=?,department=? where id=?";
        int result = 0;
        try {
            PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
            prepare(preparedStatement, student);
            preparedStatement.setInt(5, student.getId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result == 0) {
            return result;
        }
        User user = new User();
        user.setId(student.getId());
        user.setUsername(student.getName());
        result = userDao.update(user);
        return result;
    }

    @Override
    public Page find(Page page) {
        Student student = page.getStudent();
        String sql = "select * from userdb.student where name like ? and gender like ? and major like ? and department like ? limit ?,?";
        if (student.getName() == null) {
            student.setName("");
        }
        if (student.getGender() == null) {
            student.setGender("");
        }
        if (student.getMajor() == null) {
            student.setMajor("");
        }
        if (student.getDepartment() == null) {
            student.setDepartment("");
        }
        int recordNum = 0;//总记录数
        int recPerPage = page.getRecPerPage();//每页记录数
        if (recPerPage < 1)//保证数据合法
            recPerPage = 1;
        int pageNum = 0;//总页数
        int currentPage = page.getCurrentPage();//查询页码
        List<Student> list = new LinkedList<>();
        try {
            //首先根据查询条件计算总记录数和总页数
            PreparedStatement preparedStatement0 = DbUtil.getConnection()
                    .prepareStatement("select count(*) from userdb.student where name like ? and gender like ? and major like ? and department like ?");
            prepare(preparedStatement0, student, "%");
            ResultSet resultSet = preparedStatement0.executeQuery();
            resultSet.next();
            recordNum = resultSet.getInt("count(*)");
            if (recordNum % recPerPage == 0) {//计算总页数
                pageNum = recordNum / recPerPage;
            } else {
                pageNum = recordNum / recPerPage + 1;
            }

            if (currentPage > pageNum) {//保证数据合法
                currentPage = pageNum;
            } else if (currentPage < 1) {
                currentPage = 1;
            }

            DbUtil.close();
            //实施查询
            PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
            prepare(preparedStatement, student, "%");
            preparedStatement.setInt(5, currentPage * recPerPage - recPerPage);
            preparedStatement.setInt(6, recPerPage);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student1 = new Student();
                studentSetRes(student1,resultSet);
                list.add(student1);
            }
            DbUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        page.setRecordNum(recordNum);
        page.setRecPerPage(recPerPage);
        page.setPageNum(pageNum);
        page.setCurrentPage(currentPage);
        page.setStudentList(list);
        return page;
    }

    @Override
    public Student find(Student student) {
        String sql;
        Student result = new Student();
        if (student.getId() != 0) {
            sql = "select * from userdb.student where id=?";
        } else {
            sql = "select * from userdb.student where name=?and gender=?and major=?and department=?";
        }
        try {
            PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
            if (student.getId() != 0) {
                preparedStatement.setInt(1, student.getId());
            } else {
                prepare(preparedStatement, student);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                studentSetRes(result,resultSet);
            } else {
                System.out.println("none detail!  -----StudentDaoImpl.java  servlet 213 line");
            }
            DbUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //大量重复代码
    private void prepare(PreparedStatement preparedStatement, Student student) throws SQLException {
        prepare(preparedStatement, student, "");
    }

    private void prepare(PreparedStatement preparedStatement, Student student, String s) throws SQLException {
        preparedStatement.setString(1, s + student.getName() + s);
        preparedStatement.setString(2, s + student.getGender() + s);
        preparedStatement.setString(3, s + student.getMajor() + s);
        preparedStatement.setString(4, s + student.getDepartment() + s);
    }
    private void studentSetRes(Student student,ResultSet resultSet)throws SQLException{
        student.setId(resultSet.getInt("id"));
        student.setName(resultSet.getString("name"));
        student.setGender(resultSet.getString("gender"));
        student.setMajor(resultSet.getString("major"));
        student.setDepartment(resultSet.getString("department"));
    }
}