package daoImpl;

public class DaoFactory {
    public static StudentDaoImpl getStudentDaoImpl(){
        return new StudentDaoImpl();
    }
    public static UserDaoImpl getUserDaoImpl(){
        return new UserDaoImpl();
    }
}
