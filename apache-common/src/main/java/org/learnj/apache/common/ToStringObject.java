package org.learnj.apache.common;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Map;

/**
 * @author hongmiao.yu on 2016/7/15.
 */
public class ToStringObject {

    private String name;
    private Map<String, SubObject> subObjects = Maps.newHashMap();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, SubObject> getSubObjects() {
        return subObjects;
    }

    public void setSubObjects(Map<String, SubObject> subObjects) {
        this.subObjects = subObjects;
    }

    public static class SubObject {
        private String name;

        public SubObject(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.NO_CLASS_NAME_STYLE);
        }
    }
}
