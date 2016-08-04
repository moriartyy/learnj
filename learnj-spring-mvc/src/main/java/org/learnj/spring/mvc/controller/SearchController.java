package org.learnj.spring.mvc.controller;

import com.google.common.collect.ImmutableMap;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author hongmiao.yu on 2016/7/21.
 */
@Controller
public class SearchController {

    @RequestMapping(value = "/s", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Object search(HttpServletRequest request) {
        HttpParameters params = HttpParameters.builder(request).build();
        String url = request.getQueryString();
        Map<String, Object> map = ImmutableMap.<String, Object>builder()
                .put("query", params.getString("query"))
                .put("queryString", request.getQueryString())
                .build();
        return new GsonBuilder().create().toJson(map);
    }
}
