package com.pkroksite.web.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
@Table(name = "medals")
public class MedalsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60, unique = true, nullable = false)
    private String name;

    private String image;

    @Column(columnDefinition = "DECIMAL(5,3)")
    private BigDecimal price;

    @Column(nullable = false)
    private Boolean isShow;
}
