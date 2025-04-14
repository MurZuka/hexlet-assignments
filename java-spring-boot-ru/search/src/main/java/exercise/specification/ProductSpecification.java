package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

import java.util.Locale;

// BEGIN
@Component
public class ProductSpecification {
    public Specification<Product> build(ProductParamsDTO params) {
        return withCategory(params.getCategoryId())
                .and(withPriceGt(params.getPriceGt()))
                .and(withPriceLt(params.getPriceLt()))
                .and(withRatingGt(params.getRatingGt()))
                .and(withTitleCont(params.getTitleCont()));
    }

    private Specification<Product> withCategory(Long categoryId) {
        return (root, query, criteriaBuilder) -> categoryId == null ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.get("category").get("id"), categoryId);
    }

    private Specification<Product> withPriceGt(Integer priceGt) {
        return (root, query, criteriaBuilder) -> priceGt == null ? criteriaBuilder.conjunction()
                : criteriaBuilder.greaterThan(root.get("price"), priceGt);
    }

    private Specification<Product> withPriceLt(Integer priceLt) {
        return (root, query, criteriaBuilder) -> priceLt == null ? criteriaBuilder.conjunction()
                : criteriaBuilder.lessThan(root.get("price"), priceLt);
    }

    private Specification<Product> withRatingGt(Double ratingGt) {
        return (root, query, criteriaBuilder) -> ratingGt == null ? criteriaBuilder.conjunction()
                : criteriaBuilder.greaterThan(root.get("rating"), ratingGt);
    }

    private Specification<Product> withTitleCont(String titleCont) {
        return (root, query, criteriaBuilder) -> titleCont == null ? criteriaBuilder.conjunction()
                : criteriaBuilder.like(criteriaBuilder.upper(root.get("title")),
                                        String.format("%%%s%%", titleCont).toUpperCase());
    }
}
// END
