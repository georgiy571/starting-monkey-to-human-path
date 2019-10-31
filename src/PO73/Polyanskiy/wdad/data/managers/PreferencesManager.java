package PO73.Polyanskiy.wdad.data.managers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class PreferencesManager {
    private static PreferencesManager ourInstance = new PreferencesManager();

    private String filePath = "appconfig.xml";
    private Document document;

    public static PreferencesManager getInstance() {
        return ourInstance;
    }

    private PreferencesManager() {
        loadXML();
    }

    public String getCreateRegistry() {
        return document.getElementsByTagName("createregistry").item(0).getTextContent();
    }

    public String getRegistryAddress() {
        return document.getElementsByTagName("registryaddress").item(0).getTextContent();
    }

    public int getRegistryPort() {
        return Integer.parseInt(document.getElementsByTagName("registryport").item(0).getTextContent());
    }

    public String getPolicyPath() {
        return document.getElementsByTagName("policypath").item(0).getTextContent();
    }

    public String getUseCodeBaseOnly() {
        return document.getElementsByTagName("usecodebaseonly").item(0).getTextContent();
    }

    public String getClassProvider() {
        return document.getElementsByTagName("classprovider").item(0).getTextContent();
    }



    public void setCreateRegistry(String createRegistry) {
        document.getElementsByTagName("createregistry").item(0).setTextContent(createRegistry);
        saveXML();
    }

    public void setRegistryAddress(String registryAddress) {
        document.getElementsByTagName("registryaddress").item(0).setTextContent(registryAddress);
        saveXML();
    }

    public void setRegistryPort(int registryPort) {
        document.getElementsByTagName("registryport").item(0).setTextContent(Integer.toString(registryPort));
        saveXML();
    }

    public void setPolicyPath(String policyPath) {
        document.getElementsByTagName("policypath").item(0).setTextContent(policyPath);
        saveXML();
    }

    public void setUseCodeBaseOnly(String useCodeBaseOnly) {
        document.getElementsByTagName("usecodebaseonly").item(0).setTextContent(useCodeBaseOnly);
        saveXML();
    }

    public void setClassProvider(String classProvider) {
        document.getElementsByTagName("classprovider").item(0).setTextContent(classProvider);
        saveXML();
    }

    private void loadXML() {
        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(filePath);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void saveXML() {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Result out = new StreamResult(new File(filePath));
            Source in = new DOMSource(document);
            transformer.transform(in, out);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
