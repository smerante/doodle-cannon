package com.studios0110.objects;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.studios0110.splash.Splash;
import java.io.PrintStream;

public class MyCollider
{
  public static void bounceFromCorner(Vector2 paramVector2, Polygon paramPolygon, Ball paramBall)
  {
    Vector2 localVector2 = new Vector2();
    if (paramPolygon.getTransformedVertices()[4] > paramPolygon.getTransformedVertices()[0])
    {
      localVector2.set(paramPolygon.getTransformedVertices()[0] + (paramPolygon.getTransformedVertices()[4] - paramPolygon.getTransformedVertices()[0]) / 2.0F, 0.0F);
      if (paramPolygon.getTransformedVertices()[5] <= paramPolygon.getTransformedVertices()[1]) {
        break label301;
      }
      localVector2.set(localVector2.x, paramPolygon.getTransformedVertices()[1] + (paramPolygon.getTransformedVertices()[5] - paramPolygon.getTransformedVertices()[1]) / 2.0F);
    }
    for (;;)
    {
      float f1 = localVector2.x - paramVector2.x;
      float f4 = localVector2.y - paramVector2.y;
      float f3 = f1;
      if (f1 == 0.0F) {
        f3 = 1.0F;
      }
      float f2 = (float)Math.round(Math.atan(f4 / f3) * 180.0D / 3.141592653589793D);
      f1 = f2;
      if (f3 <= 0.0F)
      {
        f1 = f2;
        if (f4 >= 0.0F) {
          f1 = f2 + 180.0F;
        }
      }
      f2 = f1;
      if (f3 <= 0.0F)
      {
        f2 = f1;
        if (f4 <= 0.0F) {
          f2 = f1 + 180.0F;
        }
      }
      f1 = f2;
      if (f3 >= 0.0F)
      {
        f1 = f2;
        if (f4 <= 0.0F) {
          f1 = f2 + 360.0F;
        }
      }
      f2 = f1 + 180.0F;
      f1 = f2;
      if (f2 > 360.0F) {
        f1 = f2 - 360.0F;
      }
      paramBall.setNewVelocity(f1, null);
      return;
      localVector2.set(paramPolygon.getTransformedVertices()[4] + (paramPolygon.getTransformedVertices()[0] - paramPolygon.getTransformedVertices()[4]) / 2.0F, 0.0F);
      break;
      label301:
      localVector2.set(localVector2.x, paramPolygon.getTransformedVertices()[5] + (paramPolygon.getTransformedVertices()[1] - paramPolygon.getTransformedVertices()[5]) / 2.0F);
    }
  }
  
  public static void bounceFromTwoWalls(Ball paramBall, Polygon paramPolygon)
  {
    float f1 = paramPolygon.getTransformedVertices()[0] - paramBall.posX;
    float f4 = paramPolygon.getTransformedVertices()[1] - paramBall.posY;
    float f3 = f1;
    if (f1 == 0.0F) {
      f3 = 1.0F;
    }
    float f2 = (float)Math.round(Math.atan(f4 / f3) * 180.0D / 3.141592653589793D);
    f1 = f2;
    if (f3 <= 0.0F)
    {
      f1 = f2;
      if (f4 >= 0.0F) {
        f1 = f2 + 180.0F;
      }
    }
    f2 = f1;
    if (f3 <= 0.0F)
    {
      f2 = f1;
      if (f4 <= 0.0F) {
        f2 = f1 + 180.0F;
      }
    }
    f1 = f2;
    if (f3 >= 0.0F)
    {
      f1 = f2;
      if (f4 <= 0.0F) {
        f1 = f2 + 360.0F;
      }
    }
    f2 = f1 + 180.0F;
    f1 = f2;
    if (f2 > 360.0F) {
      f1 = f2 - 360.0F;
    }
    System.out.println("Bounce at " + f1);
    paramBall.setNewVelocity(f1, null);
  }
  
  public static boolean checkCornerOverlap(Polygon paramPolygon, Ball paramBall)
  {
    paramPolygon = paramPolygon.getTransformedVertices();
    int i = 0;
    for (;;)
    {
      if (i >= paramPolygon.length - 1) {
        return false;
      }
      if (paramBall.bounds.contains(paramPolygon[i], paramPolygon[(i + 1)])) {
        return true;
      }
      i += 1;
    }
  }
  
  public static Polygon createPolygon(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5)
  {
    Polygon localPolygon = new Polygon();
    localPolygon.setVertices(createRectangle(paramFloat1, paramFloat2, paramFloat3, paramFloat4));
    localPolygon.setOrigin(paramFloat1, paramFloat2);
    localPolygon.setRotation(paramFloat5);
    return localPolygon;
  }
  
