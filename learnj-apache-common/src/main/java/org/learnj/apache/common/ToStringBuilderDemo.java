package org.learnj.apache.common;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author hongmiao.yu on 2016/7/15.
 */
public class ToStringBuilderDemo {

    public static void main(String[] args) {
        ToStringObject object = new ToStringObject();
        object.setName("peter");
//        object.setSubObjects(ImmutableMap.<String, ToStringObject.SubObject>of(
//                "name", new ToStringObject.SubObject("abc")
//        ));
        printObj(object, ToStringStyle.DEFAULT_STYLE);
        printObj(object, ToStringStyle.DEFAULT_STYLE);
        printObj(object, ToStringStyle.SIMPLE_STYLE);
        printObj(object, ToStringStyle.SHORT_PREFIX_STYLE);
        printObj(object, ToStringStyle.NO_CLASS_NAME_STYLE);
        printObj(object, ToStringStyle.NO_FIELD_NAMES_STYLE);
    }

    private static void printObj(ToStringObject object, ToStringStyle style) {
        System.out.println(ToStringBuilder.reflectionToString(object, style));
    }


}
