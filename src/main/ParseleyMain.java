import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class ParseleyMain {

    public static void main(String[] args) throws IOException, InterruptedException {
        String targetUrl = "http://www.Amazon.com/iPad-mini-case-INVELLOP-Leatherette/dp/B00GH0P21Y/ref=sr_1_3?ie=UTF8&qid=1424737604&sr=8-3&keywords=ipad+mini+cover";

        AmazonExtractor extractor = new AmazonExtractor();

//        Document doc = extractor.getPage(targetUrl);
//        String allReviewsUrl = extractor.getAllReviewsLink(doc);
//        Thread.sleep(300);
//
//        Document reviewsDoc = extractor.getPage(allReviewsUrl);
//        List<String> profileLinks = extractor.getAllProfileLinks(reviewsDoc);
//        Thread.sleep(300);

        Document profileDoc = extractor.getPage(/*profileLinks.get(0)*/ "http://www.Amazon.com/gp/cdp/member-reviews/A2QA25UQBWMKS6?ie=UTF8&sort_by=MostRecentReview");
        int totalReviews = extractor.getTotalReviewsForProfile(profileDoc);

        System.out.println(totalReviews);
    }
}
