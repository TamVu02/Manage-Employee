package p0101;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class ManageEmployee {

    Scanner sc = new Scanner(System.in);
    Display display = new Display();
    GetInput getData = new GetInput();

    void addEmployee(ArrayList<Employee> empList) throws ParseException {
        System.out.println("--- Add employees ---");
        //enter employee'id
        String id;
        System.out.print("Enter employee's ID (less then 8 characters): ");
        do {
            // 0-9: contains number from 0 to 9
            // a-z: contains lower case character from a to z
            // A-Z: contains upper case character from A to Z
            // {1,8}: one to eight digits
            //check duplicate id
            id = getData.getString("ID", "[0-9A-Z]{1,8}");
            //check input id exists
        } while (checkIdExist(id, empList));

        //enter employee's first name
        System.out.print("Enter employee's first name: ");
        // ^[A-Z]: start with Uppercase character from A to Z
        // [a-z]+: continue with ultimate lower case character from a-z
        String fname = getData.getString("First Name", "^[A-Z][a-z]+");

        //enter employee's last name
        System.out.print("Enter employee's last name: ");
        // ^[A-Z]: start with Uppercase character from A to Z
        // [a-z]+: continue with ultimate lower case character from a-z
        String lname = getData.getString("Last Name", "^[A-Z][a-z]+");

        //enter employee's phone number
        System.out.print("Enter employee's phone number: ");
        // ^\\{0,1}: can start with '+' or not 1 time
        // [0-9]{7,15}: at least 7 to maximum 15 numerical digits
        String phone = getData.getString("Phone Number", "^\\+{0,1}[0-9]{7,15}");

        //enter employee's email
        System.out.print("Enter employee's email: ");
        // [A-Za-z0-9!#$%&'\\*\\+\\-\\/=\\?\\^_`\\{|\\}~]+: 1 or more character from A-Z, a-z, 0-9, !#$%&'*+-/=?^_`{|}~
        // @: follow by '@' character
        // \\[{0,1}: contain '[' character 0 or 1 time (optional) (in case email has format : abc123@[123.34.56])
        // [A-Za-z0-9\\.:\\-]+:  contains 1 or more from A-Z, a-z, 0-9, '.', ':', '-'
        String email = getData.getString("Email", "[A-Za-z0-9!#$%&'\\*\\+\\-\\/=\\?\\^_`\\{|\\}~]+@\\[{0,1}[A-Za-z0-9\\.:\\-]+\\]{0,1}");

        //enter employee's adress
        System.out.print("Enter employee's address: ");
        // [A-Z]{1}: 1 upper case charcter
        // [a-z]*: 0 or more time lower case character
        // |: or
        // [0-9]+: 1 or more numercial digit
        String address = getData.getString("Address", "(([A-Z]{1}[a-z]*|[0-9]+|[a-z]+) *)+");

        //enter employee's DOB
        System.out.print("Enter employee's date of birth (dd/MM/yyyy): ");
        Date dob = getData.getDate("Date of birth");

        //enter employee's sex
        System.out.print("Enter employee's sex (female/male/other): ");
        // Male|male: male or Male
        // Female|female: female or Female
        // Other|other: other or Other
        // |: or
        String sex = getData.getString("Sex", "(Male|male)|(Female|female)|(Other|other)");

        //enter employee's salary
        System.out.print("Enter employee's salary: ");
        int salary = getData.getInteger("Salary", 0, Integer.MAX_VALUE);

        //enter employee's agency
        System.out.print("Enter employee's agency: ");
        // [A-Z]{1}: 1 upper case character
        // [A-Za-z]+: 1 or more character from A to Z, a to z
        //  +: 1 or more space
        // (\\ ){0,1}: 0 or 1 space
        // *: 0 or more times
        String agency = getData.getString("Agency", "[A-Z]{1}([A-Za-z]+(\\ ){0,1})*");

        //add employee to the array list
        empList.add(new Employee(id, fname, lname, phone, email, address, dob, sex, salary, agency));
        //display employee list
        display.displayEmpList(empList);
    }

    void updateEmployee(ArrayList<Employee> empList) {
        //check if employee list is empty
        if (empList.isEmpty()) {
            System.out.println("Employee List is empty. Please try again\n");
        } else {
            System.out.println("--- Update employee ---");
            //display employee list
            display.displayEmpList(empList);
            Employee empUpdate;
            String searchId;
            do {
                //user enter employee id for updating
                System.out.print("Enter Employee's ID for update: ");
                // 0-9: contains number from 0 to 9
                // a-z: contains lower case character from a to z
                // A-Z: contains upper case character from A to Z
                // {1,8}: one to eight digits
                searchId = getData.getString("Employee ID", "[0-9A-Z]{1,8}");
                //check if employee with input id exist
                empUpdate = getEmpByID(empList, searchId);
                if (empUpdate == null) {
                    System.out.println("Employee with ID " + searchId + " can't be found. Please re-enter ");
                    continue;
                }
                break;
            } while (true);
            //display employee for update
            display.displayElement(empUpdate);
            do {
                //display employee attribute menu
                display.displayAttribute();
                //user select an option
                System.out.print("Enter choice number for update: ");
                int choice = getData.getInteger("Choice", 1, 10);
                switch (choice) {
                    case 1: //update id
                        String newId;
                        System.out.print("Enter new employee's ID (less than 8 characters): ");
                        do {
                            // 0-9: contains number from 0 to 9
                            // a-z: contains lower case character from a to z
                            // A-Z: contains upper case character from A to Z
                            // {1,8}: one to eight digits
                            newId = getData.getString("New ID", "[0-9A-Z]{1,8}");
                            if (newId.equals(searchId)) {
                                System.out.println("New Id can't be the same with the old ones. Please re-enter");
                                continue;
                            } else if (checkIdExist(newId, empList) == true) {
                                continue;
                            }
                            break;
                        } while (true);
                        empUpdate.setId(newId);
                        break;
                    case 2: //update first name
                        System.out.print("Enter new employee's first name: ");
                        // ^[A-Z]: start with Uppercase character from A to Z
                        // [a-z]+: continue with ultimate lower case character from a-z
                        String newFName = getData.getString("New first name", "^[A-Z][a-z]+");
                        empUpdate.setFirstName(newFName);
                        break;
                    case 3: //update last name
                        System.out.print("Enter new employee's last name: ");
                        // ^[A-Z]: start with Uppercase character from A to Z
                        // [a-z]+: continue with ultimate lower case character from a-z
                        String newLName = getData.getString("New last name", "^[A-Z][a-z]+");
                        empUpdate.setLastName(newLName);
                        break;
                    case 4: // update phone number
                        System.out.print("Enter new employee's phone number: ");
                        // ^\\{0,1}: can start with '+' or not 1 time
                        // [0-9]{7,15}: at least 7 to maximum 15 numerical digits
                        String newPhone = getData.getString("New phone number", "^\\+{0,1}[0-9]{7,15}");

                        empUpdate.setPhone(newPhone);
                        break;
                    case 5: //update email
                        System.out.print("Enter new employee's email: ");
                        // [A-Za-z0-9!#$%&'\\*\\+\\-\\/=\\?\\^_`\\{|\\}~]+: 1 or more character from A-Z, a-z, 0-9, !#$%&'*+-/=?^_`{|}~
                        // @: follow by '@' character
                        // \\[{0,1}: contain '[' character 0 or 1 time (optional) (in case email has format : abc123@[123.34.56])
                        // [A-Za-z0-9\\.:\\-]+:  contains 1 or more from A-Z, a-z, 0-9, '.', ':', '-'
                        String newEmail = getData.getString("New email", "[A-Za-z0-9!#$%&'\\*\\+\\-\\/=\\?\\^_`\\{|\\}~]+@\\[{0,1}[A-Za-z0-9\\.:\\-]+\\]{0,1}");
                        empUpdate.setEmail(newEmail);
                        break;
                    case 6: //update address
                        System.out.print("Enter new employee's address: ");
                        // [A-Z]{1}: 1 upper case charcter
                        // [a-z]*: 0 or more time lower case character
                        // |: or
                        // [0-9]+: 1 or more numercial digit
                        String newAddress = getData.getString("New adress", "(([A-Z]{1}[a-z]*|[0-9]+|[a-z]+) *)+");
                        empUpdate.setAddress(newAddress);
                        break;
                    case 7: //update dob
                        System.out.print("Enter new employee's date of birth (dd/MM/yyyy): ");
                        Date newDob = getData.getDate("New date of birth");
                        empUpdate.setDob(newDob);
                        break;
                    case 8: //update sex
                        System.out.print("Enter new employee's sex (female/male/other): ");
                        // Male|male: male or Male
                        // Female|female: female or Female
                        // Other|other: other or Other
                        // |: or
                        String newSex = getData.getString("New sex", "(Male|male)|(Female|female)|(Other|other)");
                        empUpdate.setSex(newSex);
                        break;
                    case 9: //update salary
                        System.out.print("Enter new employee's salary: ");
                        int newSalary = getData.getInteger("New salary", 0, Integer.MAX_VALUE);
                        empUpdate.setSalary(newSalary);
                        break;
                    case 10: //update agency
                        System.out.print("Enter new employee's agency: ");
                        // [A-Z]{1}: 1 upper case character
                        // [A-Za-z]+: 1 or more character from A to Z, a to z
                        //  +: 1 or more space
                        // (\\ ){0,1}: 0 or 1 space
                        // *: 0 or more times
                        String newAgency = getData.getString("New agency", "[A-Z]{1}([A-Za-z]+(\\ ){0,1})*");
                        empUpdate.setAgency(newAgency);
                        break;
                }
                //ask user to continue or not
                System.out.print("Continue? yes/no: ");
                String ans = getData.getString("Answer", "(Yes|yes|YES)|(No|no|NO)");
                if (ans.matches("Yes|yes|YES")) {
                    // Yes|yes|YES: yes or Yes or YES
                } else {
                    break;
                }
            } while (true);
            //Display employee list after update
            display.displayEmpList(empList);
        }
    }

    void removeEmployee(ArrayList<Employee> empList) {
        //check if employee list is empty
        if (empList.isEmpty()) {
            System.out.println("Employee List is empty. Please try again\n");
        } else {
            System.out.println("--- Remove employee ---");
            Employee empRemove;
            String searchId;
            do {
                //user enter employee id for updating
                System.out.print("Enter Employee's ID for remove: ");
                // 0-9: contains number from 0 to 9
                // a-z: contains lower case character from a to z
                // A-Z: contains upper case character from A to Z
                // {1,8}: one to eight digits
                searchId = getData.getString("Employee ID", "[0-9A-Z]{1,8}");
                //check if employee with input id exist
                empRemove = getEmpByID(empList, searchId);
                if (empRemove == null) {
                    System.out.println("Employee with ID " + searchId + " can't be found. Please re-enter ");
                    continue;
                }
                break;
            } while (true);
            //remove employee
            empList.remove(empRemove);
            //notify to user and display employee list after remove
            System.out.println("Successfully remove employee!");
            display.displayEmpList(empList);
        }
    }

    void searchEmployeeByName(ArrayList<Employee> empList) {
        //check if employee list is empty
        if (empList.isEmpty()) {
            System.out.println("Employee List is empty. Please try again\n");
        } else {
            System.out.println("--- Search employee ---");
            String checkName;
            Employee search;
            boolean isExist = false;
            do {
                //user enter employee's name for searching
                System.out.print("Enter employee's name for searching: ");
                checkName = getData.getString("Name", "[A-Za-z ]+");
                // [A-Za-z ]: A to Z, a to z, space character
                // +: 1 or more times
                System.out.println("Search result: ");
                //loop to access to each employee in employee list
                for (Employee emp : empList) {
                    String fullName = emp.getFirstName() + " " + emp.getLastName();
                    if (fullName.toLowerCase().contains(checkName.toLowerCase()) || emp.getFirstName().equals(checkName) || emp.getLastName().equals(checkName)) {
                        isExist = true;
                        display.displayElement(emp);
                    }
                }
                if (isExist == false) {
                    System.out.println("Employee's name " + checkName + " doesn't exist.");
                }
                break;
            } while (true);
            System.out.println("");
        }
    }

    public void sortEmployeeBySalary(ArrayList<Employee> empList) {
        if (empList.isEmpty()) {
            System.out.println("Employee List is empty. Please try again\n");
        } else {
            Collections.sort(empList, (Employee o1, Employee o2) -> {
                //sort employee by salary
                if (o1.getSalary() == o2.getSalary()) {
                    return 0;
                } else {
                    return o1.getSalary() - o2.getSalary();
                }
            });
            display.displayEmpList(empList);
        }
    }

    public boolean checkIdExist(String checkId, ArrayList<Employee> empList) {
        boolean isExist = false;
        //check if employee list is empty
        if (empList.isEmpty()) {
            return isExist;
        }
        //loop to access to each employee in employee list
        for (Employee emp : empList) {
            //compare employee id in the list with id input
            if (emp.getId().equals(checkId)) {
                System.out.println("Employee's ID " + checkId + " has already existed. Please re-enter");
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    public Employee getEmpByID(ArrayList<Employee> empList, String id) {
        Employee empSearch = null;
        //loop to access to each employee in the employee list
        for (Employee emp : empList) {
            if (emp.getId().equals(id)) {
                empSearch = emp;
                break;
            }
        }
        return empSearch;
    }

}
