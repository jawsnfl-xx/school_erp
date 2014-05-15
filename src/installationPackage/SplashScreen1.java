/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package installationPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SplashScreen1 extends JWindow {

    private static JProgressBar progressBar = new JProgressBar();
    private static SplashScreen1 splashScreen;
    private static int count = 1, TIMER_PAUSE = 100,PROGBAR_MAX=100;
    private static Timer progressBarTimer;
    ActionListener al = new ActionListener() {

        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            progressBar.setValue(count);
            if (PROGBAR_MAX == count) {
                splashScreen.dispose();//dispose of splashscreen
                progressBarTimer.stop();//stop the timer
                startServer s = new startServer();
                s.start();
                s.startSystem();
            }
            count++;//increase counter

        }
    };

    public SplashScreen1() {
        createSplash();
    }

    private void createSplash() {
        Container container = getContentPane();

        JPanel panel = new JPanel();
        panel.setBorder(new javax.swing.border.EmptyBorder(-5,-5,-5,-5));
        container.add(panel, BorderLayout.CENTER);

        JLabel label = new JLabel();
        ImageIcon screen = new ImageIcon("src/installationPackage/splash.jpg");
        label.setIcon(screen);
        panel.add(label);

        progressBar.setMaximum(PROGBAR_MAX);
        progressBar.setBorderPainted(false);
        progressBar.setForeground(Color.green);
        container.add(progressBar, BorderLayout.SOUTH);


        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        startProgressBar();
    }

    private void startProgressBar() {
        progressBarTimer = new Timer(TIMER_PAUSE, al);
        progressBarTimer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                splashScreen = new SplashScreen1();
            }
        });
    }
}