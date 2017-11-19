/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package createpayrollderbydatabase;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 55gontarhd03
 */
public class CreatePayrollDerbyDatabase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String dbProtocol = "jdbc:derby:";
//        String dbDirectory = "./data/PayrollDerbyDatabase/";
        String dbDirectory = "/home/hgontarz/.netbeans-derby/";
        String dbName = "PayrollMaster";
        String connectionCreateURL = dbProtocol + dbDirectory + dbName + ";create=true";
        String shutDownURL = dbProtocol + ";shutdown = true";
        String createStatement = "CREATE TABLE Payroll "
                                + "(EmployeeNumber INTEGER PRIMARY KEY, "
                                + "Name VARCHAR(20), "
                                + "TerritoryNumber INTEGER, "
                                + "AnnualSalary INTEGER)";
        try {
            Connection connection1 = DriverManager.getConnection(connectionCreateURL, "Admin", "MuCis");
            Statement statement1 = connection1.createStatement();
            statement1.execute(createStatement);
            DriverManager.getConnection(shutDownURL);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
//            Logger.getLogger(CreatePayrollDerbyDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
