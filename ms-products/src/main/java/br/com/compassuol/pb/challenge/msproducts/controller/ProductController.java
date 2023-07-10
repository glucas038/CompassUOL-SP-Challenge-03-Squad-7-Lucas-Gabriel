package br.com.compassuol.pb.challenge.msproducts.controller;

import br.com.compassuol.pb.challenge.msproducts.payload.ProductDTO;
import br.com.compassuol.pb.challenge.msproducts.payload.ProductPaginationDTO;
import br.com.compassuol.pb.challenge.msproducts.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public String status(){
        return "Product OK";
    }

    @GetMapping("/")
    public ProductPaginationDTO getAllProducts(
            @RequestParam(value = "page", defaultValue = "0", required = false)
            int page,
            @RequestParam(value = "linesPerPage", defaultValue = "10", required = false)
            int linesPerPage,
            @RequestParam(value = "direction", defaultValue = "DESC", required = false)
            String direction,
            @RequestParam(value = "orderBy", defaultValue = "name", required = false)
            String orderBy
    ){
        return productService.getAllProducts(page, linesPerPage, direction, orderBy);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable(name = "productId") long productId){
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable(name = "productId") long productId,
                                                    @Valid @RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(productService.updateProduct(productId, productDTO));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "productId") long productId){
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product deleted successfully.");
    }


}
