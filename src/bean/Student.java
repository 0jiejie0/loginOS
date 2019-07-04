package bean;

public class Student {
    private int id;
    private String name;
    private String gender;
    private String major;
    private String department;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = ((name == null) ? null : (name.trim()));
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getMajor() {
        return major;
    }

    public String getDepartment() {
        return department;
    }
}
