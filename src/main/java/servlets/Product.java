package servlets;

public class Product {
    private int id;
    private String name;
    private double price;
    private String imageFilename;
    private String category;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getImageFilename() { return imageFilename; }
    public void setImageFilename(String imageFilename) { this.imageFilename = imageFilename; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
