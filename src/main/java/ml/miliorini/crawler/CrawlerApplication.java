package ml.miliorini.crawler;

import io.dropwizard.Application;
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
		// TODO: application initialization
	}

	@Override
	public void run(final CrawlerConfiguration configuration, final Environment environment) {
		final CrawlerResource resource = new CrawlerResource();
		environment.jersey().register(resource);
		final LicitacoesHealthCheck healthCheck = new LicitacoesHealthCheck();
		environment.healthChecks().register("checkList", healthCheck);
	}

}
