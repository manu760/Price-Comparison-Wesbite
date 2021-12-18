import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import static org.junit.jupiter.api.Assertions.*;

class ScraperTest {

    @ParameterizedTest
    @CsvSource({"Apple MacBook 12 Early 2015 Core M 1.1GHz 8GB RAM 256GB SSD/ISL/INT New, 256GB"})
    public void getStorage(String title, String expectedStorage) {

        String actualValue = EbayScraper.getStorage(title);
        assertEquals(expectedStorage, actualValue);
    }

    @ParameterizedTest
    @CsvSource({"Apple MacBook Pro 2021 - 14 (512GB M1 Pro 16GB RAM) - Space Grey - BRAND NEW, 512GB"})
    public void testGetStorage512GB(String title, String expected) {

        String actualValue = EbayScraper.getStorage(title);
        assertEquals(expected, actualValue);
    }


    @Test
    public void testGetStorage() {
        EbayScraper ebayScraper = new EbayScraper();
        String title = "Apple MacBook Pro 2021 - 14” (512GB, M1 Pro, 16GB RAM) - Space Grey - BRAND NEW";
        String expected = "512GB";
        String actual = ebayScraper.getStorage(title);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"APPLE MacBook Pro 14 2021 (M1 Pro 1 TB SSD 16GB) Space Grey - NEW, Apple"})
    public void testGetBrand(String title, String expected) {

        String actual = EbayScraper.getBrand(title);
        assertEquals(expected, actual);

    }

    @ParameterizedTest
    @CsvSource({"APPLE  Pro 14 2021 (M1 Pro 1 TB SSD 16GB) Space Grey - NEW, null"})
    public void testGetNoBrand(String title, String expected) {

        String actual = EbayScraper.getBrand(title);
        assertEquals(expected, actual);

    }

    @ParameterizedTest
    @CsvSource({"APPLE MacBook Pro 14 2021 (M1 Pro 1 TB SSD 16GB) Space Grey - NEW, 2021"})
    public void testGetModel2021(String title, String expected) {

        String actualValue = EbayScraper.getModel(title);
        assertEquals(expected, actualValue);
    }

    @ParameterizedTest
    @CsvSource({"APPLE MacBook Pro 14 2011 (M1 Pro 1 TB SSD 16GB) Space Grey - NEW, 2011"})
    public void testGetModel2011(String title, String expected) {

        String actualValue = EbayScraper.getModel(title);
        assertEquals(expected, actualValue);
    }

    @Test
    public void testGetModel2015() {
        String title = "Apple MacBook 12\" Early 2015 Core M 1.1GHz 8GB RAM 256GB SSD/ISL/INT New";
        String expected = "2015";

        String actualValue = EbayScraper.getModel(title);
        assertEquals(expected, actualValue);
    }

    @Test
    public void testGetRam8GB() {
        String title = "Apple MacBook 12\"-Gold-Intel Core M 8GB Memory 256GB Flash Drive (MK4M2LL/A)";
        String expected = "8GB";
        String actualValue = EbayScraper.getRam(title);
        assertEquals(expected, actualValue);
    }

    @Test
    public void testGetRam16GB() {
        String title = "APPLE MacBook Pro 14 2021 (M1 Pro, 1 TB SSD 16GB) Space Grey - NEW";

        String expected = "16GB";
        String actual = EbayScraper.getRam(title);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetColorSpaceGrey() {
        String title = "APPLE MacBook Pro 14 2021 (M1 Pro, 1 TB SSD 16GB) Space Grey - NEW";

        String expected = "Space Grey";
        String actual = EbayScraper.getColor(title);
        assertEquals(expected, actual);
    }

    @Test
    public void testDisplaySize(){
        String title = "Apple MacBook 12\" i5 1.3ghz 8GB 512GB MNYJ2B/A (June,2017,Gold) New CPO";
    String expected = "12 inch";
    String actual = EbayScraper.getDisplay_size(title);

    assertEquals(expected,actual);
 }


}

