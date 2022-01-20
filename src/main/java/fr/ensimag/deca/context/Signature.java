package fr.ensimag.deca.context;

import java.util.ArrayList;
import java.util.List;

/**
 * Signature of a method (i.e. list of arguments)
 *
 * @author gl47
 * @date 01/01/2022
 */
public class Signature {
    List<Type> args = new ArrayList<Type>();

    public void add(Type t) {
        args.add(t);
    }

    public Type paramNumber(int n) {
        return args.get(n);
    }

    public int size() {
        return args.size();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Signature))
            return false;
        Signature signature = (Signature) o;
        if (signature.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!signature.args.get(i).equals(args.get(i))) {
                return false;
            }
        }
        return true;
    }
}
