package com.jpademo.controller;

import com.jpademo.domain.entity.Product;
import com.jpademo.domain.entity.ProductDetail;
import com.jpademo.domain.entity.detail.Card;
import com.jpademo.domain.entity.detail.Loan;
import com.jpademo.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.*;

@RestController
//@RequestMapping("/api")
@Transactional
public class SendAPI {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/")
    public String Test() {
        List<Product> productList = productRepository.findByProdCode("002");


        Map<String, Object> list = new HashMap<>();
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Map<String, Object>> detailList = new ArrayList<>();
        List<Map<String, Object>> cardList = new ArrayList<>();
        List<Map<String, Object>> loanList = new ArrayList<>();

        for (Product product : productList) {
            Map<String, Object> resMap = new HashMap<>();
            resMap.put("prod_Code",product.getProdCode());
            resMap.put("prod_Name",product.getProdName());

            List<ProductDetail> productDetails = product.getProductDetails();

            for (ProductDetail productDetail : productDetails) {
                Map<String, Object> productDetailMap = new HashMap<>();
                productDetailMap.put("prodDetail_Code",productDetail.getDetailCode());
                productDetailMap.put("prodDetail_Name",productDetail.getDetailName());
                List<Card> cards = productDetail.getCards();

                for (Card card : cards) {
                    Map<String, Object> resSubCardMap = new HashMap<>();
                    resSubCardMap.put("prodDetailCard_Code",card.getId());
                    resSubCardMap.put("prodDetailCard_Name",card.getCardName());
                    cardList.add(resSubCardMap);
                 }
                productDetailMap.put("prodDetailCard_list", cardList);

                List<Loan> loans = productDetail.getLoans();
                for (Loan loan : loans) {
                    Map<String, Object> resSubLoanMap = new HashMap<>();
                    resSubLoanMap.put("prodDetailLoan_Code",loan.getId());
                    resSubLoanMap.put("prodDetailLoan_Name",loan.getLoanName());
                    loanList.add(resSubLoanMap);
                }
                productDetailMap.put("prodDetailLoan_list", loanList);

                detailList.add(productDetailMap);
            }
            resMap.put("prodDetail_list", detailList);
            resultList.add(resMap);
        }

        return resultList.toString();
    }
}
