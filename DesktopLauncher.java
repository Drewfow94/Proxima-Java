package com.drewfow94.alienblastergame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.drewfow94.alienblastergame.ShooterGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Alien Blaster";
		config.useGL30 = false;

		new LwjglApplication(new ShooterGame(), config);
	}
}
