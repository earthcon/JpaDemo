package com.jpademo.domain.entity;


import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "prodCode")
    private String prodCode;

    @Column(name = "prodName")
    private String prodName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "prod_id")
    List<ProductDetail> productDetails;

    public void addProductDetail( ProductDetail productDetail) {
        if (this.productDetails == null) {
            this.productDetails = new ArrayList<>();
        }

        this.productDetails.add(productDetail);
    }
}
