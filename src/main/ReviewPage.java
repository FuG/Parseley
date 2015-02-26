import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReviewPage {
    Document page;
    String pageUrl;
    String domainUrl;

    public ReviewPage(String pageUrl, String domainUrl) throws IOException {
        page = Jsoup.connect(pageUrl).get();
        this.pageUrl = pageUrl;
        this.domainUrl = domainUrl;
    }

    public List<String> getAllProfileLinks() {
        List<String> profileLinkUrls = new ArrayList<String>();

        Element reviewsDiv = page.getElementById("productReviews");
        Elements allLinks = reviewsDiv.getElementsByTag("a");

        for (Element link : allLinks) {
            String linkUrl = link.attr("href");

            if (linkUrl.contains("member-reviews")) {
                profileLinkUrls.add(domainUrl + linkUrl);
            }
        }

        return profileLinkUrls;
    }
}
