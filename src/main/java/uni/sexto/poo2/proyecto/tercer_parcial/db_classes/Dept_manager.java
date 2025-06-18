package uni.sexto.poo2.proyecto.tercer_parcial.db_classes;

public class Dept_manager {
    private int emp_no;
    private String dept_name;
    private int from_date;
    private int to_date;

    public Dept_manager(int emp_no, String dept_name, int from_date, int to_date) {
        this.emp_no = emp_no;
        this.dept_name = dept_name;
        this.from_date = from_date;
        this.to_date = to_date;
    }

    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public int getFrom_date() {
        return from_date;
    }

    public void setFrom_date(int from_date) {
        this.from_date = from_date;
    }

    public int getTo_date() {
        return to_date;
    }

    public void setTo_date(int to_date) {
        this.to_date = to_date;
    }
}
