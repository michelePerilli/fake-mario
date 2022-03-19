package listener;

import configuration.BaseClass;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

/**
 * Project: fake-mario
 * Author: Michele
 * File: KeyListener
 * Creation: 19/03/2022
 */
public class KeyListener extends BaseClass {
    /**
     * The constant instance.
     */
    private static KeyListener instance;
    /**
     * The Key pressed.
     */
    private final boolean[] keyPressed = new boolean[350];

    /**
     * Instantiates a new Key listener.
     */
    private KeyListener() {

    }

    /**
     * Get key listener.
     *
     * @return the key listener
     */
    public static KeyListener get() {
        if (instance == null) instance = new KeyListener();
        return instance;
    }

    /**
     * Key callback.
     *
     * @param window   the window
     * @param key      the key
     * @param scanmode the scanmode
     * @param action   the action
     * @param mods     the mods
     */
    public static void keyCallback(long window, int key, int scanmode, int action, int mods) {
        if (action == GLFW_PRESS) {
            get().keyPressed[key] = true;
        } else if (action == GLFW_RELEASE) {
            get().keyPressed[key] = false;
        }
    }

    /**
     * Is key pressed boolean.
     *
     * @param keyCode the key code
     * @return the boolean
     */
    public static boolean isKeyPressed(int keyCode) {
        return get().keyPressed[keyCode];
    }
}
