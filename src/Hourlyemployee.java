import java.sql.SQLException;
import java.sql.Statement;

public class Hourlyemployee extends Employee {
    private int hours = 20;
    private double wage = 35;

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    private final String name;
    private final String lname;
    int ssn;

    public Hourlyemployee(String fname, String lname, int ssn) {
        super(fname, lname, ssn);
        this.ssn = ssn;
        this.name = fname;
        this.lname = lname;
    }

    @Override
    public double earnings() {
        if (this.getHours() <= 40) {
            return this.getWage() * this.getHours();
        } else {
            return this.getWage() * 40 + (this.getHours() - 40) * (wage * 1.5);
        }
    }

    @Override
    public void addToDatabase(Statement statement) {
        try {
            statement.executeUpdate("INSERT INTO HourlyEmployee " +
                    "VALUES ('" + this.name + "','" + this.lname
                    + "','" + this.ssn + "','" + this.wage
                    + "', '" + this.earnings() + "')");
            System.out.println("executed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
