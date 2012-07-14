package rosalila.studio.andenginemenu;

import org.andengine.audio.music.MusicFactory;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import android.view.KeyEvent;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga
 *
 * @author Nicolas Gramlich
 * @since 01:30:15 - 02.04.2010
 */
public class MainActivity extends SimpleBaseGameActivity implements IOnMenuItemClickListener {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final int CAMERA_WIDTH = 720;
	private static final int CAMERA_HEIGHT = 480;

	// ===========================================================
	// Fields
	// ===========================================================

	protected Camera mCamera;

	protected Scene mMainScene;
	
	protected PauseMenuScene mMenuPauseScene;

	protected ITextureRegion mMenuResetTextureRegion;
	protected ITextureRegion mMenuQuitTextureRegion;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public EngineOptions onCreateEngineOptions() {
		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
 
		EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera);
		engineOptions.getAudioOptions().setNeedsMusic(true);
		return engineOptions;
		
	}

	@Override
	public void onCreateResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		MusicFactory.setAssetBasePath("mfx/");
		
		Global.putResource(Global.TEXTURE_MANAGER,this.getTextureManager());
		Global.putResource(Global.VERTEX_BUFFERED_OBJECT_MANAGER,this.getVertexBufferObjectManager());
		Global.putResource(Global.MUSIC_MANAGER,this.mEngine.getMusicManager());
		Global.putResource(Global.MAIN_ACTIVITY,this);
		
	}

	@Override
	public Scene onCreateScene() {
		
		this.mEngine.registerUpdateHandler(new FPSLogger());
		this.mMainScene = new MainMenuScene(mCamera);
		
		this.mMenuPauseScene = new PauseMenuScene(mCamera);
		
		return mMainScene;
	}

	@Override
	public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent)
	{
		if(pEvent.getAction() == TouchEvent.ACTION_DOWN) {
			if(this.mEngine.getScene().hasChildScene()) {
				/* Remove the menu and reset it. */
				this.mEngine.getScene().back();
			} else {
				/* Attach the menu. */
				this.mEngine.getScene().setChildScene(this.mMenuPauseScene, false, true, true);
			}
			return true;
		} else {
			return super.onKeyDown(pKeyCode, pEvent);
		}
	}

	@Override
	public boolean onMenuItemClicked(final MenuScene pMenuScene, final IMenuItem pMenuItem, final float pMenuItemLocalX, final float pMenuItemLocalY) {
		if(pMenuScene instanceof MainMenuScene)
		{
			MainMenuScene main_menu = (MainMenuScene) pMenuScene;
			int item_id=pMenuItem.getID();
			if(main_menu.MENU_RESET==item_id)
			{
				/* Restart the animation. */
				this.mMainScene.reset();

				/* Remove the menu and reset it. */
				this.mMainScene.clearChildScene();
				//this.mMenuScene.reset();
				return true;
			}
			else if(main_menu.MENU_QUIT==item_id)
			{
				/* End Activity. */
				this.finish();
				return true;
			}
			else if(main_menu.MENU_SHOOTER==item_id)
			{
				/* End Activity. */
				mEngine.setScene(new ShooterScene());
				
				return true;
			}
			else if(main_menu.MENU_DATINGSIM==item_id)
			{
				/* End Activity. */
				mEngine.setScene(new DatingSimScene().getActualScene());
				
				return true;
			}
			
			return false;
		}
		else if(pMenuScene instanceof PauseMenuScene)
		{
			PauseMenuScene main_menu = (PauseMenuScene) pMenuScene;
			int item_id=pMenuItem.getID();
			if(main_menu.MENU_BACK==item_id)
			{
				this.mEngine.getScene().back();
				return true;
			}
			else if(main_menu.MENU_MAIN==item_id)
			{
				/* End Activity. */
				this.mEngine.getScene().back();
				this.mEngine.setScene(this.mMainScene);
				return true;
			}
			else if(main_menu.MENU_QUIT==item_id)
			{
				/* End Activity. */
				this.finish();
				return true;
			}
			
			return false;
		}
		
		return false;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
