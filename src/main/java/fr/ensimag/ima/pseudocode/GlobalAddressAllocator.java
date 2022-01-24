package fr.ensimag.ima.pseudocode;

/**
 * An adress allocator relative to a register.
 * This gives out adresses of the form 1(GB), 0(LB) etc.
 */
public class GlobalAddressAllocator implements AddressAllocator {
    int offset = 1;

   public DAddr allocate() {
        DAddr addr = new RegisterOffset(offset, Register.GB);
        offset++;
        return addr;
    }
}
