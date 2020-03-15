package com.studios0110.splash;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.studios0110.screens.StartScreen;

public class Splash
  implements Screen
{
  public static OrthographicCamera camera;
  public static FileHandle levels;
  public static AssetManager manager = new AssetManager();
  public static int screenH = 800;
  public static int screenW;
  SpriteBatch batch = new SpriteBatch();
  private float delayTime = 0.5F;
  private boolean[] done;
  boolean doneLoadingContacts;
  BitmapFont loadingFont;
  private ShapeRenderer shapeBatch = new ShapeRenderer();
  private Image splashScreen;
  private Image splashScreen2;
  private Stage stage = new Stage();
  
  static
  {
    camera = new OrthographicCamera();
    screenW = 1500;
  }
  
  public void dispose() {}
  
  public void hide() {}
  
  public void loadSplashScreens()
  {
    this.loadingFont = new BitmapFont();
    this.splashScreen = new Image(new Texture("splash/splash1.png"));
    this.splashScreen2 = new Image(new Texture("splash/splash2.png"));
    this.splashScreen.setWidth(Gdx.graphics.getWidth());
    this.splashScreen.setHeight(Gdx.graphics.getHeight());
    this.splashScreen2.setWidth(Gdx.graphics.getWidth());
    this.splashScreen2.setHeight(Gdx.graphics.getHeight());
    this.splashScreen.addAction(Actions.sequence(Actions.alpha(0.0F), Actions.fadeIn(this.delayTime * 3.0F), Actions.delay(this.delayTime * 3.0F), Actions.run(new Runnable()
    {
      public void run()
      {
        Splash.this.done[0] = 1;
      }
    })));
    this.splashScreen2.addAction(Actions.sequence(Actions.alpha(0.0F), Actions.delay(this.delayTime * 3.0F), Actions.fadeIn(this.delayTime * 1.5F), Actions.fadeOut(this.delayTime * 1.5F)));
    this.stage.addActor(this.splashScreen);
    this.stage.addActor(this.splashScreen2);
  }
  
  public void pause() {}
  
  public void render(float paramFloat)
  {
    Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
    Gdx.gl.glClear(16384);
    if (manager.update()) {
      this.done[1] = true;
    }
    this.shapeBatch.setProjectionMatrix(camera.combined);
    this.batch.setProjectionMatrix(camera.combined);
    this.stage.act();
    this.stage.draw();
    this.shapeBatch.begin(ShapeRenderer.ShapeType.Filled);
    this.shapeBatch.setColor(Color.DARK_GRAY);
    this.shapeBatch.rect(screenW / 2 - 120, 385.0F, 200.0F, 17.0F);
    this.shapeBatch.setColor(Color.GREEN);
    this.shapeBatch.rect(screenW / 2 - 120, 384.0F, manager.getProgress() * 200.0F, 20.0F);
    this.shapeBatch.end();
    if ((this.done[0] != 0) && (this.done[1] != 0)) {
      ((Game)Gdx.app.getApplicationListener()).setScreen(new StartScreen());
    }
  }
  
  public void resize(int paramInt1, int paramInt2) {}
  
  public void resume() {}
  
  public void show()
  {
    camera.setToOrtho(false, screenW, screenH);
    this.done = new boolean[2];
    this.done[0] = false;
    this.done[1] = false;
    loadSplashScreens();
    manager.load("objects/CannonSprite.png", Texture.class);
    manager.load("objects/CannonSprite1.png", Texture.class);
    manager.load("objects/CannonSprite2.png", Texture.class);
    manager.load("objects/CannonSprite3.png", Texture.class);
    manager.load("objects/CannonWheel.png", Texture.class);
    manager.load("objects/Platform.png", Texture.class);
    manager.load("objects/Platform2.png", Texture.class);
    manager.load("objects/Platform3.png", Texture.class);
    manager.load("objects/SmileBall.png", Texture.class);
    manager.load("objects/MonkeyBall.png", Texture.class);
    manager.load("objects/tmlBall.png", Texture.class);
    manager.load("objects/pacBall.png", Texture.class);
    manager.load("objects/CannonExplosion.png", Texture.class);
    manager.load("objects/Bucket.png", Texture.class);
    manager.load("objects/BucketFront.png", Texture.class);
    manager.load("ui/ArrowUp.png", Texture.class);
    manager.load("ui/ArrowUpPushed.png", Texture.class);
    manager.load("ui/ArrowDown.png", Texture.class);
    manager.load("ui/ArrowDownPushed.png", Texture.class);
    manager.load("ui/Fire.png", Texture.class);
    manager.load("ui/FirePushed.png", Texture.class);
    manager.load("ui/Play.png", Texture.class);
    manager.load("ui/PlayPushed.png", Texture.class);
    manager.load("ui/Cannon.png", Texture.class);
    manager.load("ui/CannonPushed.png", Texture.class);
    manager.load("ui/Ball.png", Texture.class);
    manager.load("ui/BallPushed.png", Texture.class);
    manager.load("ui/Level.png", Texture.class);
    manager.load("ui/LevelPushed.png", Texture.class);
    manager.load("ui/Reset.png", Texture.class);
    manager.load("ui/ResetPushed.png", Texture.class);
    manager.load("ui/Menu.png", Texture.class);
    manager.load("ui/MenuPushed.png", Texture.class);
    manager.load("ui/StartPage.png", Texture.class);
    manager.load("ui/LevelSelectPage.png", Texture.class);
    manager.load("ui/CannonPage.png", Texture.class);
    manager.load("ui/CannonSelect0.png", Texture.class);
    manager.load("ui/CannonSelect0Pushed.png", Texture.class);
    manager.load("ui/CannonSelect1.png", Texture.class);
    manager.load("ui/CannonSelect1Pushed.png", Texture.class);
    manager.load("ui/CannonSelect2.png", Texture.class);
    manager.load("ui/CannonSelect2Pushed.png", Texture.class);
    manager.load("ui/CannonSelect3.png", Texture.class);
    manager.load("ui/CannonSelect3Pushed.png", Texture.class);
    manager.load("ui/BallSelect0.png", Texture.class);
    manager.load("ui/BallSelect0Pushed.png", Texture.class);
    manager.load("ui/BallSelect1.png", Texture.class);
    manager.load("ui/BallSelect1Pushed.png", Texture.class);
    manager.load("ui/BallSelect2.png", Texture.class);
    manager.load("ui/BallSelect2Pushed.png", Texture.class);
    manager.load("ui/BallSelect3.png", Texture.class);
    manager.load("ui/BallSelect3Pushed.png", Texture.class);
    manager.load("ui/BallPage.png", Texture.class);
    manager.load("ui/UnkownCannonSelect.png", Texture.class);
    manager.load("ui/Back.png", Texture.class);
    manager.load("ui/BackPushed.png", Texture.class);
    manager.load("ui/EmptyStars.png", Texture.class);
    manager.load("ui/Star.png", Texture.class);
    manager.load("ui/LevelStar.png", Texture.class);
    manager.load("fonts/BallinFont.fnt", BitmapFont.class);
    manager.load("levels/bg.png", Texture.class);
    levels = Gdx.files.internal("levels/levels.json");
  }
}


/* Location:              C:\Users\sam\Documents\ballin\DoodleCannon-dex2jar.jar!\com\studios0110\splash\Splash.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */