import java.util.ArrayList;
import java.util.List;

/**
 * Scraper manager class which extends arraylist
 */
public class ScraperManager extends ArrayList {

    List<WebScraper> scraperList;

    /**
     * constructor without arguments
     */
    public ScraperManager(){

    }

    //getter and setter methods

    /**
     *
     * @return      list having scraped data
     */
    public List<WebScraper> getScraperList() {
        return scraperList;
    }

    /**
     *
     * @param scraperList       set the list of scraped data
     */
    public void setScraperList(List<WebScraper> scraperList) {
        this.scraperList = scraperList;
    }
}
