import java.sql.*;

public class DataBaseConnection {

    public static Statement getStatement() {
        Statement statement;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/LabClass2";
            Connection connection = DriverManager.getConnection(url, "root", "");
            statement = connection.createStatement();
//            System.out.println("success");
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException();
        }
        return statement;
    }

    public static String displaySalary(Statement stat) {
        String value = null;
        try {
            ResultSet set = stat.executeQuery(
                    "SELECT  * FROM SalaryEmp");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(
                    "<html> " +
                            "<table>" +
                            "<thead> <th>" + "FName" + "</th>" + "<th>" + "LName" + "</th>" +
                            "<th>" + "SSN" + "</th>" + "<th>" + "WeeklySalary" + "</th>" +
                            "<th>" + "Earnings" + "</th>" + "</thead>" +
                            "<tbody>");
            while (set.next()) {
                stringBuilder.append("<tr><td>")
                        .append(set.getString(1))
                        .append("</td><td>")
                        .append(set.getString(2))
                        .append("</td><td>")
                        .append(set.getInt(3))
                        .append("</td><td>")
                        .append(set.getFloat(4))
                        .append("</td><td>")
                        .append(set.getFloat(5))
                        .append("</td></tr>");
            }
            stringBuilder.append("<tbody>" +
                    "</html>");

            value = stringBuilder.toString();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    public static String displayCommission(Statement stat) {
        String value = null;
        try {
            ResultSet set = stat.executeQuery(
                    "SELECT  * FROM CommissionEmp");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(
                    "<html> " +
                            "<table>" +
                            "<thead> <th>" + "FName" + "</th>" + "<th>" + "LName" + "</th>" +
                            "<th>" + "SSN" + "</th>" + "<th>" + "Gross Rate" + "</th>" +"<th>Commission Rate</th>" +
                            "<th>" + "Earnings" + "</th>"  + "<th>" + "IsBasePlus" + "</th>" + "</thead>" +
                            "<tbody>");
            while (set.next()) {
                stringBuilder.append("<tr><td>")
                        .append(set.getString(1))
                        .append("</td>").append("<td>")
                        .append(set.getString(2))
                        .append("</td>").append("<td>")
                        .append(set.getInt(3))
                        .append("</td>").append("<td>")
                        .append(set.getFloat(4))
                        .append("</td>").append("<td>")
                        .append(set.getFloat(5))
                        .append("</td>").append("<td>")
                        .append(set.getFloat(6))
                        .append("</td>").append("<td>")
                        .append(set.getBoolean(7))
                        .append("</td></tr>");
                System.out.println("wow");
            }
            stringBuilder.append("<tbody>" +
                    "</html>");

//            System.out.println(value);
            value = stringBuilder.toString();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    public static String displayHourly(Statement stat) {
        String value = null;
        try {
            ResultSet set = stat.executeQuery(
                    "SELECT  * FROM HourlyEmployee");
            StringBuilder builder = new StringBuilder();
            builder.append(
                    "<html> " +
                            "<table>" +
                            "<thead><th>" + "FName" + "</th>" +
                            "<th>" + "LName" + "</th>" +
                            "<th>" + "SSN" + "</th>" +
                            "<th>" + "Wage" + "</th>" +
                            "<th>" + "Earnings" + "</th>" +
                            "</thead>" + "<tbody>");
            while (set.next()) {
                builder.append("<tr><td>")
                        .append(set.getString(1))
                        .append("</td>").append("<td>")
                        .append(set.getString(2))
                        .append("</td>").append("<td>")
                        .append(set.getInt(3))
                        .append("</td>").append("<td>")
                        .append(set.getFloat(4))
                        .append("</td>").append("<td>")
                        .append(set.getFloat(5))
                        .append("</td></tr>");
                System.out.println("wow");
            }
            builder.append("<tbody>" +
                    "</html>");

            System.out.println(value);

            value = builder.toString();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return value;
    }

}
