package utils;

/**
 * Utility class to compute the employee's share of the SSS contribution.
 */
public class SSSCalculator {

    /**
     * Computes the employee's SSS contribution based on their salary.
     *
     * @param salary The gross monthly salary of the employee.
     * @return The corresponding SSS contribution amount.
     */
    public static double calculateSSS(double salary) {
        if (salary <= 0) {
            return 0.0;
        }

        double[][] sssBrackets = {
            {3250, 135}, {3750, 157.50}, {4250, 180}, {4750, 202.50}, {5250, 225},
            {5750, 247.50}, {6250, 270}, {6750, 292.50}, {7250, 315}, {7750, 337.50},
            {8250, 360}, {8750, 382.50}, {9250, 405}, {9750, 427.50}, {10250, 450},
            {10750, 472.50}, {11250, 495}, {11750, 517.50}, {12250, 540}, {12750, 562.50},
            {13250, 585}, {13750, 607.50}, {14250, 630}, {14750, 652.50}, {15250, 675},
            {15750, 697.50}, {16250, 720}, {16750, 742.50}, {17250, 765}, {17750, 787.50},
            {18250, 810}, {18750, 832.50}, {19250, 855}, {19750, 877.50}, {20250, 900},
            {20750, 922.50}, {21250, 945}, {21750, 967.50}, {22250, 990}, {22750, 1012.50},
            {23250, 1035}, {23750, 1057.50}, {24250, 1080}, {24750, 1102.50},
            {Double.MAX_VALUE, 1125} // Max contribution cap
        };

        for (double[] bracket : sssBrackets) {
            if (salary < bracket[0]) {
                return bracket[1];
            }
        }
        return 1125.0;
    }
}