  public static Polygon createPolygon(String paramString, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    paramString = (Texture)Splash.manager.get("objects/" + paramString + ".png");
    return createPolygon(paramFloat1, paramFloat2, paramString.getWidth(), paramString.getHeight(), paramFloat3);
  }
  
  private static float[] createRectangle(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    return new float[] { paramFloat1, paramFloat2, paramFloat1 + paramFloat3, paramFloat2, paramFloat1 + paramFloat3, paramFloat2 + paramFloat4, paramFloat1, paramFloat2 + paramFloat4 };
  }
  
  public static Vector2 getCornerBallOverlaped(Polygon paramPolygon, Ball paramBall)
  {
    paramPolygon = paramPolygon.getTransformedVertices();
    int i = 0;
    for (;;)
    {
      if (i >= paramPolygon.length - 1) {
        return null;
      }
      if (paramBall.bounds.contains(paramPolygon[i], paramPolygon[(i + 1)])) {
        return new Vector2(paramPolygon[i], paramPolygon[(i + 1)]);
      }
      i += 1;
    }
  }
  
  public static CollideInfo overlap(Polygon paramPolygon, Ball paramBall)
  {
    paramPolygon = paramPolygon.getTransformedVertices();
    Vector2 localVector2 = new Vector2(paramBall.bounds.x, paramBall.bounds.y);
    float f1 = paramBall.bounds.radius * paramBall.bounds.radius;
    int i = 0;
    for (;;)
    {
      if (i >= paramPolygon.length) {
        return null;
      }
      float f4;
      float f3;
      float f2;
      if (i == 0)
      {
        if (Intersector.intersectSegmentCircle(new Vector2(paramPolygon[(paramPolygon.length - 2)], paramPolygon[(paramPolygon.length - 1)]), new Vector2(paramPolygon[i], paramPolygon[(i + 1)]), localVector2, f1))
        {
          f1 = paramPolygon[(paramPolygon.length - 2)] - paramPolygon[i];
          f4 = paramPolygon[(paramPolygon.length - 1)] - paramPolygon[(i + 1)];
          f3 = f1;
          if (f1 == 0.0F) {
            f3 = 1.0F;
          }
          f2 = (float)Math.round(Math.atan(f4 / f3) * 180.0D / 3.141592653589793D);
          f1 = f2;
          if (f3 <= 0.0F)
          {
            f1 = f2;
            if (f4 >= 0.0F) {
              f1 = f2 + 180.0F;
            }
          }
          f2 = f1;
          if (f3 <= 0.0F)
          {
            f2 = f1;
            if (f4 <= 0.0F) {
              f2 = f1 + 180.0F;
            }
          }
          f1 = f2;
          if (f3 >= 0.0F)
          {
            f1 = f2;
            if (f4 <= 0.0F) {
              f1 = f2 + 360.0F;
            }
          }
          f2 = f1;
          if (f1 > 360.0F) {
            f2 = f1 - 360.0F;
          }
          f3 = f2 + 90.0F;
          f1 = f3;
          if (f3 > 360.0F) {
            f1 = f3 - 360.0F;
          }
          f3 = paramBall.angle;
          return new CollideInfo(true, 0, f2, f1, paramBall.angle, f1 - (180.0F + f3 - f1));
        }
      }
      else if (Intersector.intersectSegmentCircle(new Vector2(paramPolygon[(i - 2)], paramPolygon[(i - 1)]), new Vector2(paramPolygon[i], paramPolygon[(i + 1)]), localVector2, f1))
      {
        if (i == 2)
        {
          f1 = paramPolygon[i] - paramPolygon[(i - 2)];
          f4 = paramPolygon[(i + 1)] - paramPolygon[(i - 1)];
          f3 = f1;
          if (f1 == 0.0F) {
            f3 = 1.0F;
          }
          f2 = (float)Math.round(Math.atan(f4 / f3) * 180.0D / 3.141592653589793D);
          f1 = f2;
          if (f3 <= 0.0F)
          {
            f1 = f2;
            if (f4 >= 0.0F) {
              f1 = f2 + 180.0F;
            }
          }
          f2 = f1;
          if (f3 <= 0.0F)
          {
            f2 = f1;
            if (f4 <= 0.0F) {
              f2 = f1 + 180.0F;
            }
          }
          f1 = f2;
          if (f3 >= 0.0F)
          {
            f1 = f2;
            if (f4 <= 0.0F) {
              f1 = f2 + 360.0F;
            }
          }
          f2 = f1;
          if (f1 > 360.0F) {
            f2 = f1 - 360.0F;
          }
          f3 = f2 + 90.0F;
          f1 = f3;
          if (f3 > 360.0F) {
            f1 = f3 - 360.0F;
          }
          f3 = paramBall.angle;
          return new CollideInfo(true, 2, f2, f1, paramBall.angle, f1 - (180.0F + f3 - f1));
        }
        if (i == 4)
        {
          f1 = paramPolygon[i] - paramPolygon[(i - 2)];
          f4 = paramPolygon[(i + 1)] - paramPolygon[(i - 1)];
          f3 = f1;
          if (f1 == 0.0F) {
            f3 = 1.0F;
          }
          f2 = (float)Math.round(Math.atan(f4 / f3) * 180.0D / 3.141592653589793D);
          f1 = f2;
          if (f3 <= 0.0F)
          {
            f1 = f2;
            if (f4 >= 0.0F) {
              f1 = f2 + 180.0F;
            }
          }
          f2 = f1;
          if (f3 <= 0.0F)
          {
            f2 = f1;
            if (f4 <= 0.0F) {
              f2 = f1 + 180.0F;
            }
          }
          f1 = f2;
          if (f3 >= 0.0F)
          {
            f1 = f2;
            if (f4 <= 0.0F) {
              f1 = f2 + 360.0F;
            }
          }
          f2 = f1;
          if (f1 > 360.0F) {
            f2 = f1 - 360.0F;
          }
          f3 = f2 + 90.0F;
          f1 = f3;
          if (f3 > 360.0F) {
            f1 = f3 - 360.0F;
          }
          f4 = f1 - (180.0F + paramBall.angle - f1);
          f3 = f4;
          if (f4 < 0.0F) {
            f3 = f4 + 360.0F;
          }
          return new CollideInfo(true, 1, f2, f1, paramBall.angle, f3);
        }
        if (i == 6)
        {
          f1 = paramPolygon[(i - 2)] - paramPolygon[i];
          f4 = paramPolygon[(i - 1)] - paramPolygon[(i + 1)];
          f3 = f1;
          if (f1 == 0.0F) {
            f3 = 1.0F;
          }
          f2 = (float)Math.round(Math.atan(f4 / f3) * 180.0D / 3.141592653589793D);
          f1 = f2;
          if (f3 <= 0.0F)
          {
            f1 = f2;
            if (f4 >= 0.0F) {
              f1 = f2 + 180.0F;
            }
          }
          f2 = f1;
          if (f3 <= 0.0F)
          {
            f2 = f1;
            if (f4 <= 0.0F) {
              f2 = f1 + 180.0F;
            }
          }
          f1 = f2;
          if (f3 >= 0.0F)
          {
            f1 = f2;
            if (f4 <= 0.0F) {
              f1 = f2 + 360.0F;
            }
          }
          f2 = f1;
          if (f1 > 360.0F) {
            f2 = f1 - 360.0F;
          }
          f3 = f2 + 90.0F;
          f1 = f3;
          if (f3 > 360.0F) {
            f1 = f3 - 360.0F;
          }
          f4 = f1 - (180.0F + paramBall.angle - f1);
          f3 = f4;
          if (f4 < 0.0F) {
            f3 = f4 + 360.0F;
          }
          return new CollideInfo(true, 3, f2, f1, paramBall.angle, f3);
        }
        return null;
      }
      i += 2;
    }
  }
  
