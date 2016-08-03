package org.learnj.lang.serviceloader;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author hongmiao.yu on 2016/8/1.
 */
public class Bootstrap {

    public static void main(String[] args) {

        String s = new String(Character.toChars(0x2F81A));
        System.out.println(s.length());
        System.out.println(Character.isHighSurrogate(s.charAt(0)));
        System.out.format("%X", s.codePointAt(0));


//        ServiceLoader<PrintService> loader = ServiceLoader.load(PrintService.class);
//        for (PrintService printService : loader) {
//            printService.print();
//        }
    }

    static Map<Set<String>, Integer> docs;
    static void search(Set<String> keywords) {
        for (Set<String> doc : docs.keySet()) {
            for (String keyword : keywords) {
                if (!contains(doc, keyword)) {
                    break;
                }
                System.out.println(docs.get(doc));
            }
        }
    }

    private static boolean contains(Set<String> doc, String keyword) {
        for (String s : doc) {
            if (s.equalsIgnoreCase(keyword)) {
                return true;
            }
        }
        return false;
    }



}
