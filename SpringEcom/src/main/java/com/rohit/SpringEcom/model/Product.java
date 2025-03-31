package com.rohit.SpringEcom.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
public class Product {

    public Product() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") // The O/P chanegd in the PostMan Response
    private Date releaseDate;

    private boolean productAvailable;
    private int stockQuantity;

    private String imageName;
    private String imageType;
    @Lob                     // Large Binary Object
    private byte[] imageData;

    public Product(String brand, String category, String description, int id, String name, BigDecimal price, boolean productAvailable, Date releaseDate, int stockQuantity) {
        this.brand = brand;
        this.category = category;
        this.description = description;
        this.id = id;
        this.name = name;
        this.price = price;
        this.productAvailable = productAvailable;
        this.releaseDate = releaseDate;
        this.stockQuantity = stockQuantity;
    }

    public Product(int i) {     // To return empty product for non-product Id
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isProductAvailable() {
        return productAvailable;
    }

    public void setProductAvailable(boolean productAvailable) {
        this.productAvailable = productAvailable;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
}
