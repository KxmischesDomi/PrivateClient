package de.kxmischesdomi.kxmischesdomi.module;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public enum ModuleTab {

	CLIENT,
	PLAYER,
	WORLD,
	SMP;

	public int x, y, focusIndex;

	public static void initPositions() {

		int i = 0;
		for (ModuleTab value : ModuleTab.values()) {
			value.focusIndex = i;
			value.x = 100 + i * 70;
			value.y = 10;
			i++;
		}

	}

	static {
		initPositions();
	}

}
