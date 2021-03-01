package ml.miliorini.crawler.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ml.miliorini.crawler.representation.Licitacao;

public class UfscCrawler {

	private String url = "http://notes.ufsc.br/aplic/licitcpl.nsf/vwLicitacao?OpenView&Start=1&Count=30&Expand=1#1";
	private String dominio = "http://notes.ufsc.br";

	private Document getDocument() throws IOException {
		return Jsoup.connect(this.url).get();
	}

	/**
	 * Retorna as linhas válidas da tabela
	 * 
	 * @return
	 * @throws IOException
	 */
	private List<Element> getRows() {
		List<Element> rowsList = new ArrayList<Element>();

		try {
			Element table = this.getDocument().select("table").get(3);
			Elements rows = table.select("tbody > tr");
			for (int i = 0; i < rows.size(); i++) {
				Elements td = rows.get(i).select("td");
				if (td.size() == 8) {
					rowsList.add(rows.get(i));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rowsList;
	}

	private Licitacao tdsToLicitacao (Elements tds) {
		return new Licitacao(tds.get(1).text(), 
				 			 tds.get(2).text(), 
				 			 tds.get(3).text() + " às " + tds.get(4).text(),
				 			 this.dominio + tds.get(6).selectFirst("a").attr("href"));
	}

	/**
	 * Retorna a lista de licitações do site
	 * 
	 * @return
	 */
	public List<Licitacao> getLicitacoes() {
		ArrayList<Licitacao> licitacoes = new ArrayList<Licitacao>();

		List<Element> rows = this.getRows();
		for (Element row : rows) {
			Elements tds = row.select("td");
			licitacoes.add(this.tdsToLicitacao(tds));
		}

		return licitacoes;
	}

}
