package com.trustrace.mongodbSpringControl.Product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final SearchService searchService;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody Product product){
        return ResponseEntity.ok (productService.save(product));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok (productService.findAll());
    }
    @GetMapping("{product-id}")
    public ResponseEntity<Product> findById(@PathVariable("product-id") String productId){
        return ResponseEntity.ok (productService.findById(productId));
    }
    @DeleteMapping("{product-id}")
    public ResponseEntity<Void> delete(@PathVariable("product-id") String productId){
        productService.delete(productId);
        return ResponseEntity.accepted ().build ();
    }

    @GetMapping("/search/is")
    public ResponseEntity<List<Product>> searchByName(@RequestParam("name") String name){
        return ResponseEntity.ok (searchService.searchByName(name));
    }

    @GetMapping("/search/start-with")
    public ResponseEntity<List<Product>> searchByNameStartsWith(@RequestParam("name") String name){
        return ResponseEntity.ok (searchService.searchByNameStartsWith(name));
    }
    @GetMapping("/search/end-with")
    public ResponseEntity<List<Product>> searchByNameEndsWith(@RequestParam("name") String name){
        return ResponseEntity.ok (searchService.searchByNameEndsWith(name));
    }
    @GetMapping("/search/price-lt")
    public ResponseEntity<List<Product>> searchByPriceLt(@RequestParam("price") BigDecimal price ){
        return ResponseEntity.ok (searchService.searchByPriceLt(price));
    }
    @GetMapping("/search/price-gt")
    public ResponseEntity<List<Product>> searchByPriceGt(@RequestParam("price") BigDecimal price ){
        return ResponseEntity.ok (searchService.searchByPriceGt(price));
    }

}
