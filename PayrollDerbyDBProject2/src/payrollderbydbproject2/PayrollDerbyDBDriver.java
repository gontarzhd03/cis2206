package payrollderbydbproject2;

public class PayrollDerbyDBDriver {

    public static void main(String[] args) {
        retrieveAllFields();
        System.out.println("------------------------------------------------------------");
        insertNewRecord();
        retrieveRecord(false);
        modifyRecords();
        deleteRecord();
        retrieveAllFields();
    }
    
    static void retrieveAllFields() {
        Employees employees = PayrollDerbyDBHelper.retrieveAll();
        for(int i = 0; i < employees.size(); i++) {
            System.out.printf("%d\t%-20s\t%d\t$%,7d%n",
                    employees.get(i).getEmployeeNumber(),
                    employees.get(i).getEmployeeName().trim(),
                    employees.get(i).getTerritoryNumber(),
                    employees.get(i).getAnnualSalary());
        }
    }
    static void insertNewRecord() {
        Employee employee = new Employee();
        employee.setEmployeeNumber(29);
        employee.setEmployeeName("ROBINSON");
        employee.setTerritoryNumber(5);
        employee.setAnnualSalary(81000);
        PayrollDerbyDBHelper.insert(employee);
    }
    static void retrieveRecord(boolean display) {
        Employee employee = PayrollDerbyDBHelper.retrieve(11);
        if(display) {
            System.out.printf("%d\t%-20s\t%d\t$%,7d%n",
                       employee.getEmployeeNumber(),
                       employee.getEmployeeName(),
                       employee.getTerritoryNumber(),
                       employee.getAnnualSalary());
        }
    }
    static void modifyRecords() {
        PayrollDerbyDBHelper.modifyTerritoryNumber(17, 14);
        PayrollDerbyDBHelper.modifyAnnualSalary(76000, 5);
        PayrollDerbyDBHelper.modifyName("GREENSTREET", 7);
    }
    static void deleteRecord() {
        PayrollDerbyDBHelper.delete(11);
    }
}
