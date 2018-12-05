package co.inventorsoft.scripty.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = CharsetValidator.class)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
public @interface ValidCharset {
    String message() default "Invalid charset input! Try to use another one.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
