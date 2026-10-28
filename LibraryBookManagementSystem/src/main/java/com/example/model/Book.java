package com.example.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	    private Integer bookId;

	    @NotBlank(message = "Book name is required")
	    private String bookName;

	    @NotBlank(message = "Author name is required")
	    private String author;

	    @NotNull(message = "Price is mandatory")
	    @Positive(message = "Price must be positive")
	    private Double price;

	    @NotNull(message = "Availability is required")
	    private Boolean available;

}
