package com.studios0110.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class RotatingPlatform
  extends Platform
{
  float rotateSpeed;
  
  public RotatingPlatform(String paramString, Polygon paramPolygon)
  {
    super(paramString, paramPolygon);
  }
  
  public RotatingPlatform(String paramString, Polygon paramPolygon, float paramFloat)
  {
    super(paramString, paramPolygon);
    this.rotateSpeed = paramFloat;
    this.platformSprite.setOrigin(this.platformSprite.getWidth() / 2.0F, this.platformSprite.getHeight() / 2.0F);
    this.platformBounds.setOrigin(this.platformSprite.getX() + this.platformSprite.getWidth() / 2.0F, this.platformSprite.getY() + this.platformSprite.getOriginY());
    this.rotationPoint1V = new Vector2();
    this.rotationPoint2V = new Vector2();
    this.rotationPoints1 = new Polygon();
    this.rotationPoints2 = new Polygon();
    paramFloat = this.platformSprite.getX();
    float f1 = this.platformSprite.getY();
    float f2 = this.platformSprite.getX();
    float f3 = this.platformSprite.getY();
    float f4 = this.platformSprite.getHeight() / 2.0F;
    float f5 = this.platformSprite.getX();
    float f6 = this.platformSprite.getY();
    float f7 = this.platformSprite.getHeight();
    this.rotationPoints1.setVertices(new float[] { paramFloat, f1, f2 + 5.0F, f3 + f4, f5, f6 + f7 });
    this.rotationPoints1.setOrigin(this.platformSprite.getX() + this.platformSprite.getWidth() / 2.0F, this.platformSprite.getY() + this.platformSprite.getOriginY());
    paramFloat = this.platformSprite.getX();
    f1 = this.platformSprite.getWidth();
    f2 = this.platformSprite.getY();
    f3 = this.platformSprite.getX();
    f4 = this.platformSprite.getWidth();
    f5 = this.platformSprite.getY();
    f6 = this.platformSprite.getHeight() / 2.0F;
    f7 = this.platformSprite.getX();
    float f8 = this.platformSprite.getWidth();
    float f9 = this.platformSprite.getY();
    float f10 = this.platformSprite.getHeight();
    this.rotationPoints2.setVertices(new float[] { paramFloat + f1, f2, f3 - 5.0F + f4, f5 + f6, f7 + f8, f9 + f10 });
    this.rotationPoints2.setOrigin(this.platformSprite.getX() + this.platformSprite.getWidth() / 2.0F, this.platformSprite.getY() + this.platformSprite.getOriginY());
  }
  
  private void update(float paramFloat)
  {
    this.platformBounds.rotate(this.rotateSpeed * paramFloat);
    this.platformSprite.rotate(this.rotateSpeed * paramFloat);
    updateSpeed(paramFloat);
  }
  
  private void updateSpeed(float paramFloat)
  {
    float f1 = this.rotationPoints1.getTransformedVertices()[2];
    float f2 = this.rotationPoints1.getTransformedVertices()[3];
    this.rotationPoints1.rotate(this.rotateSpeed * paramFloat);
    float f3 = this.rotationPoints1.getTransformedVertices()[2];
    float f4 = this.rotationPoints1.getTransformedVertices()[3];
    this.rotationPoint1V.x = ((f3 - f1) / paramFloat);
    this.rotationPoint1V.y = ((f4 - f2) / paramFloat);
    f1 = this.rotationPoints2.getTransformedVertices()[2];
    f2 = this.rotationPoints2.getTransformedVertices()[3];
    this.rotationPoints2.rotate(this.rotateSpeed * paramFloat);
    f3 = this.rotationPoints2.getTransformedVertices()[2];
    f4 = this.rotationPoints2.getTransformedVertices()[3];
    this.rotationPoint2V.x = ((f3 - f1) / paramFloat);
    this.rotationPoint2V.y = ((f4 - f2) / paramFloat);
  }
  
  public void drawMesh(ShapeRenderer paramShapeRenderer)
  {
    super.drawMesh(paramShapeRenderer);
    paramShapeRenderer.setColor(Color.PURPLE);
    paramShapeRenderer.polygon(this.rotationPoints1.getTransformedVertices());
    paramShapeRenderer.polygon(this.rotationPoints2.getTransformedVertices());
  }
  
  public void drawPlatforms(SpriteBatch paramSpriteBatch, float paramFloat)
  {
    super.drawPlatforms(paramSpriteBatch, paramFloat);
    update(paramFloat);
  }
}


/* Location:              C:\Users\sam\Documents\ballin\DoodleCannon-dex2jar.jar!\com\studios0110\objects\RotatingPlatform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */