import java.util.*;

public class EmployeeReportGenerator {

    public void generateSalaryReport(ArrayList<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employees to generate report.");
            return;
        }

        double total = 0;
        Employee highest = employees.get(0);
        Employee lowest = employees.get(0);

        for (Employee e : employees) {
            total += e.getSalary();

            if (e.getSalary() > highest.getSalary())
                highest = e;

            if (e.getSalary() < lowest.getSalary())
                lowest = e;
        }

        System.out.println("\n💰 SALARY REPORT");
        System.out.println("Total Employees: " + employees.size());
        System.out.println("Total Salary: ₹" + total);
        System.out.println("Average Salary: ₹" + (total / employees.size()));
        System.out.println("Highest Salary: ₹" + highest.getSalary() +
                " (" + highest.getName() + ")");
        System.out.println("Lowest Salary: ₹" + lowest.getSalary() +
                " (" + lowest.getName() + ")");
    }

    public void generateDepartmentReport(ArrayList<Employee> employees) {
        HashMap<String, Integer> deptCount = new HashMap<>();

        for (Employee e : employees) {
            deptCount.put(e.getDepartment(),
                    deptCount.getOrDefault(e.getDepartment(), 0) + 1);
        }

        System.out.println("\n🏢 DEPARTMENT SUMMARY");
        for (String dept : deptCount.keySet()) {
            System.out.println(dept + ": " + deptCount.get(dept) + " employees");
        }
    }
}
