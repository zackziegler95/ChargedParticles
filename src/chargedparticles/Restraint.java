package chargedparticles;

public class Restraint {
    private static final double root2 = Math.sqrt(2);
    
    public Vector3 pos;
    public double len; // Side length, full width
    public Vector3 normal;
    public int scaling;
    
    //private double impulse;
    
    public Restraint(Vector3 pos, double len, Vector3 normal, boolean inside) { // A square that stops charge
        this.pos = new Vector3(pos);
        this.len = len;
        this.normal = new Vector3(normal);
        scaling = inside ? 2 : 1;
    }
    
    public void solve(Particle a, double dt) {
        Vector3 relPos = a.pos.minus(pos);
        Vector3 tangent = relPos.minus(normal.times(relPos.dot(normal)));
        if (tangent.magSq() > len*len/scaling) return;
        
        double dist = relPos.dot(normal);
        double dist2 = a.pos.plus(a.vel.times(dt*200)).minus(pos).dot(normal);
        //System.out.println(relPos+", "+normal);
        if (dist*dist2 > 0) return;
        //a.vel = new Vector2();
        
        double relNV = a.vel.dot(normal);
        //System.out.println(relNV);
        //double remove = relNV + dist/dt;

        //double imp = relNV * a.mass;
        
        Vector3 changeVec = normal.times(relNV);
        a.vel = a.vel.minus(changeVec);
    }
}
