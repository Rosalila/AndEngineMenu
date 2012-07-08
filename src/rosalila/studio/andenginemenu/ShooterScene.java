package rosalila.studio.andenginemenu;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.content.Context;

public class ShooterScene extends Scene{
	private Sprite bg;

	public ShooterScene() {
		super();
		this.setBackgroundEnabled(false);
		BitmapTextureAtlas mBitmapTextureAtlas = new BitmapTextureAtlas((TextureManager) Global.getResource(Global.TEXTURE_MANAGER), 720, 480, TextureOptions.BILINEAR);
		ITextureRegion mDatingSimTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, (Context)Global.getResource(Global.MAIN_ACTIVITY), "shooter_background.png", 0, 0);
		mBitmapTextureAtlas.load();
		bg= new Sprite(0, 0, mDatingSimTextureRegion, (VertexBufferObjectManager) Global.getResource(Global.VERTEX_BUFFERED_OBJECT_MANAGER));
		this.attachChild(bg);
	}
	
	public void release()
	{
		bg.detachSelf();
		this.clearChildScene();
	}
}