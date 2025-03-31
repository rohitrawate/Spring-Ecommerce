package com.rohit.SpringEcom.service;

import com.rohit.SpringEcom.model.Product;
import com.rohit.SpringEcom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProducts() {
        return  productRepo.findAll();
    }

    public Product getProductById(int id) {
//        return  productRepo.findById(id).get();
//        return  productRepo.findById(id).orElse(null);
        return  productRepo.findById(id).orElse(new Product(-1));
    }

    public Product addorUpdateProduct(Product product, MultipartFile image) throws IOException {

        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());        // Image Type
        product.setImageData(image.getBytes());

        return productRepo.save(product);  // This will only save the product Object
    }

    ///  ###### Same as Aabove Method : To Add & Update Product Use Same Method
//    public Product updateProduct(Product product, MultipartFile image) throws IOException {
//        // Repo has Only save method and NO update Method
//        product.setImageName( image.getOriginalFilename());
//        product.setImageType(image.getContentType());
//        product.setImageData(image.getBytes());
//
//        return productRepo.save(product);
//    }

    public void deleteProduct(int id) {
        productRepo.deleteById(id);
    }


//  myyyyyyy///////////mmmmmmmmmmmmm//m////m/m//m//mm//mm/m/m/m/m/m/
    public Product changeName( Product oldproduct, String newName) {
        Product product =  oldproduct;
        product.setName(newName);

        return  product;
    }

    public List<Product> searchProducts(String keyword) {
        return productRepo.searchProducts(keyword);
    }
//    mmmmmmmm///mmmmmmmmmm///mnmnmnm//mnmnmn//////mmmmmm///mmmmm
}
