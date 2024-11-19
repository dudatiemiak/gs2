package br.com.fiap.bo;

import br.com.fiap.dao.CalculoEconomiaDAO;
import br.com.fiap.exception.ValoresInvalidosException;
import br.com.fiap.to.CalculoEconomiaTO;

import java.util.ArrayList;

public class CalculoEconomiaBO {
    private CalculoEconomiaDAO calculoEconomiaDAO;

    public ArrayList<CalculoEconomiaTO> findAll(){
        calculoEconomiaDAO = new CalculoEconomiaDAO();
        return calculoEconomiaDAO.findAll();
    }

    public CalculoEconomiaTO findByCodigo(Long id_economia){
        calculoEconomiaDAO = new CalculoEconomiaDAO();
        return  calculoEconomiaDAO.findByCodigo(id_economia);
    }

    public CalculoEconomiaTO save(CalculoEconomiaTO economia){
        calculoEconomiaDAO = new CalculoEconomiaDAO();

        calcularEconomia(economia);

        return calculoEconomiaDAO.save(economia);
    }

    public boolean delete(Long id_economia){
        calculoEconomiaDAO = new CalculoEconomiaDAO();
        return calculoEconomiaDAO.delete(id_economia);
    }

    public CalculoEconomiaTO update(CalculoEconomiaTO economia){
        calculoEconomiaDAO = new CalculoEconomiaDAO();
        return calculoEconomiaDAO.update(economia);
    }

    private void calcularEconomia(CalculoEconomiaTO economia){
        double consumo = economia.getConsumo_mensal_energia();
        double custo = economia.getCusto_energia();
        double percentual = economia.getEconomia_es();
        double resultadoEconomia = economia.getEconomia_total();

        try {
            if (consumo <= 0) {
                throw new ValoresInvalidosException("Consumo mensal de energia deve ser maior que zero.");
            }
            if (custo <= 0) {
                throw new ValoresInvalidosException("Custo da energia deve ser maior que zero.");
            }
            if (percentual <= 0) {
                throw new ValoresInvalidosException("Percentual de economia deve ser maior que zero.");
            }

            resultadoEconomia = consumo * custo * (percentual / 100);

        } catch (ValoresInvalidosException e) {
            System.out.println("Erro no cálculo da economia: " + e.getMessage());
            economia.setEconomia_total(0.0);
        } finally {
            System.out.println("Cálculo de economia finalizado.");
        }
    }

}
