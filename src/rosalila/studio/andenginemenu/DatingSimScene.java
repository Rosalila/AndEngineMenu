package rosalila.studio.andenginemenu;

import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.music.MusicManager;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
//import org.andengine.examples.MusicExample;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import android.content.Context;

public class DatingSimScene extends Scene {
	private Sprite bg;
	private Music music;

	public DatingSimScene()
	{
		super();
		this.setBackgroundEnabled(false);
		BitmapTextureAtlas mBitmapTextureAtlas = new BitmapTextureAtlas((TextureManager) Global.getResource(Global.TEXTURE_MANAGER), 720, 480, TextureOptions.BILINEAR);
		ITextureRegion mDatingSimTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, (Context)Global.getResource(Global.MAIN_ACTIVITY), "datingsim_background.png", 0, 0);
		mBitmapTextureAtlas.load();
		bg= new Sprite(0, 0, mDatingSimTextureRegion, (VertexBufferObjectManager)Global.getResource(Global.VERTEX_BUFFERED_OBJECT_MANAGER));
		this.attachChild(bg);
		
		try {
			this.music = MusicFactory.createMusicFromAsset((MusicManager)Global.getResource(Global.MUSIC_MANAGER), (Context)Global.getResource(Global.MAIN_ACTIVITY), "chuy_invasion.ogg");
			this.music.setLooping(true);
		} catch (final IOException e) {
			Debug.e(e);
		}
		
		DatingSimScene.this.music.play();
	}
	
	public void release()
	{
		bg.detachSelf();
		DatingSimScene.this.music.stop();
		this.clearChildScene();
	}
}
