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
import com.badlogic.gdx.math.Vector2;
import com.studios0110.splash.Splash;
import com.studios0110.user_interface.Button;
import java.io.PrintStream;

public class StartScreen
  implements NewScreenInterface
{
  public static Preferences prefs = Gdx.app.getPreferences("ballin.studios0110.prefs");
  int FPS;
  Button ball;
  Button cannon;
  BitmapFont fpsFONT;
  float height = Splash.screenH;
  Button play;
  int showFPSDelay;
  Texture startScreen;
  float width = Splash.screenW;
  
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
    batch.draw(this.startScreen, 0.0F, 0.0F);
    this.play.draw(batch);
    this.cannon.draw(batch);
    this.ball.draw(batch);
    batch.end();
    shapes.begin();
    shapes.end();
    updateButtons(paramFloat);
  }
  
  public void resize(int paramInt1, int paramInt2) {}
  
  public void resume() {}
  
  public void show()
  {
    Splash.camera.zoom = 1.0F;
    Splash.camera.update();
    if (!prefs.contains("Unlocked_Level")) {
      prefs.putInteger("Unlocked_Level", 1);
    }
    if (!prefs.contains("Current_Ball")) {
      prefs.putInteger("Current_Ball", 0);
    }
    if (!prefs.contains("Current_Cannon")) {
      prefs.putInteger("Current_Cannon", 0);
    }
    prefs.flush();
    System.out.println("Construct StartScreen");
    shapes.setAutoShapeType(true);
    this.fpsFONT = new BitmapFont();
    Gdx.input.setCatchBackKey(true);
    Gdx.input.setInputProcessor(mp);
    this.startScreen = ((Texture)Splash.manager.get("ui/StartPage.png"));
    this.play = new Button("Play", new Vector2(540.0F, 500.0F));
    this.cannon = new Button("Cannon", new Vector2(540.0F, 300.0F));
    this.ball = new Button("Ball", new Vector2(540.0F, 100.0F));
    mp.addProcessor(new GestureDetector(this.play));
    mp.addProcessor(new GestureDetector(this.cannon));
    mp.addProcessor(new GestureDetector(this.ball));
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
  
  void updateButtons(float paramFloat)
  {
    if (this.play.clicked)
    {
      mp.clear();
      ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelSelect());
    }
    if (this.cannon.clicked)
    {
      mp.clear();
      ((Game)Gdx.app.getApplicationListener()).setScreen(new CannonScreen());
    }
    if (this.ball.clicked)
    {
      mp.clear();
      ((Game)Gdx.app.getApplicationListener()).setScreen(new BallScreen());
    }
  }
}


/* Location:              C:\Users\sam\Documents\ballin\DoodleCannon-dex2jar.jar!\com\studios0110\screens\StartScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */