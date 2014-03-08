package chargedparticles;

public class Restraint {
    public Vector2 pos;
    public double len;
    public Vector2 normal;
    public int scaling;
    
    //private double impulse;
    
    public Restraint(Vector2 pos, double len, Vector2 normal, boolean inside) { // A line that stops charge
        this.pos = new Vector2(pos);
        this.len = len;
        this.normal = new Vector2(normal);
        scaling = inside ? 2 : 2;
    }
    
    public void solve(Particle a, double dt) {
        Vector2 relPos = a.pos.minus(pos);
        Vector2 tangent = relPos.minus(normal.times(relPos.dot(normal)));
        if (tangent.magSq() > len*len/scaling) return;
        
        double dist = relPos.dot(normal);
        double dist2 = a.pos.plus(a.vel.times(dt*100)).minus(pos).dot(normal);
        //System.out.println(relPos+", "+normal);
        if (dist*dist2 > 0) return;
        //a.vel = new Vector2();
        
        double relNV = a.vel.dot(normal);
        //System.out.println(relNV);
        //double remove = relNV + dist/dt;

        //double imp = relNV * a.mass;
        
        Vector2 changeVec = normal.times(relNV);
        a.vel = a.vel.minus(changeVec);
    }
}
