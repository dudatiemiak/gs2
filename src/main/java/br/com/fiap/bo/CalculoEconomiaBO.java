package br.com.fiap.bo;

import br.com.fiap.dao.CalculoEconomiaDAO;
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
}
