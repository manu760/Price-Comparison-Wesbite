import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

/**
 * This class is to scrape the data from currys website.This class extends the
 * webScraper class having  threads.
 */
public class CurrysScraper extends WebScraper {

    /**
     * run method to scrape the data from the website
     */

    @Override
    public void run() {

        //We need an options class to run headless - not needed if we want default options
        ChromeOptions options = new ChromeOptions();

        options.setHeadless(true);


        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ASUS\\Downloads\\chromedriver_win32\\chromedriver.exe");

        //Create instance of web driver - this must be on the path.
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://www.currys.co.uk/gbuk/apple-computing/laptops/laptops/315_3226_30328_267_xx/xx-criteria.html");

        List<WebElement> items = driver.findElements(By.className("ProductListItem__DivWrapper-sc-pb4x98-7"));
        for (int i = 0; i < items.size(); i++) {
            try {

                WebElement item = items.get(i);

                String title = item.findElement(By.className("productTitle")).getText();
                String price = item.findElement(By.className("ProductPriceBlock__Price-eXioPm")).getText();

                String productUrl = item.findElement(By.tagName("a")).getAttribute("href");

                Product product = new Product();
                product.setName(title);
                product.setPrice(price);
                product.setUrl(productUrl);
                product.setStore("Currys");


                Laptop laptop = new Laptop();

                String brand = getBrand(item.findElement(By.className("productTitle")).getText());
                String model = getModel(item.findElement(By.className("productTitle")).getText());
                String color = getColor(item.findElement(By.className("productTitle")).getText());
                String storage = getStorage(item.findElement(By.className("productTitle")).getText());
                String displaySize = getDisplay_size(item.findElement(By.className("productTitle")).getText());
                String urlImage = item.findElement(By.tagName("img")).getAttribute("src");

                String ram = getRam(item.findElement(By.className("productTitle")).getText());

                laptop.setBrand(brand);
                laptop.setModel(model);
                laptop.setColor(color);
                laptop.setStorage(storage);
                laptop.setRam(ram);
                laptop.setDisplay_size(displaySize);
                laptop.setUrl_image(urlImage);


                Laptop savedLaptop = laptopDao.findLaptop(laptop);
                product.setLaptop(savedLaptop);
                Product savedProduct = laptopDao.findAndSaveProduct(product);


            } catch (NoSuchElementException ex) {
                continue;
            }

        }
        driver.quit();
    }

    /**
     * This method returns the storage of the products
     * @param title     title of the product to get the storage
     * @return          not specified if the title does not contain storage of the product
     */
    public static String getStorage(String title) {
        String laptopTitle = title.toUpperCase();


        if (laptopTitle.contains("256GB")) {
            return "256 GB";
        } else if (laptopTitle.contains("256 GB")) {
            return "256 GB";
        } else if (laptopTitle.contains("512 GB")) {
            return "512 GB";
        } else if (laptopTitle.contains("512GB")) {
            return "512 GB";
        } else if (laptopTitle.contains("1")) {
            return "1 TB";
        } else
            return "Not Specified";

    }

    /**
     * This method returns the brand of the product
     * @param title     title of the product to get the brand name
     * @return          null if the title does not contain the brand in the title
     */
    public static String getBrand(String title) {

        String laptopBrand = title.toUpperCase();

        if (laptopBrand.contains("MACBOOK")) {
            return "Apple";
        } else
            return "null";

    }

    /**
     * This method returns the model number of the product
     * @param title     title from the site to get the model number of the product
     * @return          model number of the product
     */
    public static String getModel(String title) {

        String laptopModel = title.toUpperCase();
        if (laptopModel.contains("2011")) {
            return "2011";
        } else if (laptopModel.contains("2012")) {
            return "2012";
        } else if (laptopModel.contains("2013")) {
            return "2013";
        } else if (laptopModel.contains("2014")) {
            return "2014";
        } else if (laptopModel.contains("2015")) {
            return "2015";
        } else if (laptopModel.contains("2016")) {
            return "2016";
        } else if (laptopModel.contains("2017")) {
            return "2017";
        } else if (laptopModel.contains("2018")) {
            return "2018";
        } else if (laptopModel.contains("2019")) {
            return "2019";
        } else if (laptopModel.contains("2020")) {
            return "2020";
        } else
            return "2021";

    }

    /**
     * This method returns the color of the product
     * @param title          to get the color of the product from the title
     * @return               the color of the product
     */
    public static String getColor(String title) {
        String laptopColor = title.toUpperCase();
        if (laptopColor.contains("WHITE")) {
            return "White";
        } else if (laptopColor.contains("SPACE GREY")) {
            return "Space Grey";
        } else if (laptopColor.contains("SILVER")) {
            return "Silver";
        } else if (laptopColor.contains("GOLD")) {
            return "Gold";
        } else if (laptopColor.contains("ROSE GOLD")) {
            return "Rose Gold";
        } else
            return "Not Specified";
    }

    /**
     * This method returns the display size of the product
     * @param title     title of the product to get the size of the display
     * @return          size of the display
     */
    public static String getDisplay_size(String title) {
        String laptopDisplay = title.toUpperCase();

        if (laptopDisplay.contains("11")) {
            return "11 inch";
        } else if (laptopDisplay.contains("12")) {
            return "12 inch";
        } else if (laptopDisplay.contains("13")) {
            return "13 inch";
        } else if (laptopDisplay.contains("13.3")) {
            return "13.3 inch";
        } else if (laptopDisplay.contains("14")) {
            return "14 inch";
        } else if (laptopDisplay.contains("15")) {
            return "15 inch";
        } else if (laptopDisplay.contains("16")) {
            return "16 inch";
        } else
            return "";
    }

    /**
     * This method returns the ram of the product
     * @param title     title to get the ram of the product
     * @return          ram of the product
     */
    public static String getRam(String title) {
        String ram = title.toUpperCase();
        if (ram.contains("16GB")) {
            return "16 GB";
        } else if (ram.contains("8GB")) {
            return "8 GB";
        } else if (ram.contains("")) {
            return "8 GB/16 GB";
        } else
            return "Not specified";

    }

}
