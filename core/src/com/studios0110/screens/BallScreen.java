package com.studios0110.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.studios0110.splash.Splash;
import com.studios0110.user_interface.Button;
import java.io.PrintStream;
import java.util.ArrayList;

public class BallScreen
  implements NewScreenInterface
{
  int FPS;
  ArrayList<Button> ballButtons;
  Texture bg;
  BitmapFont fpsFONT;
  float height = Splash.screenH;
  int levelUnlocked;
  Button menu;
  Texture screenTexture;
  Vector2 selected;
  int showFPSDelay;
  Texture unkownBallTexture;
  float width = Splash.screenW;
  
  public void ballButtonSet()
  {
    this.ballButtons.add(new Button("BallSelect0", new Vector2(100.0F, 500.0F)));
    this.ballButtons.add(new Button("BallSelect1", new Vector2(300.0F, 500.0F)));
    this.ballButtons.add(new Button("BallSelect2", new Vector2(500.0F, 500.0F)));
    this.ballButtons.add(new Button("BallSelect3", new Vector2(700.0F, 500.0F)));
    mp.addProcessor(new GestureDetector(this.menu));
    int i = 0;
    if (i >= this.ballButtons.size())
    {
      this.selected = ((Button)this.ballButtons.get(StartScreen.prefs.getInteger("Current_Ball"))).location;
      return;
    }
    mp.addProcessor(new GestureDetector((GestureDetector.GestureListener)this.ballButtons.get(i)));
    if ((i == 0) || (this.levelUnlocked >= i * 5)) {}
    for (((Button)this.ballButtons.get(i)).visible = true;; ((Button)this.ballButtons.get(i)).visible = false)
    {
      i += 1;
      break;
    }
  }
  
  public void dispose() {}
  
  public void hide() {}
  
  public void pause() {}
  
  public void render(float paramFloat)
  {
    Gdx.gl.glClearColor(0.5F, 0.5F, 1.0F, 0.0F);
    Gdx.gl.glClear(16384);
    batch.setProjectionMatrix(Splash.camera.combined);
    shapes.setProjectionMatrix(Splash.camera.combined);
    batch.begin();
    batch.draw(this.screenTexture, 0.0F, 0.0F);
    batch.draw(this.bg, 0.0F, -100.0F);
    this.menu.draw(batch);
    int i = 0;
    if (i >= this.ballButtons.size())
    {
      batch.end();
      shapes.begin(ShapeRenderer.ShapeType.Filled);
      shapes.setColor(Color.BLACK);
      shapes.rect(this.selected.x, this.selected.y, 150.0F, 10.0F);
      shapes.end();
      update(paramFloat);
      return;
    }
    if ((i == 0) || (this.levelUnlocked >= i * 5)) {
      ((Button)this.ballButtons.get(i)).draw(batch);
    }
    for (;;)
    {
      i += 1;
      break;
      batch.draw(this.unkownBallTexture, ((Button)this.ballButtons.get(i)).location.x, ((Button)this.ballButtons.get(i)).location.y);
    }
  }
  
  public void resize(int paramInt1, int paramInt2) {}
  
  public void resume() {}
  
  public void show()
  {
    Splash.camera.zoom = 1.0F;
    Splash.camera.update();
    System.out.println("Cannon Screen on level ");
    this.levelUnlocked = StartScreen.prefs.getInteger("Unlocked_Level");
    this.screenTexture = ((Texture)Splash.manager.get("ui/BallPage.png"));
    shapes.setAutoShapeType(true);
    this.fpsFONT = new BitmapFont();
    Gdx.input.setCatchBackKey(true);
    Gdx.input.setInputProcessor(mp);
    this.ballButtons = new ArrayList();
    this.unkownBallTexture = ((Texture)Splash.manager.get("ui/UnkownCannonSelect.png"));
    this.menu = new Button("Menu", new Vector2(1150.0F, 690.0F));
    this.bg = ((Texture)Splash.manager.get("levels/bg.png"));
    ballButtonSet();
  }
  
  void showFPS(float paramFloat)
  {
    this.showFPSDelay += 1;
    if (this.showFPSDelay >= 20)
    {
      this.showFPSDelay = 0;
      this.FPS = ((int)Math.ceil(1.0F / paramFloat));
    }
    batch.begin();
    this.fpsFONT.draw(batch, "FPS " + this.FPS, this.width - 100.0F, this.height - 50.0F);
    batch.end();
  }
  
  void update(float paramFloat)
  {
    if ((Gdx.input.isKeyPressed(4)) || (Gdx.input.isKeyPressed(30))) {
      ((Game)Gdx.app.getApplicationListener()).setScreen(new StartScreen());
    }
    if (this.menu.clicked)
    {
      this.menu.resetButton();
      mp.clear();
      ((Game)Gdx.app.getApplicationListener()).setScreen(new StartScreen());
    }
    int i = 0;
    for (;;)
    {
      if (i >= this.ballButtons.size()) {
        return;
      }
      if (((Button)this.ballButtons.get(i)).clicked)
      {
        StartScreen.prefs.putInteger("Current_Ball", i);
        StartScreen.prefs.flush();
        ((Button)this.ballButtons.get(i)).resetButton();
        this.selected = ((Button)this.ballButtons.get(i)).location;
      }
      i += 1;
    }
  }
}


/* Location:              C:\Users\sam\Documents\ballin\DoodleCannon-dex2jar.jar!\com\studios0110\screens\BallScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */