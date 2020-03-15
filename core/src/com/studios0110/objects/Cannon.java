package com.studios0110.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.studios0110.splash.Splash;
import com.studios0110.user_interface.Button;
import java.util.ArrayList;
import java.util.Random;

public class Cannon
  implements GestureDetector.GestureListener
{
  public static ArrayList<Particle> particles;
  protected float angle;
  BitmapFont angleOfCannon;
  public Ball ball;
  String ballTexture;
  protected Polygon cannonBarrel;
  Sprite cannonExplosion;
  Sprite cannonSprite;
  Texture cannonTexture;
  Texture cannonWheelTexture;
  protected float explosionVisibility;
  Button fire;
  public boolean fired;
  int i = 0;
  public boolean justFired;
  public float power;
  public float previousPower;
  Button reset;
  public float shotsFired;
  Vector3 touchPos;
  public boolean win = false;
  
  public Cannon(String paramString1, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, String paramString2)
  {
    this.ballTexture = paramString2;
    this.shotsFired = 0.0F;
    this.cannonTexture = ((Texture)Splash.manager.get("objects/" + paramString1 + ".png"));
    this.cannonWheelTexture = ((Texture)Splash.manager.get("objects/CannonWheel.png"));
    this.fire = new Button("Fire", new Vector2(paramFloat3, paramFloat4));
    this.reset = new Button("Reset", new Vector2(0.0F, 200.0F));
    this.cannonSprite = new Sprite(this.cannonTexture);
    this.cannonExplosion = new Sprite((Texture)Splash.manager.get("objects/CannonExplosion.png"));
    this.cannonSprite.setX(paramFloat1);
    this.cannonSprite.setY(paramFloat2);
    this.cannonSprite.setOrigin(40.0F, 33.0F);
    this.cannonBarrel = new Polygon();
    paramFloat3 = this.cannonTexture.getWidth();
    paramFloat4 = this.cannonTexture.getHeight() / 2;
    this.cannonBarrel.setVertices(new float[] { 86.0F + paramFloat1, 42.0F + paramFloat2, paramFloat3 + paramFloat1, paramFloat4 + paramFloat2 + 5.0F, 86.0F + paramFloat1, 65.0F + paramFloat2 });
    this.cannonBarrel.setOrigin(40.0F + paramFloat1, 33.0F + paramFloat2);
    this.cannonExplosion.setX(this.cannonBarrel.getTransformedVertices()[2]);
    this.cannonExplosion.setY(this.cannonBarrel.getTransformedVertices()[3] - this.cannonExplosion.getTexture().getHeight() / 2);
    this.angle = 0.0F;
    this.previousPower = 0.0F;
    this.angleOfCannon = new BitmapFont();
    this.power = 0.0F;
    this.ball = new Ball(this.ballTexture, this, 45.0F);
    this.touchPos = new Vector3(-50.0F, -50.0F, 0.0F);
    particles = new ArrayList();
  }
  
  private void fire()
  {
    this.cannonExplosion.setX(this.cannonBarrel.getTransformedVertices()[2] - this.cannonExplosion.getTexture().getWidth() / 2);
    this.cannonExplosion.setY(this.cannonBarrel.getTransformedVertices()[3] - this.cannonExplosion.getTexture().getHeight() / 2);
    this.previousPower = this.power;
    this.ball = new Ball(this.ballTexture, this, 45.0F);
    this.shotsFired += 1.0F;
    this.fired = true;
    this.justFired = true;
    this.explosionVisibility = 1.0F;
    int j = 0;
    for (;;)
    {
      if (j >= 20)
      {
        new Particle(this.cannonBarrel.getTransformedVertices()[2], this.cannonBarrel.getTransformedVertices()[3], (float)(Math.cos(this.angle * 3.141592653589793D / 180.0D) * (this.power / 10.0F)), (float)(Math.sin(this.angle * 3.141592653589793D / 180.0D) * (this.power / 10.0F)), 10.0F, 20.0F, Color.DARK_GRAY);
        return;
      }
      float f = this.angle + (j - 10);
      Object localObject = new Random(System.nanoTime());
      localObject = new Color(((Random)localObject).nextFloat(), ((Random)localObject).nextFloat(), ((Random)localObject).nextFloat(), 1.0F);
      new Particle(this.cannonBarrel.getTransformedVertices()[2], this.cannonBarrel.getTransformedVertices()[3], (float)(Math.cos(f * 3.141592653589793D / 180.0D) * (this.power * 2.0F)), (float)(Math.sin(f * 3.141592653589793D / 180.0D) * (this.power * 2.0F)), 5.0F, -9.8F, (Color)localObject);
      j += 1;
    }
  }
  
  public void draw(SpriteBatch paramSpriteBatch, float paramFloat)
  {
    if (this.justFired) {
      this.cannonExplosion.draw(paramSpriteBatch, this.explosionVisibility);
    }
    this.cannonSprite.draw(paramSpriteBatch);
    paramSpriteBatch.draw(this.cannonWheelTexture, this.cannonSprite.getX(), this.cannonSprite.getY());
    if ((this.fired) && (this.ball.posY > -100.0F)) {
      this.ball.draw(paramSpriteBatch, paramFloat);
    }
    if (!this.win)
    {
      this.fire.draw(paramSpriteBatch);
      this.reset.draw(paramSpriteBatch);
    }
    update(paramFloat);
  }
  
  public void drawMesh(ShapeRenderer paramShapeRenderer)
  {
    paramShapeRenderer.setColor(Color.RED);
    paramShapeRenderer.polygon(this.cannonBarrel.getTransformedVertices());
    if (this.fired) {
      this.ball.drawMesh(paramShapeRenderer);
    }
  }
  
  public void drawParticles(ShapeRenderer paramShapeRenderer, float paramFloat)
  {
    int j = 0;
    if (j >= particles.size()) {
      j = 0;
    }
    for (;;)
    {
      if (j >= particles.size())
      {
        if (this.win) {
          this.ball.drawWinPath(paramShapeRenderer);
        }
        return;
        ((Particle)particles.get(j)).draw(paramShapeRenderer, paramFloat);
        j += 1;
        break;
      }
      if (((Particle)particles.get(j)).size <= 0.0F) {
        particles.remove(j);
      }
      j += 1;
    }
  }
  
  public void drawPowerBar(ShapeRenderer paramShapeRenderer)
  {
    Gdx.gl.glEnable(3042);
    paramShapeRenderer.set(ShapeRenderer.ShapeType.Filled);
    paramShapeRenderer.setColor(new Color(1.0F, 0.0F, 1.0F, 0.3F));
    paramShapeRenderer.rect(10.0F, 10.0F, this.previousPower / 1000.0F * 500.0F, 50.0F);
    paramShapeRenderer.setColor(Color.RED);
    paramShapeRenderer.rect(10.0F, 10.0F, this.power / 1000.0F * 500.0F, 50.0F);
    paramShapeRenderer.set(ShapeRenderer.ShapeType.Line);
    paramShapeRenderer.setColor(Color.BLACK);
    paramShapeRenderer.rect(10.0F, 10.0F, 500.0F, 50.0F);
  }
  
  public boolean fling(float paramFloat1, float paramFloat2, int paramInt)
  {
    return false;
  }
  
  public boolean longPress(float paramFloat1, float paramFloat2)
  {
    return false;
  }
  
  public boolean pan(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    if (!this.win)
    {
      this.touchPos.set(paramFloat1, paramFloat2, 0.0F);
      Splash.camera.unproject(this.touchPos);
      if (((this.touchPos.x >= this.fire.location.x + this.fire.button.getWidth()) || (this.touchPos.x <= this.fire.location.x) || (this.touchPos.y <= this.fire.location.y) || (this.touchPos.y >= this.fire.location.y + this.fire.button.getHeight())) && ((this.touchPos.x >= this.reset.location.x + this.reset.button.getWidth()) || (this.touchPos.x <= this.reset.location.x) || (this.touchPos.y <= this.reset.location.y) || (this.touchPos.y >= this.reset.location.y + this.reset.button.getHeight())) && (!this.justFired) && ((this.touchPos.x >= 400.0F) || (this.touchPos.x <= 0.0F) || (this.touchPos.y <= 80.0F) || (this.touchPos.y >= 190.0F)))
      {
        paramFloat1 = this.touchPos.x;
        paramFloat2 = this.cannonBarrel.getX();
        paramFloat3 = this.cannonBarrel.getOriginX();
        this.angle = ((float)((float)Math.atan2(this.touchPos.y - (this.cannonBarrel.getY() + this.cannonBarrel.getOriginY()), paramFloat1 - (paramFloat2 + paramFloat3)) * 57.29577951308232D));
        this.cannonBarrel.setRotation(this.angle - 5.0F);
        this.cannonSprite.setRotation(this.angle - 5.0F);
        this.cannonExplosion.setRotation(this.angle - 5.0F);
      }
    }
    return false;
  }
  
  public boolean panStop(float paramFloat1, float paramFloat2, int paramInt1, int paramInt2)
  {
    return false;
  }
  
  public boolean pinch(Vector2 paramVector21, Vector2 paramVector22, Vector2 paramVector23, Vector2 paramVector24)
  {
    return false;
  }
  
  public void setInput(InputMultiplexer paramInputMultiplexer)
  {
    paramInputMultiplexer.addProcessor(new GestureDetector(this.fire));
    paramInputMultiplexer.addProcessor(new GestureDetector(this.reset));
    paramInputMultiplexer.addProcessor(new GestureDetector(this));
  }
  
  public boolean tap(float paramFloat1, float paramFloat2, int paramInt1, int paramInt2)
  {
    return false;
  }
  
  public boolean touchDown(float paramFloat1, float paramFloat2, int paramInt1, int paramInt2)
  {
    if (!this.win)
    {
      this.touchPos.set(paramFloat1, paramFloat2, 0.0F);
      Splash.camera.unproject(this.touchPos);
      if (((this.touchPos.x >= this.fire.location.x + this.fire.button.getWidth()) || (this.touchPos.x <= this.fire.location.x) || (this.touchPos.y <= this.fire.location.y) || (this.touchPos.y >= this.fire.location.y + this.fire.button.getHeight())) && ((this.touchPos.x >= this.reset.location.x + this.reset.button.getWidth()) || (this.touchPos.x <= this.reset.location.x) || (this.touchPos.y <= this.reset.location.y) || (this.touchPos.y >= this.reset.location.y + this.reset.button.getHeight())) && (!this.justFired) && ((this.touchPos.x >= 400.0F) || (this.touchPos.x <= 0.0F) || (this.touchPos.y <= 80.0F) || (this.touchPos.y >= 190.0F)))
      {
        paramFloat1 = this.touchPos.x;
        paramFloat2 = this.cannonSprite.getX();
        float f = this.cannonSprite.getOriginX();
        this.angle = ((float)((float)Math.atan2(this.touchPos.y - (this.cannonSprite.getY() + this.cannonSprite.getOriginY()), paramFloat1 - (paramFloat2 + f)) * 57.29577951308232D));
        this.cannonBarrel.setRotation(this.angle - 5.0F);
        this.cannonSprite.setRotation(this.angle - 5.0F);
        this.cannonExplosion.setRotation(this.angle - 5.0F);
      }
    }
    return false;
  }
  
  void update(float paramFloat)
  {
    if (this.justFired)
    {
      this.fire.visible = false;
      this.explosionVisibility = ((float)(this.explosionVisibility - 0.5D * paramFloat));
      if (this.explosionVisibility <= 0.0F) {
        this.explosionVisibility = 0.0F;
      }
    }
    if (this.reset.clicked)
    {
      this.reset.resetButton();
      Splash.camera.zoom = 1.0F;
      Splash.camera.update();
      this.justFired = false;
      this.fire.visible = true;
    }
    if (this.fire.clicked)
    {
      fire();
      this.fire.resetButton();
    }
    if (this.fire.holding)
    {
      this.justFired = false;
      if (this.power < 1000.0F)
      {
        this.power += 700.0F * paramFloat;
        return;
      }
      this.power = 1000.0F;
      return;
    }
    this.power = 0.0F;
  }
  
  public boolean zoom(float paramFloat1, float paramFloat2)
  {
    return false;
  }
}


/* Location:              C:\Users\sam\Documents\ballin\DoodleCannon-dex2jar.jar!\com\studios0110\objects\Cannon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */