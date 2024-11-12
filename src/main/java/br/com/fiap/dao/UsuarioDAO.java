package br.com.fiap.dao;

import br.com.fiap.to.UsuarioTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO extends Repository{
    public ArrayList<UsuarioTO> findAll(){
        ArrayList<UsuarioTO> usuarios = new ArrayList<>();
        String sql = "select * from ddd_usuario order by id_usuario";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            if (rs !=null) {
                while (rs.next()){
                    UsuarioTO usuario = new UsuarioTO();
                    usuario.setId_usuario(rs.getLong("id_usuario"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setCpf(rs.getString("cpf"));
                    usuario.setSenha(rs.getString("senha"));
                    usuarios.add(usuario);
                }
            }else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return usuarios;
    }

    public UsuarioTO findByCodigo (Long id_usuario){
        UsuarioTO usuario = new UsuarioTO();
        String sql = "select * from ddd_usuario where id_usuario = ?";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setLong(1, id_usuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                usuario.setId_usuario(rs.getLong("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setSenha(rs.getString("senha"));
            }else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return usuario;
    }

    public UsuarioTO save(UsuarioTO usuario){
        String sql = "insert into ddd_usuario (nome, email, cpf, senha) values (?, ?, ?, ?)";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getCpf());
            ps.setString(4, usuario.getSenha());
            if (ps.executeUpdate() > 0){
                return usuario;
            }else {
                return null;
            }
        }catch (SQLException e){
            System.out.println("Erro ao salvar: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return null;
    }

    public boolean delete (Long id_usuario){
        String sql = "delete from ddd_usuario where id_usuario = ?";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setLong(1,id_usuario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return false;
    }

    public UsuarioTO update(UsuarioTO usuario){
        String sql = "update ddd_usuario set nome=?, email=?, cpf=?, senha=? where id_usuario=?";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getCpf());
            ps.setString(4, usuario.getSenha());
            ps.setLong(5, usuario.getId_usuario());
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return null;
    }
}
