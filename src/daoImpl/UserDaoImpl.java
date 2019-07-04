package daoImpl;

import bean.User;
import dao.UserDao;
import util.DbUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private User user;

    @Override
    public User getInfo() {
        return user;
    }

    @Override
    public User getInfo(User user) {
        String sql = "select * from userdb.tb_users where fd_username=? and fd_password=?";
        User res = null;
        try {
            PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                res = new User();
                res.setUsername(resultSet.getString("fd_username"));
                res.setUsertype(resultSet.getString("fd_usertype"));
                res.setId(resultSet.getInt("fk_id"));
            }
            DbUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.user=res;
        return res;
    }

    @Override
    public boolean login(User user) {
        return getInfo(user) != null;
    }

    @Override
    public int save(User user) {
        String sql = "insert into userdb.tb_users(fd_username, fd_password, fd_usertype, fk_id) values (?,?,?,?)";
        int res = 0;
        try {
            PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getUsertype());
            preparedStatement.setInt(4, user.getId());
            res = preparedStatement.executeUpdate();
            DbUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int delete(User user) {
        String sql = "";
        int res = 0;
        if (user.getId() != 0) {
            sql = "delete from userdb.tb_users where fk_id=?";
        } else {
            sql = "delete from userdb.tb_users where fd_username=? and fd_password=? and fd_usertype=?";
        }
        try {
            PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
            if (user.getId() != 0) {
                preparedStatement.setInt(1, user.getId());
            } else {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getUsertype());
            }
            res = preparedStatement.executeUpdate();
            DbUtil.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int update(User user) {
        String sql = "update userdb.tb_users set fd_username=? where fk_id=?";
        int result = 0;
        try {
            PreparedStatement preparedStatement = DbUtil.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setInt(2, user.getId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
