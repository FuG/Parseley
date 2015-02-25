import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AmazonExtractor implements IExtractor {
    private String domainUrl = "http://www.Amazon.com";

    @Override
    public Document getPage(String urlPath) throws IOException {
        return Jsoup.connect(urlPath).get();
    }

    @Override
    public String getAllReviewsLink(Document doc) {
        Element allReviewsDiv = doc.getElementById("revF");
        Elements allReviewsLink = allReviewsDiv.getElementsByTag("a");
        String linkUrl = allReviewsLink.attr("href");

        return linkUrl;
    }

    @Override
    public List<String> getAllProfileLinks(Document doc) {
        List<String> profileLinkUrls = new ArrayList<String>();

        Element reviewsDiv = doc.getElementById("productReviews");
        Elements allLinks = reviewsDiv.getElementsByTag("a");

        for (Element link : allLinks) {
            String linkUrl = link.attr("href");

            if (linkUrl.contains("member-reviews")) {
                profileLinkUrls.add(domainUrl + linkUrl);
            }
        }

        return profileLinkUrls;
    }

    @Override
    public int getTotalReviewsForProfile(Document doc) {
        List<String> reviewTexts = new ArrayList<String>();

        Elements boldTexts = doc.getElementsByTag("b");

        Element parent = null;
        for (Element text : boldTexts) {
            if (text.ownText().equals("Customer Reviews")) {
                parent = text.parent();
                break;
            }
        }

        String[] splits = parent.toString().split(": ");
        int spaceAfterNumber = splits[1].indexOf(' ');
        String number = splits[1].substring(0, spaceAfterNumber);
        int reviewCount = Integer.parseInt(number);

        return reviewCount;
    }
}
