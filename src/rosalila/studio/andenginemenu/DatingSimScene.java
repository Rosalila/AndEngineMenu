package rosalila.studio.andenginemenu;

import org.andengine.entity.scene.Scene;

public class DatingSimScene extends Scene {
	private DateScene temp;

	public DatingSimScene()
	{
		this.temp = new DateScene("dating_sim/bg3.png", 720, 480, 0, 0, "chuy_invasion.ogg");
		this.temp.addCharacter("Pearl", "Normal", "dating_sim/chicas/pirata_normal.png", 222, 342, 30, 30, 25);
	}
	
	public Scene getActualScene()
	{
		temp.load("Normal");
		return temp;
	}
	public void release()
	{

	}
}
