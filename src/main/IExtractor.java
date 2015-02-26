import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public interface IExtractor {

    public Document getPage(String urlPath) throws IOException;

    public String getAllReviewsLink(Document doc);

    public List<String> getAllProfileLinks(Document doc);

    public int getTotalReviewsForProfile(Document doc);
    //some
}
