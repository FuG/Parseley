import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ProductMainPage {
    Document page;
    String pageUrl;
    String domain;
    String path;

    public ProductMainPage(String domain, String path) {
        this.domain = domain;
        this.path = path;
        pageUrl = domain + path;
        page = HttpHandler.get(pageUrl);
    }

    public String getAllReviewsPageUrl() {
//        Element allReviewsDiv = page.getElementById("revF");
//        Elements allReviewsLink = allReviewsDiv.getElementsByTag("a");
//        String linkUrl = allReviewsLink.attr("href");
//
//        return linkUrl;
        String[] splits = pageUrl.split(domain + "/");
        String afterDomain = splits[1];
        splits = afterDomain.split("/dp/");
        String productName = splits[0];
        String afterdp = splits[1];
        splits = afterdp.split("/");
        String productId = splits[0];
        String productReviews = "product-reviews";

        return formUrl(new String[] { domain, productName, productReviews, productId});
    }

    private String formUrl(String[] components) {
        String url = "";
        int componentCount = components.length;
        for (int i = 0; i < componentCount; i++) {
            url += components[i];
            if (i < componentCount - 1) {
                url += "/";
            }
        }
        return url;
    }
}
