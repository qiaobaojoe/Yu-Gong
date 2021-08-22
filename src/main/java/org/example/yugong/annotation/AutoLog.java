package org.example.yugong.annotation;

import java.lang.annotation.*;

/**
 * @author qiaobao
 * @since 2021-06-03
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface AutoLog {

    /**
     * default extension name
     */
    String methodExplain() default "";
}
