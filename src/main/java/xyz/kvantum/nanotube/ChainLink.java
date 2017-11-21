package xyz.kvantum.nanotube;

import java.util.Optional;

/**
 * Any object in a {@link NanoTube} chain that is able to interact with
 * the pipeline flow
 *
 * @param <Input>  Input Type
 * @param <Output> Output Type
 */
@SuppressWarnings({"unused, WeakerAccess"})
public abstract class ChainLink<Input, Output> {

    private ChainLink<Output, ?> nextItem = null;

    /**
     * Sets the {@link ChainLink} that will be next in the procedure chain
     *
     * @param nextItem Next chain link
     * @param <T>      Link input type
     * @return the registered chain link
     */
    public final <T> ChainLink<Output, T> next(final ChainLink<Output, T> nextItem) {
        this.nextItem = nextItem;
        return nextItem;
    }

    /**
     * Get the nex item in the chain
     *
     * @return optional chain link item (if registered in {@link #next(ChainLink)}
     */
    protected final Optional<ChainLink<Output, ?>> getNextItem() {
        return Optional.ofNullable(this.nextItem);
    }

    protected void act(final Input item) {
        final Output handleStatus = this.handle(item);
        if (handleStatus != null) {
            final Optional<ChainLink<Output, ?>> nextItemOptional = getNextItem();
            if (nextItemOptional.isPresent()) {
                nextItemOptional.get().act(handleStatus);
            }
        }
    }

    protected abstract Output handle(final Input item);

}
