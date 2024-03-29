package com.example.backendinventory.Controller;


import com.example.backendinventory.DTO.ImageDto;
import com.example.backendinventory.DTO.ProductsDto;

import com.example.backendinventory.Repository.ProductRepository;
import com.example.backendinventory.Service.CloudinaryService;
import com.example.backendinventory.Service.ProductService;
import com.example.backendinventory.ServiceImpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@CrossOrigin
@RestController
//@RequestMapping("/api/gomart/product")
public class ProductsController {


    @Autowired
    ProductServiceImpl productServiceImpl;

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CloudinaryService cloudinaryService;


    @PostMapping(value = "/addProduct")
    public ResponseEntity<?> saveProduct(@RequestBody ProductsDto productsDto) {
        return productServiceImpl.save(productsDto);
    }

    @PostMapping(value = "/getAllProducts")
    public ResponseEntity<?> getProducts() {
        return productServiceImpl.getAllProducts();
    }

    @PostMapping(value = "/getLatestProducts")
    public ResponseEntity<?> getLatestProducts() {
        return productServiceImpl.getLatestProducts();
    }

    @PostMapping(value = "/getActiveProducts")
    public ResponseEntity<?> getActiveProducts() {
        return productServiceImpl.getActiveProducts();
    }

    @PostMapping("/uploadImages")
    public ResponseEntity<?> upload(@RequestPart("image1") MultipartFile image1,
                                         @RequestPart("image2") MultipartFile image2,
                                         @RequestPart("image3") MultipartFile image3) throws IOException {

        ImageDto imageDto = new ImageDto(image1, image2, image3);
//        String status = cloudinaryService.upload(imageDto);

        return cloudinaryService.upload(imageDto);
    }


    @PostMapping(value = "/deleteProduct")
    public String deleteProduct(@RequestBody ProductsDto productsDto) {
        return productServiceImpl.deleteProduct(productsDto);
    }
}
