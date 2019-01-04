package com.pkroksite.web.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.swing.text.StyledEditorKit;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor

public class MedalsDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private String image;
    private Boolean isShow;
}
