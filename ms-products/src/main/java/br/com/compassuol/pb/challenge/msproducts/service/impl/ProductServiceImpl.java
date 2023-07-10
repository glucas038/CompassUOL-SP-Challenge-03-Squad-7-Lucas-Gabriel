package br.com.compassuol.pb.challenge.msproducts.service.impl;

import br.com.compassuol.pb.challenge.msproducts.dto.ProductDTO;
import br.com.compassuol.pb.challenge.msproducts.dto.ProductPaginationDTO;
import br.com.compassuol.pb.challenge.msproducts.dto.UserDTO;
import br.com.compassuol.pb.challenge.msproducts.entity.Category;
import br.com.compassuol.pb.challenge.msproducts.entity.Product;
import br.com.compassuol.pb.challenge.msproducts.repository.CategoryRepository;
import br.com.compassuol.pb.challenge.msproducts.repository.ProductRepository;
import br.com.compassuol.pb.challenge.msproducts.service.EmailService;
import br.com.compassuol.pb.challenge.msproducts.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ModelMapper mapper;
    private EmailService emailService;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              ModelMapper mapper,
                              EmailService emailService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
        this.emailService = emailService;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {

        UserDTO userDTO = new UserDTO();
        //Preciso ler Token
        userDTO.setEmail("glucas038@gmail.com");
        userDTO.setFirstName("First");
        userDTO.setLastName("Last");

        Set<Category> categories = productDTO.getCategories().stream()
                .map(category -> {
                    Category foundCategory = categoryRepository.findById(category.getId()).orElseThrow(() ->
                            new RuntimeException("Id category not Found"));
                    return foundCategory;
                })
                .collect(Collectors.toSet());

        Product product = mapToEntity(productDTO);
        product.setCategories(categories);

        Product newProduct = productRepository.save(product);
        //emailService.sendingEmail(userDTO, productDTO);
        return mapToDTO(newProduct);
    }

    @Override
    public ProductDTO getProductById(long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new RuntimeException("Product not Found"));

        return mapToDTO(product);
    }

    @Override
    public ProductPaginationDTO getAllProducts(int page, int linesPerPage, String direction, String orderBy) {

        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending()
                : Sort.by(orderBy).descending();

        PageRequest pageRequest = PageRequest.of(page, linesPerPage,sort);
        Page<Product> products = productRepository.findAll(pageRequest);

        List <Product> productList = products.getContent();

        List<ProductDTO> content = productList.stream().map(product ->
                mapToDTO(product)).collect(Collectors.toList());

        ProductPaginationDTO productPaginationDTO = new ProductPaginationDTO();
        productPaginationDTO.setContent(content);
        productPaginationDTO.setPage(products.getNumber());
        productPaginationDTO.setLinesPerPage(products.getSize());

        productPaginationDTO.setTotalElements(products.getTotalElements());
        productPaginationDTO.setTotalPages(products.getTotalPages());
        productPaginationDTO.setLast(products.isLast());


        return productPaginationDTO;
    }

    @Override
    public ProductDTO updateProduct(long productId, ProductDTO productDTO) {
        Product currentProduct = productRepository.findById(productId).orElseThrow(() ->
                new RuntimeException("Product Not Found"));

        Product product = mapToEntity(productDTO);

        currentProduct.setDate(product.getDate());
        currentProduct.setDescription(product.getDescription());
        currentProduct.setName(product.getName());
        currentProduct.setImgUrl(product.getImgUrl());
        currentProduct.setPrice(product.getPrice());
        currentProduct.setCategories(product.getCategories());

        Product updateProduct = productRepository.save(currentProduct);

        return mapToDTO(updateProduct);
    }

    @Override
    public void deleteProduct(long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new RuntimeException("Product not Found"));

        productRepository.delete(product);

    }

    private ProductDTO mapToDTO(Product product){
        return mapper.map(product, ProductDTO.class);
    }

    private Product mapToEntity(ProductDTO productDTO){
        return mapper.map(productDTO, Product.class);
    }
}
