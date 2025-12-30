import java.util.*;

public class EmployeeManagementSystem {

    private ArrayList<Employee> employees;
    private HashMap<String, Employee> employeeMap;
    private Scanner scanner;
    private EmployeeFileHandler fileHandler;
    private EmployeeReportGenerator reportGenerator;

    public EmployeeManagementSystem() {
        scanner = new Scanner(System.in);
        fileHandler = new EmployeeFileHandler();
        reportGenerator = new EmployeeReportGenerator();

        employees = fileHandler.loadEmployees();
        employeeMap = new HashMap<>();

        for (Employee e : employees) {
            employeeMap.put(e.getId(), e);
        }
    }

    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem();
        ems.menu();
    }

    public void menu() {
        while (true) {
            System.out.println("\n=== EMPLOYEE MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Search Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Reports");
            System.out.println("6. Exit");
            System.out.print("Choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input!");
                continue;
            }

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> viewEmployees();
                case 3 -> searchEmployee();
                case 4 -> deleteEmployee();
                case 5 -> generateReports();
                case 6 -> {
                    fileHandler.saveEmployees(employees);
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void addEmployee() {
        System.out.print("ID: ");
        String id = scanner.nextLine();

        if (employeeMap.containsKey(id)) {
            System.out.println("Employee ID already exists!");
            return;
        }

        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Department: ");
        String dept = scanner.nextLine();
        System.out.print("Position: ");
        String pos = scanner.nextLine();

        System.out.print("Salary: ");
        double salary = Double.parseDouble(scanner.nextLine());

        Employee emp = new Employee(id, name, dept, pos, salary);
        employees.add(emp);
        employeeMap.put(id, emp);

        fileHandler.saveEmployees(employees);
        System.out.println("✅ Employee added!");
    }

    private void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    private void searchEmployee() {
        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine();

        Employee e = employeeMap.get(id);
        if (e != null)
            System.out.println(e);
        else
            System.out.println("Employee not found!");
    }

    private void deleteEmployee() {
        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine();

        Employee e = employeeMap.remove(id);
        if (e != null) {
            employees.remove(e);
            fileHandler.saveEmployees(employees);
            System.out.println("Employee deleted.");
        } else {
            System.out.println("Employee not found!");
        }
    }

    private void generateReports() {
        reportGenerator.generateSalaryReport(employees);
        reportGenerator.generateDepartmentReport(employees);
    }
}
