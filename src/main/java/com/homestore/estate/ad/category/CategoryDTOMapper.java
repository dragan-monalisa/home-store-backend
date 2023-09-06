package com.homestore.estate.ad.category;

import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class CategoryDTOMapper implements Function<Category, CategoryDTO> {

    @Override
    public CategoryDTO apply(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getCategory(),
                category.getType()
        );
    }
}
