package org.learnj.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

/**
 * @author hongmiao.yu on 2016/7/28.
 */
public interface Printf extends Library {

    Printf INSTANCE = (Printf)
                Native.loadLibrary((Platform.isWindows() ? "msvcrt" : "c"),
                        Printf.class);

        void printf(String format, Object... args);
}
