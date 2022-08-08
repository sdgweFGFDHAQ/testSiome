package testAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DefineAnnotation {
    String role()
            default "作用";
    int[] nums();
}
