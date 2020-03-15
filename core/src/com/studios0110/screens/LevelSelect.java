package com.studios0110.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.studios0110.splash.Splash;
import com.studios0110.user_interface.Button;
import com.studios0110.user_interface.LevelButton;
import java.io.PrintStream;
import java.util.ArrayList;

public class LevelSelect
  implements NewScreenInterface
{
  int FPS;
  Texture bg;
  int currentUnlockedLevel;
  BitmapFont fpsFONT;
  float height = Splash.screenH;
  ArrayList<LevelButton> levelButtons;
  Texture levelSelectScreen;
  Button menu;
  int page;
  int showFPSDelay;
  float width = Splash.screenW;
  
  private void setLevelButtons()
  {
    this.levelSelectScreen = ((Texture)Splash.manager.get("ui/LevelSelectPage.png"));
    this.levelButtons.add(new LevelButton("Level", new Vector2(70.0F, 500.0F), 1));
    this.levelButtons.add(new LevelButton("Level", new Vector2(270.0F, 500.0F), 2));
    this.levelButtons.add(new LevelButton("Level", new Vector2(470.0F, 500.0F), 3));
    this.levelButtons.add(new LevelButton("Level", new Vector2(670.0F, 500.0F), 4));
    this.levelButtons.add(new LevelButton("Level", new Vector2(870.0F, 500.0F), 5));
    this.levelButtons.add(new LevelButton("Level", new Vector2(1070.0F, 500.0F), 6));
    this.levelButtons.add(new LevelButton("Level", new Vector2(1270.0F, 500.0F), 7));
    this.levelButtons.add(new LevelButton("Level", new Vector2(70.0F, 300.0F), 8));
    this.levelButtons.add(new LevelButton("Level", new Vector2(270.0F, 300.0F), 9));
    this.levelButtons.add(new LevelButton("Level", new Vector2(470.0F, 300.0F), 10));
    this.levelButtons.add(new LevelButton("Level", new Vector2(670.0F, 300.0F), 11));
    this.levelButtons.add(new LevelButton("Level", new Vector2(870.0F, 300.0F), 12));
    this.levelButtons.add(new LevelButton("Level", new Vector2(1070.0F, 300.0F), 13));
    this.levelButtons.add(new LevelButton("Level", new Vector2(1270.0F, 300.0F), 14));
    this.levelButtons.add(new LevelButton("Level", new Vector2(70.0F, 100.0F), 15));
    this.levelButtons.add(new LevelButton("Level", new Vector2(270.0F, 100.0F), 16));
    this.levelButtons.add(new LevelButton("Level", new Vector2(470.0F, 100.0F), 17));
    this.levelButtons.add(new LevelButton("Level", new Vector2(670.0F, 100.0F), 18));
    this.levelButtons.add(new LevelButton("Level", new Vector2(870.0F, 100.0F), 19));
    this.levelButtons.add(new LevelButton("Level", new Vector2(1070.0F, 100.0F), 20));
    this.levelButtons.add(new LevelButton("Level", new Vector2(1270.0F, 100.0F), 21));
    int i = 0;
    for (;;)
    {
      if (i >= this.levelButtons.size()) {
        return;
      }
      mp.addProcessor(new GestureDetector((GestureDetector.GestureListener)this.levelButtons.get(i)));
      i += 1;
    }
  }
  
  public void dispose() {}
  
  public void hide() {}
  
  public void pause() {}
  
  public void render(float paramFloat)
  {
    Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
    Gdx.gl.glClear(16384);
    batch.setProjectionMatrix(Splash.camera.combined);
    shapes.setProjectionMatrix(Splash.camera.combined);
    batch.begin();
    batch.draw(this.levelSelectScreen, 0.0F, 0.0F);
    batch.draw(this.bg, 0.0F, -100.0F);
    int i = this.page - 1;
    for (;;)
    {
      if (i >= this.page * 21)
      {
        this.menu.draw(batch);
        batch.end();
        shapes.begin();
        shapes.end();
        update(paramFloat);
        return;
      }
      ((LevelButton)this.levelButtons.get(i)).draw(batch);
      i += 1;
    }
  }
  
  public void resize(int paramInt1, int paramInt2) {}
  
  public void resume() {}
  
  public void show()
  {
    Splash.camera.zoom = 1.0F;
    Splash.camera.update();
    this.levelButtons = new ArrayList();
    this.currentUnlockedLevel = StartScreen.prefs.getInteger("Unlocked_Level");
    this.menu = new Button("Menu", new Vector2(1150.0F, 690.0F));
    Gdx.input.setCatchBackKey(true);
    mp.addProcessor(new GestureDetector(this.menu));
    System.out.println("Level Select Screen");
    shapes.setAutoShapeType(true);
    this.fpsFONT = new BitmapFont();
    Gdx.input.setInputProcessor(mp);
    this.bg = ((Texture)Splash.manager.get("levels/bg.png"));
    this.page = 1;
    setLevelButtons();
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
    int i = 0;
    for (;;)
    {
      if (i >= this.levelButtons.size())
      {
        if (this.menu.clicked)
        {
          this.menu.resetButton();
          mp.clear();
          ((Game)Gdx.app.getApplicationListener()).setScreen(new StartScreen());
        }
        return;
      }
      if ((this.currentUnlockedLevel >= ((LevelButton)this.levelButtons.get(i)).level) && (((LevelButton)this.levelButtons.get(i)).clicked))
      {
        GameScreen.currentLevel = ((LevelButton)this.levelButtons.get(i)).level;
        ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen());
      }
      i += 1;
    }
  }
}


/* Location:              C:\Users\sam\Documents\ballin\DoodleCannon-dex2jar.jar!\com\studios0110\screens\LevelSelect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */