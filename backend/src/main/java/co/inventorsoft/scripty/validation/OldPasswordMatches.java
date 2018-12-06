package co.inventorsoft.scripty.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD,TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = OldPasswordMatchesValidator.class)
@Documented
public @interface OldPasswordMatches {

    String message() default "New password cannot be the same as the old one.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

