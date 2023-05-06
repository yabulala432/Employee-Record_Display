import java.sql.Statement;

public abstract class Employee{

    private String firstName, lastName;
    private int SSN;

    public Employee(String fname, String lname, int ssn) {
        this.firstName = fname;
        this.lastName = lname;
        this.SSN = ssn;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSSN() {
        return SSN;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }

    public abstract double earnings();

    public abstract void addToDatabase(Statement statement);

    @Override
    public String toString() {
        return getClass().getName();
    }


}
