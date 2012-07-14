package rosalila.studio.andenginemenu;

import java.util.HashMap;

public class DateCharacter {
	private String name;
	private int affection; //Ranging from 0 to 100
	private HashMap <String, SceneSprite> emotionSprite;
	
	public DateCharacter(String name, String emotionKey, String emotionSpritePath, int emotionSpriteWidth, int emotionSpriteHeight, int spriteX, int spriteY, int affection){
		this.name = name;
		if (affection >= 0 && affection <= 100){
			this.affection = affection;
		}
		else{
			this.affection = 0;
		}
		SceneSprite mSprite = new SceneSprite (emotionSpritePath, emotionSpriteWidth, emotionSpriteHeight, spriteX, spriteY);
		this.emotionSprite = new HashMap <String, SceneSprite>();
		this.emotionSprite.put(emotionKey, mSprite);
	}
	
	public void addEmotionSprite(String emotionKey, String emotionSpritePath, int emotionSpriteWidth, int emotionSpriteHeight, int spriteX, int spriteY){
		SceneSprite mSprite = new SceneSprite (emotionSpritePath, emotionSpriteWidth, emotionSpriteHeight, spriteX, spriteY);
		this.emotionSprite.put(emotionKey, mSprite);
	}
	
	public SceneSprite getEmotionSprite(String emotionKey){
		if(this.emotionSprite.containsKey(emotionKey)){
			return this.emotionSprite.get(emotionKey);
		}
		else{
			return null;
		}
	}
	
	public int getAffection(){
		return this.affection;
	}
	
	public void addAffection(int points){
		if(points >= 0){
			this.affection += points;
			if (this.affection > 100){
				this.affection = 100;
			}
		}
	}
	
	public void substractAffection(int points){
		if(points >= 0){
			this.affection -= points;
			if (this.affection < 0){
				this.affection = 0;
			}
		}
	}
	
	public String getName(){
		return this.name;
	}
}