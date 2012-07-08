package rosalila.studio.andenginemenu;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.content.Context;
import android.opengl.GLES20;

public class MainMenuScene extends MenuScene{
	
	private Sprite bg;

	public static final int MENU_RESET = 0;
	public static final int MENU_QUIT = MENU_RESET + 1;
	public static final int MENU_SHOOTER = MENU_QUIT + 1;
	public static final int MENU_DATINGSIM = MENU_SHOOTER + 1;
	
	private BitmapTextureAtlas mMenuTexture;
	private ITextureRegion mMenuShooterTextureRegion;
	private ITextureRegion mMenuDatingSimTextureRegion;
	private ITextureRegion mMenuQuitTextureRegion;
	
	public MainMenuScene(Camera camera)
	{
		super(camera);
		
		this.setBackgroundEnabled(false);
		BitmapTextureAtlas mBitmapTextureAtlas = new BitmapTextureAtlas((TextureManager) Global.getResource(Global.TEXTURE_MANAGER), 720, 480, TextureOptions.BILINEAR);
		ITextureRegion mDatingSimTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, (Context)Global.getResource(Global.MAIN_ACTIVITY), "main_background.png", 0, 0);
		mBitmapTextureAtlas.load();
		bg= new Sprite(0, 0, mDatingSimTextureRegion, (VertexBufferObjectManager) Global.getResource(Global.VERTEX_BUFFERED_OBJECT_MANAGER));
		this.attachChild(bg);
		
		this.mMenuTexture = new BitmapTextureAtlas((TextureManager) Global.getResource(Global.TEXTURE_MANAGER), 256, 328, TextureOptions.BILINEAR);
		this.mMenuQuitTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mMenuTexture, (Context)Global.getResource(Global.MAIN_ACTIVITY), "menu_quit.png", 0, 50);
		this.mMenuShooterTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mMenuTexture, (Context)Global.getResource(Global.MAIN_ACTIVITY), "menu_shooter.png", 0, 100);
		this.mMenuDatingSimTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mMenuTexture, (Context)Global.getResource(Global.MAIN_ACTIVITY), "menu_datingsim.png", 0, 150);
		this.mMenuTexture.load();
		
		final SpriteMenuItem shooterMenuItem = new SpriteMenuItem(MENU_SHOOTER, this.mMenuShooterTextureRegion, (VertexBufferObjectManager)Global.getResource(Global.VERTEX_BUFFERED_OBJECT_MANAGER) );
		shooterMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		this.addMenuItem(shooterMenuItem);
		
		final SpriteMenuItem datingsimMenuItem = new SpriteMenuItem(MENU_DATINGSIM, this.mMenuDatingSimTextureRegion, (VertexBufferObjectManager)Global.getResource(Global.VERTEX_BUFFERED_OBJECT_MANAGER) );
		datingsimMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		this.addMenuItem(datingsimMenuItem);
		
		final SpriteMenuItem quitMenuItem = new SpriteMenuItem(MENU_QUIT, this.mMenuQuitTextureRegion, (VertexBufferObjectManager)Global.getResource(Global.VERTEX_BUFFERED_OBJECT_MANAGER) );
		quitMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		this.addMenuItem(quitMenuItem);

		this.buildAnimations();

		this.setOnMenuItemClickListener((MainActivity)Global.getResource(Global.MAIN_ACTIVITY));
	}
}
