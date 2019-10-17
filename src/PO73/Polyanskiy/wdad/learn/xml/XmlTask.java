package PO73.Polyanskiy.wdad.learn.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlTask {
    public static final String FILE_PATH = "";
    private Document document;

    public XmlTask() {
        loadXML("notes.xml");
    }

    /**
     * возвращает текст заметки по её владельцу и названию заметки
     * @param owner владелец
     * @param title название заметки
     * @return текст заметки
     */
    public String getNoteText (User owner, String title) {
        List<Node> noteNodes = getNoteNodes(owner);
        if (noteNodes.size() == 0) return "";
        for (Node noteNode : noteNodes) {
            for (Node noteNodeChild : getChildNodes(noteNode)) {
                if (noteNodeChild.getNodeName().equals("text"))
                    return noteNodeChild.getTextContent();
            }
        }
        return "";
    }

    public void updateNote (User owner, String title, String newText){
        List<Node> notes = getNoteNodes(owner);
        for (Node note : notes) {
            List<Node> noteChildNodes = getChildNodes(note);
            for (Node noteChild : noteChildNodes) {
                if (noteChild.getNodeName().equals("title") && noteChild.getTextContent().equals(title)) {
                    for (Node noteChild2 : noteChildNodes) {
                        if (noteChild2.getNodeName().equals("text")) {
                            noteChild2.setTextContent(newText);
                            return;
                        }
                    }
                }
            }
        }
    }

    public void setPrivileges (String noteTitle, User user, int newRights) {
        List<Node> allNotes = getChildNodes(document.getElementsByTagName("notes").item(0));
        for (Node note : allNotes) {
            List<Node> noteChildNodes = getChildNodes(note);
            for (Node noteChild : noteChildNodes) {
                if (noteChild.getNodeName().equals("title") && noteChild.getTextContent().equals(noteTitle)) {
                    for (Node privilegies : noteChildNodes) {
                        if (privilegies.getNodeName().equals("privilegies")) {
                            Element userElement = document.createElement("user");
                            userElement.setAttribute("name", user.getName());
                            userElement.setAttribute("mail", user.getMail());
                            userElement.setAttribute("rights", newRights == 0 ? "R" : "RW");
                            privilegies.appendChild(userElement);
                        }
                    }
                }
            }
        }
        //todo saveXML
    }

    private List<Node> getNoteNodes(User owner) {
        ArrayList<Node> nodes = new ArrayList<>();
        //получаем сам элемент notes
        Node notesNode = document.getDocumentElement().getElementsByTagName("notes").item(0);
        for (Node note : getChildNodes(notesNode)) {
            for (Node noteChild : getChildNodes(note)) {
                if (noteChild.getNodeName().equals("owner")) {
                    String name = noteChild.getAttributes().getNamedItem("name").getNodeValue();
                    String mail = noteChild.getAttributes().getNamedItem("mail").getNodeValue();
                    if (name.equals(owner.getName()) && mail.equals(owner.getMail()))
                        nodes.add(note);
                }
            }
        }
        return nodes;
    }

    private void loadXML(String path) {
        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(path);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void saveXML(String path) {
        //todo save
    }

    private List<Node> getChildNodes(Node node) {
        ArrayList<Node> childNodes = new ArrayList<>();
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() != Node.ELEMENT_NODE)
                childNodes.add(childNode);
        }
        return childNodes;
    }
}
