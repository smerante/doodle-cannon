package com.studios0110.user_interface;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.studios0110.screens.StartScreen;
import com.studios0110.splash.Splash;

public class LevelButton
  implements GestureDetector.GestureListener
{
  public Texture button;
  public Texture buttonPushed;
  public boolean clicked;
  int height;
  public boolean holding;
  public int level;
  BitmapFont levelNumFont;
  Texture levelStars;
  public Vector2 location;
  public boolean locked;
  public int stars;
  Vector3 touchPos;
  public boolean touched;
  public boolean visible;
  int width;
  
  public LevelButton(String paramString, Vector2 paramVector2, int paramInt)
  {
    this.location = paramVector2;
    this.button = ((Texture)Splash.manager.get("ui/" + paramString + ".png"));
    this.buttonPushed = ((Texture)Splash.manager.get("ui/" + paramString + "Pushed.png"));
    this.width = this.buttonPushed.getWidth();
    this.height = this.buttonPushed.getHeight();
    this.touchPos = new Vector3(-50.0F, -50.0F, 0.0F);
    this.visible = true;
    this.levelNumFont = ((BitmapFont)Splash.manager.get("fonts/BallinFont.fnt"));
    this.level = paramInt;
    if (!StartScreen.prefs.contains("level_" + this.level + "_stars")) {
      StartScreen.prefs.putInteger("level_" + this.level + "_stars", 0);
    }
    this.stars = StartScreen.prefs.getInteger("level_" + this.level + "_stars");
    if (StartScreen.prefs.getInteger("Unlocked_Level") < paramInt) {
      this.locked = true;
    }
    StartScreen.prefs.flush();
    this.levelStars = ((Texture)Splash.manager.get("ui/LevelStar.png"));
  }
  
  public void draw(SpriteBatch paramSpriteBatch)
  {
    int i;
    if (this.visible)
    {
      if (!this.touched) {
        break label98;
      }
      paramSpriteBatch.draw(this.buttonPushed, this.location.x, this.location.y);
      i = 0;
    }
    for (;;)
    {
      if (i >= this.stars)
      {
        this.levelNumFont.draw(paramSpriteBatch, this.level, this.location.x + this.width / 2 - 10.0F, this.location.y);
        return;
        label98:
        paramSpriteBatch.draw(this.button, this.location.x, this.location.y);
        break;
      }
      paramSpriteBatch.draw(this.levelStars, this.location.x + 10.0F + i * 50, this.location.y + this.height - 30.0F);
      i += 1;
    }
  }
  
  public boolean fling(float paramFloat1, float paramFloat2, int paramInt)
  {
    if (this.visible)
    {
      if ((this.touchPos.x < this.location.x + this.width) && (this.touchPos.x > this.location.x) && (this.touchPos.y > this.location.y) && (this.touchPos.y < this.location.y + this.height)) {
        this.clicked = true;
      }
    }
    else {
      return false;
    }
    this.touched = false;
    this.clicked = false;
    return false;
  }
  
  public boolean longPress(float paramFloat1, float paramFloat2)
  {
    if (this.visible)
    {
      this.touchPos.set(paramFloat1, paramFloat2, 0.0F);
      Splash.camera.unproject(this.touchPos);
      if ((this.touchPos.x < this.location.x + this.width) && (this.touchPos.x > this.location.x) && (this.touchPos.y > this.location.y) && (this.touchPos.y < this.location.y + this.height)) {
        this.holding = true;
      }
    }
    else
    {
      return false;
    }
    this.holding = false;
    return false;
  }
  
  public boolean pan(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    if ((this.visible) && (this.touchPos.x < this.location.x + this.width) && (this.touchPos.x > this.location.x) && (this.touchPos.y > this.location.y) && (this.touchPos.y < this.location.y + this.height))
    {
      this.touchPos.set(paramFloat1, paramFloat2, 0.0F);
      Splash.camera.unproject(this.touchPos);
      if ((this.touchPos.x < this.location.x + this.width) && (this.touchPos.x > this.location.x) && (this.touchPos.y > this.location.y) && (this.touchPos.y < this.location.y + this.height))
      {
        this.touched = true;
        this.holding = true;
      }
    }
    else
    {
      return false;
    }
    this.touched = false;
    this.holding = false;
    return false;
  }
  
  public boolean panStop(float paramFloat1, float paramFloat2, int paramInt1, int paramInt2)
  {
    if ((this.visible) && (this.touchPos.x < this.location.x + this.width) && (this.touchPos.x > this.location.x) && (this.touchPos.y > this.location.y) && (this.touchPos.y < this.location.y + this.height))
    {
      this.touchPos.set(paramFloat1, paramFloat2, 0.0F);
      Splash.camera.unproject(this.touchPos);
      if ((this.touchPos.x < this.location.x + this.width) && (this.touchPos.x > this.location.x) && (this.touchPos.y > this.location.y) && (this.touchPos.y < this.location.y + this.height)) {
        this.clicked = true;
      }
    }
    else
    {
      return false;
    }
    this.touched = false;
    this.clicked = false;
    return false;
  }
  
  public boolean pinch(Vector2 paramVector21, Vector2 paramVector22, Vector2 paramVector23, Vector2 paramVector24)
  {
    return false;
  }
  
  public void resetButton()
  {
    this.touched = false;
    this.clicked = false;
    this.holding = false;
    this.touchPos = new Vector3(-50.0F, -50.0F, 0.0F);
  }
  
  public boolean tap(float paramFloat1, float paramFloat2, int paramInt1, int paramInt2)
  {
    if (this.visible)
    {
      this.touchPos.set(paramFloat1, paramFloat2, 0.0F);
      Splash.camera.unproject(this.touchPos);
      if ((this.touchPos.x < this.location.x + this.width) && (this.touchPos.x > this.location.x) && (this.touchPos.y > this.location.y) && (this.touchPos.y < this.location.y + this.height)) {
        this.clicked = true;
      }
    }
    else
    {
      return false;
    }
    this.touched = false;
    this.clicked = false;
    return false;
  }
  
  public boolean touchDown(float paramFloat1, float paramFloat2, int paramInt1, int paramInt2)
  {
    if (this.visible)
    {
      this.touchPos.set(paramFloat1, paramFloat2, 0.0F);
      Splash.camera.unproject(this.touchPos);
      if ((this.touchPos.x < this.location.x + this.width) && (this.touchPos.x > this.location.x) && (this.touchPos.y > this.location.y) && (this.touchPos.y < this.location.y + this.height))
      {
        this.touched = true;
        this.holding = true;
      }
    }
    else
    {
      return false;
    }
    this.touched = false;
    this.holding = false;
    return false;
  }
  
  public boolean zoom(float paramFloat1, float paramFloat2)
  {
    return false;
  }
}


/* Location:              C:\Users\sam\Documents\ballin\DoodleCannon-dex2jar.jar!\com\studios0110\user_interface\LevelButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */