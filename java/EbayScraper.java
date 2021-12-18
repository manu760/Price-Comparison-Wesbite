import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.*;

/**
 * This class is to scrape the data from ebay website.This class extends the
 * webScraper class having  threads.
 */
public class EbayScraper extends WebScraper {


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

        driver.get("https://www.ebay.co.uk/b/Apple-MacBook-Apple-Laptops/111422/bn_7025320411?LH_BIN=1&_dmd=1&rt=nc&LH_ItemCondition=1000&mag=1");

        List<WebElement> items = driver.findElements(By.className("s-item"));

        for (int k = 0; k < items.size(); k++) {
            try {
                WebElement item = items.get(k);

                Product product = new Product();


                String title = item.findElement(By.className("s-item__title")).getText();
                if (isValidProduct(title)) {
                    String price = item.findElement(By.className("s-item__price")).getText();
                    String product_url = item.findElement(By.className("s-item__link")).getAttribute("href");


                    product.setName(title);
                    product.setPrice(price);
                    product.setUrl(product_url);
                    product.setStore("ebay");

                    String brand = getBrand(item.findElement(By.className("s-item__title")).getText());

                    String model = getModel(item.findElement(By.className("s-item__title")).getText());

                    String color = getColor(item.findElement(By.className("s-item__title")).getText());

                    String storage = getStorage(item.findElement(By.className("s-item__title")).getText());

                    String displaySize = getDisplay_size(item.findElement(By.className("s-item__title")).getText());

                    String imageUrl = item.findElement(By.className("s-item__image-img")).getAttribute("src");

                    String ram = getRam(item.findElement(By.className("s-item__title")).getText());

                    Laptop laptop = new Laptop();

                    laptop.setBrand(brand);
                    laptop.setUrl_image(imageUrl);
                    laptop.setModel(model);
                    laptop.setColor(color);
                    laptop.setStorage(storage);
                    laptop.setRam(ram);
                    laptop.setDisplay_size(displaySize);



                    Laptop savedLaptop = laptopDao.findLaptop(laptop);
                    product.setLaptop(savedLaptop);
                    Product savedProduct = laptopDao.findAndSaveProduct(product);


                }
            } catch (NoSuchElementException e) {
                continue;
            }
        }


        int i = 0;
        while (i < 4) {
            driver.get("https://www.ebay.co.uk/b/MacBook-Air-Laptops/111422/bn_7028230163?LH_BIN=1&LH_ItemCondition=1000&mag=1&rt=nc&_pgn=" + i);
            items = driver.findElements(By.className("s-item"));

            for (int k = 0; k < items.size(); k++)

                try {

                    WebElement item = items.get(k);

                    String title = item.findElement(By.className("s-item__title")).getText();
                    if (isValidProduct(title)) {

                        String price = item.findElement(By.className("s-item__price")).getText();

                        String product_url = item.findElement(By.className("s-item__link")).getAttribute("href");

                        Product product = new Product();
                        product.setName(title);
                        product.setPrice(price);
                        product.setUrl(product_url);
                        product.setStore("ebay");


                        String brand = getBrand(item.findElement(By.className("s-item__title")).getText());

                        String model = getModel(item.findElement(By.className("s-item__title")).getText());

                        String color = getColor(item.findElement(By.className("s-item__title")).getText());

                        String storage = getStorage(item.findElement(By.className("s-item__title")).getText());

                        String displaySize = getDisplay_size(item.findElement(By.className("s-item__title")).getText());

                        String imageUrl = item.findElement(By.className("s-item__image-img")).getAttribute("src");

                        String ram = getRam(item.findElement(By.className("s-item__title")).getText());

                        Laptop laptop = new Laptop();

                        laptop.setBrand(brand);
                        laptop.setUrl_image(imageUrl);
                        laptop.setModel(model);
                        laptop.setColor(color);
                        laptop.setStorage(storage);
                        laptop.setRam(ram);
                        laptop.setDisplay_size(displaySize);


                    Laptop savedLaptop = laptopDao.findLaptop(laptop);
                    product.setLaptop(savedLaptop);
                    Product savedProduct = laptopDao.findAndSaveProduct(product);

                    }
                } catch (NoSuchElementException ex) {

                    ex.getMessage();
                }
            i++;
        }


