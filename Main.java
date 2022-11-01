package p0101;

import java.text.ParseException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws ParseException {
        ManageEmployee employeeManage = new ManageEmployee();
        ArrayList<Employee> empList = new ArrayList<Employee>();
        while (true) {
            //display menu
            Display display = new Display();
            display.displayMenu();
            //user select an option
            GetInput option = new GetInput();
            int choice = option.getInteger("Choice", 1, 6);
            switch (choice) {
                case 1: //Add employee
                    employeeManage.addEmployee(empList);
                    break;
                case 2: //Update employee
                    employeeManage.updateEmployee(empList);
                    break;
                case 3: //Remove employee
                    employeeManage.removeEmployee(empList);
                    break;
                case 4: //Search employee by name
                    employeeManage.searchEmployeeByName(empList);
                    break;
                case 5: //Sort employee by salary
                    employeeManage.sortEmployeeBySalary(empList);
                    break;
                case 6: //Exit
                    System.exit(0);
                    break;
            }
        }
    }

}
