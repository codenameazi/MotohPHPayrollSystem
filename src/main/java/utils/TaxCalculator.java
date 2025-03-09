package utils;

/**
 * TaxCalculator calculates the withholding tax based on the latest BIR tax brackets.
 */
public class TaxCalculator {

    /**
     * Computes the withholding tax based on the taxable income.
     *
     * @param taxableIncome The taxable salary after deductions.
     * @return The computed withholding tax.
     */
    public static double calculateTax(double taxableIncome) {
        if (taxableIncome <= 0) {
            return 0.0;
        }

        if (taxableIncome <= 20832) {
            return 0.0;
        } else if (taxableIncome <= 33333) {
            return (taxableIncome - 20833) * 0.20;
        } else if (taxableIncome <= 66667) {
            return 2500 + (taxableIncome - 33333) * 0.25;
        } else if (taxableIncome <= 166667) {
            return 10833 + (taxableIncome - 66667) * 0.30;
        } else if (taxableIncome <= 666667) {
            return 40833.33 + (taxableIncome - 166667) * 0.32;
        } else {
            return 200833.33 + (taxableIncome - 666667) * 0.35;
        }
    }
}