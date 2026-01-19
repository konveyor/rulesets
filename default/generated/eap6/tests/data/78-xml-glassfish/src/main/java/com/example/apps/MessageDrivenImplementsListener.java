package com.example.apps;

import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven()
public class MessageDrivenImplementsListener implements MessageListener {

    @Override
    public void onMessage(Message message) {

    }
}
