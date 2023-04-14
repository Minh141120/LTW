package main.java.com.example.demo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @NotNull(message="Bookcode cannot be null")
    private int bookcode;
    @NotBlank(message="Title is required")
    @Size(min=10, max=50, message="Title must be between 10 and 50 characters")
    private String title;
    @NotBlank(message="Author is required")

    private String author;
    @NotBlank(message="Category is required")

    private String category;

    private boolean approved;

}
