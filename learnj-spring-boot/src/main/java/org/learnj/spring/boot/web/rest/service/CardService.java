package org.learnj.spring.boot.web.rest.service;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author Loster on 5/7/17.
 */
@Component
public class CardService {

    private static ImmutableMap<String, Card> cards = ImmutableMap.<String, Card>builder()
            .put("333302202202", Card.of("333302202202", "39923"))
            .put("233302202202", Card.of("333302202202", "39323"))
            .build();


    public Card get(String number) {
        Preconditions.checkArgument(
                !Strings.isNullOrEmpty(number),
                "Card number can not be null or empty!"
        );
        return cards.get(number);
    }

    public ArrayList<Card> getAll() {
        return new ArrayList<>(cards.values());
    }
}
