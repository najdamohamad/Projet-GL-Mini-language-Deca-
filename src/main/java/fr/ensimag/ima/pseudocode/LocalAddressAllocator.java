package fr.ensimag.ima.pseudocode;

public class LocalAddressAllocator implements AddressAllocator {
    int offset = 0;

    @Override
    public DAddr allocate()  {
        DAddr addr = new RegisterOffset(offset, Register.LB);
        offset++;
        return addr;
    }

    @Override
    public DAddr allocate(int size) {
        DAddr addr = new RegisterOffset(offset, Register.LB);
        offset += size;
        return addr;
    }
}
