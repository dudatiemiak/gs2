package br.com.fiap.bo;

import br.com.fiap.dao.FonteDeEnergiaDAO;
import br.com.fiap.to.FonteDeEnergiaTO;

import java.util.ArrayList;

public class FonteDeEnergiaBO {
    private FonteDeEnergiaDAO fonteDAO;

    public ArrayList<FonteDeEnergiaTO> findAll(){
        fonteDAO = new FonteDeEnergiaDAO();
        return fonteDAO.findAll();
    }

    public FonteDeEnergiaTO findByCodigo(Long id_es){
        fonteDAO = new FonteDeEnergiaDAO();
        return  fonteDAO.findByCodigo(id_es);
    }

    public FonteDeEnergiaTO save(FonteDeEnergiaTO fonte){
        fonteDAO = new FonteDeEnergiaDAO();
        return fonteDAO.save(fonte);
    }

    public boolean delete(Long id_es){
        fonteDAO = new FonteDeEnergiaDAO();
        return fonteDAO.delete(id_es);
    }

    public FonteDeEnergiaTO update(FonteDeEnergiaTO fonte){
        fonteDAO = new FonteDeEnergiaDAO();
        return fonteDAO.update(fonte);
    }
}
