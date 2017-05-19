package org.learnj.spring.boot.web.rest.controller;

import com.google.common.collect.ImmutableMap;
import org.learnj.spring.boot.web.rest.RestResponse;
import org.learnj.spring.boot.web.rest.RestStatus;
import org.learnj.spring.boot.web.rest.service.Card;
import org.learnj.spring.boot.web.rest.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.learnj.spring.boot.web.rest.validation.CardValidator.checkExist;

/**
 * @author Loster on 5/7/17.
 */
@RequestMapping("/cards")
@RestController
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @RequestMapping("")
    public RestResponse list() {
        return RestResponse.success(RestStatus.OK, cardService.getAll());
    }

    @RequestMapping("/{number}")
    public RestResponse get(@PathVariable("number") String number) {
        Card card = checkExist(number);
        return RestResponse.success(RestStatus.OK, card);
    }

    @RequestMapping("/search")
    public RestResponse search(@RequestParam("card_number") String cardNumber,
                               @RequestParam("d_card_number") String dCardNumber) {
        return RestResponse.success(
                RestStatus.OK,
                ImmutableMap.builder()
                        .put("cardNumber: ", cardNumber)
                        .put("dCardNumber:", dCardNumber)
                        .build()
        );
    }
}
