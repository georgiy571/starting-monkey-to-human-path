package PO73.Polyanskiy.wdad.learn.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class XmlTask {
    public static final String FILE_PATH = "";

    /**
     * возвращает текст заметки по её владельцу и названию заметки
     * @param owner владелец
     * @param title название заметки
     * @return текст заметки
     */
    public String getNoteText (User owner, String title) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("notes.xml");
            //получаем сам элемент notes
            Node notesNode = document.getDocumentElement().getElementsByTagName("notes").item(0);
            //получаем дочерние элементы notes
            NodeList notes = notesNode.getChildNodes();
            //проходимся по дочерним элементам элемента notes
            for (int i = 0; i < notes.getLength(); i++) {
                //если дочерний элемент является элементом а не мусором, то мы получаем сам элемент note
                if (notes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Node noteNode = notes.item(i);
                    NodeList noteChildNodes = noteNode.getChildNodes();
                    for (int j = 0; j < noteChildNodes.getLength(); j++) {
                        Node noteChildNode = noteChildNodes.item(j);
                        String text = "";
                        if (noteChildNode.getNodeType() == Node.ELEMENT_NODE && noteChildNode.getNodeName().equals("text")) {
                            text = noteChildNode.getTextContent();
                        } else if (noteChildNode.getNodeType() == Node.ELEMENT_NODE && noteChildNode.getNodeName().equals("owner")) {
                            String name = noteChildNode.getAttributes().getNamedItem("name").getNodeValue();
                            String mail = noteChildNode.getAttributes().getNamedItem("mail").getNodeValue();
                            if (name.equals(owner.getName()) && mail.equals(owner.getMail())) {
                                return text;
                            }
                        }
                    }
                }
            }
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateNote (User owner, String title, String newText){
        
    }

    public void setPrivileges (String noteTitle, User user, int newRights){

    }
}
