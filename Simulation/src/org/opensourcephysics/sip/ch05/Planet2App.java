//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.opensourcephysics.sip.ch05;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.frames.PlotFrame;

public class Planet2App extends AbstractSimulation {
    PlotFrame frame = new PlotFrame("x (AU)", "y (AU)", "Planet Program");
    Planet2 planet2 = new Planet2();

    public Planet2App() {
        this.frame.addDrawable(this.planet2);
        this.frame.setPreferredMinMax(-10.0, 10.0, -10.0, 10.0);
        this.frame.setSquareAspect(true);
    }

    public void doStep() {
        for(int var1 = 0; var1 < 5; ++var1) {
            this.planet2.doStep();
        }

        this.frame.setMessage("t=" + this.decimalFormat.format(this.planet2.state[4]));
    }

    public void initialize() {
        this.planet2.odeSolver.setStepSize(this.control.getDouble("dt"));
        double var1 = this.control.getDouble("x1");
        double var3 = this.control.getDouble("vy1");
        double var5 = this.control.getDouble("x2");
        double var7 = this.control.getDouble("vy2");
        this.planet2.initialize(new double[]{var1, 0.0, 0.0, var3, var5, 0.0, 0.0, var7, 0.0});
        this.frame.setMessage("t=0");
    }

    public void reset() {
        this.control.setValue("x1", 2.52);
        this.control.setValue("vy1", Math.sqrt(15.66603873188787));
        this.control.setValue("x2", 5.24);
        this.control.setValue("vy2", Math.sqrt(7.534049161136914));
        this.control.setValue("dt", 0.01);
        this.initialize();
    }

    public static void main(String[] var0) {
        SimulationControl.createApp(new Planet2App());
    }
}
