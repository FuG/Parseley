import java.io.IOException;

public class ParseleyMain {

    public static void main(String[] args) throws IOException, InterruptedException {
        String path = "/Rated-Veggie-Capsules-E-Book-Blueprint/dp/B00IZLM3QA";

        AmazonExtractor extractor = new AmazonExtractor(path);

        long startMillis = System.currentTimeMillis();
        extractor.run();
        System.out.println();
        System.out.println("Script runtime: " + (System.currentTimeMillis() - startMillis) / 1000 + " seconds");
        System.out.println();
    }
}
