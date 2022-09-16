package org.opensourcephysics.sip.ch05;
import org.opensourcephysics.controls.*;
import org.opensourcephysics.controls.AbstractAnimation;
import org.opensourcephysics.frames.*;
import org.opensourcephysics.sip.ch05.Planet2;
import org.opensourcephysics.controls.*;
import java.util.Collection;

//import org.opensourcephysics.tools.FunctionEditor.decimalFormat;


public class PlanetApp {
    PlotFrame frame = new PlotFrame("x (AU)", "y (AU)", "Planet Simulation");
    Planet2 planet = new Planet2();
    Control control = new OSPControl("a");
    public PlanetApp(){
        frame.addDrawable(new Planet2());
        frame.setPreferredMinMax(-5, 5, -5, 5);
        frame.setSquareAspect(true);
    }

    public void doStep(){
        for(int i = 0; i<5;i++){
            planet.doStep();
        }
       // frame.setMessage("t = " + decimalFormat.format(planet.state[4]));
        this.planet.odeSolver.setStepSize(this.control.getDouble("dt"));
    }

    public void initialize(){
        planet.odeSolver.setStepSize(this.control.getDouble("dt"));
        double x = control.getDouble("x");
        double vx = control.getDouble("vx");
        double y = control.getDouble("vy");
        double vy = control.getDouble("vy");
        double[] arr = {x, vx, y, vy, 0};
        planet.initialize(arr);
        frame.setMessage("t - 0");
    }

    public void reset(){
        control.setValue("x", 1);
        control.setValue("vx", 0);
        control.setValue("y", 0);
        control.setValue("dy", 6.28);
        control.setValue("dt", 0.01);
        initialize();
    }
    public static void main(String[] args){
      SimulationControl.createApp(new PlanetApp());
    }
}
