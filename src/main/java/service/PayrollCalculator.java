package service;

import utils.DeductionsCalculator;
import model.Employee;

public class PayrollCalculator {

    public double computeGrossSalary(Employee employee, double hoursWorked) {
        return employee.getHourlyRate() * hoursWorked;
    }

    public double computeNetSalary(Employee employee, double hoursWorked) {
        double grossSalary = computeGrossSalary(employee, hoursWorked);

        // Compute all deductions using DeductionsCalculator
        double sss = DeductionsCalculator.calculateSSS(grossSalary);
        double philHealth = DeductionsCalculator.calculatePhilHealth(grossSalary);
        double pagIbig = DeductionsCalculator.calculatePagIbig(grossSalary);
        
        // Compute taxable income AFTER deductions
        double taxableIncome = grossSalary - (sss + philHealth + pagIbig);
        double tax = DeductionsCalculator.calculateTax(taxableIncome);

        return grossSalary - (sss + philHealth + pagIbig + tax);
    }

    public void displayPayroll(Employee employee, double hoursWorked) {
        double grossSalary = computeGrossSalary(employee, hoursWorked);
        double sss = DeductionsCalculator.calculateSSS(grossSalary);
        double philHealth = DeductionsCalculator.calculatePhilHealth(grossSalary);
        double pagIbig = DeductionsCalculator.calculatePagIbig(grossSalary);
        double taxableIncome = grossSalary - (sss + philHealth + pagIbig);
        double tax = DeductionsCalculator.calculateTax(taxableIncome);
        double netSalary = grossSalary - (sss + philHealth + pagIbig + tax);

        System.out.println("\n📌 Payroll Summary for " + employee.getFirstName() + " " + employee.getLastName());
        System.out.println("-------------------------------------------------");
        System.out.printf("Gross Salary: PHP %.2f%n", grossSalary);
        System.out.printf("SSS Deduction: PHP %.2f%n", sss);
        System.out.printf("PhilHealth Deduction: PHP %.2f%n", philHealth);
        System.out.printf("Pag-IBIG Deduction: PHP %.2f%n", pagIbig);
        System.out.printf("Taxable Income: PHP %.2f%n", taxableIncome);
        System.out.printf("Withholding Tax: PHP %.2f%n", tax);
        System.out.printf("Net Salary: PHP %.2f%n", netSalary);
        System.out.println("-------------------------------------------------\n");
    }
}