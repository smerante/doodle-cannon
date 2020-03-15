package com.studios0110.objects;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.studios0110.levels.Level;
import com.studios0110.splash.Splash;
import java.util.ArrayList;
import java.util.Random;

public class Ball
{
  static Random r = new Random(System.currentTimeMillis());
  public float angle;
  Sprite ballSprite;
  public Texture ballTexture;
  public Circle bounds;
  private float currentDrawDot = 0.0F;
  private float drawDelay = 0.0F;
  public boolean finishedDrawing = false;
  private float pathDelay = -0.01F;
  public float posX;
  public float posY;
  public float power;
  public float velocityX;
  public float velocityY;
  public float weight;
  public boolean win = false;
  private float winPSize = 0.0F;
  ArrayList<Circle> winPath;
  
  protected Ball(String paramString, Cannon paramCannon, float paramFloat)
  {
    this.weight = paramFloat;
    this.ballTexture = ((Texture)Splash.manager.get("objects/" + paramString + ".png"));
    this.ballSprite = new Sprite(this.ballTexture);
    this.angle = paramCannon.angle;
    this.power = paramCannon.power;
    this.velocityX = ((float)(Math.cos(this.angle * 3.141592653589793D / 180.0D) * this.power));
    this.velocityY = ((float)(Math.sin(this.angle * 3.141592653589793D / 180.0D) * this.power));
    this.posX = (paramCannon.cannonBarrel.getTransformedVertices()[2] - this.ballTexture.getWidth() / 2);
    this.posY = (paramCannon.cannonBarrel.getTransformedVertices()[3] - this.ballTexture.getHeight() / 2);
    this.ballSprite.setX(this.posX);
    this.ballSprite.setY(this.posY);
    this.bounds = new Circle(this.posX - this.ballTexture.getWidth() / 2, this.posY - this.ballTexture.getWidth() / 2, this.ballTexture.getWidth() / 2);
    this.winPath = new ArrayList();
  }
  
  private void update(float paramFloat)
  {
    if (!this.win) {
      this.pathDelay += paramFloat;
    }
    if ((!this.win) && (this.posY > -100.0F) && (this.pathDelay > 0.02D))
    {
      this.winPath.add(new Circle(this.bounds.x, this.bounds.y, 1.0F));
      this.winPSize += 1.0F;
      this.pathDelay = 0.0F;
    }
    float f2 = this.posX;
    float f1 = this.posY;
    this.posX += this.velocityX * paramFloat;
    this.posY += this.velocityY * paramFloat;
    if (Math.abs(this.velocityY) < 1000.0F) {
      this.velocityY += Level.GRAVITY * paramFloat * this.weight;
    }
    this.bounds.setX(this.posX + this.ballTexture.getWidth() / 2);
    this.bounds.setY(this.posY + this.ballTexture.getWidth() / 2);
    this.ballSprite.setX(this.posX);
    this.ballSprite.setY(this.posY);
    f2 = this.posX - f2;
    f1 = this.posY - f1;
    this.angle = ((float)(Math.atan(f1 / f2) * 180.0D / 3.141592653589793D));
    if ((f2 <= 0.0F) && (f1 >= 0.0F)) {
      this.angle += 180.0F;
    }
    if ((f2 <= 0.0F) && (f1 <= 0.0F)) {
      this.angle += 180.0F;
    }
    if ((f2 >= 0.0F) && (f1 <= 0.0F)) {
      this.angle += 360.0F;
    }
    this.power = ((float)Math.sqrt(f2 * f2 + f1 * f1) * (1.0F / paramFloat));
  }
  
  protected void draw(SpriteBatch paramSpriteBatch, float paramFloat)
  {
    this.ballSprite.draw(paramSpriteBatch);
    if (this.velocityX <= 0.0F) {
      this.ballSprite.rotate(1.0F * Math.abs(this.velocityX / 60.0F));
    }
    for (;;)
    {
      if (!this.win) {
        update(paramFloat);
      }
      this.drawDelay += paramFloat;
      return;
      this.ballSprite.rotate(-1.0F * Math.abs(this.velocityX / 60.0F));
    }
  }
  
  protected void drawMesh(ShapeRenderer paramShapeRenderer)
  {
    paramShapeRenderer.circle(this.bounds.x, this.bounds.y, this.bounds.radius);
  }
  
