package ml.miliorini.crawler;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ml.miliorini.crawler.health.LicitacoesHealthCheck;
import ml.miliorini.crawler.resources.CrawlerResource;

public class CrawlerApplication extends Application<CrawlerConfiguration> {

	public static void main(final String[] args) throws Exception {
		new CrawlerApplication().run(args);
	}

	@Override
	public String getName() {
		return "Crawler";
	}

	@Override
	public void initialize(final Bootstrap<CrawlerConfiguration> bootstrap) {
		// Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                                                   new EnvironmentVariableSubstitutor(false)
                )
        );
	}

	@Override
	public void run(final CrawlerConfiguration configuration, final Environment environment) {
		// CORS
		this.setupCors(environment);

		// Endpoint
		final CrawlerResource resource = new CrawlerResource();
		environment.jersey().register(resource);

		// Health Check
		final LicitacoesHealthCheck healthCheck = new LicitacoesHealthCheck();
		environment.healthChecks().register("checkList", healthCheck);
	}

	/**
	 * Configura CORS para permitir qualquer origem
	 * 
	 * @param environment
	 */
	private void setupCors(Environment environment) {
		final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
		cors.setInitParameter("allowedOrigins", "*");
		cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
		cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
		cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
	}

}
