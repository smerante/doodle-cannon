package com.studios0110.objects;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.studios0110.splash.Splash;

public class Platform
{
  public Vector2 addedVelocity;
  public boolean hit;
  public boolean image;
  public Polygon platformBounds = new Polygon();
  public Sprite platformSprite;
  public Vector2 rotationPoint1V;
  public Vector2 rotationPoint2V;
  public Polygon rotationPoints1;
  public Polygon rotationPoints2;
  public int sideHit;
  
  public Platform(String paramString, Polygon paramPolygon)
  {
    this.platformBounds.setVertices(paramPolygon.getTransformedVertices());
    this.hit = false;
    this.addedVelocity = new Vector2(0.0F, 0.0F);
    if (!paramString.equals("null"))
    {
      this.platformSprite = new Sprite((Texture)Splash.manager.get("objects/" + paramString + ".png"));
      this.platformSprite.setX(paramPolygon.getVertices()[0]);
      this.platformSprite.setY(paramPolygon.getVertices()[1]);
      this.platformSprite.setOrigin(0.0F, 0.0F);
      this.platformSprite.setRotation(paramPolygon.getRotation());
      this.image = true;
      return;
    }
    this.image = false;
  }
  
  public void drawMesh(ShapeRenderer paramShapeRenderer)
  {
    paramShapeRenderer.polygon(this.platformBounds.getTransformedVertices());
  }
  
  public void drawPlatforms(SpriteBatch paramSpriteBatch, float paramFloat)
  {
    if (this.image) {
      this.platformSprite.draw(paramSpriteBatch);
    }
  }
  
  public void setHit(boolean paramBoolean)
  {
    this.hit = paramBoolean;
  }
}


/* Location:              C:\Users\sam\Documents\ballin\DoodleCannon-dex2jar.jar!\com\studios0110\objects\Platform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */