package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CalculoEconomiaTO {
    private Long id_economia;
    @NotNull
    private Double consumo_mensal_energia;
    @NotNull
    private Double custo_energia;
    @NotNull
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

    public @NotNull Double getConsumo_mensal_energia() {
        return consumo_mensal_energia;
    }

    public void setConsumo_mensal_energia(@NotNull Double consumo_mensal_energia) {
        this.consumo_mensal_energia = consumo_mensal_energia;
    }

    public @NotNull Double getCusto_energia() {
        return custo_energia;
    }

    public void setCusto_energia(@NotNull Double custo_energia) {
        this.custo_energia = custo_energia;
    }

    public @NotNull Double getEconomia_es() {
        return economia_es;
    }

    public void setEconomia_es(@NotNull Double economia_es) {
        this.economia_es = economia_es;
    }

    public Double getEconomia_total() {
        return economia_total;
    }

    public void setEconomia_total(Double economia_total) {
        this.economia_total = economia_total;
    }
}
