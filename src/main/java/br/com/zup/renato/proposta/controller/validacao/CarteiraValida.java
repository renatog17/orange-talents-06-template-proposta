package br.com.zup.renato.proposta.controller.validacao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = { CarteiraValidaValidator.class })
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CarteiraValida {

	String message() default "Valores válidos: PayPal, SamsungPay;";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
