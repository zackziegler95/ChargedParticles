package chargedparticles;

public class Vector3 {
    private final double[] array;
    
    /*public Vector3(double[] array) {
        if (array.length != 3) {
            System.err.println("Error: array passed to Vector3 constructor is the wrong size.");
        }
        
        this.array = new double[3];
        System.arraycopy(array, 0, this.array, 0, 3);
    }*/
    
    public Vector3() {
        array = new double[3];
        array[0] = 0;
        array[1] = 0;
        array[2] = 0;
    }
    
    public Vector3(double one, double two, double three) {
        array = new double[3];
        array[0] = one;
        array[1] = two;
        array[2] = three;
    }
    
    public Vector3(Vector3 p) {
        array = new double[3];
        System.arraycopy(p.array, 0, this.array, 0, 3);
    }
    
    public double x() {return array[0];}
    public double y() {return array[1];}
    public double z() {return array[2];}
    
    public double dot(Vector3 vec) {
        return x()*vec.x() + y()*vec.y() + z()*vec.z();
    }
    
    public Vector3 plus(Vector3 vec) {
        return new Vector3(x()+vec.x(), y()+vec.y(), z()+vec.z());
    }
    
    public Vector3 minus(Vector3 vec) {
        return new Vector3(x()-vec.x(), y()-vec.y(), z()-vec.z());
    }
    
    public Vector3 times(double d) {
        return new Vector3(x()*d, y()*d, z()*d);
    }
    
    public Vector3 normalize() { // Make magnitude one
        if (x() == 0 && y() == 0 && z() == 0) {
            System.err.println("Error, can't normalize zero vector");
        }
        return times(1/mag());
    }
    
    public double mag() {
        return Math.sqrt(magSq()); 
    }
    
    public double magSq() {
        return x()*x() + y()*y() + z()*z(); 
    }
    
    /*public Vector3 perp() {
        return new Vector3(-y(), x());
    }*/
    
    @Override
    public final String toString() {
        String s = "{";
        for (int i = 0; i < array.length-1; i++) {
            s += array[i]+", ";
        }
        return s + array[array.length-1]+"}";
    }
}
