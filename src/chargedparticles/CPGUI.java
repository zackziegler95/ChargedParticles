package chargedparticles;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class CPGUI extends javax.swing.JFrame {
    public static final double realWidth = 10;
    public static final double realHeight = 10;
    
    public CPGUI() {
        initComponents();
        Engine e = new Engine(this);
        e.start();
        mainPanel.particles = e.particles;
        mainPanel.restraints = e.restraints;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new MainPanel();
        javax.swing.JPanel mainPanel = this.mainPanel;

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void draw() {
        mainPanel.repaint();
    }
    
    public class MainPanel extends JPanel {
        public ArrayList<Particle> particles = new ArrayList<>();
        public ArrayList<Restraint> restraints = new ArrayList<>();
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            for (Particle p : particles) {
                if (p.charge > 0) g.setColor(Color.RED);
                else g.setColor(Color.BLACK);
                
                int r = 5;
                g.fillOval((int)(p.pos.x()*getWidth()/realWidth+getWidth()/2)-r,
                        (int)(-p.pos.y()*getHeight()/realHeight+getHeight()/2)-r, (int)(2*r), (int)(2*r));
            }
            
            g.setColor(Color.BLACK);
            for (Restraint r : restraints) {
                Vector2 tangent = r.normal.perp().times(r.len/2.0);
                Vector2 p1 = r.pos.plus(tangent);
                Vector2 p2 = r.pos.minus(tangent);
                
                g.drawLine((int)(p1.x()*getWidth()/realWidth+getWidth()/2),
                        (int)(-p1.y()*getHeight()/realHeight+getHeight()/2),
                        (int)(p2.x()*getWidth()/realWidth+getWidth()/2),
                        (int)(-p2.y()*getHeight()/realHeight+getHeight()/2));
            }
        }
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CPGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CPGUI().setVisible(true);
            }
        });
    }
    
    private MainPanel mainPanel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
