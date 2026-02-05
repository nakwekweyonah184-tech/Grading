package courseWork;
import java.util.Scanner;
public class GradingSystem {
    public static void main(String[]args){
         // Create a scanner to read user input
        Scanner input = new Scanner(System.in);
        
        System.out.println("===== UGANDA O-LEVEL GRADING SYSTEM =====");
        System.out.println("This program will grade 5 students\n");
        
        // Counters for each grade
        int countGrade1 = 0, countGrade2 = 0, countGrade3 = 0;
        int countGrade4 = 0, countGrade5 = 0, countGrade6 = 0;
        int countGrade7 = 0, countGrade8 = 0, countGrade9 = 0;
        
        int studentNumber = 1;
        
        // Process 5 students using a while loop
        while (studentNumber <= 5) {
            System.out.println("\n----- Student " + studentNumber + " -----");
            
            // Ask for score
            System.out.print("Enter score (0-100): ");
            int score = input.nextInt();
            
            // Check if score is valid
            if (score < 0 || score > 100) {
                System.out.println("Error! Score must be between 0 and 100. Try again.");
                continue; // Skip to next iteration
            }
            
            String grade = "";
            String remark = "";
            
            // Grade determination using if-else-if
            if (score >= 80) {
                grade = "1";
                remark = "D1";
                countGrade1 = countGrade1 + 1;
            }
            else if (score >= 75) {
                grade = "2";
                remark = "D2";
                countGrade2 = countGrade2 + 1;
            }
            else if (score >= 66) {
                grade = "3";
                remark = "C3";
                countGrade3 = countGrade3 + 1;
            }
            else if (score >= 60) {
                grade = "4";
                remark = "C4";
                countGrade4 = countGrade4 + 1;
            }
            else if (score >= 50) {
                grade = "5";
                remark = "C5";
                countGrade5 = countGrade5 + 1;
            }
            else if (score >= 45) {
                grade = "6";
                remark = "C6";
                countGrade6 = countGrade6 + 1;
            }
            else if (score >= 35) {
                grade = "7";
                remark = "P7";
                countGrade7 = countGrade7 + 1;
            }
            else if (score >= 30) {
                grade = "8";
                remark = "P8";
                countGrade8 = countGrade8 + 1;
            }
            else {
                grade = "9";
                remark = "F";
                countGrade9 = countGrade9 + 1;
            }
            
            // Display the result
            System.out.println("Result:");
            System.out.println("Score: " + score);
            System.out.println("Grade: " + grade);
            System.out.println("Remark: " + remark);
            
            studentNumber = studentNumber + 1; // Move to next student
        }
        
        // Display summary
        System.out.println("\n\n===== GRADE SUMMARY =====");
        System.out.println("Grade 1: " + countGrade1 + " student(s)");
        System.out.println("Grade 2: " + countGrade2 + " student(s)");
        System.out.println("Grade 3: " + countGrade3 + " student(s)");
        System.out.println("Grade 4: " + countGrade4 + " student(s)");
        System.out.println("Grade 5: " + countGrade5 + " student(s)");
        System.out.println("Grade 6: " + countGrade6 + " student(s)");
        System.out.println("Grade 7: " + countGrade7 + " student(s)");
        System.out.println("Grade 8: " + countGrade8 + " student(s)");
        System.out.println("Grade 9: " + countGrade9 + " student(s)");
        System.out.println("Total students: 5");
        
        // Close the scanner
        input.close();
    }
}
