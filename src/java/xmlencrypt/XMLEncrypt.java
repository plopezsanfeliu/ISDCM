/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlencrypt;

import java.io.File;
import java.io.IOException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.xml.security.Init;
import org.apache.xml.security.encryption.XMLCipher;
import org.w3c.dom.Document;

import org.xml.sax.SAXException;

/**
 *
 * @author claudicervello
 */
public class XMLEncrypt {

    private static XMLCipher keyCipher;
    private static SecretKey symmetricKey;
    private static final String XML_FILE = "D:\\Dropbox\\Material Docent UPC\\2n quadrimestre\\ISDCM\\Pràctiques\\P5\\didlFilm1.xml";
    private static final String XML_FILE_ENCRYPTED = "D:\\Dropbox\\Material Docent UPC\\2n quadrimestre\\ISDCM\\Pràctiques\\P5\\didlFilm1_ENCRY.xml";
    private static final String XML_FILE_DECRYPTED = "D:\\Dropbox\\Material Docent UPC\\2n quadrimestre\\ISDCM\\Pràctiques\\P5\\didlFilm1_DECRY.xml";

    public static void main(String[] args) throws Exception {
        init();
        encrypter();
    }

    private static void init() throws Exception {
        Init.init();
        keyCipher = XMLCipher.getInstance(XMLCipher.AES_128);
        symmetricKey = new SecretKeySpec("ISDCMAAAAAAAAAAA".getBytes(), "AES");
    }

    private static void encrypter() throws Exception {
        //Enccrypt
        keyCipher.init(XMLCipher.ENCRYPT_MODE, symmetricKey);
        boolean encryptContentsOnly = false;
        Document node = fileToDoc(XML_FILE);

        Document result = keyCipher.doFinal(node, node.getDocumentElement(), encryptContentsOnly);
        System.out.println("ENCRYPTED DOC  " + result);
        docToFile(result, XML_FILE_ENCRYPTED);
    }

    private static Document fileToDoc(String path) throws Exception {

        try {

            File file = new File(path);

            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();

            Document node = dBuilder.parse(file);

            System.out.println("Root element :" + node.getDocumentElement().getNodeName()
            + "Root value :" + node.getDocumentElement().getAttributes().getLength());

            return node;
        } catch (IOException | ParserConfigurationException | SAXException e1) {
            System.out.println(e1.getMessage());
        }
        return null;
    }

    private static void docToFile(Document result, String path) throws Exception {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Result output = new StreamResult(new File(path));
        Source input = new DOMSource(result);

        transformer.transform(input, output);
        System.out.println("Result file created");
    }

}