package com.jpademo.domain.entity;

import com.jpademo.domain.entity.detail.Card;
import com.jpademo.domain.entity.detail.Loan;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
@Data
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "detailCode")
    private String detailCode;

    @Column(name = "detailName")
    private String detailName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "prod_detail_id")
    List<Card> cards;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "prod_detail_id")
    List<Loan> loans;

    public void addCard(Card card) {
        if(this.cards == null)
            this.cards = new ArrayList<>();

        this.cards.add(card);
    }

    public void addLoan(Loan loan) {
        if(this.loans == null)
            this.loans = new ArrayList<>();

        this.loans.add(loan);
    }
}
