package com.ucap.toolkit.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.ucap.toolkit.ex.ToolkitException;

/**
 * usage :
 * <li> XMLUtil.update("foo.xml", "books/book/@id", 1);
 */
public class XMLUtil {

    private static final String utf8 = "utf-8";

    /**
     * @param fname /opt/foo.xml
     * @param xpath /books/book or /books/book/@id
     */
    @SuppressWarnings("unchecked")
    public static void update(String fname, String xpath, String val) {
        try {

            SAXReader sax = new SAXReader();
            Document doc = sax.read( new File( fname ) );

            List list = doc.selectNodes( xpath );
            Iterator it = list.iterator();

            boolean is_attr = xpath.indexOf( "@" ) != -1;
            while ( it.hasNext() ) {
                // update attribute
                if ( is_attr ) {
                    Attribute attr = (Attribute) it.next();
                    attr.setValue( val );
                }
                // update node
                else {
                    Element e = (Element) it.next();
                    e.setText( val );
                }
            }

            OutputFormat fmt = OutputFormat.createPrettyPrint();
            fmt.setEncoding( utf8 );

            XMLWriter writer = new XMLWriter( new OutputStreamWriter( new FileOutputStream( fname ), utf8 ), fmt );
            writer.write( doc );
            writer.close();

        } catch ( Exception e ) {
            throw new ToolkitException( e );
        }

    }

}