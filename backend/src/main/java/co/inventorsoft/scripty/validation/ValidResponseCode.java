package co.inventorsoft.scripty.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = ResponseCodeValidator.class)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
public @interface ValidResponseCode {
    String message() default "Invalid response code input! Try to use another one.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
