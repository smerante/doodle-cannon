package com.studios0110.objects;

public class CollideInfo
{
  public final int BOTTOM = 2;
  public final int LEFT = 0;
  public final int RIGHT = 1;
  public final int TOP = 3;
  public float angle;
  public float ballHitAngle;
  public float ballReflectionAngle;
  public boolean collided;
  public float normalAngle;
  public int sideHit;
  
  public CollideInfo(boolean paramBoolean, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    this.collided = paramBoolean;
    this.sideHit = paramInt;
    this.angle = paramFloat1;
    this.normalAngle = paramFloat2;
    this.ballHitAngle = paramFloat3;
    this.ballReflectionAngle = paramFloat4;
  }
}


/* Location:              C:\Users\sam\Documents\ballin\DoodleCannon-dex2jar.jar!\com\studios0110\objects\CollideInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */