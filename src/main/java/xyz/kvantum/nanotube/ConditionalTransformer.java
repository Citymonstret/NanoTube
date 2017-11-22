package xyz.kvantum.nanotube;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * A {@link Transformer} that only acts on the object when the
 * supplied {@link Predicate} returns true
 * @param <T> Input type
 */
@SuppressWarnings({"unused, WeakerAccess"})
public abstract class ConditionalTransformer<T> extends ChainLink<T, T> {

    private final Predicate<T> condition;

    /**
     * Construct a new conditional transformer with a given condition
     *
     * @param condition Condition that has to be me in order for the
     *                  transformer to act on the object
     */
    public ConditionalTransformer(final Predicate<T> condition) {
        this.condition = condition;
    }

    /**
     * Overrides {@link ChainLink#act(Object)}
     */
    public final ChainLink<?, ?> act(final T item) throws Throwable {
        final T handleStatus;
        if (condition.test(item)) {
            handleStatus = this.handle(item);
        } else {
            handleStatus = item;
        }
        if (handleStatus != null) {
            final Optional<ChainLink<T, ?>> nextItemOptional = getNextItem();
            if (nextItemOptional.isPresent()) {
                return nextItemOptional.get().act(handleStatus);
            }
        }
        return this;
    }
}
