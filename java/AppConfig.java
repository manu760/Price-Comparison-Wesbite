import org.hibernate.PropertyNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


/**
 * App config class with spring and hibernate to scrape all the websites together with threads
 */
@Configuration
public class AppConfig {
    private SessionFactory sessionFactory;

    /**
     * This method has the list of all the scrapers
     * @return scraperManager
     */
    @Bean
    public ScraperManager scraperManager() {
        ScraperManager scraperManager = new ScraperManager();
        List<WebScraper> scraperList = new ArrayList<>();
        scraperList.add(ebayScraper());
        scraperList.add(techInTheBasket());
        scraperList.add(onBuyScraper());
        scraperList.add(eBuyerScraper());
        scraperList.add(currysScraper());


        scraperList.get(0).scrapeAModel();
        scraperManager.setScraperList(scraperList);

        return scraperManager;

    }

    /**
     * This method is linked to laptopDao class having sessionfactory
     * @return laptopDao class
     */
    @Bean
    public LaptopDao laptopDao() {
        LaptopDao laptopDao = new LaptopDao();
        laptopDao.setSessionFactory(init());
        return laptopDao;
    }

    /**
     * This method will return all the scraped products from the ebay website
     * @return  scraped data from ebay
     */
    @Bean
    public EbayScraper ebayScraper() {
        EbayScraper ebayScraper = new EbayScraper();
        ebayScraper.setLaptopDao(laptopDao());
        ebayScraper.setScrapeDelay_ms(1000);
        ebayScraper.start();
        return ebayScraper;
    }

    /**
     * This method will return all the scraped products from the tech in the basket website
     * @return  scraped data from techInTheBasket
     */
    @Bean
    public TechInTheBasket techInTheBasket() {
        TechInTheBasket techInTheBasket = new TechInTheBasket();
        techInTheBasket.setLaptopDao(laptopDao());
        techInTheBasket.setScrapeDelay_ms(2000);
        techInTheBasket.start();

        return techInTheBasket;
    }

    /**
     * This method will return all the scraped products from the currys website
     * @return  scraped data from currys
     */
    @Bean
    public CurrysScraper currysScraper(){
        CurrysScraper currysScraper = new CurrysScraper();
        currysScraper.setLaptopDao(laptopDao());
        currysScraper.setScrapeDelay_ms(3000);
        currysScraper.start();

        return currysScraper;
    }

    /**
     * This method will return all the scraped products from the eBuyer website
     * @return  scraped data from eBuyer
     */
    @Bean
    public EBuyerScraper eBuyerScraper(){
        EBuyerScraper ebuyercraper = new EBuyerScraper();
        ebuyercraper.setLaptopDao(laptopDao());
        ebuyercraper.setScrapeDelay_ms(4000);
        ebuyercraper.start();

        return ebuyercraper;
    }

    /**
     * This method will return all the scraped products from the onBuy website
     * @return  scraped data from onBuy
     */
    @Bean
    public OnBuyScraper onBuyScraper(){
        OnBuyScraper onBuyScraper = new OnBuyScraper();
        onBuyScraper.setLaptopDao(laptopDao());
        onBuyScraper.setScrapeDelay_ms(5000);
        onBuyScraper.start();

        return onBuyScraper;
    }

    /**
     * This method will start the sessionfactory for every website
     * @return  sessionfactory
     */
    @Bean
    public SessionFactory init() {
        try {
            //Create a builder for the standard service registry
            StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();

            //Load configuration from hibernate configuration file
            standardServiceRegistryBuilder.configure("hibernate.cfg.xml");

            //Create the registry that will be used to build the session factory
            StandardServiceRegistry registry = standardServiceRegistryBuilder.build();

            try {
                //Create the session factory - this is the goal of the init method.
                sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            } catch (PropertyNotFoundException e) {
                    /* The registry would be destroyed by the SessionFactory,
                        but we had trouble building the SessionFactory, so destroy it manually */
                System.err.println("Session Factory build failed.");
                e.printStackTrace();
                StandardServiceRegistryBuilder.destroy(registry);
            }

            //Ouput result
            System.out.println("Session factory built.");

        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("SessionFactory creation failed." + ex);
        }

        return sessionFactory;
    }

}