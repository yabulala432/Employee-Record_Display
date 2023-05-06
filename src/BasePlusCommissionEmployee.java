public class BasePlusCommissionEmployee extends CommissionEmp {
    private double baseSalary;

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }


    public BasePlusCommissionEmployee(String fname, String lname, int ssn,
                                      double grossSales, double comissionRate) {
        super(fname, lname, ssn, grossSales, comissionRate);
        this.isBasePlus = true;
    }

    @Override
    public String toString() {
        return getClass().getName();
    }

    @Override
    public double earnings() {
        return this.getCommissionRate()
                * this.getGrossSales()
                + this.getBaseSalary();
    }
}
