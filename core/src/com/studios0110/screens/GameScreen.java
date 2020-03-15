package com.studios0110.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.studios0110.levels.Level;
import com.studios0110.objects.Ball;
import com.studios0110.objects.Cannon;
import com.studios0110.splash.Splash;
import java.io.PrintStream;

public class GameScreen
  implements NewScreenInterface
{
  public static int currentLevel = 1;
  int FPS;
  Texture bg;
  BitmapFont fpsFONT;
  float height = Splash.screenH;
  Level level;
  int showFPSDelay;
  float width = Splash.screenW;
  
  public void dispose() {}
  
  public void hide() {}
  
  public void pause() {}
  
  public void render(float paramFloat)
  {
    Gdx.gl.glClearColor(1.0F, 1.0F, 1.0F, 0.0F);
    Gdx.gl.glClear(16384);
    batch.setProjectionMatrix(Splash.camera.combined);
    shapes.setProjectionMatrix(Splash.camera.combined);
    batch.begin();
    batch.draw(this.bg, 0.0F, 0.0F);
    this.level.draw(batch, paramFloat);
    batch.end();
    shapes.begin();
    this.level.drawPowerBar(shapes);
    this.level.drawParticles(shapes, paramFloat);
    shapes.end();
    update(paramFloat);
  }
  
  public void resize(int paramInt1, int paramInt2) {}
  
  public void resume() {}
  
  public void show()
  {
    Splash.camera.zoom = 1.0F;
    Splash.camera.update();
    System.out.println("Game Screen on level " + currentLevel);
    shapes.setAutoShapeType(true);
    this.fpsFONT = new BitmapFont();
    Gdx.input.setCatchBackKey(true);
    Gdx.input.setInputProcessor(mp);
    this.level = new Level(currentLevel);
    this.level.setInput(mp);
    this.bg = ((Texture)Splash.manager.get("levels/bg.png"));
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
    this.fpsFONT.draw(batch, "FPS " + this.FPS, this.width - 150.0F, this.height - 50.0F);
    this.fpsFONT.draw(batch, (int)this.level.cannon.ball.posX + " , " + (int)this.level.cannon.ball.posY, this.width - 150.0F, this.height - 70.0F);
    this.fpsFONT.draw(batch, "Level: " + this.level.level, this.width - 150.0F, this.height - 90.0F);
    this.fpsFONT.draw(batch, "Cam Pos: " + (int)Splash.camera.position.x + "," + (int)Splash.camera.position.y, this.width - 150.0F, this.height - 110.0F);
    this.fpsFONT.draw(batch, "Cam zoom: " + (int)Splash.camera.zoom, this.width - 150.0F, this.height - 130.0F);
    batch.end();
  }
  
  void update(float paramFloat)
  {
    if ((Gdx.input.isKeyPressed(4)) || (Gdx.input.isKeyPressed(30))) {
      ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelSelect());
    }
  }
}


/* Location:              C:\Users\sam\Documents\ballin\DoodleCannon-dex2jar.jar!\com\studios0110\screens\GameScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */