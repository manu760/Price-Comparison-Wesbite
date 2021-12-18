import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "products",schema = "coursework3")
@Inheritance(strategy = InheritanceType.JOINED)

/**
 * product class to get the data from websites
 */
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")

    private int id;
    //products table has laptops_id which has foreign key relation to id in laptops table
    @ManyToOne
    @JoinColumn(name = "laptops_id",nullable = false)
    private Laptop laptop;


    @Column(name = "price")
    private String price;

    @Column(name = "url")
    private String url;

    @Column(name = "name")
    private String name;

    @Column(name = "store")
    private String store;

    //getter and setter methods

    /**
     *
     * @return      name of the site
     */
    public String getStore() {
        return store;
    }

    /**
     *
     * @param store     set name of the site
     */
    public void setStore(String store) {
        this.store = store;
    }

    /**
     *
     * @return      get laptop with specifications of products
     */
    public Laptop getLaptop() {
        return laptop;
    }

    /**
     *
     * @param laptop        set laptop with specifications of products
     */
    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    /**
     *
     * @return      id of the product
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id        set id of the product
     */
    public void setId(int id) {

        this.id = id;
    }

    /**
     *
     * @return      name of the product
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name      set name of the product
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     *
     * @return  price pf the product
     */
    public String getPrice() {
        return price;
    }

    /**
     *
     * @param price     set price of the product
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     *
     * @return      url of the product
     */
    public String getUrl() {

        return url;
    }

    /**
     *
     * @param url       set url of the product
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return the string of data
     */
    @Override
    public String toString() {
        return "PRODUCT(" + " PRICE - " + price + "," + " PRODUCT URL - " + url + "," + " NAME - " + name + "," + " STORE - " + store + ")";

    }
}

