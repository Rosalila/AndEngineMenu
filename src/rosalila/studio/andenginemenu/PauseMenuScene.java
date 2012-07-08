package rosalila.studio.andenginemenu;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.content.Context;
import android.opengl.GLES20;

public class PauseMenuScene extends MenuScene{

	public static final int MENU_BACK = 0;
	public static final int MENU_MAIN = MENU_BACK + 1;
	public static final int MENU_QUIT = MENU_MAIN + 1;
	
	private BitmapTextureAtlas mMenuTexture;
	private ITextureRegion mMenuBackTextureRegion;
	private ITextureRegion mMenuMainTextureRegion;
	private ITextureRegion mMenuQuitTextureRegion;
	
	public PauseMenuScene(Camera camera)
	{
		super(camera);
		
		this.setBackgroundEnabled(false);
		
		this.mMenuTexture = new BitmapTextureAtlas((TextureManager) Global.getResource(Global.TEXTURE_MANAGER), 256, 328, TextureOptions.BILINEAR);
		this.mMenuBackTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mMenuTexture, (Context)Global.getResource(Global.MAIN_ACTIVITY), "menu_back.png", 0, 0);
		this.mMenuMainTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mMenuTexture, (Context)Global.getResource(Global.MAIN_ACTIVITY), "menu_main.png", 0, 50);
		this.mMenuQuitTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mMenuTexture, (Context)Global.getResource(Global.MAIN_ACTIVITY), "menu_quit.png", 0, 150);
		this.mMenuTexture.load();
		
		final SpriteMenuItem resetMenuItem = new SpriteMenuItem(MENU_BACK, this.mMenuBackTextureRegion, (VertexBufferObjectManager)Global.getResource(Global.VERTEX_BUFFERED_OBJECT_MANAGER) );
		resetMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		this.addMenuItem(resetMenuItem);
		
		final SpriteMenuItem mainMenuItem = new SpriteMenuItem(MENU_MAIN, this.mMenuMainTextureRegion, (VertexBufferObjectManager)Global.getResource(Global.VERTEX_BUFFERED_OBJECT_MANAGER) );
		mainMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		this.addMenuItem(mainMenuItem);

		final SpriteMenuItem quitMenuItem = new SpriteMenuItem(MENU_QUIT, this.mMenuQuitTextureRegion, (VertexBufferObjectManager)Global.getResource(Global.VERTEX_BUFFERED_OBJECT_MANAGER) );
		quitMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		this.addMenuItem(quitMenuItem);

		this.buildAnimations();

		this.setOnMenuItemClickListener((MainActivity)Global.getResource(Global.MAIN_ACTIVITY));
	}
}
