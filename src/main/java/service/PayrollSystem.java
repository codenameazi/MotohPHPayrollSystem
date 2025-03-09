package service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import model.Employee;
import utils.PhilHealthCalculator;
import utils.SSSCalculator;
import utils.TaxCalculator;
import utils.PayslipGenerator;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PayrollSystem {
    private static final Logger LOGGER = Logger.getLogger(PayrollSystem.class.getName());
    private final List<Employee> employees = new ArrayList<>();

    // ✅ No-Argument Constructor
    public PayrollSystem() {
        // Initialize the employee list
    }

    public void loadEmployeeData(String filePath) throws IOException, CsvException {
        try (
            InputStreamReader fileReader = new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8);
            CSVReader reader = new CSVReader(fileReader)
        ) {
            String[] line;
            boolean firstLine = true;
            
            while ((line = reader.readNext()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                if (line.length < 19) { // Adjusted to fit correct CSV structure
                    LOGGER.log(Level.WARNING, "⚠ Invalid data format: {0}", String.join("|", line));
                    continue;
                }

                try {
                    String employeeNumber = line[0].trim();
                    String lastName = line[1].trim();
                    String firstName = line[2].trim();
                    String position = line[10].trim();
                    String basicSalaryStr = line[13].trim();
                    String hourlyRateStr = line[18].trim(); // Column S: Hourly Rate

                    double basicSalary = parseSalary(basicSalaryStr);
                    double hourlyRate = parseSalary(hourlyRateStr);

                    employees.add(new Employee(
                        employeeNumber, lastName, firstName, "", "", "",
                        "", "", "", "", "", position, "", basicSalary, hourlyRate
                    ));

                } catch (NumberFormatException e) {
                    LOGGER.log(Level.WARNING, "⚠ Error parsing numeric fields in line: {0}", String.join("|", line));
                }
            }
            System.out.println("✅ Employee data loaded successfully.");
        } catch (IOException | CsvException e) {
            LOGGER.log(Level.SEVERE, "❌ Error loading employee data: {0}", e.getMessage());
            throw e;
        }
    }

    public void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("⚠ No employee data found.");
            return;
        }

        System.out.println("\n📋 Employee List:");
        System.out.println("------------------------------------------------------");
        for (Employee emp : employees) {
            System.out.println(emp);
        }
        System.out.println("------------------------------------------------------");
    }

    private double parseSalary(String salary) {
        try {
            salary = salary.replace(",", "").trim();
            if (!salary.matches("\\d+(\\.\\d+)?")) {
                throw new NumberFormatException("Invalid salary format: " + salary);
            }
            return Double.parseDouble(salary);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "❌ Error parsing salary: {0}", salary);
            return 0.0;
        }
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void processPayroll(Employee emp, double hoursWorked) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}