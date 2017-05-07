package org.learnj.spring.boot.web.rest.validation;

import org.learnj.spring.boot.web.rest.error.CardErrors;
import org.learnj.spring.boot.web.rest.exception.ValidationException;
import org.learnj.spring.boot.web.rest.service.Card;
import org.learnj.spring.boot.web.rest.service.CardService;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Loster on 5/7/17.
 */
@Component
public class CardValidator implements SmartInitializingSingleton {

    private static CardService cardService;
    private static AtomicBoolean initialized = new AtomicBoolean(false);

    @Autowired
    public CardValidator(CardService cardService) {
        CardValidator.cardService = cardService;
    }

    public static String checkNumber(String number) {
        return GeneralValidator.checkNotEmpty(number, CardErrors.EmptyNumber);
    }

    public static Card checkExist(String number) {
        checkInitialized();

        checkNumber(number);
        Card card = cardService.get(number);
        if (card == null) {
            throw new ValidationException(CardErrors.NotExist);
        }
        return card;
    }

    private static void checkInitialized() {
        if (!initialized.get()) {
            throw new IllegalStateException("CardValidator has not initialized!");
        }
    }

    @Override
    public void afterSingletonsInstantiated() {
        initialized.set(true);
    }

}
