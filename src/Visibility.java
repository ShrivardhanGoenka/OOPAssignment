/**
 * The {@code Visibility} interface represents objects that can be toggled between hidden or shown.
 * This serves to manage the visibility states.
 */

public interface Visibility {
    /**
     * Hides the object
     */
    void hide();

    /**
     * Shows the object
     */
    void show();

    /**
     * Checks if the object is currently visible.
     *
     * @return {@code true} if the object is visible, otherwise {@code false}.
     */
    boolean isVisible();
}
