package xyz.kvantum.nanotube;

/**
 * A {@link ChainLink} object that receives items but
 * is unable to act on them. However, the class is still
 * able to modify the instance it is sent.
 *
 * @param <Input> Input type
 */
@SuppressWarnings({"unused, WeakerAccess"})
public abstract class Receiver<Input> extends ChainLink<Input, Input> {

    /**
     * Defines the action that will be performed
     * once the item is passed into the Receiver
     *
     * @param item Incoming item
     */
    protected abstract void receive(Input item);

    @Override
    protected final Input handle(final Input item) {
        this.receive(item);
        return item;
    }

}
