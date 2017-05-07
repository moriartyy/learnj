package org.learnj.spring.boot.web.rest.service;

import lombok.Data;

/**
 * @author Loster on 5/7/17.
 */
@Data
public class Card {

    private String number;
    private String productCode;

    public static Card of(String number, String productCode) {
        Card card = new Card();
        card.number = number;
        card.productCode = productCode;
        return card;
    }
}
