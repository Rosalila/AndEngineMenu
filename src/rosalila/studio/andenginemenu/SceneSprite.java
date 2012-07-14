package rosalila.studio.andenginemenu;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import android.content.Context;

public class SceneSprite {
	private BitmapTextureAtlas sceneTextureAtlas;
	private ITextureRegion sceneTextureRegion;
	private Sprite sceneSprite;
	
	public SceneSprite(String spritePath, int width, int height, int posX, int posY){
		this.sceneTextureAtlas = new BitmapTextureAtlas((TextureManager) Global.getResource(Global.TEXTURE_MANAGER), width, height, TextureOptions.BILINEAR);
		this.sceneTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.sceneTextureAtlas, (Context)Global.getResource(Global.MAIN_ACTIVITY), spritePath, 0, 0);
		this.sceneSprite = new Sprite(posX, posY, this.sceneTextureRegion, (VertexBufferObjectManager)Global.getResource(Global.VERTEX_BUFFERED_OBJECT_MANAGER));
	}
	
	public void setPosition(int posX, int posY){
		this.sceneSprite.setX(posX);
		this.sceneSprite.setY(posY);
	}
	
	public BitmapTextureAtlas getAtlas(){
		return this.sceneTextureAtlas;
	}
	
	public ITextureRegion getRegion(){
		return this.sceneTextureRegion;
	}
	
	public Sprite getSprite(){
		return this.sceneSprite;
	}
}
