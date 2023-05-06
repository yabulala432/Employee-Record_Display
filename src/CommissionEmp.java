import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class CommissionEmp extends Employee {
    private double commissionRate, grossRate;

    public boolean isBasePlus = false;

    public double getCommissionRate() {
        return commissionRate;
    }

    public double getGrossSales() {
        return grossRate;
    }

    String fname, lname;
    int ssn;

    public CommissionEmp(String fname, String lname, int ssn,
                         double commissionRate, double grossRate) {
        super(fname, lname, ssn);
        this.fname = fname;
        this.lname = lname;
        this.ssn = ssn;
        this.commissionRate = commissionRate;
        this.grossRate = grossRate;
    }

    @Override
    public double earnings() {
        return this.getGrossSales() * this.getCommissionRate();
    }

    @Override
    public void addToDatabase(Statement statement) {
        String basePlus = (isBasePlus)?  "1":"0";
        try {
            statement.executeUpdate("INSERT INTO CommissionEmp " +
                    "VALUES ('" + this.fname + "','" + this.lname
                    + "','" + this.ssn + "','" + this.grossRate
                    + "', '" + this.commissionRate + "' ,'" +
                    this.earnings() + "','" + basePlus + "')");

            System.out.println("executed");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("duplicate id not allowed. Id must be unique");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public String display(Statement statement) {
        try{
            ResultSet set = statement.executeQuery(
                    "SELECT  * FROM CommissionEmp");
            while (set.next()) {
                System.out.println(set.getString(1));
                System.out.println(set.getString(2));
                System.out.println(set.getInt(3));
                System.out.println(set.getFloat(4));
                System.out.println(set.getFloat(5));
                System.out.println(set.getFloat(6));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

return null;
    }

    @Override
    public String toString() {
        return getClass().getName();
    }

}
