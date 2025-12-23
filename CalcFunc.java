public class CalcFunc {

    public static double add(double a, double b) { return a + b; }
    public static double subtract(double a, double b) { return a - b; }
    public static double multiply(double a, double b) { return a * b; }
    public static double divide(double a, double b)
    {
        if (b == 0)
        { throw new NumberFormatException("Cannot divide by 0!"); }
        return a / b;
    }
    public static double square_root(double a) {
        if (a < 0)
        { throw new NumberFormatException("Radicand cannot be negative!"); }
        return Math.sqrt(a);
    }
    public static double number_squared(double a) { return a * a; }
}