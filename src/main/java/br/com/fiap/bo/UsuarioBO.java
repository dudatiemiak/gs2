package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.UsuarioTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioBO {
    private UsuarioDAO usuarioDAO;

    public ArrayList<UsuarioTO> findAll(){
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.findAll();
    }

    public UsuarioTO findByCodigo(Long id_usuario){
        usuarioDAO = new UsuarioDAO();
        return  usuarioDAO.findByCodigo(id_usuario);
    }

    public UsuarioTO save(UsuarioTO usuario){
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.save(usuario);
    }

    public boolean delete(Long id_usuario){
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.delete(id_usuario);
    }

    public UsuarioTO update(UsuarioTO usuario) throws SQLException {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.update(usuario);
    }
}
