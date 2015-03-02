import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class MemberReviewsPage {
    Document page;
    String pageUrl;

    public MemberReviewsPage(String pageUrl) {
        page = HttpHandler.get(pageUrl);
        this.pageUrl = pageUrl;
    }

    public int getTotalReviewsForProfile() {
        List<String> reviewTexts = new ArrayList<String>();

        Elements boldTexts = page.getElementsByTag("b");

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

    public boolean allReviewsAreSameDay() {
        Elements reviewDates = page.getElementsByTag("nobr");
        String pageString = page.toString();

        String reviewDate = reviewDates.first().ownText();

        for (Element rd : reviewDates) {
            System.out.println(rd.ownText());
            if (!rd.ownText().equals(reviewDate)) {
                return false;
            }
        }

        return true;
    }

    public void print() {
        System.out.println(page);
    }
}
