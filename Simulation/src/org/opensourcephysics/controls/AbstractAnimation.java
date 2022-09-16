package org.opensourcephysics.controls;

import java.awt.Frame;
import java.text.DecimalFormat;
import java.util.Collection;
import javax.swing.JFrame;
import org.opensourcephysics.display.OSPFrame;

public abstract class AbstractAnimation implements Animation, Runnable {
    protected OSPFrame mainFrame;
    protected Control control;
    protected volatile Thread animationThread;
    protected int delayTime = 100;
    protected DecimalFormat decimalFormat = new DecimalFormat("0.00E0");

    public AbstractAnimation() {
    }

    public void setControl(Control var1) {
        this.control = var1;
        this.mainFrame = null;
        if (var1 != null) {
            if (var1 instanceof MainFrame) {
                this.mainFrame = ((MainFrame)var1).getMainFrame();
            }

            var1.setLockValues(true);
            this.resetAnimation();
            var1.setLockValues(false);
            if (var1 instanceof Frame) {
                ((Frame)var1).pack();
            }
        }

    }

    public void setDelayTime(int var1) {
        this.delayTime = var1;
    }

    public int getDelayTime() {
        return this.delayTime;
    }

    public OSPFrame getMainFrame() {
        return this.mainFrame;
    }

    public OSPApplication getOSPApp() {
        return this.control instanceof MainFrame ? ((MainFrame)this.control).getOSPApp() : null;
    }

    public void addChildFrame(JFrame var1) {
        if (this.mainFrame != null && var1 != null) {
            this.mainFrame.addChildFrame(var1);
        }
    }

    public void clearChildFrames() {
        if (this.mainFrame != null) {
            this.mainFrame.clearChildFrames();
        }
    }

    public Collection<JFrame> getChildFrames() {
        return this.mainFrame.getChildFrames();
    }

    public Control getControl() {
        return this.control;
    }

    public void initializeAnimation() {
        this.control.clearMessages();
    }

    protected abstract void doStep();

    public synchronized void stopAnimation() {
        if (this.animationThread != null) {
            Thread var1 = this.animationThread;
            this.animationThread = null;
            if (Thread.currentThread() != var1) {
                try {
                    var1.interrupt();
                    var1.join(1000L);
                } catch (Exception var3) {
                }

            }
        }
    }

    public final boolean isRunning() {
        return this.animationThread != null;
    }

    public synchronized void stepAnimation() {
        if (this.animationThread != null) {
            this.stopAnimation();
        }

        this.doStep();
    }

    public synchronized void startAnimation() {
        if (this.animationThread == null) {
            this.animationThread = new Thread(this);
            this.animationThread.setPriority(5);
            this.animationThread.setDaemon(true);
            this.animationThread.start();
        }
    }

    public void resetAnimation() {
        if (this.animationThread != null) {
            this.stopAnimation();
        }

        this.control.clearMessages();
    }

    public void run() {
        long var1 = (long)this.delayTime;

        while(this.animationThread == Thread.currentThread()) {
            long var3 = System.currentTimeMillis();
            this.doStep();
            var1 = Math.max(10L, (long)this.delayTime - (System.currentTimeMillis() - var3));

            try {
                Thread.sleep(var1);
            } catch (InterruptedException var6) {
            }
        }

    }

    public static XML.ObjectLoader getLoader() {
        return new OSPAnimationLoader();
    }

    static class OSPAnimationLoader extends XMLLoader {
        OSPAnimationLoader() {
        }

        public Object loadObject(XMLControl var1, Object var2) {
            ((Animation)var2).initializeAnimation();
            return var2;
        }
    }
}
