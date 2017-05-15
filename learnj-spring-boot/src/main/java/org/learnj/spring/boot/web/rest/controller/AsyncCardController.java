package org.learnj.spring.boot.web.rest.controller;

import com.google.common.collect.Lists;
import org.learnj.spring.boot.web.rest.RestResponse;
import org.learnj.spring.boot.web.rest.RestStatus;
import org.learnj.spring.boot.web.rest.service.Card;
import org.learnj.spring.boot.web.rest.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;

import static org.learnj.spring.boot.web.rest.validation.CardValidator.checkExist;

/**
 * @author Loster on 5/11/17.
 */
@RequestMapping("/async/cards")
@RestController
public class AsyncCardController {

    private final CardService cardService;

    @Autowired
    public AsyncCardController(CardService cardService) {
        this.cardService = cardService;
    }

    @RequestMapping("")
    public DeferredResult<RestResponse> list() {
        DeferredResult<RestResponse> result = new DeferredResult<>(
                200L,
                Lists.<Card>newArrayList());
        result.setResult(RestResponse.success(RestStatus.OK, cardService.getAll()));
        return result;
    }

    @RequestMapping("/{number}")
    public RestResponse get(@PathVariable("number") String number) {
        Card card = checkExist(number);
        return RestResponse.success(RestStatus.OK, card);
    }
}
