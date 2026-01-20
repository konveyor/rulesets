package org.konveyor;

public class Myclass {
//  org.konveyor.Myclass.method(*) java.util.List<? extends java.lang.String>
    public ExtendedString method(String str) {
        return new ExtendedString();
    }

    public static void main(String[] args) {
        Myclass m = new Myclass();
        m.method("hello");
    }
}
