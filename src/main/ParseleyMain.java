import java.io.IOException;

public class ParseleyMain {

    public static void main(String[] args) throws IOException, InterruptedException {
        String path = "/iPad-mini-case-INVELLOP-Leatherette/dp/B00GH0P21Y/ref=sr_1_3?ie=UTF8&qid=1424737604&sr=8-3&keywords=ipad+mini+cover";
//        String path = "/dp/B00O5VZ00U";

        AmazonExtractor extractor = new AmazonExtractor(path);

        extractor.run();

        System.out.println();
    }
}
