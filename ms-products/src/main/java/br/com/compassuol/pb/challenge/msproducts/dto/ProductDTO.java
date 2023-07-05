package br.com.compassuol.pb.challenge.msproducts.dto;

import br.com.compassuol.pb.challenge.msproducts.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Negative;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private long id;
    @NotEmpty
    private String date;
    @NotEmpty
    private String description;
    @NotEmpty
    private String name;
    @NotNull
    private String imgUrl;
    @NotNull
    @DecimalMin(value = "0", inclusive = false)
    private BigDecimal price;

    private Set<CategoryDTO> categories;
}
