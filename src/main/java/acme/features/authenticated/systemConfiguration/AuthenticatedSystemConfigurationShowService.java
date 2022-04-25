package acme.features.authenticated.systemConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedSystemConfigurationShowService implements AbstractShowService<Authenticated, SystemConfiguration>{
	
	@Autowired
	protected AuthenticatedSystemConfigurationRepository authenticatedSystemConfigurationRepository;

	@Override
	public boolean authorise(final Request<SystemConfiguration> request) {
		
		assert request != null;
		
		return true;
	}

	@Override
	public SystemConfiguration findOne(final Request<SystemConfiguration> request) {
		
		assert request != null;
		
		SystemConfiguration res;
		res = this.authenticatedSystemConfigurationRepository.findSystemConfiguration();
		
		return res;
	}

	@Override
	public void unbind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;

        request.unbind(entity, model, "systemCurrency", "acceptedCurrencies", "strongSpamTermsEn", "strongSpamTermsEs",
            "strongThreshold", "weakSpamTermsEn", "weakSpamTermsEs","weakThreshold");
        model.setAttribute("confirmation", false);
        model.setAttribute("readonly", true);
    }

}
