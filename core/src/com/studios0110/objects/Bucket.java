package com.studios0110.objects;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.studios0110.splash.Splash;
import java.util.ArrayList;

public class Bucket
{
  public float angle;
  public Sprite backBucket = new Sprite((Texture)Splash.manager.get("objects/Bucket.png"));
  public Sprite frontBucket = new Sprite((Texture)Splash.manager.get("objects/BucketFront.png"));
  public Polygon goal;
  public boolean win;
  public float x;
  public float y;
  
  public Bucket(float paramFloat1, float paramFloat2, ArrayList<Platform> paramArrayList, float paramFloat3)
  {
    this.x = paramFloat1;
    this.y = paramFloat2;
    this.angle = paramFloat3;
    this.goal = new Polygon();
    createBucketBounds(paramArrayList);
    this.backBucket.setX(paramFloat1);
    this.backBucket.setY(paramFloat2);
    this.frontBucket.setX(paramFloat1);
    this.frontBucket.setY(paramFloat2);
    this.backBucket.setOrigin(this.backBucket.getWidth() / 2.0F, this.backBucket.getHeight() / 2.0F);
    this.frontBucket.setOrigin(this.frontBucket.getWidth() / 2.0F, this.frontBucket.getHeight() / 2.0F);
    this.goal.setOrigin(this.backBucket.getX() + this.backBucket.getWidth() / 2.0F, this.backBucket.getY() + this.backBucket.getHeight() / 2.0F);
    this.frontBucket.setRotation(paramFloat3);
    this.backBucket.setRotation(paramFloat3);
    this.goal.rotate(paramFloat3);
    this.win = false;
  }
  
  public void createBucketBounds(ArrayList<Platform> paramArrayList)
  {
    Platform localPlatform = new Platform("null", MyCollider.createPolygon(this.x, this.y + this.frontBucket.getHeight() / 2.0F, 10.0F, this.frontBucket.getHeight() / 2.0F, -25.0F));
    localPlatform.platformBounds.setOrigin(this.x + this.backBucket.getWidth() / 2.0F, this.y + this.backBucket.getHeight() / 2.0F);
    localPlatform.platformBounds.setRotation(this.angle);
    paramArrayList.add(localPlatform);
    localPlatform = new Platform("null", MyCollider.createPolygon(this.x + 15.0F, this.y, 10.0F, this.frontBucket.getHeight() / 2.0F, 25.0F));
    localPlatform.platformBounds.setOrigin(this.x + this.backBucket.getWidth() / 2.0F, this.y + this.backBucket.getHeight() / 2.0F);
    localPlatform.platformBounds.setRotation(this.angle);
    paramArrayList.add(localPlatform);
    localPlatform = new Platform("null", MyCollider.createPolygon(this.x, this.y, this.frontBucket.getWidth(), 20.0F, 0.0F));
    localPlatform.platformBounds.setOrigin(this.x + this.backBucket.getWidth() / 2.0F, this.y + this.backBucket.getHeight() / 2.0F);
    localPlatform.platformBounds.setRotation(this.angle);
    paramArrayList.add(localPlatform);
    localPlatform = new Platform("null", MyCollider.createPolygon(this.x + this.frontBucket.getWidth() - 10.0F, this.y + this.frontBucket.getHeight() / 2.0F, 10.0F, this.frontBucket.getHeight() / 2.0F - 10.0F, 25.0F));
    localPlatform.platformBounds.setOrigin(this.x + this.backBucket.getWidth() / 2.0F, this.y + this.backBucket.getHeight() / 2.0F);
    localPlatform.platformBounds.setRotation(this.angle);
    paramArrayList.add(localPlatform);
    localPlatform = new Platform("null", MyCollider.createPolygon(this.x + this.frontBucket.getWidth() - 30.0F, this.y, 10.0F, this.frontBucket.getHeight() / 2.0F, -25.0F));
    localPlatform.platformBounds.setOrigin(this.x + this.backBucket.getWidth() / 2.0F, this.y + this.backBucket.getHeight() / 2.0F);
    localPlatform.platformBounds.setRotation(this.angle);
    paramArrayList.add(localPlatform);
    float f1 = this.x;
    float f2 = this.y;
    float f3 = this.x;
    float f4 = this.y;
    float f5 = this.x;
    float f6 = this.y;
    float f7 = this.x;
    float f8 = this.y;
    float f9 = this.x;
    float f10 = this.y;
    float f11 = this.x;
    float f12 = this.y;
    float f13 = this.x;
    float f14 = this.y;
    float f15 = this.x;
    float f16 = this.y;
    float f17 = this.x;
    float f18 = this.y;
    float f19 = this.x;
    float f20 = this.y;
    this.goal.setVertices(new float[] { f1 + 26.0F, f2 + 10.0F + 20.0F, f3 + 21.0F, f4 + 41.0F + 20.0F, f5 + 22.0F, f6 + 39.0F + 20.0F, f7 + 39.0F, f8 + 42.0F + 20.0F, f9 + 75.0F, f10 + 35.0F + 20.0F, f11 + 105.0F, f12 + 37.0F + 20.0F, f13 + 120.0F, f14 + 50.0F + 20.0F, f15 + 120.0F, f16 + 41.0F + 20.0F, f17 + 110.0F, f18 + 11.0F + 20.0F, f19 + 71.0F, f20 + 1.0F + 20.0F });
  }
  
  public void drawBack(SpriteBatch paramSpriteBatch)
  {
    this.backBucket.draw(paramSpriteBatch);
  }
  
  public void drawFront(SpriteBatch paramSpriteBatch)
  {
    this.frontBucket.draw(paramSpriteBatch);
  }
  
  public void update(Ball paramBall)
  {
    if (MyCollider.overlapCheck(this.goal, paramBall)) {
      this.win = true;
    }
  }
}


/* Location:              C:\Users\sam\Documents\ballin\DoodleCannon-dex2jar.jar!\com\studios0110\objects\Bucket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */