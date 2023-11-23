//javadoc
/**
 * The {@code Visibility} interface represents objects that can be toggled between hidden or shown.
 * This serves to manage the visibility states.
 */

public interface Visibility {
    /**
     * Hides the object
     */
    void hide() throws CampException;

    /**
     * Shows the object
     */
    void show() throws CampException;

    /**
     * Checks if the object is currently visible.
     *
     * @return {@code true} if the object is visible, otherwise {@code false}.
     */
    boolean isVisible();
}
