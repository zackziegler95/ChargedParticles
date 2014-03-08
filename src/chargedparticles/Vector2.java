package chargedparticles;

public class Vector2 {
    private final double[] array;
    
    /*public Vector3(double[] array) {
        if (array.length != 3) {
            System.err.println("Error: array passed to Vector3 constructor is the wrong size.");
        }
        
        this.array = new double[3];
        System.arraycopy(array, 0, this.array, 0, 3);
    }*/
    
    public Vector2() {
        array = new double[2];
        array[0] = 0;
        array[1] = 0;
    }
    
    public Vector2(double one, double two) {
        array = new double[2];
        array[0] = one;
        array[1] = two;
    }
    
    public Vector2(Vector2 p) {
        array = new double[2];
        System.arraycopy(p.array, 0, this.array, 0, 2);
    }
    
    public double x() {return array[0];}
    public double y() {return array[1];}
    
    public double dot(Vector2 vec) {
        return x()*vec.x() + y()*vec.y();
    }
    
    public Vector2 plus(Vector2 vec) {
        return new Vector2(x()+vec.x(), y()+vec.y());
    }
    
    public Vector2 minus(Vector2 vec) {
        return new Vector2(x()-vec.x(), y()-vec.y());
    }
    
    public Vector2 times(double d) {
        return new Vector2(x()*d, y()*d);
    }
    
    public Vector2 normalize() { // Make magnitude one
        if (x() == 0 && y() == 0) {
            System.err.println("Error, can't normalize zero vector");
        }
        return times(1/mag());
    }
    
    public double mag() {
        return Math.sqrt(magSq()); 
    }
    
    public double magSq() {
        return x()*x() + y()*y(); 
    }
    
    public Vector2 perp() {
        return new Vector2(-y(), x());
    }
    
    @Override
    public final String toString() {
        String s = "{";
        for (int i = 0; i < array.length-1; i++) {
            s += array[i]+", ";
        }
        return s + array[array.length-1]+"}";
    }
}
