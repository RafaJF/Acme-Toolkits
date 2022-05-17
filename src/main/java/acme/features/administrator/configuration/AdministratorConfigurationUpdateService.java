package acme.features.administrator.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorConfigurationUpdateService implements AbstractUpdateService<Administrator, SystemConfiguration>{
	
	@Autowired
	protected AdministratorConfigurationRepository adminSystemConfigurationRepository;

	@Override
	public boolean authorise(final Request<SystemConfiguration> request) {
		assert request != null;
		return true;
	}

	@Override
	public SystemConfiguration findOne(final Request<SystemConfiguration> request) {
		
		assert request != null;
		
		SystemConfiguration res;
		res = this.adminSystemConfigurationRepository.findSystemConfiguration();
		
		return res;
	}
	
	@Override
	public void bind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
        assert request != null;
        assert entity != null;
        assert errors != null;

        request.bind(entity, errors, "systemCurrency", "acceptedCurrencies", "strongSpamTermsEn", "strongSpamTermsEs",
            "strongThreshold", "weakSpamTermsEn", "weakSpamTermsEs","weakThreshold");
    }

	@Override
	public void unbind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;

        request.unbind(entity, model, "systemCurrency", "acceptedCurrencies", "strongSpamTermsEn", "strongSpamTermsEs",
            "strongThreshold", "weakSpamTermsEn", "weakSpamTermsEs","weakThreshold");
        model.setAttribute("confirmation", false);
    }
	
	@Override
	public void validate(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("systemCurrency")) {
			errors.state(request, entity.getAcceptedCurrencies().contains(entity.getSystemCurrency()) , "systemCurrency", "administrator.configuration.form.error.systemCurrency");
		}
		
		if(!errors.hasErrors("strongSpamTreshold")) {
			errors.state(request, entity.getStrongThreshold()>0 && entity.getStrongThreshold()<=100, "strongTreshold", "administrator.configuration.form.error.strongTreshold");
		}

		if(!errors.hasErrors("weakSpamTreshold")) {
			errors.state(request, entity.getWeakThreshold()>0 && entity.getWeakThreshold()<=100, "weakTreshold", "administrator.configuration.form.error.weakTreshold");
		}

	}
	
	@Override
	public void update(final Request<SystemConfiguration> request, final SystemConfiguration entity) {
		assert request != null;
		assert entity != null;

		this.adminSystemConfigurationRepository.save(entity);

	}

}
