package br.com.zup.renato.proposta.controller.validacao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = { UniqueValueValidator.class })
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueValue {

	String message() default "Este CPF ou CNPJ já está cadastrado";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String fieldName();

	Class<?> domainClass();
}
