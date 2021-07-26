package br.com.zup.renato.proposta.controller.validacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CarteiraValidaValidator implements ConstraintValidator<CarteiraValida, Object> {

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value.equals("PayPal") || value.equals("SamsungPay"))
			return true;
		else
			return false;
	}

}
