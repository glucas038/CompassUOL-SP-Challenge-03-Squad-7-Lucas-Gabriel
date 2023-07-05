package br.com.compassuol.pb.challenge.msproducts.service;

import br.com.compassuol.pb.challenge.msproducts.dto.ProductDTO;
import br.com.compassuol.pb.challenge.msproducts.dto.ProductPaginationDTO;

public interface ProductService {

    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO getProductById(long productId);
    ProductPaginationDTO getAllProducts(int page, int linesPerPage, String direction, String orderBy);
    ProductDTO updateProduct(long productId, ProductDTO productDTO);
    void deleteProduct(long productId);


}
