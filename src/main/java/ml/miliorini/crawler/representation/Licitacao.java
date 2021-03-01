package ml.miliorini.crawler.representation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Licitacao {
	public String id;
	public String descricao;
	public String data;
	public String linkDocumentos;

	public Licitacao(String id, String descricao, String data, String linkDocumentos) {
		this.id = id;
		this.descricao = descricao;
		this.data = data;
		this.linkDocumentos = linkDocumentos;
	}

	@JsonProperty
	public String getId() {
		return id;
	}

	@JsonProperty
	public String getDescricao() {
		return descricao;
	}

	@JsonProperty
	public String getData() {
		return data;
	}

	@JsonProperty
	public String getLinkDocumentos() {
		return linkDocumentos;
	}
}
