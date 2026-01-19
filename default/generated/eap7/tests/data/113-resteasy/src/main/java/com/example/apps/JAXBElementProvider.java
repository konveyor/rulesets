package com.example.apps;

import org.jboss.resteasy.util.Types;
import org.jboss.resteasy.plugins.providers.jaxb.SecureUnmarshaller;
import org.xml.sax.InputSource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * <p>
 * A JAXB Provider which handles parameter and return types of {@link JAXBElement}. This provider will be selected when the resource is declared as:
 * </p>
 * <code> 
 * &#64;POST<br/> 
 * &#64;Consumes("applictaion/xml")<br/> 
 * &#64;Produces("applictaion/xml")<br/> 
 * public JAXBElement<Contact> getContact(JAXBElement<Contact> value);<br/> 
 * </code>
 * 
 * @author <a href="ryan@damnhandy.com">Ryan J. McDonough</a>
 * @version $Revision:$
 */
@Provider
@Produces({ "application/*+xml", "text/*+xml" })
@Consumes({ "application/*+xml", "text/*+xml" })
public class JAXBElementProvider
{

    protected boolean isReadWritable(Class<?> type,
                Type genericType,
                Annotation[] annotations,
                MediaType mediaType)
    {

        return JAXBElement.class.equals(type);
    }

    /**
    * 
    */
    public JAXBElement<?> readFrom(Class<JAXBElement<?>> type,
                Type genericType,
                Annotation[] annotations,
                MediaType mediaType,
                MultivaluedMap<String, String> httpHeaders,
                InputStream entityStream) throws Exception {
        Class<?> typeArg = Object.class;
        if (genericType != null)
            typeArg = Types.getTypeArgument(genericType);
        JAXBContext jaxb = null;
        JAXBElement<?> result;
        try {
            Unmarshaller unmarshaller = jaxb.createUnmarshaller();
//            unmarshaller = decorateUnmarshaller(type, annotations, mediaType, unmarshaller);

            if (true) {
                SecureUnmarshaller unmarshaller1 = new SecureUnmarshaller();
                if (!unmarshaller1.isValidating()) {
                    unmarshaller1.setValidating(true);
                    unmarshaller1.setAdapter(null);
                    SAXSource source = null;
                    source = new SAXSource(new InputSource(entityStream));
                    result = unmarshaller.unmarshal(source, (Class<?>) typeArg);
                } else {
                    if (true) {
                        InputSource is = new InputSource(entityStream);
                        is.setEncoding("UTF-8");
                        StreamSource source = new StreamSource(new InputStreamReader(entityStream, "UTF-8"));
                        source.setInputStream(entityStream);
                        result = unmarshaller.unmarshal(source, (Class<?>) typeArg);
                    } else {
                        JAXBElement<?> e = unmarshaller.unmarshal(new StreamSource(entityStream), (Class<?>) typeArg);
                        result = e;
                    }
                }
            }
            JAXBElement<?> element = result;
            return element;
        } catch (JAXBException e) {

        }
        return null;
    }

    public void writeTo(JAXBElement<?> t,
                Class<?> type,
                Type genericType,
                Annotation[] annotations,
                MediaType mediaType,
                MultivaluedMap<String, Object> httpHeaders,
                OutputStream outputStream) throws IOException
    {

        Class<?> typeArg = Object.class;
        if (genericType != null)
            typeArg = Types.getTypeArgument(genericType);
//        super.writeTo(t, typeArg, genericType, annotations, mediaType, httpHeaders, outputStream);
    }

}