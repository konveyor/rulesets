package com.example.apps;

import javax.ejb.MessageDriven;

@MessageDriven(messageListenerInterface=com.example.Foo.class)
public class MessageDrivenWithListenerAnnotationNoImplements {

}
