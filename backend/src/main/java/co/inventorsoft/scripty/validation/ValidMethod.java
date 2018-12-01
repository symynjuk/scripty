package co.inventorsoft.scripty.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = MethodValidator.class)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
public @interface ValidMethod {
    String message() default "Invalid method input!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
