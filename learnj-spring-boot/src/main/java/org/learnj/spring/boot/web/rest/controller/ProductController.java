package org.learnj.spring.boot.web.rest.controller;

import org.learnj.spring.boot.web.rest.RestResponse;
import org.learnj.spring.boot.web.rest.RestStatus;
import org.learnj.spring.boot.web.rest.error.ProductErrors;
import org.learnj.spring.boot.web.rest.rule.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Loster on 5/4/17.
 */
@RestController
public class ProductController {

    @RequestMapping("/products")
    public RestResponse<List<String>> list() {
        List<String> list = new ArrayList<>();
        list.add("iphone5");
        list.add("iphone6");
        return RestResponse.success(RestStatus.OK, list);
    }

    @RequestMapping("/products/iphone5")
    public Object iphone5() {
        Validator.NotEmpty("", ProductErrors.EmptyName);
        return "iphone5";
    }

    @RequestMapping("/products/iphone6")
    public Object iphone6() {
        System.out.println(Thread.currentThread().getId());
        Validator.NotEmpty("", "This is empty");
        throw new RuntimeException();
    }

}
