package villagist.villageism.command;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CommandMethod {
    /**
     * @return The name of Command
     */
    String value();
    int permission() default 4;
    boolean isSourceNecessary() default false;
}
