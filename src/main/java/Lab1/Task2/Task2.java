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

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
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
        gl.glColor3d(1, 1, 1);  // r g b
        // Size of the point
        gl.glPointSize(10.0f);

        drawTriangle(gl, 0, 100, -100, 0, 100, 0);

        // Outer One
        drawRectangle(gl, 80, 0, -80, 0, -80, -80, 80, -80);
        // Inner 1st one
        drawRectangle(gl, -15, -15, -50, -15, -50, -35, -15, -35);
        // Inner 2nd one
        drawRectangle(gl, 15, -15, 50, -15, 50, -35, 15, -35);
        // Inner 3rd One
        drawRectangle(gl, 15, -45, -15, -45, -15, -75, 15, -75);

        // Size of the point
        gl.glPointSize(3.0f);
        gl.glBegin(GL2.GL_POINTS);
        // Point on the door
        gl.glVertex2d(10, -65);

        gl.glEnd();

    }

    @SuppressWarnings("DuplicatedCode")
    private static void drawRectangle(GL2 gl, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2d(x1, y1);
        gl.glVertex2d(x2, y2);

        gl.glVertex2d(x2, y2);
        gl.glVertex2d(x3, y3);

        gl.glVertex2d(x3, y3);
        gl.glVertex2d(x4, y4);

        gl.glVertex2d(x4, y4);
        gl.glVertex2d(x1, y1);
        gl.glEnd();
    }

    @SuppressWarnings("DuplicatedCode")
    private static void drawTriangle(GL2 gl, int x1, int y1, int x2, int y2, int x3, int y3) {
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2d(x1, y1);
        gl.glVertex2d(x2, y2);

        gl.glVertex2d(x2, y2);
        gl.glVertex2d(x3, y3);

        gl.glVertex2d(x3, y3);
        gl.glVertex2d(x1, y1);

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
        //getting the capability object of GL2 profile
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