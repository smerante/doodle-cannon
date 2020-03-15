package com.studios0110.levels;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Json;
import com.studios0110.objects.Ball;
import com.studios0110.objects.Bucket;
import com.studios0110.objects.Cannon;
import com.studios0110.objects.CollideInfo;
import com.studios0110.objects.MovingPlatform;
import com.studios0110.objects.MyCollider;
import com.studios0110.objects.Particle;
import com.studios0110.objects.Platform;
import com.studios0110.objects.RotatingPlatform;
import com.studios0110.screens.LevelSelect;
import com.studios0110.screens.StartScreen;
import com.studios0110.splash.Splash;
import com.studios0110.user_interface.Button;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Level
{
  public static float GRAVITY = -9.8F;
  boolean alreadyUpdated;
  public Button back;
  int ballSelected;
  String[] balls = { "SmileBall", "MonkeyBall", "tmlBall", "pacBall" };
  float cameraDelay;
  float cameraZoom;
  public Cannon cannon;
  int cannonSelected;
  String[] cannons = { "CannonSprite", "CannonSprite1", "CannonSprite2", "CannonSprite3" };
  Texture emptyStars;
  public BitmapFont font;
  String helpText;
  ArrayList<Polygon> intersections;
  Json json = new Json();
  public int level = 1;
  ArrayList<LevelManager> lm = (ArrayList)this.json.fromJson(ArrayList.class, Splash.levels);
  public ArrayList<Platform> platforms;
  Animation starAnimation;
  float stateTime;
  public Bucket winBucket;
  
  public Level(int paramInt)
  {
    GRAVITY = -9.8F;
    this.cameraZoom = 1.0F;
    this.cameraDelay = 1.0F;
    this.platforms = new ArrayList();
    this.ballSelected = StartScreen.prefs.getInteger("Current_Ball");
    this.cannonSelected = StartScreen.prefs.getInteger("Current_Cannon");
    this.intersections = new ArrayList();
    this.cannon = new Cannon(this.cannons[this.cannonSelected], -1000.0F, -1000.0F, -1000.0F, -1000.0F, this.balls[this.ballSelected]);
    this.winBucket = new Bucket(-1000.0F, -1000.0F, this.platforms, 0.0F);
    this.back = new Button("Back", new Vector2(0.0F, 80.0F));
    this.back.visible = true;
    this.font = ((BitmapFont)Splash.manager.get("fonts/BallinFont.fnt"));
    this.helpText = "";
    this.level = paramInt;
    level(paramInt);
    this.alreadyUpdated = false;
    this.emptyStars = ((Texture)Splash.manager.get("ui/EmptyStars.png"));
    Object localObject = (Texture)Splash.manager.get("ui/Star.png");
    localObject = TextureRegion.split((Texture)localObject, ((Texture)localObject).getWidth() / 8, ((Texture)localObject).getHeight() / 8);
    TextureRegion[] arrayOfTextureRegion = new TextureRegion[62];
    int i = 0;
    paramInt = 0;
    if (paramInt >= 8)
    {
      this.starAnimation = new Animation(0.033F, arrayOfTextureRegion);
      return;
    }
    int j = 0;
    for (;;)
    {
      if (j >= 8)
      {
        paramInt += 1;
        break;
      }
      int k = i;
      if (i < 62)
      {
        arrayOfTextureRegion[i] = localObject[paramInt][j];
        k = i + 1;
      }
      j += 1;
      i = k;
    }
  }
  
  private boolean bounceOffCorner(CollideInfo paramCollideInfo, int paramInt)
  {
    if ((Math.abs(paramCollideInfo.ballReflectionAngle) > 360.0F) || (Math.abs(paramCollideInfo.ballReflectionAngle) < 0.0F)) {
      paramCollideInfo.ballReflectionAngle = 0.0F;
    }
    if (MyCollider.checkCornerOverlap(((Platform)this.platforms.get(paramInt)).platformBounds, this.cannon.ball))
    {
      MyCollider.bounceFromCorner(MyCollider.getCornerBallOverlaped(((Platform)this.platforms.get(paramInt)).platformBounds, this.cannon.ball), ((Platform)this.platforms.get(paramInt)).platformBounds, this.cannon.ball);
      ((Platform)this.platforms.get(paramInt)).setHit(true);
      ((Platform)this.platforms.get(paramInt)).sideHit = paramCollideInfo.sideHit;
      return true;
    }
    return false;
  }
  
  private boolean bounceOffIntersection()
  {
    int j = 0;
    int i = 0;
    for (;;)
    {
      if (i >= this.platforms.size()) {
        return false;
      }
      float f1;
      Polygon localPolygon;
      int k;
      if (MyCollider.overlap(((Platform)this.platforms.get(i)).platformBounds, this.cannon.ball) == null)
      {
        ((Platform)this.platforms.get(i)).setHit(false);
        ((Platform)this.platforms.get(i)).sideHit = -1;
        if (j >= 2)
        {
          f1 = 9999999.0F;
          localPolygon = null;
          k = 0;
        }
      }
      else
      {
        for (;;)
        {
          if (k >= this.intersections.size())
          {
            if (localPolygon == null) {
              break label242;
            }
            MyCollider.bounceFromTwoWalls(this.cannon.ball, localPolygon);
            return true;
            j += 1;
            break;
          }
          float f2 = ((Polygon)this.intersections.get(k)).getTransformedVertices()[0] - this.cannon.ball.posX;
          float f3 = ((Polygon)this.intersections.get(k)).getTransformedVertices()[1] - this.cannon.ball.posY;
          f3 = (float)Math.sqrt(f2 * f2 + f3 * f3);
          f2 = f1;
          if (f3 < f1)
          {
            localPolygon = (Polygon)this.intersections.get(k);
            f2 = f3;
          }
          k += 1;
          f1 = f2;
        }
      }
      label242:
      i += 1;
    }
  }
  
  private void bounceOffPlatform(CollideInfo paramCollideInfo, int paramInt)
  {
    if (!((Platform)this.platforms.get(paramInt)).hit)
    {
      this.cannon.ball.setNewVelocity(paramCollideInfo.ballReflectionAngle, (Platform)this.platforms.get(paramInt));
      ((Platform)this.platforms.get(paramInt)).setHit(true);
      ((Platform)this.platforms.get(paramInt)).sideHit = paramCollideInfo.sideHit;
    }
    while (((Platform)this.platforms.get(paramInt)).sideHit == paramCollideInfo.sideHit) {
      return;
    }
    this.cannon.ball.setNewVelocity(paramCollideInfo.ballReflectionAngle, (Platform)this.platforms.get(paramInt));
    ((Platform)this.platforms.get(paramInt)).setHit(true);
    ((Platform)this.platforms.get(paramInt)).sideHit = paramCollideInfo.sideHit;
  }
  
  public void BounceOffPlatforms(float paramFloat)
  {
    if ((this.cannon.ball == null) || (bounceOffIntersection())) {}
    for (;;)
    {
      return;
      int i = 0;
      while (i < this.platforms.size())
      {
        CollideInfo localCollideInfo = MyCollider.overlap(((Platform)this.platforms.get(i)).platformBounds, this.cannon.ball);
        if ((localCollideInfo != null) && (!bounceOffCorner(localCollideInfo, i))) {
          bounceOffPlatform(localCollideInfo, i);
        }
        i += 1;
      }
    }
  }
  
  void cameraUpdate(float paramFloat)
  {
    if (this.cameraDelay <= 0.0F)
    {
      if (Splash.camera.zoom < this.cameraZoom)
      {
        Splash.camera.zoom = this.cameraZoom;
        Splash.camera.update();
      }
      while (Splash.camera.zoom <= this.cameraZoom) {
        return;
      }
      OrthographicCamera localOrthographicCamera = Splash.camera;
      localOrthographicCamera.zoom -= 1.0F * paramFloat;
      Splash.camera.update();
      return;
    }
    this.cameraDelay -= 1.0F * paramFloat;
  }
  
  public void draw(SpriteBatch paramSpriteBatch, float paramFloat)
  {
    update(paramFloat);
    Object localObject = this.platforms.iterator();
    for (;;)
    {
      if (!((Iterator)localObject).hasNext())
      {
        this.back.draw(paramSpriteBatch);
        this.winBucket.drawBack(paramSpriteBatch);
        this.cannon.draw(paramSpriteBatch, paramFloat);
        this.winBucket.drawFront(paramSpriteBatch);
        if (!this.winBucket.win) {
          break;
        }
        this.stateTime += paramFloat;
        localObject = this.starAnimation.getKeyFrame(this.stateTime, true);
        paramSpriteBatch.draw(this.emptyStars, Splash.screenW / 2 - this.emptyStars.getWidth() / 2, Splash.screenH / 2);
        if (this.cannon.shotsFired <= 1.0F) {
          paramSpriteBatch.draw((TextureRegion)localObject, Splash.screenW / 2 - this.emptyStars.getWidth() / 2 - 60 + 600, Splash.screenH / 2 - 75);
        }
        if (this.cannon.shotsFired <= 2.0F) {
          paramSpriteBatch.draw((TextureRegion)localObject, Splash.screenW / 2 - this.emptyStars.getWidth() / 2 - 60 + 300, Splash.screenH / 2 - 75);
        }
        paramSpriteBatch.draw((TextureRegion)localObject, Splash.screenW / 2 - this.emptyStars.getWidth() / 2 - 60, Splash.screenH / 2 - 75);
        this.cannon.win = true;
        this.cannon.ball.win = true;
        if (Cannon.particles.size() < 20)
        {
          paramSpriteBatch = new Random(System.nanoTime());
          paramFloat = this.winBucket.angle + 90.0F + (paramSpriteBatch.nextInt(90) - 45 - 5);
          paramSpriteBatch = new Color(0.3F + paramSpriteBatch.nextFloat(), paramSpriteBatch.nextFloat(), paramSpriteBatch.nextFloat(), 1.0F);
          new Particle(this.cannon.ball.bounds.x, this.cannon.ball.bounds.y, (float)(Math.cos(paramFloat * 3.141592653589793D / 180.0D) * 300.0D), (float)(Math.sin(paramFloat * 3.141592653589793D / 180.0D) * 300.0D), 15.0F, 0.0F, paramSpriteBatch);
        }
        return;
      }
      ((Platform)((Iterator)localObject).next()).drawPlatforms(paramSpriteBatch, paramFloat);
    }
    this.font.draw(paramSpriteBatch, this.helpText, 580.0F, 650.0F);
  }
  
  public void drawMesh(ShapeRenderer paramShapeRenderer)
  {
    paramShapeRenderer.setColor(Color.RED);
    Iterator localIterator = this.platforms.iterator();
    if (!localIterator.hasNext())
    {
      paramShapeRenderer.setColor(Color.PURPLE);
      localIterator = this.intersections.iterator();
    }
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        this.cannon.drawMesh(paramShapeRenderer);
        paramShapeRenderer.polygon(this.winBucket.goal.getTransformedVertices());
        return;
        ((Platform)localIterator.next()).drawMesh(paramShapeRenderer);
        break;
      }
      paramShapeRenderer.polygon(((Polygon)localIterator.next()).getTransformedVertices());
    }
  }
  
  public void drawParticles(ShapeRenderer paramShapeRenderer, float paramFloat)
  {
    this.cannon.drawParticles(paramShapeRenderer, paramFloat);
  }
  
  public void drawPowerBar(ShapeRenderer paramShapeRenderer)
  {
    this.cannon.drawPowerBar(paramShapeRenderer);
  }
  
  public void level(int paramInt)
  {
    this.lm = ((ArrayList)this.json.fromJson(ArrayList.class, Splash.levels));
    GRAVITY = ((LevelManager)this.lm.get(paramInt - 1)).grav;
    this.helpText = ((LevelManager)this.lm.get(paramInt - 1)).helpText;
    this.cameraZoom = 1.0F;
    Splash.camera.zoom = ((LevelManager)this.lm.get(paramInt - 1)).camZoom;
    Splash.camera.update();
    this.cannon = new Cannon(this.cannons[this.cannonSelected], ((LevelManager)this.lm.get(paramInt - 1)).canPos.x, ((LevelManager)this.lm.get(paramInt - 1)).canPos.y, ((LevelManager)this.lm.get(paramInt - 1)).canButPos.x, ((LevelManager)this.lm.get(paramInt - 1)).canButPos.y, this.balls[this.ballSelected]);
    this.winBucket = new Bucket(((LevelManager)this.lm.get(paramInt - 1)).bucketPos.x, ((LevelManager)this.lm.get(paramInt - 1)).bucketPos.y, this.platforms, ((LevelManager)this.lm.get(paramInt - 1)).bucketPos.z);
    int j = ((LevelManager)this.lm.get(paramInt - 1)).numP;
    int k = ((LevelManager)this.lm.get(paramInt - 1)).numMP;
    int i = 0;
    if (i >= ((LevelManager)this.lm.get(paramInt - 1)).numP)
    {
      i = j;
      label327:
      if (i < ((LevelManager)this.lm.get(paramInt - 1)).numMP + j) {
        break label529;
      }
      i = j + k;
    }
    for (;;)
    {
      if (i >= ((LevelManager)this.lm.get(paramInt - 1)).numRP + (j + k))
      {
        return;
        this.platforms.add(new Platform((String)((LevelManager)this.lm.get(paramInt - 1)).platformTextures.get(i), MyCollider.createPolygon((String)((LevelManager)this.lm.get(paramInt - 1)).platformTextures.get(i), ((Vector3)((LevelManager)this.lm.get(paramInt - 1)).platformPositions.get(i)).x, ((Vector3)((LevelManager)this.lm.get(paramInt - 1)).platformPositions.get(i)).y, ((Vector3)((LevelManager)this.lm.get(paramInt - 1)).platformPositions.get(i)).z)));
        i += 1;
        break;
        label529:
        this.platforms.add(new MovingPlatform((String)((LevelManager)this.lm.get(paramInt - 1)).platformTextures.get(i), MyCollider.createPolygon((String)((LevelManager)this.lm.get(paramInt - 1)).platformTextures.get(i), ((Vector3)((LevelManager)this.lm.get(paramInt - 1)).platformPositions.get(i)).x, ((Vector3)((LevelManager)this.lm.get(paramInt - 1)).platformPositions.get(i)).y, ((Vector3)((LevelManager)this.lm.get(paramInt - 1)).platformPositions.get(i)).z), new Vector2(((Vector2)((LevelManager)this.lm.get(paramInt - 1)).velocity.get(i - j)).x, ((Vector2)((LevelManager)this.lm.get(paramInt - 1)).velocity.get(i - j)).y), ((Float)((LevelManager)this.lm.get(paramInt - 1)).changeTime.get(i - j)).floatValue()));
        i += 1;
        break label327;
      }
      this.platforms.add(new RotatingPlatform((String)((LevelManager)this.lm.get(paramInt - 1)).platformTextures.get(i), MyCollider.createPolygon((String)((LevelManager)this.lm.get(paramInt - 1)).platformTextures.get(i), ((Vector3)((LevelManager)this.lm.get(paramInt - 1)).platformPositions.get(i)).x, ((Vector3)((LevelManager)this.lm.get(paramInt - 1)).platformPositions.get(i)).y, ((Vector3)((LevelManager)this.lm.get(paramInt - 1)).platformPositions.get(i)).z), ((Float)((LevelManager)this.lm.get(paramInt - 1)).rotationSpeeds.get(i - (j + k))).floatValue()));
      i += 1;
    }
  }
  
  public void setInput(InputMultiplexer paramInputMultiplexer)
  {
    this.cannon.setInput(paramInputMultiplexer);
    paramInputMultiplexer.addProcessor(new GestureDetector(this.back));
  }
  
  public void update(float paramFloat)
  {
    cameraUpdate(paramFloat);
    BounceOffPlatforms(paramFloat);
    this.winBucket.update(this.cannon.ball);
    if (this.back.clicked) {
      ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelSelect());
    }
    if ((this.winBucket.win) && (!this.alreadyUpdated))
    {
      if ((this.cannon.shotsFired > 1.0F) || (StartScreen.prefs.getInteger("level_" + this.level + "_stars") >= 3)) {
        break label171;
      }
      StartScreen.prefs.putInteger("level_" + this.level + "_stars", 3);
    }
    for (;;)
    {
      updateUnlockedLevels();
      boolean bool = this.winBucket.win;
      return;
      label171:
      if ((this.cannon.shotsFired <= 2.0F) && (StartScreen.prefs.getInteger("level_" + this.level + "_stars") < 2)) {
        StartScreen.prefs.putInteger("level_" + this.level + "_stars", 2);
      } else if (StartScreen.prefs.getInteger("level_" + this.level + "_stars") < 1) {
        StartScreen.prefs.putInteger("level_" + this.level + "_stars", 1);
      }
    }
  }
  
  public void updateUnlockedLevels()
  {
    if (StartScreen.prefs.getInteger("Unlocked_Level") < this.level + 1) {
      StartScreen.prefs.putInteger("Unlocked_Level", this.level + 1);
    }
    StartScreen.prefs.flush();
    this.alreadyUpdated = true;
  }
}


/* Location:              C:\Users\sam\Documents\ballin\DoodleCannon-dex2jar.jar!\com\studios0110\levels\Level.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */