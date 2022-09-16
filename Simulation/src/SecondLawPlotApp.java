import org.opensourcephysics.frames.PlotFrame;

import javax.swing.*;

public class SecondLawPlotApp {
    public static void main(String[] args){
        PlotFrame frame = new PlotFrame("ln(a)", "ln(T)", "Kepler's 2nd law");

        frame.setLogScale(false, true);
        frame.setConnected(false);
        double[] period = {0.241, 0.615, 1, 1.88, 11.86, 29.5, 84, 165, 248};
        double[] a = {0.387, 0.723, 1, 1.523, 5.202, 9.539, 19.18, 30.06, 39.44};
        double[] x = {0, 0.5, 1, 1.5, 2, 2.5};
        double[] y1 = {0, 0.75, 3, 6.75, 12, 18.75};
        frame.append(0, x, y1);
        frame.setVisible(true);
        frame.setXYColumnNames(0, "T (years", "a (AU)");
        frame.showDataTable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
