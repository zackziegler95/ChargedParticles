package chargedparticles;

public class Particle {
    public Vector2 pos;
    public Vector2 vel;
    public Vector2 force;
    public double mass;
    public double charge;
    
    public Particle(Vector2 pos, Vector2 vel, double mass, double charge) {
        this.pos = new Vector2(pos);
        this.vel = new Vector2(vel);
        this.force = new Vector2(0, 0);
        this.mass = mass;
        this.charge = charge;
    }
    
    public void esForce(Particle p) {
        Vector2 r = p.pos.minus(pos);
        double rSq = r.magSq();
        
        if (rSq < 0.001) return;
        
        Vector2 newForce = r.times(Engine.k*charge*p.charge/r.magSq());
        p.force = p.force.plus(newForce);
    }
}
