

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LaptopsDirectScraperTest {

    @Test
    public void getBrand() {

        String title = "NEW Apple MacBook Air 13-inch Apple M1 chip 8-core CPU 7 Core GPU 8GB 256GB SSD - Space Grey - MGN63B/A";

        String expected = "Apple";
        String actual = EbayScraper.getBrand(title);

        assertEquals(expected, actual);
    }

}