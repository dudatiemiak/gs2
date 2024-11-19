package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FonteDeEnergiaTO {
    private Long id_es;
    private String tp_energia;
    @NotBlank
    private String localizacao_geografica;
    @NotNull
    private Double energia_mensal;
    @NotBlank
    private String obj_implementacao;
    @NotNull
    private Double orcamento;
    @NotBlank
    private String necessidade_atendimento;
    @NotBlank
    private String usuario_es;
    @NotBlank
    private String preferencia_contato;
    @NotBlank
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

    public @NotBlank String getTp_energia() {
        return tp_energia;
    }

    public void setTp_energia(@NotBlank String tp_energia) {
        this.tp_energia = tp_energia;
    }

    public @NotBlank String getLocalizacao_geografica() {
        return localizacao_geografica;
    }

    public void setLocalizacao_geografica(@NotBlank String localizacao_geografica) {
        this.localizacao_geografica = localizacao_geografica;
    }

    public @NotNull Double getEnergia_mensal() {
        return energia_mensal;
    }

    public void setEnergia_mensal(@NotNull Double energia_mensal) {
        this.energia_mensal = energia_mensal;
    }

    public @NotBlank String getObj_implementacao() {
        return obj_implementacao;
    }

    public void setObj_implementacao(@NotBlank String obj_implementacao) {
        this.obj_implementacao = obj_implementacao;
    }

    public @NotNull Double getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(@NotNull Double orcamento) {
        this.orcamento = orcamento;
    }

    public @NotBlank String getNecessidade_atendimento() {
        return necessidade_atendimento;
    }

    public void setNecessidade_atendimento(@NotBlank String necessidade_atendimento) {
        this.necessidade_atendimento = necessidade_atendimento;
    }

    public @NotBlank String getUsuario_es() {
        return usuario_es;
    }

    public void setUsuario_es(@NotBlank String usuario_es) {
        this.usuario_es = usuario_es;
    }

    public @NotBlank String getPreferencia_contato() {
        return preferencia_contato;
    }

    public void setPreferencia_contato(@NotBlank String preferencia_contato) {
        this.preferencia_contato = preferencia_contato;
    }

    public @NotBlank String getContato() {
        return contato;
    }

    public void setContato(@NotBlank String contato) {
        this.contato = contato;
    }
}
