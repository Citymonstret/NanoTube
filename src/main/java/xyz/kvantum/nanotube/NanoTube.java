package xyz.kvantum.nanotube;

import java.util.function.Consumer;

/**
 * The main pipeline class
 */
@SuppressWarnings({"unused, WeakerAccess"})
public final class NanoTube<Input, Output> {

    private final ChainLink<Input, Output> firstItem;
    private Consumer<Throwable> exceptionHandler = Throwable::printStackTrace;

    private NanoTube(final ChainLink<Input, Output> firstItem) {
        this.firstItem = firstItem;
    }

    /**
     * Get the first item in the chain
     *
     * @return first item
     */
    public ChainLink<Input, Output> getFirstItem() {
        return this.firstItem;
    }

    /**
     * Start the chain
     *
     * @param item First item that will be passed into the chain
     */
    public ChainLink<?, ?> initiate(final Input item) {
        try {
            return this.firstItem.act(item);
        } catch (final Throwable throwable) {
            exceptionHandler.accept(throwable);
        }
        return null;
    }

    /**
     * Set the exception handler
     *
     * @param exceptionHandler Exception handler
     * @return this
     */
    public NanoTube<Input, Output> setExceptionHandler(final Consumer<Throwable> exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
        return this;
    }

    /**
     * Construct a new NanoTube and specify the chain initiator
     *
     * @param firstItem Chain initiator
     * @return the initialized NanoTube instance
     */
    public static <Input, Output> NanoTube<Input, Output> construct(final ChainLink<Input, Output> firstItem) {
        return new NanoTube<>(firstItem);
    }

    /**
     * Get a copy of this NanoTube, with the same initiator
     * chain link. Note, however, that the chain link instances
     * are the same. Change any one of them, and all copies will
     * be modified too.
     *
     * @return copy of this
     */
    public NanoTube<Input, Output> copy() {
        final NanoTube<Input, Output> nanoTube = new NanoTube<>(this.getFirstItem());
        nanoTube.setExceptionHandler( this.exceptionHandler );
        return nanoTube;
    }

    /**
     * Set the last chain link
     *
     * @param chainLink (New last)
     * @return this
     */
    @SuppressWarnings("ALL")
    public NanoTube<Input, Output> setLast(final ChainLink chainLink) {
        ChainLink<?, ?> active = getFirstItem();
        while (active.getNextItem().isPresent()) {
            active = active.getNextItem().get();
        }
        active.next(chainLink);
        return this;
    }

}
