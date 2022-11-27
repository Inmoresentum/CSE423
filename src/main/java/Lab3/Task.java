package Lab3;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

class ThirdGLEventListener implements GLEventListener {
    private ArrayList<Pair> drawingPixels;

    /**
     * Take care of initialization here.
     */
    public void init(GLAutoDrawable gld) {
        GL2 gl = gld.getGL().getGL2();
        /*
         * Interface to the GLU library.
         */
        GLU glu = new GLU();

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
        gl.glColor3d(1, 0, 0);
        gl.glPointSize(2.5f);
        gl.glBegin(GL2.GL_POINTS);
        for (Pair pair : drawingPixels) {
            gl.glVertex2d(pair.x, pair.y);
        }
        gl.glEnd();
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width,
                        int height) {
    }

    @SuppressWarnings("unused")
    public void displayChanged(GLAutoDrawable drawable,
                               boolean modeChanged, boolean deviceChanged) {
    }

    public void dispose(GLAutoDrawable arg0) {

    }

    public void setDrawingPixels(ArrayList<Pair> drawingPixels) {
        this.drawingPixels = drawingPixels;
    }
}

@SuppressWarnings("SuspiciousNameCombination")
class MidPointCircleCalculation {
    public ArrayList<Pair> takeInputAndInitialize() throws IOException {
        PrintWriter pw = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        pw.print("Please enter the center of the circle seperated by a space : ");
        pw.flush();
        StringTokenizer tk = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(tk.nextToken()), y = Integer.parseInt(tk.nextToken());
        Pair center = new Pair(x, y);
        pw.print("Please enter the radius of the circle : ");
        pw.flush();
        tk = new StringTokenizer(br.readLine());
        int radius = Integer.parseInt(tk.nextToken());
        pw.print("Please enter the number of inner circle : ");
        pw.close();
        tk = new StringTokenizer(br.readLine());
        int numberOfInnerCircle = Integer.parseInt(tk.nextToken());
        br.close();
        ArrayList<Pair> drawingPixels = findPixelsForInnerCircle(radius, center, numberOfInnerCircle);
        drawingPixels.addAll(findDrawingPixels(center, radius));
        return drawingPixels;
    }

    private ArrayList<Pair> findPixelsForInnerCircle(int radiusOfTheOuterCircle, Pair centerOfTheOuterCircle,
                                                     int numberOfInnerCircle) {
        final double ONE_FULL_REVOLUTION = 2 * Math.PI;
        final double RADIUS_OF_INNER_CIRCLE = radiusOfTheOuterCircle / 2.0;
        ArrayList<Pair> pixels = new ArrayList<>();
        double delTheta = ONE_FULL_REVOLUTION / numberOfInnerCircle;
        double curThetaValue = 0.0;
        while (curThetaValue < ONE_FULL_REVOLUTION) {
            int x = (int) Math.round((Math.cos(curThetaValue) * RADIUS_OF_INNER_CIRCLE));
            int y = (int) Math.round((Math.sin(curThetaValue) * RADIUS_OF_INNER_CIRCLE));
            // Now it's time to convert x, y relative to the center of the Outer Circle.
            x = centerOfTheOuterCircle.x + x;
            y = centerOfTheOuterCircle.y + y;
            pixels.addAll(findDrawingPixels(new Pair(x, y), (int) RADIUS_OF_INNER_CIRCLE));
            curThetaValue += delTheta;
        }
        return pixels;
    }

    private ArrayList<Pair> findDrawingPixels(Pair center, int radius) {
        ArrayList<Pair> zoneOnePixels = midPointCircle(radius);
        zoneOnePixels.addAll(findPixelsForOtherZone(zoneOnePixels));
        ArrayList<Pair> originalPixels = new ArrayList<>();
        for (Pair pair : zoneOnePixels) {
            originalPixels.add(findOriginalPixels(pair, center));
        }
        return originalPixels;
    }

    private ArrayList<Pair> findPixelsForOtherZone(ArrayList<Pair> zoneOneOriginalPixel) {
        ArrayList<Pair> temp = new ArrayList<>();
        for (Pair pair : zoneOneOriginalPixel) {
            temp.add(findPixelForZoneZero(pair));
            temp.add(findPixelForZoneTwo(pair));
            temp.add(findPixelForZoneThree(pair));
            temp.add(findPixelForZoneFour(pair));
            temp.add(findPixelForZoneFive(pair));
            temp.add(findPixelForZoneSix(pair));
            temp.add(findPixelForZoneSeven(pair));
        }
        return temp;
    }

    private ArrayList<Pair> midPointCircle(int radius) {
        ArrayList<Pair> pixels = new ArrayList<>();
        int d = 1 - radius;
        int x = 0, y = radius;
        while (x <= y) {
            pixels.add(new Pair(x, y));
            if (d >= 0) {
                y--;
                d += x + x - y - y + 5;
            } else {
                d += x + x + 3;
            }
            x++;
        }
        return pixels;
    }

    private Pair findOriginalPixels(Pair zoneOnePixel, Pair originalCenter) {
        return new Pair(zoneOnePixel.x + originalCenter.x,
                zoneOnePixel.y + originalCenter.y);
    }

    private Pair findPixelForZoneZero(Pair zoneOnePixel) {
        return new Pair(zoneOnePixel.y, zoneOnePixel.x);
    }

    private Pair findPixelForZoneTwo(Pair zoneOnePixel) {
        return new Pair(-zoneOnePixel.x, zoneOnePixel.y);
    }

    private Pair findPixelForZoneThree(Pair zoneOnePixel) {
        return new Pair(-zoneOnePixel.y, zoneOnePixel.x);
    }

    private Pair findPixelForZoneFour(Pair zoneOnePixel) {
        return new Pair(-zoneOnePixel.y, -zoneOnePixel.x);
    }

    private Pair findPixelForZoneFive(Pair zoneOnePixel) {
        return new Pair(-zoneOnePixel.x, -zoneOnePixel.y);
    }

    private Pair findPixelForZoneSix(Pair zoneOnePixel) {
        return new Pair(zoneOnePixel.x, -zoneOnePixel.y);
    }

    private Pair findPixelForZoneSeven(Pair zoneOnePixel) {
        return new Pair(zoneOnePixel.y, -zoneOnePixel.x);
    }
}

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Task {
    public static void main(String[] args) throws IOException {
        //getting the capability object of GL2 profile
        MidPointCircleCalculation midPointCircleCalculation = new MidPointCircleCalculation();
        ArrayList<Pair> pixels = midPointCircleCalculation.takeInputAndInitialize();
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        ThirdGLEventListener b = new ThirdGLEventListener();
        b.setDrawingPixels(pixels);
        glcanvas.addGLEventListener(b);
        glcanvas.setSize(400, 400);
        //creating frame
        final JFrame frame = new JFrame("Lab 3");
        //adding canvas to frame
        frame.add(glcanvas);
        frame.setSize(640, 480);
        frame.setVisible(true);
    }
}