package br.com.fiap.to;

public class FonteDeEnergiaTO {
    private Long id_es;
    private String tp_energia;
    private String localizacao_geografica;
    private Double energia_mensal;
    private String obj_implementacao;
    private Double orcamento;
    private String necessidade_atendimento;
    private String usuario_es;
    private String preferencia_contato;
    private String contato;

    public FonteDeEnergiaTO() {
    }

    public FonteDeEnergiaTO(Long id_es, String tp_energia, String localizacao_geografica, Double energia_mensal, String obj_implementacao, Double orcamento, String necessidade_atendimento, String usuario_es, String preferencia_contato, String contato) {
        this.id_es = id_es;
        this.tp_energia = tp_energia;
        this.localizacao_geografica = localizacao_geografica;
        this.energia_mensal = energia_mensal;
        this.obj_implementacao = obj_implementacao;
        this.orcamento = orcamento;
        this.necessidade_atendimento = necessidade_atendimento;
        this.usuario_es = usuario_es;
        this.preferencia_contato = preferencia_contato;
        this.contato = contato;
    }

    public Long getId_es() {
        return id_es;
    }

    public void setId_es(Long id_es) {
        this.id_es = id_es;
    }

    public String getTp_energia() {
        return tp_energia;
    }

    public void setTp_energia(String tp_energia) {
        this.tp_energia = tp_energia;
    }

    public String getLocalizacao_geografica() {
        return localizacao_geografica;
    }

    public void setLocalizacao_geografica(String localizacao_geografica) {
        this.localizacao_geografica = localizacao_geografica;
    }

    public Double getEnergia_mensal() {
        return energia_mensal;
    }

    public void setEnergia_mensal(Double energia_mensal) {
        this.energia_mensal = energia_mensal;
    }

    public String getObj_implementacao() {
        return obj_implementacao;
    }

    public void setObj_implementacao(String obj_implementacao) {
        this.obj_implementacao = obj_implementacao;
    }

    public Double getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Double orcamento) {
        this.orcamento = orcamento;
    }

    public String getNecessidade_atendimento() {
        return necessidade_atendimento;
    }

    public void setNecessidade_atendimento(String necessidade_atendimento) {
        this.necessidade_atendimento = necessidade_atendimento;
    }

    public String getUsuario_es() {
        return usuario_es;
    }

    public void setUsuario_es(String usuario_es) {
        this.usuario_es = usuario_es;
    }

    public String getPreferencia_contato() {
        return preferencia_contato;
    }

    public void setPreferencia_contato(String preferencia_contato) {
        this.preferencia_contato = preferencia_contato;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}
