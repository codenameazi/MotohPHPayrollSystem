package service;

import utils.DeductionsCalculator;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import model.Employee;
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
        // Initialize employee list
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

                if (line.length < 19) { // Ensure proper CSV format
                    LOGGER.log(Level.WARNING, "⚠ Invalid data format: {0}", String.join("|", line));
                    continue;
                }

                try {
                    String employeeNumber = line[0].trim();
                    String lastName = line[1].trim();
                    String firstName = line[2].trim();
                    String position = line[10].trim();
                    double basicSalary = parseSalary(line[13].trim()); // Column N: Basic Salary
                    double hourlyRate = parseSalary(line[18].trim()); // Column S: Hourly Rate

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
        if (emp == null) {
            System.out.println("❌ Error: Employee data is missing.");
            return;
        }

        double basicSalary = emp.getBasicSalary();
        double hourlyRate = emp.getHourlyRate();
        double grossSalary = (basicSalary > 0) ? basicSalary : (hoursWorked * hourlyRate);

        // Compute deductions
        double sssDeduction = DeductionsCalculator.calculateSSS(grossSalary);
        double philHealthDeduction = DeductionsCalculator.calculatePhilHealth(grossSalary);
        double pagIbigDeduction = DeductionsCalculator.calculatePagIbig(grossSalary);
        double taxableIncome = grossSalary - (sssDeduction + philHealthDeduction + pagIbigDeduction);
        double taxDeduction = DeductionsCalculator.calculateTax(taxableIncome);

        double totalDeductions = sssDeduction + philHealthDeduction + pagIbigDeduction + taxDeduction;
        double netSalary = grossSalary - totalDeductions;

        // Display payroll details
        System.out.println("\n📑 Payroll Summary for " + emp.getFirstName() + " " + emp.getLastName());
        System.out.println("---------------------------------------------");
        System.out.printf("💰 Gross Salary: ₱%.2f%n", grossSalary);
        System.out.printf("🛡 SSS Deduction: ₱%.2f%n", sssDeduction);
        System.out.printf("🏥 PhilHealth Deduction: ₱%.2f%n", philHealthDeduction);
        System.out.printf("🏠 Pag-IBIG Deduction: ₱%.2f%n", pagIbigDeduction);
        System.out.printf("📝 Taxable Income: ₱%.2f%n", taxableIncome);
        System.out.printf("📜 Tax Deduction: ₱%.2f%n", taxDeduction);
        System.out.printf("📉 Total Deductions: ₱%.2f%n", totalDeductions);
        System.out.printf("💵 Net Salary: ₱%.2f%n", netSalary);
        System.out.println("---------------------------------------------");

        // ✅ Fixed Payslip Generation (passing 8 parameters)
        PayslipGenerator.generatePayslip(emp, hoursWorked, grossSalary, sssDeduction, philHealthDeduction, pagIbigDeduction, taxDeduction, netSalary);
    }
}