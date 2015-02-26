import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ProductPage {
    Document page;
    String pageUrl;

    public ProductPage(String pageUrl) throws IOException {
        page = Jsoup.connect(pageUrl).get();
        this.pageUrl = pageUrl;
    }

    public String getAllReviewsPageUrl() {
        Element allReviewsDiv = page.getElementById("revF");
        Elements allReviewsLink = allReviewsDiv.getElementsByTag("a");
        String linkUrl = allReviewsLink.attr("href");

        return linkUrl;
    }
}
