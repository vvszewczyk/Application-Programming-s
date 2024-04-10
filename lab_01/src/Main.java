import java.util.Scanner;
import java.util.Locale;

public class Main
{
    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    public static void main(String[] args)
    {
        while(true)
        {
            System.out.println("Choose your figure: ");
            System.out.println("1 - triangle");
            System.out.println("2 - square");
            System.out.println("3 - circle");
            System.out.println("4 - quit");

            String choice = scanner.nextLine();

            switch (choice)
            {
                case "1":
                    Triangle();
                    break;
                case "2":
                    Square();
                    break;
                case "3":
                    Circle();
                    break;
                case "4":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Incorrect choice. Try again.");
                    break;
            }
        }
    }

    private static double readDouble(String prompt)
    {
        while (true)
        {
            try
            {
                System.out.println(prompt);
                return Double.parseDouble(scanner.nextLine());
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid input, please enter numbers only. Try again.");
            }
        }
    }

    private static void Triangle()
    {
        System.out.println("\nTRIANGLE SELECTED");
        double base = readDouble("Insert base of the triangle: ");
        double side2 = readDouble("Insert second side of the triangle: ");
        double side3 = readDouble("Insert third side of the triangle: ");
        double height = readDouble("Insert height of the spatial figure: ");

        try
        {
            Triangle triangle = new Triangle(base, side2, side3);
            Prism prism = new Prism(triangle, height);
            prism.print();
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void Square()
    {
        System.out.println("\nSQUARE SELECTED");
        double side = readDouble("Insert side of the square: ");
        double height = readDouble("Insert height of the spatial figure: ");

        try
        {
            Square square = new Square(side);
            Prism prism = new Prism(square, height);
            prism.print();
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void Circle()
    {
        System.out.println("\nCIRCLE SELECTED");
        double radius = readDouble("Insert radius of the circle: ");
        double height = readDouble("Insert height of the spatial figure: ");

        try
        {
            Circle circle = new Circle(radius);
            Prism prism = new Prism(circle, height);
            prism.print();
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
