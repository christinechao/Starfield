import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Starfield extends PApplet {

Particle [] particleList;

public void setup()
{
  background(255);
  size(1200, 800);
  particleList = new Particle[800];
  colorMode(HSB);
  for (int i=2; i< particleList.length -2; i++) {
    int h = (int)(Math.random()*255);
    int someSize = (int)(Math.random()*30)+3;
    particleList[i] = new NormalParticle(600, 400, someSize, h, 255/2, 255);
  }
  int x= (int)(Math.random()*570) +30;
  int y= (int)(Math.random()*370)+30;
  particleList[0] = new OddballParticle (x, y);
  particleList[1] = new JumboParticle();
}

public void draw()
{
  background(255);
  for (int i=2; i< particleList.length-2; i++) {
    particleList[i].move();
    particleList[i].show();
  }
  particleList[0].move();
  particleList[0].show();
  particleList[1].move();
  particleList[1].show();
}

public void mousePressed() {
  if (mouseButton == LEFT) {
    particleList = new Particle[800];
    colorMode(HSB);
    for (int i=2; i< particleList.length-2; i++) {
      int h = (int)(Math.random()*255);
      int someSize = (int)(Math.random()*30)+3;
      particleList[i] = new NormalParticle(mouseX, mouseY, someSize, h, 255/2, 255);
    }
    int x= (int)(Math.random()*570) +30;
    int y= (int)(Math.random()*370)+30;
    particleList[0] = new OddballParticle (x, y);
    particleList[1] = new JumboParticle();
  }
}

class NormalParticle implements Particle 
{
  double myX, myY, mySpeed, myAngle;
  int mySize, myR, myG, myB;

  NormalParticle(double x, double y, int theSize, int r, int g, int b) {
    mySize = theSize;
    myX = x;
    myY = y;
    myR = r;
    myG = g;
    myB = b;
    mySpeed = Math.random()*5;
    myAngle = Math.random()*2*Math.PI;
  }

  NormalParticle() {
    mySize = 10;
    myX = 600;
    myY = 400;
    myR = (int)(Math.random()*255);
    myG = 255/2;
    myB = 255;
    mySpeed = Math.random()*5;
    myAngle = Math.random()*2*Math.PI;
  }

  public void move() {
    myX = Math.cos(myAngle) * mySpeed + myX;
    myY = Math.sin(myAngle)* mySpeed + myY;
  }

  public void show() {
    noStroke();
    fill(myR, myG, myB);
    ellipse((int)myX, (int)myY, mySize, mySize);
  }
}

interface Particle
{
  public void show();
  public void move();
}

class OddballParticle implements Particle 
{
  int myX, myY;
  OddballParticle(int x, int y) {
    myX  = x;
    myY = y;
  }

  public void move() {
    myX = myX +(int)(Math.random()*7)-3;
    myY = myY +(int)(Math.random()*7)-3;
  }

  public void show() {
    fill(0xff9A56FF);
    float angle = TWO_PI / 5;
    float halfAngle = angle/2.0f;
    beginShape();
    for (float a = 0; a < TWO_PI; a += angle) {
      float sx = myX + cos(a) * 5;
      float sy = myY + sin(a) * 5;
      vertex(sx, sy);
      sx = myX + cos(a+halfAngle) * 10;
      sy = myY + sin(a+halfAngle) * 10;
      vertex(sx, sy);
    }
    endShape(CLOSE);
  }
}

class JumboParticle extends NormalParticle {
  JumboParticle() {
    mySize = 100;
    myX = 600;
    myY = 400;
  }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Starfield" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
