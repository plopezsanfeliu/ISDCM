/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlencrypt;

import java.io.File;
import java.io.FileOutputStream;

import java.security.Key;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.xml.security.encryption.XMLCipher;
import org.apache.xml.security.utils.EncryptionConstants;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.OutputKeys;
import org.apache.xml.security.Init;

/**
 *
 * @author claudicervello
 */
public class XMLDecrypt {

    private static XMLCipher keyCipher;
    private static SecretKey symmetricKey;
    private static final String XML_FILE = "D:\\Dropbox\\Material Docent UPC\\2n quadrimestre\\ISDCM\\Pràctiques\\P5\\didlFilm1.xml";
    private static final String XML_FILE_ENCRYPTED = "D:\\Dropbox\\Material Docent UPC\\2n quadrimestre\\ISDCM\\Pràctiques\\P5\\didlFilm1_ENCRY.xml";
    private static final String XML_FILE_DECRYPTED = "D:\\Dropbox\\Material Docent UPC\\2n quadrimestre\\ISDCM\\Pràctiques\\P5\\didlFilm1_DECRY.xml";

    public static void main(String[] args) throws Exception {

        init();
        decrypt();

    }

    private static void init() throws Exception {
        Init.init();
        keyCipher = XMLCipher.getInstance(XMLCipher.AES_128);
        symmetricKey = new SecretKeySpec("ISDCMAAAAAAAAAAA".getBytes(), "AES");
    }

    private static void decrypt() throws Exception {
        
        Document document = fileToDoc(XML_FILE_ENCRYPTED);

        Element encryptedDataElement =
            (Element) document.getElementsByTagNameNS(
                EncryptionConstants.EncryptionSpecNS,
                EncryptionConstants._TAG_ENCRYPTEDDATA).item(0);

        /*
         * Load the key to be used for decrypting the xml data
         * encryption key.
         */
        Key kek = symmetricKey;

        XMLCipher xmlCipher =
            XMLCipher.getInstance();
        /*
         * The key to be used for decrypting xml data would be obtained
         * from the keyinfo of the EncrypteData using the kek.
         */
        xmlCipher.init(XMLCipher.DECRYPT_MODE, kek);
        /*
         * The following doFinal call replaces the encrypted data with
         * decrypted contents in the document.
         */
        xmlCipher.doFinal(document, encryptedDataElement);

        docToFile(document, XML_FILE_DECRYPTED);

    }

    private static Document fileToDoc(String path) throws Exception {
        String fileName = path;
        File encryptionFile = new File(fileName);
        javax.xml.parsers.DocumentBuilderFactory dbf
                = javax.xml.parsers.DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(encryptionFile);
        System.out.println(
                "Encryption document loaded from " + encryptionFile.toURI().toURL().toString()
        );
        return document;
    }

    private static void docToFile(Document doc, String fileName) throws Exception {
        File encryptionFile = new File(fileName);
        FileOutputStream f = new FileOutputStream(encryptionFile);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(f);
        transformer.transform(source, result);

        f.close();
        System.out.println(
                "Wrote document containing decrypted data to " + encryptionFile.toURI().toURL().toString()
        );
    }
}