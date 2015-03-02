import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AmazonExtractor {
    String domain = "http://www.Amazon.com";
    String path;
    ProductMainPage productMainPage;

    public AmazonExtractor(String path) {
        this.path = path;
        productMainPage = new ProductMainPage(domain, path);
    }

    public void run() throws IOException {
        String baseReviewPageUrl = productMainPage.getAllReviewsPageUrl();

        ProductReviewPage productReviewPageMain = new ProductReviewPage(baseReviewPageUrl, domain);
        int totalReviewPages = productReviewPageMain.getLastReviewPageNumber();

        List<ProductReviewPage> productReviewPages = new ArrayList<ProductReviewPage>();

        for (int i = 1; i <= totalReviewPages; i++) {
            String reviewPageUrl = baseReviewPageUrl + "?pageNumber=" + i;
            productReviewPages.add(new ProductReviewPage(reviewPageUrl, domain));
            try {
                System.out.println("Grabbed Page# " + i);
                Thread.sleep(HttpHandler.sleepMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        List<String> profileLinkUrls = new ArrayList<String>();
        for (ProductReviewPage rp : productReviewPages) {
            for (String url : rp.getAllProfileLinks()) {
                profileLinkUrls.add(url);
            }
        }

        List<MemberReviewsPage> memberReviewsPageList = new ArrayList<MemberReviewsPage>();
        for (String url : profileLinkUrls) {
            memberReviewsPageList.add(new MemberReviewsPage(url));
            try {
                System.out.println("Profile added " + memberReviewsPageList.size());
                Thread.sleep(HttpHandler.sleepMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int totalReviewCount = 0;
        int reviewCountOfLessThanThree = 0;
        int reviewCountOfSameDate = 0;

        for (MemberReviewsPage mrp : memberReviewsPageList) {
            int profileReviewCount = mrp.getTotalReviewsForProfile();
            System.out.println();
            System.out.println("Profile Review Count: " + profileReviewCount);
            if (profileReviewCount <= 3) {
                reviewCountOfLessThanThree += 1;
                continue;
            }
            if (mrp.allReviewsAreSameDay()) {
                reviewCountOfSameDate += 1;
                continue;
            }
        }

        totalReviewCount = reviewCountOfSameDate + reviewCountOfLessThanThree;
        System.out.println();
        System.out.println("--- Product Review Analysis ---");
        System.out.println("Profiles w/ < 3 reviews: " + reviewCountOfLessThanThree);
        System.out.println("Profiles w/ all reviews posted same date: " + reviewCountOfSameDate);
        System.out.println("Fake/Total: " + totalReviewCount + "/" + memberReviewsPageList.size());
    }
}
