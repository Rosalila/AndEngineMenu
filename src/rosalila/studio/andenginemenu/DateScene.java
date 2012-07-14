package rosalila.studio.andenginemenu;

import java.io.IOException;
import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.music.MusicManager;
import org.andengine.entity.scene.Scene;
import org.andengine.util.debug.Debug;
import android.content.Context;

public class DateScene extends Scene{
	private SceneSprite background;
	private Music music;
	private DateCharacter character;

	public DateScene(String bgPath, int bgSpriteWidth, int bgSpriteHeight, int bgX, int bgY, String musicPath ){
		super();
		this.setBackgroundEnabled(false);
		
		//Set background
		this.background = new SceneSprite(bgPath, bgSpriteWidth, bgSpriteHeight, bgX, bgY);
		this.attachChild(this.background.getSprite());
		
		//Creates music
		try {
			this.music = MusicFactory.createMusicFromAsset((MusicManager)Global.getResource(Global.MUSIC_MANAGER), (Context)Global.getResource(Global.MAIN_ACTIVITY), musicPath);
			this.music.setLooping(true);
		} catch (final IOException e) {
			Debug.e(e);
		}
	}
	
	public void addCharacter(String charName, String charEmotionKey, String charEmotionSpritePath, int emotionSpriteWidth, int emotionSpriteHeight, int spriteX, int spriteY, int charAffection){
		this.character = new DateCharacter(charName, charEmotionKey, charEmotionSpritePath, emotionSpriteWidth, emotionSpriteHeight, spriteX, spriteY, charAffection);
		this.attachChild(this.character.getEmotionSprite(charEmotionKey).getSprite()); //Que ondas con varios characters y las layers? 
	}
	
	public DateCharacter getCharacter(){
		return this.character;
	}
	
	public void load(String charEmotionKey){
		this.background.getAtlas().load();
		if (charEmotionKey != null){
			this.character.getEmotionSprite(charEmotionKey).getAtlas().load();
		}
		DateScene.this.music.play();
	}
}
