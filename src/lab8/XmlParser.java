package lab8;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class XmlParser {
    private static final String FILE_PATH = "src/lab8/example.xml";

    public static void main(String[] args) {
        try {
            Document doc = readDocument(FILE_PATH);
            doc.getDocumentElement().normalize();

            printBooks(doc, "Исходный список книг");

            addBookIfAbsent(doc, "Преступление и наказание", "Федор Достоевский", "1866");
            saveDocument(doc, FILE_PATH);
            printBooks(doc, "После добавления книги");

            System.out.println("\nПоиск книг автора Лев Толстой:");
            findBooks(doc, "Лев Толстой", null).forEach(XmlParser::printBook);

            System.out.println("\nПоиск книг за 1967 год:");
            findBooks(doc, null, "1967").forEach(XmlParser::printBook);

            if (deleteBookByTitle(doc, "Мастер и Маргарита")) {
                saveDocument(doc, FILE_PATH);
                System.out.println("\nКнига удалена из XML-файла.");
            }
            printBooks(doc, "После удаления книги");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Document readDocument(String filePath) throws Exception {
        File inputFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        return dBuilder.parse(inputFile);
    }

    private static void printBooks(Document doc, String title) {
        System.out.println("\n" + title);
        System.out.println("Корневой элемент: " + doc.getDocumentElement().getNodeName());
        getBookElements(doc).forEach(XmlParser::printBook);
    }

    private static void printBook(Element element) {
        System.out.println("\nТекущий элемент: book");
        System.out.println("Название книги: "
                + element.getElementsByTagName("title").item(0).getTextContent());
        System.out.println("Автор: "
                + element.getElementsByTagName("author").item(0).getTextContent());
        System.out.println("Год издания: "
                + element.getElementsByTagName("year").item(0).getTextContent());
    }

    private static List<Element> getBookElements(Document doc) {
        NodeList nodeList = doc.getElementsByTagName("book");
        List<Element> books = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                books.add((Element) node);
            }
        }

        return books;
    }

    private static List<Element> findBooks(Document doc, String author, String year) {
        return getBookElements(doc).stream()
                .filter(element -> author == null
                        || author.equalsIgnoreCase(element.getElementsByTagName("author").item(0).getTextContent()))
                .filter(element -> year == null
                        || year.equals(element.getElementsByTagName("year").item(0).getTextContent()))
                .collect(Collectors.toList());
    }

    private static void addBook(Document doc, String title, String author, String year) {
        Element book = doc.createElement("book");
        appendTextElement(doc, book, "title", title);
        appendTextElement(doc, book, "author", author);
        appendTextElement(doc, book, "year", year);
        doc.getDocumentElement().appendChild(book);
    }

    private static void addBookIfAbsent(Document doc, String title, String author, String year) {
        boolean exists = getBookElements(doc).stream()
                .anyMatch(book -> title.equalsIgnoreCase(
                        book.getElementsByTagName("title").item(0).getTextContent()));

        if (!exists) {
            addBook(doc, title, author, year);
        }
    }

    private static void appendTextElement(Document doc, Element parent, String tagName, String value) {
        Element element = doc.createElement(tagName);
        element.appendChild(doc.createTextNode(value));
        parent.appendChild(element);
    }

    private static boolean deleteBookByTitle(Document doc, String title) {
        for (Element book : getBookElements(doc)) {
            String currentTitle = book.getElementsByTagName("title").item(0).getTextContent();
            if (title.equalsIgnoreCase(currentTitle)) {
                book.getParentNode().removeChild(book);
                return true;
            }
        }

        return false;
    }

    private static void saveDocument(Document doc, String filePath) throws Exception {
        doc.setXmlStandalone(true);
        doc.normalizeDocument();

        javax.xml.transform.Transformer transformer =
                javax.xml.transform.TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(javax.xml.transform.OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(javax.xml.transform.OutputKeys.STANDALONE, "yes");
        transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");

        javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(doc);
        javax.xml.transform.stream.StreamResult result =
                new javax.xml.transform.stream.StreamResult(new File(filePath));
        transformer.transform(source, result);
    }
}
