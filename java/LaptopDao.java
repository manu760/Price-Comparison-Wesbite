
import org.hibernate.*;
import org.springframework.context.annotation.Configuration;

import java.util.List;


/**
 * LaptopDao (Data Access Object) class to save find and update the data into database
 */
@Configuration
public class LaptopDao {

    protected static SessionFactory sessionFactory;

    /**
     * Constructor without arguments
     */
    LaptopDao() {
    }

    /**
     * Constrcutor with sessionfactory
     * @param sessionFactory     sessionfactory
     */
    LaptopDao(SessionFactory sessionFactory) {
        if (sessionFactory == null) {
            throw new IllegalStateException();
        }
        this.sessionFactory = sessionFactory;
    }

    /**
     *
     * @return     get the sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     *
     * @param sessionFactory   set teh sessiomfactory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        LaptopDao.sessionFactory = sessionFactory;
    }


    /**
     * this method  will save laptop in the database
     * @param laptop        laptop to save
     * @return               laptop
     */
    public Laptop saveLaptops(Laptop laptop) {

        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        //Add Cereal to database - will not be stored until we commit the transaction
        session.save(laptop);

        //Commit transaction to save it to database
        session.getTransaction().commit();

        //Close the session and release database connection
        session.close();
        System.out.println("Laptop added with id " + laptop.getId());

        return laptop;


    }

    /**
     * This method will save the product in teh database
     * @param product           product to save
     * @return                  product
     */
    public Product saveProducts(Product product) {

        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();


        //Add product to database - will not be stored until we commit the transaction
        session.save(product);

        //Commit transaction to save it to database
        session.getTransaction().commit();

        //Close the session and release database connection
        session.close();
        System.out.println("Product added  with id  " + product.getId());

return product;

    }

    /**
     * This method will update the data in database
     * @param product           product to update
     */
    public void updateProduct(Product product) {


        if (product.getId() > 0) {
            Session session = sessionFactory.getCurrentSession();


            session.beginTransaction();

            String queryStr = "from Product  where id=" + product.getId();
            List<Product> productList = session.createQuery(queryStr).getResultList();

            if (productList.size() == 1) {
                productList.get(0).setName(product.getName());
                productList.get(0).setPrice(product.getPrice());
                productList.get(0).setUrl(product.getUrl());

            }

            session.getTransaction().commit();
        }
    }

    /**
     * this method will  find the laptop and if its not present in the database it will save new laptop
     * @param product           product to find and save
     * @return                  saveProducts
     */
    public Product findAndSaveProduct(Product product) {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        String queryStr = "from  Product  where   url='" + product.getUrl() +  "'";

        List<Product> productList =  session.createQuery(queryStr).getResultList();

        session.close();
     if(productList.size() >= 1) {
         System.out.println("Found product");
         return productList.get(0);
     }else
         return saveProducts(product);
    }

    /**
     * this method will find the laptop in the database and if the laptop does not exist it will save the new laptop
     * @param laptop            laptop tp find and save
     * @return                  savedlaptops
     */
    public Laptop findLaptop(Laptop laptop) {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        String queryStr = "from Laptop where brand='" + laptop.getBrand() + "' And model='" + laptop.getModel()  + "' And ram='" + laptop.getRam() + "' And storage='" + laptop.getStorage() +  "'";

        List<Laptop> laptopList =  session.createQuery(queryStr).getResultList();


        session.close();
        if (laptopList.size() >= 1) {
            System.out.println("Found laptop model");
            return laptopList.get(0);
        }
        else

            return saveLaptops(laptop);

    }


}






