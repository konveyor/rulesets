package weblogic.common;

import java.util.Hashtable;

public interface T3StartupDef {
    void setServices(T3ServicesDef services);
    String startup(String name, Hashtable ht);
}