  public static boolean overlapCheck(Polygon paramPolygon, Ball paramBall)
  {
    boolean bool2 = true;
    paramPolygon = paramPolygon.getTransformedVertices();
    Vector2 localVector2 = new Vector2(paramBall.bounds.x, paramBall.bounds.y);
    float f = paramBall.bounds.radius * paramBall.bounds.radius;
    int i = 0;
    boolean bool1;
    if (i >= paramPolygon.length) {
      bool1 = false;
    }
    do
    {
      return bool1;
      if (i != 0) {
        break;
      }
      bool1 = bool2;
    } while (Intersector.intersectSegmentCircle(new Vector2(paramPolygon[(paramPolygon.length - 2)], paramPolygon[(paramPolygon.length - 1)]), new Vector2(paramPolygon[i], paramPolygon[(i + 1)]), localVector2, f));
    while (!Intersector.intersectSegmentCircle(new Vector2(paramPolygon[(i - 2)], paramPolygon[(i - 1)]), new Vector2(paramPolygon[i], paramPolygon[(i + 1)]), localVector2, f))
    {
      i += 2;
      break;
    }
    return true;
  }
}


/* Location:              C:\Users\sam\Documents\ballin\DoodleCannon-dex2jar.jar!\com\studios0110\objects\MyCollider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */