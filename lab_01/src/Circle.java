public class Circle extends Figure
{
    double radius;
    Circle(double r) throws IllegalArgumentException
    {
        if(r <= 0)
        {
            throw new IllegalArgumentException("CIRCLE\nThe radius should be bigger than 0");
        }
        else
        {
            this.radius = r;
        }
    }

    @Override
    double calculateArea()
    {
        return Math.PI * Math.pow(this.radius, 2);
    }

    @Override
    double calculatePerimeter()
    {
        return 2 * Math.PI * this.radius;
    }

    @Override
    public void print()
    {
        System.out.println("CIRCLE\nRadius: " + this.radius + "\nArea: " + this.calculateArea() + "\nPerimeter: " + this.calculatePerimeter());
    }
}