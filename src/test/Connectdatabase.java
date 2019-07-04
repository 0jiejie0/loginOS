package test;

import util.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connectdatabase {


    public static void main(String[] args) {
        h();
    }
    private static void h(){
        int num=2147483647;
        num+=2L;
        System.out.println(num);
    }
    private static void f(){
        try {
            String username="xkm";
            ResultSet resultSet;
            String sql = "update student set gender = ?, major = ?, department = ? where name = ? ";
//            resultSet=DbUtil.executeQuery(sqlquery,"name");
            DbUtil.executeUpdate(sql,"女","教育技术","传媒学","王小天");
            System.out.println(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private static void g(){
        try {
            Connection connection= DbUtil.getConnection();
            if (connection!=null){
                System.out.println("connect success");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private static void h(Object... param){
        for (int i = 0; i < param.length; i++) {
            System.out.println(param[i]);
        }
    }
}