        i = 0;
        while (i < 8) {
            driver.get("https://www.ebay.co.uk/b/MacBook-Pro-Laptops/111422/bn_7028574778?LH_ItemCondition=1000&rt=nc&LH_BIN=1&mag=1" + i);
            items = driver.findElements(By.className("s-item"));

            for (int k = 0; k < items.size(); k++)

                try {

                    WebElement item = items.get(k);

                    String title = item.findElement(By.className("s-item__title")).getText();
                    if (isValidProduct(title)) {
                        String price = item.findElement(By.className("s-item__price")).getText();
                        String product_url = item.findElement(By.className("s-item__link")).getAttribute("href");

                        Product product = new Product();
                        product.setName(title);
                        product.setPrice(price);
                        product.setUrl(product_url);
                        product.setStore("ebay");

                        String brand = getBrand(item.findElement(By.className("s-item__title")).getText());
                        String model = getModel(item.findElement(By.className("s-item__title")).getText());
                        String color = getColor(item.findElement(By.className("s-item__title")).getText());
                        String storage = getStorage(item.findElement(By.className("s-item__title")).getText());
                        String displaySize = getDisplay_size(item.findElement(By.className("s-item__title")).getText());
                        String imageUrl = item.findElement(By.className("s-item__image-img")).getAttribute("src");
                        String ram = getRam(item.findElement(By.className("s-item__title")).getText());

                        Laptop laptop = new Laptop();

                        laptop.setBrand(brand);
                        laptop.setUrl_image(imageUrl);
                        laptop.setModel(model);
                        laptop.setColor(color);
                        laptop.setStorage(storage);
                        laptop.setRam(ram);
                        laptop.setDisplay_size(displaySize);



                    Laptop savedLaptop = laptopDao.findLaptop(laptop);
                    product.setLaptop(savedLaptop);
                    Product savedProduct = laptopDao.findAndSaveProduct(product);

                    }
                } catch (NoSuchElementException ex) {

                    ex.getMessage();
                }
            i++;

        }
        i = 0;
        while (i < 10) {
            driver.get("https://www.ebay.co.uk/b/Dell-Laptops-Netbooks/175672/bn_6385322?LH_BIN=1&rt=nc&LH_ItemCondition=1000&mag=1" + i);
            items = driver.findElements(By.className("s-item"));

            for (int k = 0; k < items.size(); k++)

                try {

                    WebElement item = items.get(k);

                    String title = item.findElement(By.className("s-item__title")).getText();
                    if (isValidProduct(title)) {
                        String price = item.findElement(By.className("s-item__price")).getText();
                        String product_url = item.findElement(By.className("s-item__link")).getAttribute("href");

                        Product product = new Product();
                        product.setName(title);
                        product.setPrice(price);
                        product.setUrl(product_url);
                        product.setStore("ebay Dell");

                        String brand = getBrand(item.findElement(By.className("s-item__title")).getText());
                        String model = getModel(item.findElement(By.className("s-item__title")).getText());
                        String color = getColor(item.findElement(By.className("s-item__title")).getText());
                        String storage = getStorage(item.findElement(By.className("s-item__title")).getText());
                        String displaySize = getDisplay_size(item.findElement(By.className("s-item__title")).getText());
                        String imageUrl = item.findElement(By.className("s-item__image-img")).getAttribute("src");
                        String ram = getRam(item.findElement(By.className("s-item__title")).getText());

                        Laptop laptop = new Laptop();

                        laptop.setBrand(brand);
                        laptop.setUrl_image(imageUrl);
                        laptop.setModel(model);
                        laptop.setColor(color);
                        laptop.setStorage(storage);
                        laptop.setRam(ram);
                        laptop.setDisplay_size(displaySize);



                        Laptop savedLaptop = laptopDao.findLaptop(laptop);
                        product.setLaptop(savedLaptop);
                        Product savedProduct = laptopDao.findAndSaveProduct(product);

                    }
                } catch (NoSuchElementException ex) {

                    ex.getMessage();
                }
            i++;

        }
        i = 0;
        while (i < 10) {
            driver.get("https://www.ebay.co.uk/b/Acer-Laptops-Netbooks/175672/bn_6385902?LH_BIN=1&rt=nc&LH_ItemCondition=1000&mag=1" + i);
            items = driver.findElements(By.className("s-item"));

            for (int k = 0; k < items.size(); k++)

                try {

                    WebElement item = items.get(k);

                    String title = item.findElement(By.className("s-item__title")).getText();
                    if (isValidProduct(title)) {
                        String price = item.findElement(By.className("s-item__price")).getText();
                        String product_url = item.findElement(By.className("s-item__link")).getAttribute("href");

                        Product product = new Product();
                        product.setName(title);
                        product.setPrice(price);
                        product.setUrl(product_url);
                        product.setStore("ebay acer");

                        String brand = getBrand(item.findElement(By.className("s-item__title")).getText());
                        String model = getModel(item.findElement(By.className("s-item__title")).getText());
                        String color = getColor(item.findElement(By.className("s-item__title")).getText());
                        String storage = getStorage(item.findElement(By.className("s-item__title")).getText());
                        String displaySize = getDisplay_size(item.findElement(By.className("s-item__title")).getText());
                        String imageUrl = item.findElement(By.className("s-item__image-img")).getAttribute("src");
                        String ram = getRam(item.findElement(By.className("s-item__title")).getText());

                        Laptop laptop = new Laptop();

                        laptop.setBrand(brand);
                        laptop.setUrl_image(imageUrl);
                        laptop.setModel(model);
                        laptop.setColor(color);
                        laptop.setStorage(storage);
                        laptop.setRam(ram);
                        laptop.setDisplay_size(displaySize);



                        Laptop savedLaptop = laptopDao.findLaptop(laptop);
                        product.setLaptop(savedLaptop);
                        Product savedProduct = laptopDao.findAndSaveProduct(product);

                    }
                } catch (NoSuchElementException ex) {

                    ex.getMessage();
                }
            i++;

        }
        i = 0;
        while (i < 10) {
            driver.get("https://www.ebay.co.uk/b/HP-Laptops-Netbooks/175672/bn_6385761?LH_BIN=1&rt=nc&LH_ItemCondition=1000&mag=1" + i);
            items = driver.findElements(By.className("s-item"));

            for (int k = 0; k < items.size(); k++)

                try {

                    WebElement item = items.get(k);

                    String title = item.findElement(By.className("s-item__title")).getText();
                    if (isValidProduct(title)) {
                        String price = item.findElement(By.className("s-item__price")).getText();
                        String product_url = item.findElement(By.className("s-item__link")).getAttribute("href");

                        Product product = new Product();
                        product.setName(title);
                        product.setPrice(price);
                        product.setUrl(product_url);
                        product.setStore("ebay hp");

                        String brand = getBrand(item.findElement(By.className("s-item__title")).getText());
                        String model = getModel(item.findElement(By.className("s-item__title")).getText());
                        String color = getColor(item.findElement(By.className("s-item__title")).getText());
                        String storage = getStorage(item.findElement(By.className("s-item__title")).getText());
                        String displaySize = getDisplay_size(item.findElement(By.className("s-item__title")).getText());
                        String imageUrl = item.findElement(By.className("s-item__image-img")).getAttribute("src");
                        String ram = getRam(item.findElement(By.className("s-item__title")).getText());

                        Laptop laptop = new Laptop();

                        laptop.setBrand(brand);
                        laptop.setUrl_image(imageUrl);
                        laptop.setModel(model);
                        laptop.setColor(color);
                        laptop.setStorage(storage);
                        laptop.setRam(ram);
                        laptop.setDisplay_size(displaySize);



                        Laptop savedLaptop = laptopDao.findLaptop(laptop);
                        product.setLaptop(savedLaptop);
                        Product savedProduct = laptopDao.findAndSaveProduct(product);

                    }
                } catch (NoSuchElementException ex) {

                    ex.getMessage();
                }
            i++;

        }
        driver.quit();
    }

    //    @Override
    public void scrapeAModel() {


    }

    /**
     * this method returns the valid title from the website
     * @param title         title from the website
     * @return              true if the title is valid
     */
    public static boolean isValidProduct(String title) {
        String valid = title.toLowerCase();
        if (valid.contains("keyboard") || valid.contains("service") || valid.contains("repair") || valid.contains("trackpad") || valid.contains("send & recieve")
                || valid.contains("new listing") || valid.contains("password removal") || valid.contains("efi") || valid.contains("icloud"))
            return false;
        else
            return true;
    }

    /**
     * This method returns the storage of the products
     * @param title     title of the product to get the storage
     * @return          not specified if the title does not contain storage of the product
     */
    public static String getStorage(String title) {
        String laptopTitle = title.toUpperCase();


        if (laptopTitle.contains("256GB")) {
            return "256GB";
        } else if (laptopTitle.contains("512GB")) {
            return "512GB";
        } else if (laptopTitle.contains("1")) {
            return "1TB";
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
            return "16GB";
        } else if (ram.contains("8GB")) {
            return "8GB";
        } else
            return "Not specified";

    }
}
