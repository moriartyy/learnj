package org.learnj.spring.boot.web.rest.filter;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import org.springframework.util.CollectionUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author hongmiao.yu
 */
public class CardNumberDecryptFilter implements Filter {

    private static final Set<String> CARD_NUMBER_PARAMETER_NAMES = ImmutableSet.<String>builder()
            .add("card_number")
            .add("d_card_number")
            .build();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // nothing to init
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        filterChain.doFilter(new CardNumberAutoDecryptRequest((HttpServletRequest) request), response);
    }

    @Override
    public void destroy() {
        // nothing to destroy
    }

    class CardNumberAutoDecryptRequest extends HttpServletRequestWrapper {

        private CardNumberAutoDecryptRequest(HttpServletRequest request) {
            super(request);
        }

        @Override
        public String getParameter(String name) {
            String value = super.getParameter(name);
            return maybeDecryptCardNumber(name, value);
        }

        private String[] maybeDecryptCardNumbers(String name, String[] values) {
            if (values == null || values.length == 0) {
                return values;
            }
            return Stream.of(values)
                    .map(v -> maybeDecryptCardNumber(name, v))
                    .collect(Collectors.toList())
                    .toArray(new String[0]);
        }

        private String maybeDecryptCardNumber(String name, String value) {
            if (!Strings.isNullOrEmpty(value)
                    && isCardNumberParameter(name)
                    && isGetRequest(getMethod())
                    && isEncrypted(value)) {
                return decrypt(value);
            }
            return value;
        }

        public String[] getParameterValues(String name) {
            String[] values = super.getParameterValues(name);
            return maybeDecryptCardNumbers(name, values);
        }

        public Map<String, String[]> getParameterMap() {
            Map<String, String[]> parameterMap = super.getParameterMap();
            if (CollectionUtils.isEmpty(parameterMap)) {
                return parameterMap;
            }

            Map<String, String[]> map = new HashMap<>();
            parameterMap.forEach((name, values) -> map.put(name, maybeDecryptCardNumbers(name, values)));
            return Collections.unmodifiableMap(map);
        }

        private boolean isEncrypted(String value) {
            return value.length() == 2;
        }
//8050484900000018
        private String decrypt(String value) {
            return "dec" + value;
        }

        private boolean isGetRequest(String method) {
            return "get".equalsIgnoreCase(method);
        }

        private boolean isCardNumberParameter(String name) {
            return CARD_NUMBER_PARAMETER_NAMES.contains(name);
        }
    }
}
