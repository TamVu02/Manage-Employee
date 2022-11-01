package p0101;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Display {

    void displayMenu() {
        System.out.print("--- Main menu ---\n"
                + "1. Add employees\n"
                + "2. Update employees\n"
                + "3. Remove employees\n"
                + "4. Search employees\n"
                + "5. Sort employees by salary\n"
                + "6. Exit\n"
                + "Your choice: ");
    }

    void displayAttribute() {
        System.out.print("--- Employee's Attribute ---\n"
                + "1. ID\n"
                + "2. First Name\n"
                + "3. Last Name\n"
                + "4. Phone number\n"
                + "5. Email\n"
                + "6. Address\n"
                + "7. Date of birth\n"
                + "8. Sex\n"
                + "9. Salary\n"
                + "10. Agency\n");
    }

    void displayEmpList(ArrayList<Employee> empList) {
        if (empList.isEmpty()) {
            System.out.println("Employee List is empty\n");
        } else {
            System.out.println("Employee List: ");
            System.out.format("%9s|%12s|%12s|%16s|%25s|%20s|%12s|%8s|%7s|%15s\n", "ID", "First Name", "Last Name", "Phone Number",
                    "Email", "Address", "DOB", "Sex", "Salary", "Agency");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            //loop to acess to each employee in the arraylist
            for (Employee emp : empList) {
                displayElement(emp);
            }
            System.out.println("");
        }
    }

    void displayElement(Employee emp) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        System.out.format("%9s|%12s|%12s|%16s|%25s|%20s|%12s|%8s|%7d|%15s\n", emp.getId(), emp.getFirstName(),
                emp.getLastName(), emp.getPhone(), emp.getEmail(), emp.getAddress(),
                formatter.format(emp.getDob()), emp.getSex(), emp.getSalary(), emp.getAgency());
    }

}
