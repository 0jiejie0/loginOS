package test.user;

import util.DbUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Find {
    private static ArrayList sqlList = new ArrayList();
    private static ArrayList keywordList = new ArrayList();
    private static boolean canExecute = false;
    private static Find finder = new Find();

    private Find() {
    }

    /**
     * 设定查询的位置
     *
     * @param db_table (database.)table
     * @return Find 自身的实例
     * @throws SQLException
     */
    public static Find from(String db_table) throws SQLException {
        if (db_table == null || db_table.equals("") || db_table.contains(" ")) {
            throw new SQLException("select table error!");
        }
        sqlList.add("from " + db_table);
        canExecute = true;
        return finder;
    }

    /**
     * 根据参数加入查询字段
     *
     * @param args 第一个不为空的是为该位字段的关键词
     * @return Find 自身的实例
     */
    private static Find where(String... args) throws SQLException {
        testExecute();
        sqlList.add(sqlList.size() == 1 ? "where" : "and");
        int i = 0;
        String sql = null;
        for (i = 0; i < args.length; i++) {
            if (args != null) {
                break;
            }
        }
        if (i == 0) {
            sql = "department";
        } else if (i == 1) {
            sql = "gender";
        } else if (i == 2) {
            sql = "major";
        } else if (i == 3) {
            sql = "department";
        }
        sqlList.add(sql + " " + args[i]);
        return finder;
    }

    //姓名
    public static Find nameIs(String name) throws SQLException {
        return name(name, "=");
    }

    public static Find nameLike(String name) throws SQLException {
        return name("%" + name + "%", "like");
    }

    //姓名与匹配条件（“=”/“like”）
    private static Find name(String name, String match) throws SQLException {
        String sql = match + " ?";
        keywordList.add(name);
        return where(sql);
    }

    //性别
    public static Find gender(String gender) throws SQLException {
        keywordList.add(gender);
        String sql = "= ?";
        return where(null, sql);
    }

    //专业
    public static Find majorIs(String major) throws SQLException {
        return major(major, "=");
    }

    public static Find majorLike(String major) throws SQLException {
        return major("%" + major + "%", "like");
    }

    //专业与匹配条件（“=”/“like”）
    private static Find major(String major, String match) throws SQLException {
        String sql = match + " ?";
        keywordList.add(major);
        return where(null, null, sql);
    }

    //学院
    public static Find departmentIs(String department) throws SQLException {
        return department(department, "=");
    }

    public static Find departmentLike(String department) throws SQLException {
        return department("%" + department + "%", "like");
    }

    //系名与匹配条件（“=”/“like”）
    private static Find department(String department, String match) throws SQLException {
        String sql = match + " ?";
        keywordList.add(department);
        return where(null, null, null, sql);
    }

    /**
     * 增
     *
     * @return
     */
    public static int insert() {
        return 0;
    }

    /**
     * 删
     *
     * @return
     */
    public static int delete() throws SQLException {
        testExecute();
        StringBuilder sql = new StringBuilder("delete ");
        for (int i = 0; i < sqlList.size(); i++) {
            sql.append(sqlList.get(i) + " ");
        }
        int lines = DbUtil.executeUpdate(sql.toString(), keywordList.toArray());
        resetList();
        return lines;
    }

    /**
     * 改
     *
     * @return
     */
    public static int update() {
        return 0;
    }

    /**
     * 查
     *
     * @return
     * @throws SQLException
     */
    public static ResultSet select() throws SQLException {
        return select("*");
    }

    public static ResultSet select(String fields) throws SQLException {
        testExecute();
        StringBuilder sql = new StringBuilder("select " + fields + " ");
        for (int i = 0; i < sqlList.size(); i++) {
            sql.append(sqlList.get(i) + " ");
        }
        ResultSet resultSet = DbUtil.executeQuery(sql.toString(), keywordList.toArray());
        resetList();
        return resultSet;
    }

    //测试能否执行sql语句，不能执行则清空语句和设定的关键词
    private static void testExecute() throws SQLException {
        if (!canExecute) {
            resetList();
            from(null);
        }
    }

    //重置语句和关键词
    private static void resetList() {
        sqlList = new ArrayList();
        keywordList = new ArrayList();
    }
}
