package ml.miliorini.crawler.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ml.miliorini.crawler.crawler.UfscCrawler;
import ml.miliorini.crawler.representation.Licitacao;

@Path("/licitacoes")
@Produces(MediaType.APPLICATION_JSON)
public class CrawlerResource {

	@GET
	public List<Licitacao> listLicitacoes() {
		UfscCrawler crawler = new UfscCrawler();
		List<Licitacao> licitacoes = crawler.getLicitacoes();
		return licitacoes;
	}
}
