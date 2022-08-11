package jpabook.jpashop.repository;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateItemDTO {
    String name;
    int price;
    int stockQuantity;
    String author;
    String isbn;

    public UpdateItemDTO(String name, int price, int stockQuantity, String author, String isbn) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.author = author;
        this.isbn = isbn;
    }
}
