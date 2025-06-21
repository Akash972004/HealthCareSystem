import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Health Care Management System ---");
            System.out.println("1. Register Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Book Appointment");
            System.out.println("4. Generate Bill");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String pname = sc.nextLine();
                    System.out.print("Gender: ");
                    String gender = sc.nextLine();
                    System.out.print("Age: ");
                    int age = sc.nextInt(); sc.nextLine();
                    System.out.print("Phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Address: ");
                    String addr = sc.nextLine();
                    Patient.register(pname, gender, age, phone, addr);
                    break;
                case 2:
                    System.out.print("Doctor Name: ");
                    String dname = sc.nextLine();
                    System.out.print("Specialization: ");
                    String spec = sc.nextLine();
                    System.out.print("Phone: ");
                    String dphone = sc.nextLine();
                    Doctor.addDoctor(dname, spec, dphone);
                    break;
                case 3:
                    System.out.print("Patient ID: ");
                    int pid = sc.nextInt();
                    System.out.print("Doctor ID: ");
                    int did = sc.nextInt(); sc.nextLine();
                    System.out.print("Date (YYYY-MM-DD): ");
                    String date = sc.nextLine();
                    System.out.print("Remarks: ");
                    String remarks = sc.nextLine();
                    Appointment.book(pid, did, date, remarks);
                    break;
                case 4:
                    System.out.print("Patient ID: ");
                    int bid = sc.nextInt();
                    System.out.print("Amount: ");
                    double amount = sc.nextDouble();
                    Billing.generateBill(bid, amount);
                    break;
                case 5:
                    System.out.println("Exiting System.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 5);
        sc.close();
    }
}