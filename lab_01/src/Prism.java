public class Prism  implements Printable
{
    Figure base;
    double height;
    Prism(Figure f, double h) throws IllegalArgumentException
    {
        if(f == null || h <= 0)
        {
            throw new IllegalArgumentException("The figure cannot be null or height should be bigger than 0");
        }
        else
        {
            this.base = f;
            this.height = h;
        }
    }
    public double calculateSurfaceArea()
    {
        return 2 * this.base.calculateArea() + this.base.calculatePerimeter() * this.height;
    }


    public double calculateVolume()
    {
        return this.base.calculateArea() * this.height;
    }

    @Override
    public void print()
    {
        if (this.base != null)
        {
            this.base.print();
            System.out.println("Spatial height: "+this.height + "\nSurface area: " + this.calculateSurfaceArea() + "\nVolume: " + this.calculateVolume()+ "\n");
        }
        else
        {
            System.out.println("Prism base is not initialized.");
        }
    }
}