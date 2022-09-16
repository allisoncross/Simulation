//package org.opensourcephysics.sip.ch02;

import org.opensourcephysics.frames.PlotFrame;

public class PlotFrameApp {
    public static void main(String[] args) {
        PlotFrame frame = new PlotFrame("x", "sim(x)/x", "Example");
        for (int i = 1; i <= 100; i++) {
            double x = i + 0.2;
            frame.append(0, x, Math.sin(x)/x);
        }
        frame.setVisible(true);
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }
}
