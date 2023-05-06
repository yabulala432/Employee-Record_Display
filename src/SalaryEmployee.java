import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class SalaryEmployee extends Employee {
    private final String fname;
    private final String lname;
    private final int ssn;
    private float salary = 3_000;

    public SalaryEmployee(String first, String last, int ssn) {
        super(first, last, ssn);
        this.fname = first;
        this.lname = last;
        this.ssn = ssn;
    }

    @Override
    public double earnings() {
        return this.salary;
    }

    @Override
    public void addToDatabase(Statement statement) {
        try {
            statement.executeUpdate("INSERT INTO SalaryEmp " +
                    "VALUES ('" + this.fname + "','" + this.lname + "','" +
                    this.ssn + "','" + this.weeklySalary() + "',' " + this.earnings() + "')");
            System.out.println("executed");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("duplicate id not allowed." +
                    " Id must be unique");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public String toString() {
        return this.getClass().getName();
    }

    private void setSalary(float salary) {
        this.salary = salary;
    }

    private float getSalary() {
        return this.salary;
    }

    private float weeklySalary() {
        return this.salary / 4;
    }

}
