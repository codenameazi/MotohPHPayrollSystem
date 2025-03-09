package utils;

public class DeductionsCalculator {

    /**
     * Calculates Pag-IBIG contribution.
     * - 1% for salaries up to PHP 1,500
     * - 2% for salaries above PHP 1,500
     * - Maximum contribution is PHP 100
     */
    public static double calculatePagIbig(double grossSalary) {
        if (grossSalary <= 0) return 0.0;

        double pagIbigRate = (grossSalary <= 1500) ? 0.01 : 0.02;
        return Math.min(grossSalary * pagIbigRate, 100);
    }

    /**
     * Calculates PhilHealth contribution based on the official rate.
     */
    public static double calculatePhilHealth(double grossSalary) {
        if (grossSalary <= 0) return 0.0;
        return PhilHealthCalculator.calculatePhilHealth(grossSalary); // ✅ Fixed Method Name
    }

    /**
     * Calculates SSS contribution using the official bracket system.
     */
    public static double calculateSSS(double grossSalary) {
        if (grossSalary <= 0) return 0.0;
        return SSSCalculator.calculateSSS(grossSalary); // ✅ Fixed Method Name
    }

    /**
     * Computes withholding tax based on taxable income.
     */
    public static double calculateTax(double taxableIncome) {
        if (taxableIncome <= 0) return 0.0;
        return TaxCalculator.calculateTax(taxableIncome); // ✅ Fixed Method Name
    }
}