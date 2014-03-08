package chargedparticles;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Engine {
    private CPGUI gui;
    private double dt = 0.015;
    public static final double k = 5;
    
    public ArrayList<Particle> particles = new ArrayList<>();
    public ArrayList<Restraint> restraints = new ArrayList<>();

    public Engine(CPGUI gui) {
        this.gui = gui;
    }
    
    public void start() {
        
        for (int i = 0; i < 0; i++) {
            double r = Math.random()*0.9+1.05;
            double theta = Math.random()*2*Math.PI;
            particles.add(new Particle(new Vector2(r*Math.cos(theta), r*Math.sin(theta)),
                    new Vector2(0, 0), 1, 1.0/20));
        }
        
        for (int i = 0; i < 200; i++) {
            double r = Math.random()*0.9+1.05;
            double theta = Math.random()*2*Math.PI;
            particles.add(new Particle(new Vector2(r*Math.cos(theta), r*Math.sin(theta)),
                    new Vector2(0, 0), 1, -1.0/20));
        }
        
        particles.add(new Particle(new Vector2(0, 0), new Vector2(0, 0), 9999999, 10));
        //particles.add(new Particle(new Vector2(4, 0), new Vector2(0, 0), 9999999, 20));
        //particles.add(new Particle(new Vector2(3, 0), new Vector2(0, 0), 1, -1));
        //particles.add(new Particle(new Vector2(3, 3), new Vector2(0, 0), 1, -1));
        
        //restraints.add(new Restraint(new Vector2(0, 0), 8, new Vector2(1, 1).normalize()));
        
        makeCircle(new Vector2(), 3, 30, false);
        makeCircle(new Vector2(), 1, 30, true);
        
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                step();
            }
        }, 0, (int)(dt*1000));
        
        Timer calc = new Timer();
        calc.schedule(new TimerTask() {
            @Override
            public void run() {
                Calculate.calcPotentials(particles);
            }
        }, 30000);
    }
    
    public void makeCircle(Vector2 center, double r, int n, boolean inside) {
        double rad = 2*Math.PI/n;
        double len = 2*r*Math.tan(rad/2);
        
        for (int i = 0; i < n; i++) {
            double ang = i*rad;
            Vector2 pos = new Vector2(r*Math.cos(ang), r*Math.sin(ang)).plus(center);
            Vector2 normal = pos.minus(center).normalize();
            restraints.add(new Restraint(pos, len, normal, inside));
        }
    }
    
    private void step() {
        gui.draw();
        
        for (Particle p1 : particles) {
            for (Particle p2 : particles) {
                if (p1 == p2) continue;
                p2.esForce(p1);
            }
        }
        
        for (Particle p : particles) {
            p.vel = p.vel.plus(p.force.times(dt/p.mass));
        }
        
        for (Restraint r : restraints) {
            for (Particle p : particles) {
                r.solve(p, dt);
            }
        }
        
        for (Particle p : particles) {
            p.pos = p.pos.plus(p.vel.times(dt));
            p.force = new Vector2();
        }
    }
}
