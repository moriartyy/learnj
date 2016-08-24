package org.learnj.spring.schema.custom;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author Loster on 2016/8/24.
 */
public class LeechNamespaceHandler extends NamespaceHandlerSupport {
    public void init() {
        registerBeanDefinitionParser("node", new LeechBeanDefinitionParser());
    }
}
