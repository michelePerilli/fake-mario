package jade;

import configuration.BaseClass;
import configuration.Constants;
import listener.KeyListener;
import listener.MouseListener;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Project: FakeMario
 * Author: Michele
 * File: Windows
 * Creation: 19/03/2022
 */
public class Window extends BaseClass {

    /**
     * The Width.
     */
    private final Integer width;
    /**
     * The Height.
     */
    private final Integer height;
    /**
     * The Title.
     */
    private final String title;
    /**
     * The Glfw window.
     */
    private long glfwWindow;
    /**
     * The constant window.
     */
    private static Window window = null;

    /**
     * Instantiates a new Window.
     */
    private Window() {
        this.width = Constants.WIN_WIDTH;
        this.height = Constants.WIN_HEIGHT;
        this.title = Constants.WIN_TITLE;
    }

    /**
     * Get window.
     *
     * @return the window
     */
    public static Window get() {
        if (window == null) window = new Window();
        return window;
    }


    /**
     * Run.
     */
    public void run() {
        init();
        loop();

        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        glfwTerminate();
        var x = glfwSetErrorCallback(null);
        if (x != null) x.free();
    }

    /**
     * Init.
     */
    public void init() {
        GLFWErrorCallback.createPrint(System.err).set();


        if (!glfwInit()) {
            throw new IllegalStateException("S'e' rotto tutto");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_FALSE);

        glfwWindow = glfwCreateWindow(width, height, title, NULL, NULL);
        if (glfwWindow == NULL) {
            throw new IllegalStateException("S'e' rotto tutto xd");
        }

        glfwSetCursorPosCallback(glfwWindow, MouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, MouseListener::mouseScrollCallback);

        glfwSetKeyCallback(glfwWindow, KeyListener::keyCallback);

        glfwMakeContextCurrent(glfwWindow);
        glfwSwapInterval(1);

        glfwShowWindow(glfwWindow);

        GL.createCapabilities();
    }

    /**
     * Loop.
     */
    public void loop() {
        while (!glfwWindowShouldClose(glfwWindow)) {
            glfwPollEvents();
            glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);


            if (KeyListener.isKeyPressed(GLFW_KEY_SPACE)) {
                info("spazio");
            }

            glfwSwapBuffers(glfwWindow);
        }
    }

}
