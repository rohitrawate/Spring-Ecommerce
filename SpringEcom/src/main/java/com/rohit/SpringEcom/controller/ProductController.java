package com.rohit.SpringEcom.controller;

import com.rohit.SpringEcom.model.Product;
import com.rohit.SpringEcom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>( productService.getAllProducts(), HttpStatus.CREATED) ;
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductByID(@PathVariable int id){  // the `id` mentioned here is extracted from the URL {id}
        Product product = productService.getProductById(id);

//        if( product != null )
        if( product.getId() > 0)
            return new ResponseEntity<>(product, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("product/{productId}/image")        // "/product"   the forward Slash `/`
    public  ResponseEntity<byte[]> getImageByProductId( @PathVariable int productId){
        Product product = productService.getProductById(productId);
        if( product.getId() > 0)
            return new ResponseEntity<>(product.getImageData(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    public ResponseEntity<?>  addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){        //? for something can be product or an Error message
        Product savedProduct = null;

        try {
            savedProduct = productService.addorUpdateProduct(product, imageFile);
            System.out.println("saved Product : "+savedProduct);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);  // Send Error in the Response Format
        }

    }

    // Updating and Saving is kind of Same as Both accept Product data and Image
    @PutMapping("/product/{id}")
    public ResponseEntity<String>  updateProduct(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile imageFile ){
        Product updatedProduct = null;
        try{
            updatedProduct = productService.addorUpdateProduct(product, imageFile);
            return new ResponseEntity<>("Updated", HttpStatus.OK);         // Can return a Updated Product or Just a Message
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {  //Return Message
        Product product = productService.getProductById(id);

        if(product != null){
            productService.deleteProduct(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {   // Sending as a QUERY Parameter therefore @RequestParam
        List<Product> products = productService.searchProducts(keyword);
        System.out.println("Searching ... " + keyword);
        return  new ResponseEntity<>(products, HttpStatus.OK);
    }

//   .////////////// --------==-===-==-====-=-=-=-=-=-=-=-==-=-= ////////////////////////////////////
//    @PostMapping("/productname/{id}/{newName}")
//    public ResponseEntity<Product> productIdName(@PathVariable int id, @PathVariable String newName) {
//        Product product = productService.getProductById(id) ;
//
//        if( product.getId() > 0) {
//            Product product1 = productService.changeName(product, newName);
//            return new ResponseEntity<>(product1, HttpStatus.CREATED);
//        }
//        else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//     mmmmmmmmmm//mmmmm/mmm/////mmmm////mmmmmm//

}
