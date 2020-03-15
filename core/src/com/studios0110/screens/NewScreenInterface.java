package com.studios0110.screens;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract interface NewScreenInterface
  extends Screen
{
  public static final SpriteBatch batch = new SpriteBatch();
  public static final InputMultiplexer mp = new InputMultiplexer();
  public static final ShapeRenderer shapes = new ShapeRenderer();
  
  public abstract void dispose();
  
  public abstract void hide();
  
  public abstract void pause();
  
  public abstract void render(float paramFloat);
  
  public abstract void resize(int paramInt1, int paramInt2);
  
  public abstract void resume();
  
  public abstract void show();
}


/* Location:              C:\Users\sam\Documents\ballin\DoodleCannon-dex2jar.jar!\com\studios0110\screens\NewScreenInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */