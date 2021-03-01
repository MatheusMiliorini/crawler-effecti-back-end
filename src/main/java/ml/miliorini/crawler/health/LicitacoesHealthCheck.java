package ml.miliorini.crawler.health;

import java.util.List;

import com.codahale.metrics.health.HealthCheck;

import ml.miliorini.crawler.crawler.UfscCrawler;
import ml.miliorini.crawler.representation.Licitacao;

public class LicitacoesHealthCheck extends HealthCheck {

	@Override
	protected Result check() throws Exception {
		UfscCrawler crawler = new UfscCrawler();
		List<Licitacao> licitacoes = crawler.getLicitacoes();
		if (licitacoes.size() == 0 || licitacoes.get(0).id.isEmpty()) {
			return Result.unhealthy("Nenhuma licitação ou formato inválido!");
		}
		return Result.healthy();
	}

}
