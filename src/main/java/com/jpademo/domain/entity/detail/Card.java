package com.jpademo.domain.entity.detail;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Data
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String cardName;

}
