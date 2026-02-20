
package vupay;

import java.util.Scanner;

public class VUCourses {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Part (b): variables
        String moduleCode;
        String moduleName = "";
        double tuition = 0;

        // Prompt user
        System.out.println("Enter the Module Code (BSF, BIT, BCS, BCE): ");
        moduleCode = input.nextLine().toUpperCase(); // convert to uppercase

        // Match module code
        switch (moduleCode) {
            case "BSF":
                moduleName = "BSc. Software Engineering";
                tuition = 900000;
                break;
            case "BIT":
                moduleName = "BSc. Information Technology";
                tuition = 750000;
                break;
            case "BCS":
                moduleName = "BSc. Computer Science";
                tuition = 800000;
                break;
            case "BCE":
                moduleName = "BSc. Computer Engineering";
                tuition = 950000;
                break;
            default:
                System.out.println("Wrong Module Code details");
                System.exit(0); // exit program
        }

        // Display course details
        System.out.println("Module: " + moduleName);
        System.out.println("Module Code: " + moduleCode);
        System.out.println("Tuition: UGX " + tuition);
    }
}
