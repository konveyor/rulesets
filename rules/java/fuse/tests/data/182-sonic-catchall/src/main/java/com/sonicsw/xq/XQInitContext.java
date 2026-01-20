package com.sonicsw.xq;

/**
 * Mock class for Sonic ESB XQInitContext.
 * Created for testing purposes.
 */
public class XQInitContext {
    public XQInitContext() {
    }
    
    public XQParameters getParameters() {
        return new XQParameters();
    }
    
    public XQEnvelope createDefaultEnvelope() {
        return new XQEnvelope();
    }
}

