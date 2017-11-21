package xyz.kvantum.nanotube;

/**
 * A {@link ChainLink} object that modifies the incoming item and
 * passed it onwards in the chain
 *
 * @param <T> Input &amp; Output type
 */
@SuppressWarnings({"unused, WeakerAccess"})
public abstract class Transformer<T> extends ChainLink<T, T> {
}
