package weblogic.security;

import javax.security.auth.Subject;

public class Security {
    public static Subject getCurrentSubject() {
        return Subject.getSubject(java.security.AccessController.getContext());
    }
}

