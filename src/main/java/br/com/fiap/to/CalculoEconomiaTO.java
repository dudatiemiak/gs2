package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;

public class CalculoEconomiaTO {
    private Long id_economia;
    @NotBlank
    private Double consumo_mensal_energia;
    @NotBlank
    private Double custo_energia;
    @NotBlank
    private Double economia_es;
    private Double economia_total;

    public CalculoEconomiaTO() {
    }

    public CalculoEconomiaTO(Long id_economia, Double consumo_mensal_energia, Double custo_energia, Double economia_es, Double economia_total) {
        this.id_economia = id_economia;
        this.consumo_mensal_energia = consumo_mensal_energia;
        this.custo_energia = custo_energia;
        this.economia_es = economia_es;
        this.economia_total = economia_total;
    }

    public Long getId_economia() {
        return id_economia;
    }

    public void setId_economia(Long id_economia) {
        this.id_economia = id_economia;
    }

    public @NotBlank Double getConsumo_mensal_energia() {
        return consumo_mensal_energia;
    }

    public void setConsumo_mensal_energia(@NotBlank Double consumo_mensal_energia) {
        this.consumo_mensal_energia = consumo_mensal_energia;
    }

    public @NotBlank Double getCusto_energia() {
        return custo_energia;
    }

    public void setCusto_energia(@NotBlank Double custo_energia) {
        this.custo_energia = custo_energia;
    }

    public @NotBlank Double getEconomia_es() {
        return economia_es;
    }

    public void setEconomia_es(@NotBlank Double economia_es) {
        this.economia_es = economia_es;
    }

    public Double getEconomia_total() {
        return economia_total;
    }

    public void setEconomia_total(Double economia_total) {
        this.economia_total = economia_total;
    }
}
