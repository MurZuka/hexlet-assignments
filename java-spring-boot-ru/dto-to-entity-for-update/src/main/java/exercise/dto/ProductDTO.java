package exercise.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private long id;
    private String title;
    private int price;
    private long vendorCode;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
