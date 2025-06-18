package uni.sexto.poo2.proyecto.tercer_parcial.db_classes;

public class Department {
    private String dept_no;
    private String dept_name;
    private int emp_no;

    public Department(String dept_no, String dept_name) {
        this.dept_no = dept_no;
        this.dept_name = dept_name;
    }

    public Department(String dept_no, String dept_name, int emp_no) {
        this.dept_no = dept_no;
        this.dept_name = dept_name;
        this.emp_no = emp_no;
    }


    public String getDept_no() {
        return dept_no;
    }

    public void setDept_no(String dept_no) {
        this.dept_no = dept_no;
    }


    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public int getEmp_no() {
        return emp_no;
    }
    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }
}
