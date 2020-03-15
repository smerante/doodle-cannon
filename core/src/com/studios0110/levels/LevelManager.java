package com.studios0110.levels;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import java.util.ArrayList;

public class LevelManager
{
  Vector3 bucketPos;
  float camZoom;
  Vector2 canButPos;
  Vector2 canPos;
  ArrayList<Float> changeTime;
  float grav;
  String helpText;
  int level;
  int numMP;
  int numP;
  int numRP;
  ArrayList<Vector3> platformPositions;
  ArrayList<String> platformTextures;
  ArrayList<Float> rotationSpeeds;
  ArrayList<Vector2> velocity;
  
  public LevelManager() {}
  
  public LevelManager(Vector2 paramVector21, Vector2 paramVector22, Vector3 paramVector3, String paramString, float paramFloat1, float paramFloat2, int paramInt1, int paramInt2, int paramInt3, ArrayList<String> paramArrayList, ArrayList<Vector3> paramArrayList1, ArrayList<Vector2> paramArrayList2, ArrayList<Float> paramArrayList3, ArrayList<Float> paramArrayList4)
  {
    this.canPos = paramVector21;
    this.canButPos = paramVector22;
    this.bucketPos = paramVector3;
    this.helpText = paramString;
    this.grav = paramFloat1;
    this.camZoom = paramFloat2;
    this.numP = paramInt1;
    this.numMP = paramInt2;
    this.numRP = paramInt3;
    this.platformTextures = paramArrayList;
    this.platformPositions = paramArrayList1;
    this.velocity = paramArrayList2;
    this.changeTime = paramArrayList4;
    this.rotationSpeeds = paramArrayList3;
  }
}


/* Location:              C:\Users\sam\Documents\ballin\DoodleCannon-dex2jar.jar!\com\studios0110\levels\LevelManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */