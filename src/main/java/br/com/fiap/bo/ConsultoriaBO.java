package br.com.fiap.bo;

import br.com.fiap.dao.ConsultoriaDAO;
import br.com.fiap.to.ConsultoriaTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ConsultoriaBO {
    private ConsultoriaDAO consultoriaDAO;

    public ArrayList<ConsultoriaTO> findAll(){
        consultoriaDAO = new ConsultoriaDAO();
        return consultoriaDAO.findAll();
    }

    public ConsultoriaTO findByCodigo(Long id_consultoria){
        consultoriaDAO = new ConsultoriaDAO();
        return  consultoriaDAO.findByCodigo(id_consultoria);
    }

    public ConsultoriaTO save(ConsultoriaTO consultoria){
        consultoriaDAO = new ConsultoriaDAO();
        return consultoriaDAO.save(consultoria);
    }

    public boolean delete(Long id_consultoria){
        consultoriaDAO = new ConsultoriaDAO();
        return consultoriaDAO.delete(id_consultoria);
    }

    public ConsultoriaTO update(ConsultoriaTO consultoria) throws SQLException {
        consultoriaDAO = new ConsultoriaDAO();
        return consultoriaDAO.update(consultoria);
    }
}
