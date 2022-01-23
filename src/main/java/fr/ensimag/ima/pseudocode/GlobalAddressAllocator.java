package fr.ensimag.ima.pseudocode;

public class GlobalAddressAllocator implements AddressAllocator {
    int offset = 1;

    @Override
    public DAddr allocate()  {
        DAddr addr = new RegisterOffset(offset, Register.GB);
        offset++;
        return addr;
    }

    @Override
    public DAddr allocate(int size) {
        DAddr addr = new RegisterOffset(offset, Register.GB);
        offset += size;
        return addr;
    }
}
