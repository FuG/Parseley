import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HttpHandler {
    static int retryCount = 5;
    static int sleepMillis = 1000;

    public static Document get(String url) {
        int retry = 0;
        while(retry < retryCount) {
            try {
                return Jsoup.connect(url).get();
            } catch (Exception e) {
                if (!(e instanceof HttpStatusException)) break;

                HttpStatusException httpStatusException = (HttpStatusException) e;
                if (httpStatusException.getStatusCode() != 503) {
                    break;
                }

                try {
                    Thread.sleep(sleepMillis);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
                retry++;
                System.out.println("Retry: " + retry);
            }
        }
        return null;
    }
}
