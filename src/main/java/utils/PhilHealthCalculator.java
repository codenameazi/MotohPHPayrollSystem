package utils;

/**
 * Utility class to compute the employee's PhilHealth contribution.
 */
public class PhilHealthCalculator {

    // Constants for PhilHealth computation
    private static final double CONTRIBUTION_RATE = 0.03; // 3% total
    private static final double EMPLOYEE_SHARE = 0.5; // Employee pays half (1.5%)

    /**
     * Computes the PhilHealth contribution based on the given salary.
     *
     * @param salary The gross monthly salary of the employee.
     * @return The employee's share of the PhilHealth contribution.
     */
    public static double calculatePhilHealth(double salary) {
        if (salary <= 0) {
            return 0.0;
        }
        return (salary * CONTRIBUTION_RATE) * EMPLOYEE_SHARE;
    }
}