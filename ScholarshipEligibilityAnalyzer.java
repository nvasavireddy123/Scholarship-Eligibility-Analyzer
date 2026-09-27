import java.util.*;

class Student {
    String name;
    double cgpa;
    double income;
    double attendance;
    String category;
    int backlogs;

    Student(String name, double cgpa, double income,
            double attendance, String category, int backlogs) {

        this.name = name;
        this.cgpa = cgpa;
        this.income = income;
        this.attendance = attendance;
        this.category = category;
        this.backlogs = backlogs;
    }
}

class Scholarship {

    String name;
    double minimumCGPA;
    double maximumIncome;
    double minimumAttendance;
    String category;
    int maximumBacklogs;

    Scholarship(String name, double minimumCGPA,
                double maximumIncome, double minimumAttendance,
                String category, int maximumBacklogs) {

        this.name = name;
        this.minimumCGPA = minimumCGPA;
        this.maximumIncome = maximumIncome;
        this.minimumAttendance = minimumAttendance;
        this.category = category;
        this.maximumBacklogs = maximumBacklogs;
    }

    boolean isEligible(Student student) {

        boolean cgpaOK = student.cgpa >= minimumCGPA;
        boolean incomeOK = student.income <= maximumIncome;
        boolean attendanceOK = student.attendance >= minimumAttendance;
        boolean backlogOK = student.backlogs <= maximumBacklogs;

        boolean categoryOK =
                category.equalsIgnoreCase("All")
                || category.equalsIgnoreCase(student.category);

        return cgpaOK
                && incomeOK
                && attendanceOK
                && backlogOK
                && categoryOK;
    }
}

public class ScholarshipEligibilityAnalyzer {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Scholarship> scholarships =
            new ArrayList<>();

    public static void main(String[] args) {

        loadScholarships();

        while (true) {

            System.out.println("\n========================================");
            System.out.println("     SCHOLARSHIP ELIGIBILITY ANALYZER");
            System.out.println("========================================");

            System.out.println("1. Check Scholarship Eligibility");
            System.out.println("2. View Available Scholarships");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    checkEligibility();
                    break;

                case 2:
                    viewScholarships();
                    break;

                case 3:
                    System.out.println("\nThank you!");
                    return;

                default:
                    System.out.println("\nInvalid choice.");
            }
        }
    }

    // Create scholarship database
    static void loadScholarships() {

        scholarships.add(
                new Scholarship(
                        "Merit Excellence Scholarship",
                        8.5,
                        500000,
                        75,
                        "All",
                        0
                )
        );

        scholarships.add(
                new Scholarship(
                        "Need Based Scholarship",
                        7.0,
                        250000,
                        75,
                        "All",
                        1
                )
        );

        scholarships.add(
                new Scholarship(
                        "Women in Technology Scholarship",
                        7.5,
                        400000,
                        75,
                        "Female",
                        1
                )
        );

        scholarships.add(
                new Scholarship(
                        "SC/ST Education Scholarship",
                        6.5,
                        500000,
                        75,
                        "SC/ST",
                        2
                )
        );

        scholarships.add(
                new Scholarship(
                        "Academic Excellence Scholarship",
                        9.0,
                        600000,
                        85,
                        "All",
                        0
                )
        );
    }

    // Get student details
    static void checkEligibility() {

        System.out.println("\n----------------------------------------");
        System.out.println("       ENTER STUDENT DETAILS");
        System.out.println("----------------------------------------");

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter CGPA: ");
        double cgpa = sc.nextDouble();

        System.out.print("Enter Family Annual Income: ₹");
        double income = sc.nextDouble();

        System.out.print("Enter Attendance Percentage: ");
        double attendance = sc.nextDouble();

        sc.nextLine();

        System.out.print(
                "Enter Category (General/OBC/SC/ST/Female): "
        );
        String category = sc.nextLine();

        System.out.print("Enter Number of Backlogs: ");
        int backlogs = sc.nextInt();

        Student student = new Student(
                name,
                cgpa,
                income,
                attendance,
                category,
                backlogs
        );

        analyzeStudent(student);
    }

    // Analyze eligibility
    static void analyzeStudent(Student student) {

        System.out.println("\n========================================");
        System.out.println("       ELIGIBILITY RESULTS");
        System.out.println("========================================");

        System.out.println("Student Name : " + student.name);
        System.out.println("CGPA         : " + student.cgpa);
        System.out.println("Income       : ₹" + student.income);
        System.out.println("Attendance   : " + student.attendance + "%");
        System.out.println("Category     : " + student.category);
        System.out.println("Backlogs     : " + student.backlogs);

        System.out.println("\n----------------------------------------");
        System.out.println("SCHOLARSHIP ANALYSIS");
        System.out.println("----------------------------------------");

        boolean found = false;

        for (Scholarship scholarship : scholarships) {

            if (scholarship.isEligible(student)) {

                System.out.println(
                        "✓ " + scholarship.name
                );

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No scholarships found based on the entered details."
            );
        }

        System.out.println("----------------------------------------");
    }

    // Display scholarships
    static void viewScholarships() {

        System.out.println("\n========================================");
        System.out.println("       AVAILABLE SCHOLARSHIPS");
        System.out.println("========================================");

        int number = 1;

        for (Scholarship s : scholarships) {

            System.out.println("\n" + number++ + ". " + s.name);

            System.out.println(
                    "   Minimum CGPA       : " + s.minimumCGPA
            );

            System.out.println(
                    "   Maximum Income     : ₹" + s.maximumIncome
            );

            System.out.println(
                    "   Minimum Attendance : "
                    + s.minimumAttendance + "%"
            );

            System.out.println(
                    "   Category           : " + s.category
            );

            System.out.println(
                    "   Maximum Backlogs   : "
                    + s.maximumBacklogs
            );
        }
    }
}

