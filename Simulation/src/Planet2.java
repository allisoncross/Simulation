package org.opensourcephysics.sip.ch05;

import java.awt.Graphics;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.Drawable;
import org.opensourcephysics.display.DrawingPanel;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.numerics.ODE;
import org.opensourcephysics.numerics.ODESolver;
import org.opensourcephysics.numerics.RK45MultiStep;
import org.opensourcephysics.numerics.Verlet;

public class Planet2 implements Drawable, ODE {
    static final double GM = 39.47841760435743;
    static final double GM1 = 1.5791367041742974;
    static final double GM2 = 0.039478417604357434;
    double[] state = new double[9];
    ODESolver odeSolver = new RK45MultiStep(this);
    ODESolver odeSolver2 = new Verlet(this);
    Mass mass1 = new Mass();
    Mass mass2 = new Mass();

    public Planet2() {
    }

    public void doStep() {
        this.odeSolver2.step();
        this.mass1.setXY(this.state[0], this.state[2]);
        this.mass2.setXY(this.state[4], this.state[6]);
    }

    public void draw(DrawingPanel var1, Graphics var2) {
        this.mass1.draw(var1, var2);
        this.mass2.draw(var1, var2);
    }

    void initialize(double[] var1) {
        System.arraycopy(var1, 0, this.state, 0, var1.length);
        this.mass1.clear();
        this.mass2.clear();
        this.mass1.setXY(this.state[0], this.state[2]);
        this.mass2.setXY(this.state[4], this.state[6]);
    }

    public void getRate(double[] var1, double[] var2) {
        double var3 = var1[0] * var1[0] + var1[2] * var1[2];
        double var5 = var3 * Math.sqrt(var3);
        double var7 = var1[4] * var1[4] + var1[6] * var1[6];
        double var9 = var7 * Math.sqrt(var7);
        double var11 = var1[4] - var1[0];
        double var13 = var1[6] - var1[2];
        double var15 = var11 * var11 + var13 * var13;
        double var17 = Math.sqrt(var15) * var15;
        var2[0] = var1[1];
        var2[2] = var1[3];
        var2[4] = var1[5];
        var2[6] = var1[7];
        var2[1] = -39.47841760435743 * var1[0] / var5 + 1.5791367041742974 * var11 / var17;
        var2[3] = -39.47841760435743 * var1[2] / var5 + 1.5791367041742974 * var13 / var17;
        var2[5] = -39.47841760435743 * var1[4] / var9 - 0.039478417604357434 * var11 / var17;
        var2[7] = -39.47841760435743 * var1[6] / var9 - 0.039478417604357434 * var13 / var17;
        var2[8] = 1.0;
    }

    public double[] getState() {
        return this.state;
    }

    class Mass extends Circle {
        Trail trail = new Trail();

        Mass() {
        }

        public void draw(DrawingPanel var1, Graphics var2) {
            this.trail.draw(var1, var2);
            super.draw(var1, var2);
        }

        void clear() {
            this.trail.clear();
        }

        public void setXY(double var1, double var3) {
            super.setXY(var1, var3);
            this.trail.addPoint(var1, var3);
        }
    }
}

