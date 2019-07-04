package bean;

import java.util.List;

public class Page {
    private int recordNum;//总记录数
    private int recPerPage;//每页显示记录数
    private int pageNum;//总页数
    private int currentPage;//当前页码
    private Student student;
    private List<Student> studentList;//记录

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setRecordNum(int recordNum) {
        this.recordNum = recordNum;
    }

    public void setRecPerPage(int recPerPage) {
        this.recPerPage = recPerPage;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public int getRecordNum() {
        return recordNum;
    }

    public int getRecPerPage() {
        return recPerPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public List<Student> getStudentList() {
        return studentList;
    }
}