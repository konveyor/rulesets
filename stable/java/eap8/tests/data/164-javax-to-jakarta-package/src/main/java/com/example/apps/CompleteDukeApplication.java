package com.example.apps;

// --- Should MATCH: javax packages that moved to jakarta (27) ---
import javax.activation.DataHandler;
import javax.batch.api.Batchlet;
import javax.decorator.Decorator;
import javax.ejb.Stateless;
import javax.el.ELContext;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.jms.Message;
import javax.json.JsonObject;
import javax.jws.WebService;
import javax.mail.Session;
import javax.persistence.Entity;
import javax.resource.ResourceException;
import javax.servlet.Servlet;
import javax.validation.constraints.NotNull;
import javax.websocket.CloseReason;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.xml.bind.JAXBContext;
import javax.xml.ws.Service;
import javax.xml.soap.SOAPMessage;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.transaction.UserTransaction;
import javax.security.enterprise.SecurityContext;

// --- Should NOT MATCH: JDK packages that stayed in javax (false positives) ---
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.annotation.processing.Generated;
import javax.security.auth.login.LoginContext;
import javax.security.cert.X509Certificate;
import javax.transaction.xa.XAResource;
import javax.swing.JButton;
import javax.sql.DataSource;

@ApplicationPath("")
public class CompleteDukeApplication extends Application {
}
