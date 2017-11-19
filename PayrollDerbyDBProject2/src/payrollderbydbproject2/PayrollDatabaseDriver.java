package payrollderbydbproject2;

public class PayrollDatabaseDriver {

    public static void main(String[] args) {
        int    insertEmployeeNumber1 = 29;
        String insertEmployeeName1 = "ROBINSON";
        int    insertTerritoryNumber1 = 5;
        int    insertAnnualSalary1 = 81000;
        int    retrieveRecord1 = 11;
        int    modifyEmployeeNumber1 = 14;
        int    modifyTerritoryNumber1 = 17;
        int    modifyEmployeeNumber2 = 5;
        int    modifyAnnualSalary2 = 76000;
        int    modifyEmployeeNumber3 = 7;
        String modifyEmployeeName3 = "GREENSTREET";
        int    deleteEmployeeNumber1 = 11;
        
        retrieveAllFields();
        System.out.println("------------------------------------------------------------");
        insertNewRecord(insertEmployeeNumber1, insertEmployeeName1, insertTerritoryNumber1, insertAnnualSalary1);
        retrieveRecord(false, retrieveRecord1);
        DatabaseOperations.modifyTerritoryNumber(modifyTerritoryNumber1, modifyEmployeeNumber1);
        DatabaseOperations.modifyAnnualSalary(modifyAnnualSalary2, modifyEmployeeNumber2);
        DatabaseOperations.modifyName(modifyEmployeeName3, modifyEmployeeNumber3);
        DatabaseOperations.delete(deleteEmployeeNumber1);
        retrieveAllFields();
    }
    /**
     * 
     */
    static void retrieveAllFields() {
        Employees employees = DatabaseOperations.retrieveAll();
        for(int i = 0; i < employees.size(); i++) {
            System.out.printf("%d\t%-20s\t%d\t$%,7d%n",
                    employees.get(i).getEmployeeNumber(),
                    employees.get(i).getEmployeeName().trim(),
                    employees.get(i).getTerritoryNumber(),
                    employees.get(i).getAnnualSalary());
        }
    }
    /**
     * 
     * @param insertEmployeeNumber
     * @param insertEmployeeName
     * @param insertTerritoryNumber
     * @param insertAnnualSalary 
     */
    static void insertNewRecord(int insertEmployeeNumber, String insertEmployeeName,
        int insertTerritoryNumber, int insertAnnualSalary) {
        
        Employee employee = new Employee();
        employee.setEmployeeNumber(insertEmployeeNumber);
        employee.setEmployeeName(insertEmployeeName);
        employee.setTerritoryNumber(insertTerritoryNumber);
        employee.setAnnualSalary(insertAnnualSalary);
        DatabaseOperations.insert(employee);
    }
    /**
     * 
     * @param display
     * @param employeeNumber 
     */
    static void retrieveRecord(boolean display, int employeeNumber) {
        Employee employee = DatabaseOperations.retrieve(11);
        if(display) {
            System.out.printf("%d\t%-20s\t%d\t$%,7d%n",
                       employee.getEmployeeNumber(),
                       employee.getEmployeeName(),
                       employee.getTerritoryNumber(),
                       employee.getAnnualSalary());
        }
    }
}
