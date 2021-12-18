import javax.persistence.*;

@Entity
@Table(name = "laptops",schema = "coursework3")

/**
 * Laptop class to get the specific detail of the product
 */
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "model")
    private String model;

    @Column(name = "display_size")
    private String display_size;

    @Column(name = "brand")
    private String brand;

    @Column(name = "ram")
    private String ram;

    @Column(name = "storage")
    private String storage;

    @Column(name = "color")
    private String color;

    @Column(name = "url_image")
    private String url_image;


    /**
     * Empty Constructor for Laptop class
     */
    public Laptop() {

    }

   //getter and setter methods

    /**
     *
     * @return      storage of the product
     */
    public String getStorage() {
        return storage;
    }

    /**
     *
     * @param storage       set the storage of product
     */
    public void setStorage(String storage) {
        this.storage = storage;
    }

    /**
     *
     * @return      color of the product
     */
    public String getColor() {
        return color;
    }

    /**
     *
     * @param color     set the color
     */
    public void setColor(String color) {
        this.color = color;
    }


    /**
     *
     * @return url of the image
     */
    public String getUrl_image() {
        return url_image;
    }

    /**
     *
     * @param url_image     set the url for the image
     */
    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }


    /**
     *
     * @return         id of the product
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
     * @return       model of the product
     */
    public String getModel() {
        return model;
    }

    /**
     *
     * @param model     set model of the product
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     *
     * @return      size od display of the product
     */
    public String getDisplay_size() {
        return display_size;
    }

    /**
     *
     * @param display_size      set the size of display
     */
    public void setDisplay_size(String display_size) {
        this.display_size = display_size;
    }

    /**
     *
     * @return      get the brand of the product
     */
    public String getBrand() {
        return brand;
    }

    /**
     *
     * @param brand     set the brand of the product
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     *
     * @return      ram of the product
     */
    public String getRam() {
        return ram;
    }

    /**
     *
     * @param ram set the ram of the product
     */
    public void setRam(String ram) {
        this.ram = ram;
    }

    /**
     *
     * @return the string of data
     */
    @Override
    public String toString() {
        return "LAPTOP(" + "BRAND - " + getBrand() + "," + " MODEL - " + getModel() + "," + " COLOR - " + getColor() + "," + " STORAGE - " + getStorage() + "," + " DISPLAY SIZE - " + getDisplay_size() + "," + " URL IMAGE - " + getUrl_image() + "," + " Ram - " + getRam() + ")";
    }

}
