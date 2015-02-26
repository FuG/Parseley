import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfilePage {
    Document page;
    String pageUrl;

    public ProfilePage(String pageUrl) throws IOException {
        page = Jsoup.connect(pageUrl).get();
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
}
