import javax.swing.*;
import java.awt.*;
import java.sql.Statement;
import java.util.Objects;

public class GuiForm extends JFrame {
    private JPanel MainPanel;
    private JComboBox<String[]> forDisplayCombo;
    private JComboBox<String[]> forRecordCombo;
    private JButton OKButton;
    private JButton showButton;
    private JTextField wageField;
    private JTextField commissionField;
    private JTextField grossField;
    private JTextField fnameField;
    private JTextField lnameField;
    private JTextField salaryField;
    private JTextField ssnField;
    private JTextField hoursField;
    private JTextField baseplusField;
    private JButton submitButton;
    private JLabel infoLabel;
    private JPanel display;
    public GuiForm() {

        setContentPane(MainPanel);
        setTitle("Employee");
        setSize(1400, 777);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        disable all textFields and the submit button
//        if nothing is selected in the comboBox

        disable(grossField);
        disable(commissionField);
        disable(hoursField);
        disable(baseplusField);
        disable(wageField);
        disable(salaryField);
        disable(fnameField);
        disable(lnameField);
        disable(ssnField);
        disable(submitButton);

        Statement statement = DataBaseConnection.getStatement();

        OKButton.addActionListener(e -> disabler());

        showButton.addActionListener(e -> {
            String selected = Objects.requireNonNull(forDisplayCombo.getSelectedItem()).toString();
            if(Objects.equals(selected, "Salary")){
                infoLabel.setText(DataBaseConnection.displaySalary(statement));
            }else if(Objects.equals(selected,"Commission")){
                infoLabel.setText(DataBaseConnection.displayCommission(statement));
            }else if(Objects.equals(selected,"Hourly")){
                infoLabel.setText(DataBaseConnection.displayHourly(statement));
            }
        });

        submitButton.addActionListener(e -> {

            var choice = Objects.requireNonNull(
                    forRecordCombo.getSelectedItem()).toString();
            if (Objects.equals(choice, "Salary")) {

                new SalaryEmployee(
                        fnameField.getText(),
                        lnameField.getText(),
                        Integer.parseInt(ssnField.getText())
                ).addToDatabase(statement);
            } else if (Objects.equals(choice, "Hourly")) {
                var hourEmp = new Hourlyemployee(fnameField.getText(),
                        lnameField.getText(), 1);
                hourEmp.setHours(Integer.parseInt(hoursField.getText()));
                hourEmp.setWage(Integer.parseInt(wageField.getText()));
                hourEmp.addToDatabase(statement);
            } else if (Objects.equals(choice, "Commission")) {
                new CommissionEmp(
                        fnameField.getText(),
                        lnameField.getText(),
                        Integer.parseInt(ssnField.getText()),
                         Double.parseDouble(commissionField.getText()),
                        Double.parseDouble(grossField.getText())
                ).addToDatabase(statement);

            } else if (Objects.equals(choice, "BasePlus Commission")) {
                var baseEmp = new BasePlusCommissionEmployee(
                        fnameField.getText(),
                        lnameField.getText(),
                        Integer.parseInt(ssnField.getText()),
                        Double.parseDouble(grossField.getText()),
                        Double.parseDouble(commissionField.getText()));
                baseEmp.setBaseSalary(Double.parseDouble(baseplusField.getText()));
                baseEmp.addToDatabase(statement);
            }

        });
    }
    private void disable(Component component) {
        component.setEnabled(false);
    }
    private void enable(Component component) {
        component.setEnabled(true);
    }
    private void disabler() {
        String choice = Objects.requireNonNull(forRecordCombo.getSelectedItem()).toString();
        System.out.println(choice);
        if (Objects.equals(choice, "Select Employee")) {
            disable(grossField);
            disable(commissionField);
            disable(hoursField);
            disable(baseplusField);
            disable(wageField);
            disable(salaryField);
            disable(fnameField);
            disable(lnameField);
            disable(ssnField);
        } else {
            enable(submitButton);
            enable(ssnField);
            enable(fnameField);
            enable(lnameField);

            if (Objects.equals(choice, "Salary")) {
                disable(grossField);
                disable(commissionField);
                disable(hoursField);
                disable(wageField);
            } else if (Objects.equals(choice, "Hourly")) {
                disable(grossField);
                disable(commissionField);
                disable(baseplusField);
                disable(salaryField);
                enable(hoursField);
                enable(wageField);
            } else if (Objects.equals(choice, "Commission")) {
                enable(grossField);
                enable(commissionField);
                disable(baseplusField);
                disable(salaryField);
                disable(hoursField);
                disable(wageField);
            } else if (Objects.equals(choice, "BasePlus Commission")) {
                enable(grossField);
                enable(commissionField);
                enable(baseplusField);
                disable(salaryField);
                disable(hoursField);
                disable(wageField);
            }
        }
    }
}
