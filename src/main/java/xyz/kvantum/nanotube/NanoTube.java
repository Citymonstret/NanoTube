package xyz.kvantum.nanotube;

/**
 * The main pipeline class
 *
 * @param <T> (First) input type
 */
@SuppressWarnings({"unused, WeakerAccess"})
public final class NanoTube<T> {

    private final ChainLink<T, T> firstItem;

    private NanoTube(final ChainLink<T, T> firstItem) {
        this.firstItem = firstItem;
    }

    /**
     * Get the first item in the chain
     *
     * @return first item
     */
    public final ChainLink<T, T> getFirstItem() {
        return this.firstItem;
    }

    /**
     * Start the chain
     *
     * @param item First item that will be passed into the chain
     */
    public final void initiate(final T item) {
        this.firstItem.act(item);
    }

    /**
     * Construct a new NanoTube and specify the chain initiator
     *
     * @param firstItem Chain initiator
     * @param <T>       Object type
     * @return the initialized NanoTube instance
     */
    public static <T> NanoTube<T> construct(final ChainLink<T, T> firstItem) {
        return new NanoTube<>(firstItem);
    }

}
