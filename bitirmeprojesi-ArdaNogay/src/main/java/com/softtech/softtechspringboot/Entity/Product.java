package com.softtech.softtechspringboot.Entity;

import com.softtech.softtechspringboot.Entity.BaseEntity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "PRODUCT")
public class Product extends BaseEntity {

    @Id
    @SequenceGenerator(name = "Product", sequenceName = "PRODUCT_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "Product")
    private Long id;

    @Column(name = "NAME" ,nullable = false)
    private String name;

    @Column(name ="TAX_FREE_PRICE" ,nullable = false )
    private BigDecimal taxFreePrice;

    @Column(name = "LAST_PRICE_WITH_TAX" ,nullable = false)
    private BigDecimal lastPriceWithTax;

    @Column(name = "TAX_PRICE" ,nullable = false)
    private BigDecimal taxPrice;

    @Column(name ="ID_CATEGORY" ,nullable = false)
    private Long categoryId;



}
