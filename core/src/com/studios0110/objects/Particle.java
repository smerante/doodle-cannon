package com.studios0110.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import java.util.ArrayList;

public class Particle
{
  Color color;
  float gravity;
  float size;
  float xPos;
  float xSpeed;
  float yPos;
  float ySpeed;
  
  public Particle(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, Color paramColor)
  {
    this.xPos = paramFloat1;
    this.yPos = paramFloat2;
    this.xSpeed = paramFloat3;
    this.ySpeed = paramFloat4;
    this.size = paramFloat5;
    this.gravity = paramFloat6;
    this.color = paramColor;
    Cannon.particles.add(this);
  }
  
  public void draw(ShapeRenderer paramShapeRenderer, float paramFloat)
  {
    paramShapeRenderer.set(ShapeRenderer.ShapeType.Filled);
    paramShapeRenderer.setColor(this.color);
    paramShapeRenderer.circle(this.xPos, this.yPos, this.size);
    update(paramFloat);
  }
  
  void update(float paramFloat)
  {
    this.size -= 20.0F * paramFloat;
    this.xPos += this.xSpeed * paramFloat;
    this.yPos += this.ySpeed * paramFloat;
    this.ySpeed += this.gravity * paramFloat * 40.0F;
  }
}


/* Location:              C:\Users\sam\Documents\ballin\DoodleCannon-dex2jar.jar!\com\studios0110\objects\Particle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */