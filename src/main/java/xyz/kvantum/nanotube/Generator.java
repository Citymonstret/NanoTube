package xyz.kvantum.nanotube;

/**
 * A {@link ChainLink} object that generates an output of a specified
 * type, from a given input type. Whilst both the input type and the output
 * type may be the same, it is recommended to use {@link Transformer} in such
 * a case
 *
 * @param <Input>  Input type
 * @param <Output> Output type
 */
@SuppressWarnings({"unused, WeakerAccess"})
public abstract class Generator<Input, Output> extends ChainLink<Input, Output> {
}
