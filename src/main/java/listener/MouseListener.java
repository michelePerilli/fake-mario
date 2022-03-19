package listener;

import it.pixel.vectors.Vector2f;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

/**
 * Project: fake-mario
 * Author: Michele
 * File: MouseListener
 * Creation: 19/03/2022
 */
public class MouseListener {

    private static final Logger LOG = Logger.getLogger("[Mouse Listener]");

    /**
     * The constant instance.
     */
    private static MouseListener instance = null;

    /**
     * The Scroll.
     */
    private Vector2f scroll;
    /**
     * The Pos.
     */
    private Vector2f pos;
    /**
     * The Last pos.
     */
    private Vector2f lastPos;

    /**
     * The Mouse button pressed.
     */
    private final Boolean[] mouseButtonPressed = {
            Boolean.FALSE, Boolean.FALSE, Boolean.FALSE
    };

    /**
     * The Is dragging.
     */
    private boolean isDragging;

    /**
     * Instantiates a new Mouse listener.
     */
    private MouseListener() {
        scroll = new Vector2f(0, 0);
        pos = new Vector2f(0, 0);
        lastPos = new Vector2f(0, 0);
    }


    /**
     * Get mouse listener.
     *
     * @return the mouse listener
     */
    public static MouseListener get() {
        if (instance == null) instance = new MouseListener();
        return instance;
    }

    /**
     * Mouse pos callback.
     *
     * @param window the window
     * @param x      the x
     * @param y      the y
     */
    public static void mousePosCallback(long window, double x, double y) {
        get().lastPos = new Vector2f(get().pos.x(), get().pos.y());
        get().pos = new Vector2f((float) x, (float) y);
        get().isDragging = new ArrayList<>(List.of(get().mouseButtonPressed)).contains(Boolean.TRUE);
//        LOG.info("mousePosCallback: " + x + " " + y + "\n");
    }

    /**
     * Mouse button callback.
     *
     * @param window the window
     * @param button the button
     * @param action the action
     * @param mods   the mods
     */
    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        if (button < get().mouseButtonPressed.length) {
            if (action == GLFW_PRESS) {
                get().mouseButtonPressed[button] = true;
            } else if (action == GLFW_RELEASE) {
                get().mouseButtonPressed[button] = false;
                get().isDragging = false;
            }
        }
        LOG.info("mouseButtonCallback: " + button + " " + action + "\n");

    }

    /**
     * Mouse scroll callback.
     *
     * @param window the window
     * @param x      the x
     * @param y      the y
     */
    public static void mouseScrollCallback(long window, double x, double y) {
        get().scroll = new Vector2f((float) x, (float) y);
        LOG.info("mouseScrollCallback: " + x + " " + y + "\n");

    }

    /**
     * End frame.
     */
    public static void endFrame() {
        get().scroll = new Vector2f(0, 0);
        get().lastPos = new Vector2f(get().pos.x(), get().pos.y());
    }

    /**
     * Gets pos.
     *
     * @return the pos
     */
    public static Vector2f getPos() {
        return get().pos;
    }

    /**
     * Gets shift.
     *
     * @return the shift
     */
    public static Vector2f getShift() {
        return new Vector2f(get().lastPos.x() - get().pos.x(), get().lastPos.y() - get().pos.y());
    }

    /**
     * Gets scroll.
     *
     * @return the scroll
     */
    public static Vector2f getScroll() {
        return get().scroll;
    }

    /**
     * Is dragging boolean.
     *
     * @return the boolean
     */
    public static boolean isDragging() {
        return get().isDragging;
    }

    /**
     * Is mouse button down boolean.
     *
     * @param button the button
     * @return the boolean
     */
    public static boolean isMouseButtonDown(int button) {
        if (button < get().mouseButtonPressed.length) {
            return get().mouseButtonPressed[button];
        } else return false;
    }

}