  protected void drawWinPath(ShapeRenderer paramShapeRenderer)
  {
    int i;
    if (this.win)
    {
      if ((this.currentDrawDot < this.winPath.size()) && (this.drawDelay >= 1.0F / (this.winPSize / 2.0F)))
      {
        this.currentDrawDot += 1.0F;
        this.drawDelay = 0.0F;
      }
      i = 0;
    }
    for (;;)
    {
      if (i >= this.currentDrawDot) {
        return;
      }
      paramShapeRenderer.setColor(Color.BLACK);
      paramShapeRenderer.circle(((Circle)this.winPath.get(i)).x, ((Circle)this.winPath.get(i)).y, 3.0F);
      i += 1;
    }
  }
  
  public void setNewVelocity(float paramFloat, Platform paramPlatform)
  {
    int i = 0;
    float f1;
    if (i >= 18)
    {
      if ((Level.GRAVITY != 0.0F) && (this.power > 200.0F)) {
        this.power -= this.weight;
      }
      if (this.power < 150.0F) {
        this.power = 150.0F;
      }
      this.velocityX = ((float)(Math.cos(paramFloat * 3.141592653589793D / 180.0D) * this.power));
      this.velocityY = ((float)(Math.sin(paramFloat * 3.141592653589793D / 180.0D) * this.power));
      if (paramPlatform != null)
      {
        if (paramPlatform.rotationPoints1 == null) {
          break label520;
        }
        paramFloat = paramPlatform.rotationPoints1.getTransformedVertices()[2] - this.posX;
        f1 = paramPlatform.rotationPoints1.getTransformedVertices()[3] - this.posY;
        paramFloat = (float)Math.sqrt(paramFloat * paramFloat + f1 * f1);
        f1 = paramPlatform.rotationPoints2.getTransformedVertices()[2] - this.posX;
        float f2 = paramPlatform.rotationPoints2.getTransformedVertices()[3] - this.posY;
        f1 = (float)Math.sqrt(f1 * f1 + f2 * f2);
        if (Math.abs(paramFloat - f1) > this.ballTexture.getWidth())
        {
          if (paramFloat >= f1) {
            break label471;
          }
          this.velocityX = ((float)(this.velocityX + paramPlatform.rotationPoint1V.x * 1.05D));
          this.velocityY = ((float)(this.velocityY + paramPlatform.rotationPoint1V.y * 1.3D));
        }
      }
    }
    for (;;)
    {
      if (this.velocityY < -1000.0F) {
        this.velocityY = -1000.0F;
      }
      if (this.velocityX < -1000.0F) {
        this.velocityX = -1000.0F;
      }
      if (this.velocityY > 1000.0F) {
        this.velocityY = 1000.0F;
      }
      if (this.velocityX > 1000.0F) {
        this.velocityX = 1000.0F;
      }
      return;
      f1 = i * 20;
      Object localObject = new Random(System.nanoTime());
      localObject = new Color(0.3F + ((Random)localObject).nextFloat(), ((Random)localObject).nextFloat(), ((Random)localObject).nextFloat(), 1.0F);
      new Particle(this.bounds.x, this.bounds.y, -(float)(Math.cos(f1 * 3.141592653589793D / 180.0D) * this.power), -(float)(Math.sin(f1 * 3.141592653589793D / 180.0D) * this.power), 5.0F, 0.0F, (Color)localObject);
      i += 1;
      break;
      label471:
      this.velocityX = ((float)(this.velocityX + paramPlatform.rotationPoint2V.x * 1.05D));
      this.velocityY = ((float)(this.velocityY + paramPlatform.rotationPoint2V.y * 1.3D));
      continue;
      label520:
      if (paramPlatform.addedVelocity.x != 0.0F) {
        this.velocityX = ((float)(this.velocityX + paramPlatform.addedVelocity.x * 1.05D));
      }
      if (paramPlatform.addedVelocity.y != 0.0F) {
        this.velocityY += paramPlatform.addedVelocity.y * 1.3F;
      }
    }
  }
}


/* Location:              C:\Users\sam\Documents\ballin\DoodleCannon-dex2jar.jar!\com\studios0110\objects\Ball.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */