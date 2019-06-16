package producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@ApplicationScoped
public class TemplateEngineProducer {

	private TemplateEngine templateEngine;

	@Produces
	public TemplateEngine getTemplateEngine() {
		if (templateEngine != null) {
			return templateEngine;
		}

		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setPrefix("/view/");
		templateResolver.setSuffix(".html");
		templateResolver.setCacheTTLMs(3_600_000L);
		templateResolver.setCacheable(true);

		templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);

		return templateEngine;
	}

}
