package chargedparticles;

import java.util.ArrayList;

public class Calculate {
    public static void calcPotentials(ArrayList<Particle> particles) {
        double dx = CPGUI.realWidth/100;
        double dy = CPGUI.realHeight/100;
        
        for (double x = -CPGUI.realWidth/2; x < CPGUI.realWidth/2; x += dx) {
            if (x < 0.00001 && x > -0.00001) x = 0;
            for (double y = -CPGUI.realHeight/2; y < CPGUI.realHeight/2; y += dy) {
                if (y < 0.00001 && y > -0.00001) y = 0;
                double V = 0;
                for (Particle p : particles) {
                    Vector2 r = new Vector2(x, y).minus(p.pos);
                    V += Engine.k*p.charge/r.mag();
                }
                System.out.println("{"+x+", "+y+", "+V+"},");
            }
        }
    }
}
