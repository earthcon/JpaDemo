package com.jpademo;

import com.jpademo.domain.entity.Product;
import com.jpademo.domain.entity.ProductDetail;
import com.jpademo.domain.entity.detail.Card;
import com.jpademo.domain.entity.detail.Loan;
import com.jpademo.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@Commit
class JpaDemoApplicationTests {

    @Autowired
    ProductRepository productRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void 데이터입력() {
        Card card = new Card();
        card.setCardName("현대카드");

        Card card1 = new Card();
        card1.setCardName("우리카드");

        Card card2 = new Card();
        card2.setCardName("국민카드");

        Card card3 = new Card();
        card3.setCardName("국민카드체크");

        ///////////////////////////////////
        Loan loan = new Loan();
        loan.setLoanName("직장인중금리");

        Loan loan1 = new Loan();
        loan1.setLoanName("직장인저금리");

///////////////////////////////////
///////////////////////////////////

        ProductDetail productDetail = new ProductDetail();
        productDetail.setDetailCode("001");
        productDetail.setDetailName("대출신용");
        productDetail.addLoan(loan);
        productDetail.addLoan(loan1);

        ProductDetail productDetail1 = new ProductDetail();
        productDetail1.setDetailCode("004");
        productDetail1.setDetailName("카드신용");
        productDetail1.addCard(card);
        productDetail1.addCard(card1);
        productDetail1.addCard(card2);

        ProductDetail productDetail2 = new ProductDetail();
        productDetail2.setDetailCode("005");
        productDetail2.setDetailName("카드체크");
        productDetail2.addCard(card3);


/*        ProductDetail productDetail3 = new ProductDetail();
        productDetail3.setDetailCode("004");
        productDetail3.setDetailName("대부");
        productDetail3.addLoan(loan2);
        productDetail3.addLoan(loan3);*/
///////////////////////////////////
///////////////////////////////////
        Product product = new Product();
        product.setProdCode("001");
        product.setProdName("카드");
        product.addProductDetail(productDetail1);
        product.addProductDetail(productDetail2);
        productRepository.saveAndFlush(product);

        Product product1 = new Product();
        product1.setProdCode("002");
        product1.setProdName("대출");
        product1.addProductDetail(productDetail);
        productRepository.saveAndFlush(product1);
    }

}
