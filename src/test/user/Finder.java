package test.user;


import java.sql.ResultSet;

public interface Finder {
    static Finder from(String Db_table) {
        return null;
    }

    static Finder where(String... args) {
        return null;
    }

    static ResultSet select() {
        return null;
    }

    public static int insert() {
        return 0;
    }

    public static int delete() {
        return 0;
    }

    static int update() {
        return 0;
    }

}
