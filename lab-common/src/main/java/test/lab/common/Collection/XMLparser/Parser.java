package test.lab.common.Collection.XMLparser;

import test.lab.common.Collection.CollectionManager;
import test.lab.common.Untils.OutputManager;
import test.lab.common.client.Product;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import java.awt.*;
import java.io.*;


public class Parser {
    private Parser(){

    }
    @XmlElement( name = "book")


    public static CollectionManager convertToJavaObject(File filename) throws  JAXBException{
        JAXBContext context = JAXBContext.newInstance(CollectionManager.class);
        Unmarshaller unmarshaller= context.createUnmarshaller();
        return (CollectionManager) unmarshaller.unmarshal(filename);
    }

}
