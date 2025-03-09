package utils;

public class PhilHealthCalculator {

    /**
     * Computes the PhilHealth contribution based on the given salary.
     * The total contribution rate is 3%, but the employee only pays half (1.5%).
     *
     * @param salary The gross monthly salary of the employee.
     * @return The employee's share of the PhilHealth contribution.
     */
    public static double getPhilHealthContribution(double salary) {
        // Employee share of the PhilHealth contribution (1.5% of salary)
        return (salary * 0.03) / 2;
    }
}