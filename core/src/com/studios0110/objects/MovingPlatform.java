package com.studios0110.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class MovingPlatform
  extends Platform
{
  float changeTime;
  float delay;
  Vector2 velocity;
  
  public MovingPlatform(String paramString, Polygon paramPolygon)
  {
    super(paramString, paramPolygon);
  }
  
  public MovingPlatform(String paramString, Polygon paramPolygon, Vector2 paramVector2, float paramFloat)
  {
    super(paramString, paramPolygon);
    this.velocity = paramVector2;
    this.changeTime = paramFloat;
    this.delay = 0.0F;
    this.addedVelocity = paramVector2;
  }
  
  private void update(float paramFloat)
  {
    this.platformBounds.translate(this.velocity.x * paramFloat, this.velocity.y * paramFloat);
    this.platformSprite.setX(this.platformSprite.getX() + this.velocity.x * paramFloat);
    this.platformSprite.setY(this.platformSprite.getY() + this.velocity.y * paramFloat);
    this.delay += 1.0F * paramFloat;
    if (this.delay >= this.changeTime)
    {
      this.delay = 0.0F;
      this.velocity.x = (-this.velocity.x);
      this.velocity.y = (-this.velocity.y);
      this.addedVelocity = this.velocity;
    }
  }
  
  public void drawPlatforms(SpriteBatch paramSpriteBatch, float paramFloat)
  {
    super.drawPlatforms(paramSpriteBatch, paramFloat);
    update(paramFloat);
  }
}


/* Location:              C:\Users\sam\Documents\ballin\DoodleCannon-dex2jar.jar!\com\studios0110\objects\MovingPlatform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */