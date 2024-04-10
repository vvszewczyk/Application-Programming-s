public class Square extends Figure
{
    double side;
    Square(double a) throws IllegalArgumentException
    {
        if(a <= 0)
        {
            throw new IllegalArgumentException("SQUARE\nSide should be bigger than 0");
        }
        else
        {
            this.side = a;
        }
    }

    @Override
    double calculateArea()
    {
        return this.side * this.side;
    }

    @Override
    double calculatePerimeter()
    {
        return 4 * this.side;
    }

    @Override
    public void print()
    {
        System.out.println("SQUARE\nSide: " + this.side + "\nArea: " + this.calculateArea() + "\nPerimeter: " + this.calculatePerimeter());
    }
}