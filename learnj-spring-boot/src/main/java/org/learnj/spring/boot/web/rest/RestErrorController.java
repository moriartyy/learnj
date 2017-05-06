package org.learnj.spring.boot.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Loster on 5/6/17.
 */
@Controller
@RequestMapping("/error")
public class RestErrorController extends AbstractErrorController {

    @Autowired
    public RestErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping
    @ResponseBody
    public RestResponse error(HttpServletRequest request) {
        Map<String, Object> errorAttributes = this.getErrorAttributes(request, false);
        int status = (int) errorAttributes.get("status");
        if (status == HttpStatus.NOT_FOUND.value()) {
            return RestResponse.error(RestStatus.NOT_FOUND);
        } else {
            return RestResponse.error(RestStatus.INTERNAL_ERROR);
        }
    }
}
