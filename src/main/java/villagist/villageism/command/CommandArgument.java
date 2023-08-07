package villagist.villageism.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface CommandArgument {
    /**
     * @return name of argument
     */
    String value();
    int[] argParameters() default {};
    @Deprecated
    boolean useDefaultType() default true;
}
