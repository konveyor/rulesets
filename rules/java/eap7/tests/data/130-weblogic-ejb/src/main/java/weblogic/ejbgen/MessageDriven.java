package weblogic.ejbgen;

public @interface MessageDriven {
    String ejbName();

    String destinationJndiName();

    String destinationType();
}
