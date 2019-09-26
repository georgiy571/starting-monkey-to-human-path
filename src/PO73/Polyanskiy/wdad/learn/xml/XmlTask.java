package PO73.Polyanskiy.wdad.learn.xml;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XmlTask {
    public static final String FILE_PATH = "";

    public String getNoteText (User owner, String title) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(FILE_PATH);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateNote (User owner, String title, String newText){

    }

    public void setPrivileges (String noteTitle, User user, int newRights){

    }
}
