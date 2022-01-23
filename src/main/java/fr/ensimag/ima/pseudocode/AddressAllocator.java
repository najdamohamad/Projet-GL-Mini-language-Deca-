package fr.ensimag.ima.pseudocode;

/**
 * A generic address allocator.
 * This should be used to allocate on the stack.
 *
 * For example, a global allocator will give out adresses relative to GB,
 * like 0(GB), 1(GB),
 * while a local allocator will give out adresses like 0(LB)...
 *
 * We use this class in order to not have to keep track of addresses ourselves.
 */
public interface AddressAllocator {
    /**
     * Allocate a block of size one on the stack.
     * @return The adress to the allocated block.
     */
    DAddr allocate();

    /**
     * Allocate a block of a size on the stack.
     * @param size The size to allocate.
     * @return The adress to the start of the allocated block.
     */
    DAddr allocate(int size);


}
