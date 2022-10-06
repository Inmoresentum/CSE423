package Lab1.Task2;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import javax.swing.*;

class ThirdGLEventListener implements GLEventListener {
    /**
     * Interface to the GLU library.
     */
    private GLU glu;

    /**
     * Take care of initialization here.
     */
    public void init(GLAutoDrawable gld) {
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();

        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glViewport(-250, -150, 250, 150);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-250.0, 250.0, -150.0, 150.0);
    }

    /**
     * Take care of drawing here.
     */
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        /*
         * put your code here
         */
        gl.glColor3d(0, 0, 1);  // r g b
        // Size of the point
        gl.glPointSize(10.0f);
        gl.glBegin(GL2.GL_TRIANGLES);

        gl.glVertex2d(0, 100);
        gl.glVertex2d(-100, 0);
        gl.glVertex2d(100, 0);

        gl.glEnd();

        gl.glColor3d(1, 0, 0);
        gl.glPointSize(10.0f);
        gl.glBegin(GL2.GL_QUADS);

        gl.glVertex2d(80, 0);
        gl.glVertex2d(-80, 0);
        gl.glVertex2d(-80, -80);
        gl.glVertex2d(80, -80);

        gl.glColor3d(0, 1, 0);

        gl.glVertex2d(-15, -15);
        gl.glVertex2d(-50, -15);
        gl.glVertex2d(-50, -35);
        gl.glVertex2d(-15, -35);

        gl.glVertex2d(15, -15);
        gl.glVertex2d(50, -15);
        gl.glVertex2d(50, -35);
        gl.glVertex2d(15, -35);

        gl.glVertex2d(15, -45);
        gl.glVertex2d(-15, -45);
        gl.glVertex2d(-15, -75);
        gl.glVertex2d(15, -75);

        gl.glEnd();

        gl.glColor3d(0, 0, 1);  // r g b
        // Size of the point
        gl.glPointSize(3.0f);
        gl.glBegin(GL2.GL_POINTS);

        gl.glVertex2d(10, -65);

        gl.glEnd();

    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width,
                        int height) {
    }

    public void displayChanged(GLAutoDrawable drawable,
                               boolean modeChanged, boolean deviceChanged) {
    }

    public void dispose(GLAutoDrawable arg0) {

    }
}

public class Task2 {
    public static void main(String[] args) {
        //getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        ThirdGLEventListener b = new ThirdGLEventListener();
        glcanvas.addGLEventListener(b);
        glcanvas.setSize(400, 400);
        //creating frame
        final JFrame frame = new JFrame("Lab 1 Task 2");
        //adding canvas to frame
        frame.add(glcanvas);
        frame.setSize(640, 480);
        frame.setVisible(true);
    }
}