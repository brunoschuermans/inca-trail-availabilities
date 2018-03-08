//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package be.schuermans.incatrailavailabilities;

import be.schuermans.incatrailavailabilities.appengine.Datastore;
import com.google.api.client.util.Throwables;

import java.util.stream.IntStream;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

@Component
public class MonthAvailabilityParser {
    @Autowired
    private MonthAvailabilityGateway gateway;
    @Autowired
    private Datastore datastore;

    public MonthAvailabilityParser() {
    }

    public void parse(int year, int month, NumberOfDays numberOfDays) {
        try {
            TagNode tagNode = (new HtmlCleaner()).clean(this.gateway.post(year, month, numberOfDays.getValue()));
            Document doc = (new DomSerializer(new CleanerProperties())).createDOM(tagNode);
            XPath xpath = XPathFactory.newInstance().newXPath();
            NodeList nodeList = (NodeList)xpath.evaluate("//div[@class='it-number']", doc, XPathConstants.NODESET);
            IntStream.range(0, nodeList.getLength()).forEach((i) -> {
                this.datastore.put(year, month, i + 1, numberOfDays, Integer.parseInt(this.availability(nodeList, i)));
            });
        } catch (Exception var8) {
            throw Throwables.propagate(var8);
        }
    }

    private String availability(NodeList nodeList, int i) {
        return nodeList.item(i).getTextContent().isEmpty() ? "0" : nodeList.item(i).getTextContent();
    }
}