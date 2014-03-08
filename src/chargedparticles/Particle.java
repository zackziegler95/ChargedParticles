package chargedparticles;

public class Particle {
    public Vector3 pos;
    public Vector3 vel;
    public Vector3 force;
    public double mass;
    public double charge;
    
    public Particle(Vector3 pos, Vector3 vel, double mass, double charge) {
        this.pos = new Vector3(pos);
        this.vel = new Vector3(vel);
        this.force = new Vector3();
        this.mass = mass;
        this.charge = charge;
    }
    
    public void esForce(Particle p) {
        Vector3 r = p.pos.minus(pos);
        double rSq = r.magSq();
        
        if (rSq < 0.000001) return;
        
        Vector3 newForce = r.times(Engine.k*charge*p.charge/r.magSq());
        p.force = p.force.plus(newForce);
    }
}
