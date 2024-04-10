public class Triangle extends Figure
{
    double side1; //base
    double side2;
    double side3;
    double height;

    Triangle(double a1, double a2, double a3) throws IllegalArgumentException
    {
        if(a1 <= 0 || a2 <= 0 || a3 <= 0)
        {
            throw new IllegalArgumentException("TRIANGLE\nThe sides should be bigger than 0");
        }
        else if(a1 + a2 <= a3 || a1 + a3 <= a2 || a2 + a3 <= a1)
        {
            throw new IllegalArgumentException("TRIANGLE\nThe sides do not satisfy the triangle inequality");
        }
        else
        {
            this.side1 = a1;
            this.side2 = a2;
            this.side3 = a3;
            this.height = (2 * calculateArea()) / a1; //a1 = base
        }
    }
    @Override
    double calculateArea()
    {
        //Heron's formula
        double p = (this.side1 + this.side2 + this.side3) / 2;
        return Math.sqrt(p * (p - this.side1) * (p - this.side2) * (p - this.side3));
    }

    @Override
    double calculatePerimeter()
    {
        return this.side1 + this.side2 + this.side3;
    }

    @Override
    public void print()
    {
        System.out.println("TRIANGLE\nSide 1: " + this.side1 +"\nSide 2: " + this.side2 + "\nSide 3: " + this.side3 + "\nBase height: " + this.height + "\nArea: " + this.calculateArea() + "\nPerimeter: " + this.calculatePerimeter());
    }
}