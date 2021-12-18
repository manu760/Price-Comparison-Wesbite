/**
 * Web scraper class extending threads
 */
public class WebScraper extends Thread {

    LaptopDao laptopDao;
    Integer scrapeDelay_ms;

    /**
     * Contructor with laptopDao class
     */
    public WebScraper() {
        laptopDao = new LaptopDao();


    }

    /**
     * Constructor with timer
     * @param scrapeDelay_ms        time of execution of each thread
     */
    public WebScraper(Integer scrapeDelay_ms){
        this.scrapeDelay_ms = scrapeDelay_ms;
    }

    // Getter and setter methods

    /**
     *
     * @return laptopDao class
     */
    public LaptopDao getLaptopDao() {
        return laptopDao;
    }

    /**
     *
     * @param laptopDao set laptopDao class
     */
    public void setLaptopDao(LaptopDao laptopDao) {
        this.laptopDao = laptopDao;
    }


    /**
     * getter method for time
     * @return scraoeDelay_ms
     */
    public  Integer getScrapeDelay_ms() {
        return scrapeDelay_ms;
    }

    /**
     * setter method for time
     * @param scrapeDelay_ms    set scraping timer
     */
    public void setScrapeDelay_ms(Integer scrapeDelay_ms) {
        this.scrapeDelay_ms = scrapeDelay_ms;
    }

    /**
     * method to scrape a model
     */
    public void scrapeAModel() {
        System.out.println("To be implemented");
    }


}
