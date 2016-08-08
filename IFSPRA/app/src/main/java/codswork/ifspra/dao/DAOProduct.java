package codswork.ifspra.dao;

/**
 * Created by Felipe on 12/07/2016.
 */
public class DAOProduct {
    private int idProduct;
    private String Name;
    private String Description;
    private float Price;
    private String ShortDescription;
    private int Stock;
    private Boolean Featured;
    private float Weight;
    private String Picture1;
    private String Picture2;
    private int SubCategory_idSubCategory;

    public DAOProduct( int idProduct, String name, String description, float price,
                       String shortDescription, int stock, Boolean featured, float weight,
                       String picture1, String picture2, int subCategory_idSubCategory) {

        SubCategory_idSubCategory = subCategory_idSubCategory;
        this.idProduct = idProduct;
        Name = name;
        Description = description;
        Price = price;
        ShortDescription = shortDescription;
        Stock = stock;
        Featured = featured;
        Weight = weight;
        Picture1 = picture1;
        Picture2 = picture2;

    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public String getShortDescription() {
        return ShortDescription;
    }

    public void setShortDescription(String shortDescription) {
        ShortDescription = shortDescription;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public Boolean getFeatured() {
        return Featured;
    }

    public void setFeatured(Boolean featured) {
        Featured = featured;
    }

    public float getWeight() {
        return Weight;
    }

    public void setWeight(float weight) {
        Weight = weight;
    }

    public String getPicture1() {
        return Picture1;
    }

    public void setPicture1(String picture1) {
        Picture1 = picture1;
    }

    public String getPicture2() {
        return Picture2;
    }

    public void setPicture2(String picture2) {
        Picture2 = picture2;
    }

    public int getSubCategory_idSubCategory() {
        return SubCategory_idSubCategory;
    }

    public void setSubCategory_idSubCategory(int subCategory_idSubCategory) {
        SubCategory_idSubCategory = subCategory_idSubCategory;
    }
}
