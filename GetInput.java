package p0101;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class GetInput {

    Scanner sc = new Scanner(System.in);

    public int getInteger(String type, int min_range, int max_range) {
        String input;
        int output;
        do {
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println(type + " can't be empty. Please re-eneter");
                continue;
            } else {
                try {
                    double result = Double.parseDouble(input);
                    //check if input is a decimal number & notify user if it is not    
                    if ((int) result != result) {
                        System.out.println(type + " must be a integer number. Please re-enter");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(type + " can't contains special character. Please re-enter");
                    continue;
                }
            }
            output = Integer.parseInt(input);
            //check if option is valid & notify user
            if (output < min_range || output > max_range) {
                System.out.println(type + " must be in range [" + min_range + "," + max_range + "] Please re-enter");
                continue;
            }
            break;
        } while (true);
        return output;
    }

    String getString(String type, String regex) {
        String input;
        do {
            input = sc.nextLine();
            //check empty string
            if (input.isEmpty()) {
                System.out.println(type + " can't be empty. Please re-enter");
                continue;
            } //if there is no restriction on format -> return input
            else if (regex.isEmpty()) {
                return input;
            } else {
                //check string matches a format
                if (input.matches(regex) == false) {
                    System.out.println(type + "'s format is wrong. Please re-enter");
                    continue;
                }
            }
            break;
        } while (true);
        return input;
    }

    Date getDate(String type) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.setLenient(false);
        String input;
        Date dob;
        do {
            input = sc.nextLine();
            //check empty string
            if (input.isEmpty()) {
                System.out.println(type + " can't be empty.Please re-enter");
                continue;
            } //check contain special character
            else {
                if (checkContain(input) == true) {
                    System.out.println(type + " can't contains special character. Please re-enter");
                    continue;
                } //check format
                //\d: [0-9]: digits from 0 to 9
                //{1,2}: 1 or 2 digits is allowed
                //[/]: character '/'
                //\d{4}: must have 4 digits
                else {
                    if (input.matches("\\d{1,2}[/]\\d{1,2}[/]\\d{4}") == false) {
                        System.out.println(type + "'s format is wrong. Please re-enter");
                        continue;
                    }
                }
            }
            try {
                Date now = new Date();
                dob = formatter.parse(input);
                //check if dob is possilble
                if (dob.after(now)) {
                    System.out.println(type + " must be before current day");
                    continue;
                }
                break;
            } catch (ParseException e) {
                System.out.println(type + " doesn't exist. Please re-enter");
            }
        } while (true);
        return dob;
    }

    boolean checkContain(String input) {
        boolean check = false;
        for (int i = 0; i < input.length(); i++) {
            if (!(input.charAt(i) >= '-' && input.charAt(i) <= '9')) {
                check = true;
                break;
            }
        }
        return check;
    }
}
