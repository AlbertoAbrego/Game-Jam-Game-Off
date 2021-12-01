package com.gamejam.gameoff.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gamejam.gameoff.ants.GameManager;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//config.fullscreen = true;
		config.width = 1800;
		config.height = 900;
		config.title = "Ants Game by Koch Team";
		new LwjglApplication(new GameManager(), config);
	}
}
