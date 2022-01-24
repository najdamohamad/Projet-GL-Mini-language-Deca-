package fr.ensimag.ima.pseudocode;

/**
 * An adress allocator relative to a register.
 * This gives out adresses of the form 1(GB), 0(LB) etc.
 */
public interface AddressAllocator {
    /**
     * Allocate an address,
     * and bumps the next allocated address.
     * @return the address
     */
    DAddr allocate();
}
