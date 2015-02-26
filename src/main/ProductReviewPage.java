import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ProductReviewPage {
    Document page;
    String pageUrl;
    String domainUrl;

    public ProductReviewPage(String pageUrl, String domainUrl) {
        page = HttpHandler.get(pageUrl);
        this.pageUrl = pageUrl;
        this.domainUrl = domainUrl;
    }

    public int getLastReviewPageNumber() {
        Element pagingSpan = page.getElementsByClass("paging").first();

        if (pagingSpan == null) {
            return 1;
        }

        Elements hrefs = pagingSpan.getElementsByTag("a");

        int largestPageNumber = 1;
        for (Element href : hrefs) {
            String hrefText = href.ownText();

            if (isInteger(hrefText)) {
                int number = Integer.parseInt(hrefText);
                if (number > largestPageNumber) {
                    largestPageNumber = number;
                }
            }
        }

        return largestPageNumber;
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
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
