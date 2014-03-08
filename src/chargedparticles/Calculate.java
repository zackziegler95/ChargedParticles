package chargedparticles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculate {
    private static double[][] expected = new double[10201][3];
    private static double[][] experimental = new double[10201][3];
    
    public static void calcPotentials(ArrayList<Particle> particles) {
        readValues("real.txt", false);
        readValues("output.txt", true);
        
        for (int i = 0; i < expected.length; i++) {
            //System.out.println("{"+expected[i][0]+", "+expected[i][1]+", "+expected[i][2]+"}, {"
            //        +experimental[i][0]+", "+experimental[i][1]+", "+experimental[i][2]+"}");
            System.out.println("{"+expected[i][0]+", "+expected[i][1]+", "+(expected[i][2]-experimental[i][2])+"}, ");
        }//*/
        
        /*double dx = CPGUI.realWidth/100;
        double dy = CPGUI.realHeight/100;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
            bw.write("{");
            
            int i = 0;
            for (double x = -CPGUI.realWidth/2; x < CPGUI.realWidth/2; x += dx) {
                if (x < 0.00001 && x > -0.00001) x = 0;
                for (double y = -CPGUI.realHeight/2; y < CPGUI.realHeight/2; y += dy) {
                    if (y < 0.00001 && y > -0.00001) y = 0;
                    double V = 0;
                    for (Particle p : particles) {
                        Vector3 r = new Vector3(x, y, 0).minus(p.pos);
                        V += Engine.k*p.charge/r.mag();
                    }
                    bw.write("{"+x+", "+y+", "+V+"},");
                    //System.out.println("{"+x+", "+y+", "+V+"},");
                    //System.out.println("{"+x+", "+y+", "+(V-expected[i][2])+"},");
                    //System.out.println("("+expected[i][0]+", "+expected[i][1]+", "++")");
                    i++;
                }
            }
            
            bw.write("}");
            bw.close();
            System.out.println("Done writing.");
        } catch (IOException ex) {
            Logger.getLogger(Calculate.class.getName()).log(Level.SEVERE, null, ex);
        }//*/
    }
    
    private static void readValues(String name, boolean experiment) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(name));
            String input;
            StringBuilder sb = new StringBuilder();
            
            while ((input = br.readLine()) != null) {
                sb.append(input);
            }
            
            sb.deleteCharAt(0);
            sb.deleteCharAt(sb.length()-1);
            
            Pattern p = Pattern.compile("\\{([^,]*), ([^,]*), ([^,]*)\\}");
            Matcher m = p.matcher(sb);
            
            int i = 0;
            while (m.find()) {
                double d1 = Double.parseDouble(m.group(1));
                double d2 = Double.parseDouble(m.group(2));
                double d3 = m.group(3).equals("ComplexInfinity") ? 9999999 : Double.parseDouble(m.group(3));
                if (experiment) experimental[i] = new double[]{d1, d2, d3};
                else expected[i] = new double[]{d1, d2, d3};
                i++;
            }
            
        } catch (IOException ex) {
            System.err.println("Error, unable to read file");
            System.exit(1);
        }
    }
}
