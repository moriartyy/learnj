package org.learnj.spring.mvc.controller;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

/**
 * Created by hongmiao.yu on 2015/12/10.
 */
public class HttpParameters {

    private final Map<String, String> parameters;

    public static HttpParameters wrap(Map<String, String> parameters) {
        return new HttpParameters(parameters);
    }

    public Map<String, String> asMap() {
        return Maps.newHashMap(this.parameters);
    }

    private HttpParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public String getString(String key) {
        return parameters.get(key);
    }

    public String getString(String key, String defaultVal) {
        String v = getString(key);
        return Strings.isNullOrEmpty(v) ? defaultVal : v;
    }

    public int getInt(String key, int defaultVal) {
        String v = getString(key);
        return Strings.isNullOrEmpty(v) ? defaultVal : Integer.parseInt(v);
    }

    public static HttpServletRequestParametersBuilder builder(HttpServletRequest request) {
        return new HttpServletRequestParametersBuilder(request);
    }

    public boolean getBoolean(String key, boolean defaultVal) {
        String v = getString(key);
        if (Strings.isNullOrEmpty(v)) {
            return defaultVal;
        } else {
            return Boolean.parseBoolean(v);
        }
    }

    public interface ParametersBuilder {
        HttpParameters build();
    }

    public static class HttpServletRequestParametersBuilder implements ParametersBuilder {

        private final Map<String, String> map = Maps.newHashMap();
        private final HttpServletRequest request;

        public HttpServletRequestParametersBuilder(HttpServletRequest request) {
            this.request = request;
            String queryString = this.request.getQueryString();
            if (!Strings.isNullOrEmpty(queryString)) {
                Map<String, String> paramsMap = Splitter.on('&').withKeyValueSeparator('=').split(queryString);
                for (Map.Entry<String, String> kv : paramsMap.entrySet()) {
                    try {
                        map.put(kv.getKey(), URLDecoder.decode(kv.getValue(), "utf-8"));
                    } catch (UnsupportedEncodingException ignored) {
                    }
                }
            }
        }

        @Override
        public HttpParameters build() {
            return new HttpParameters(map);
        }
    }

}
