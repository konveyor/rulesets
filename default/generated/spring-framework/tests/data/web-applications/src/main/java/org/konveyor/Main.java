package org.konveyor;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.beans.IntrospectionException;
import java.io.StringReader;

public class Main {

    public static void main(String[] args) throws IntrospectionException {

    }

    public Source getXmlData() {
        String xmlData =
                "<greeting>\n" +
                        "<message>Hello, world!</message>\n" +
                        "</greeting>\n";

        return new StreamSource(new StringReader(xmlData));
    }

}
