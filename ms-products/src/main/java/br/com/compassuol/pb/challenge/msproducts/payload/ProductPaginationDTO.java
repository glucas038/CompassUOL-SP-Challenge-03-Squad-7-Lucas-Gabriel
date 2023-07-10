package br.com.compassuol.pb.challenge.msproducts.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPaginationDTO {
    private List<ProductDTO> content;

    private int page;
    private int linesPerPage;

    private long totalElements;
    private int totalPages;
    private boolean last;

}
