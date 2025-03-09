package utils;

import model.Employee;

public class PayslipGenerator {

    /**
     * Generates and prints the payslip for an employee.
     *
     * @param emp            The employee object containing personal details.
     * @param hoursWorked    The total hours worked by the employee.
     * @param grossSalary    The total gross salary before deductions.
     * @param sss            The SSS contribution deduction.
     * @param philHealth     The PhilHealth contribution deduction.
     * @param pagIbig        The Pag-IBIG contribution deduction.
     * @param withholdingTax The computed withholding tax deduction.
     * @param netSalary      The final net salary after deductions.
     */
    public static void generatePayslip(Employee emp, double hoursWorked, double grossSalary,
                                       double sss, double philHealth, double pagIbig,
                                       double withholdingTax, double netSalary) {
        System.out.println("\n===========================================");
        System.out.println("                 PAYSLIP ");
        System.out.println("===========================================");
        System.out.printf(" Employee: %-25s%n", emp.getFirstName() + " " + emp.getLastName());
        System.out.printf(" Employee ID: %-20s%n", emp.getEmployeeNumber());
        System.out.printf(" Position: %-25s%n", emp.getPosition());
        System.out.printf(" Department: %-25s%n", emp.getDepartment());
        System.out.printf(" Address: %-30s%n", emp.getAddress());
        System.out.printf(" Tax ID: %-20s%n", emp.getTaxID());
        System.out.println("-------------------------------------------");
        System.out.printf(" Basic Salary: PHP %,10.2f%n", emp.getBasicSalary());
        System.out.printf(" Hourly Rate: PHP %,10.2f%n", emp.getHourlyRate());
        System.out.printf(" Hours Worked: %10.2f%n", hoursWorked);
        System.out.printf(" Gross Salary: PHP %,10.2f%n", grossSalary);
        System.out.println("-------------------------------------------");
        System.out.println(" Deductions:");

        System.out.printf(" - Pag-IBIG: PHP %,10.2f%n", Math.max(0, pagIbig));
        System.out.printf(" - PhilHealth: PHP %,10.2f%n", Math.max(0, philHealth));
        System.out.printf(" - SSS: PHP %,10.2f%n", Math.max(0, sss));
        System.out.printf(" - Withholding Tax: PHP %,10.2f%n", Math.max(0, withholdingTax));
        System.out.println("-------------------------------------------");
        System.out.printf(" Net Salary: PHP %,10.2f%n", netSalary);
        System.out.println("===========================================\n");
    }
}