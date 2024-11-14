package br.com.fiap.dao;

import br.com.fiap.to.ConsultoriaTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConsultoriaDAO extends Repository{
    public ArrayList<ConsultoriaTO> findAll(){
        ArrayList<ConsultoriaTO> consultorias = new ArrayList<>();
        String sql = "select * from ddd_consultoria order by id_consultoria";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            if (rs !=null) {
                while (rs.next()){
                    ConsultoriaTO consultoria = new ConsultoriaTO();
                    consultoria.setId_consultoria(rs.getLong("id_consultoria"));
                    consultoria.setNome_usuario(rs.getString("nome_usuario"));
                    consultoria.setEmail_usuario(rs.getString("email_usuario"));
                    consultoria.setDuvidas(rs.getString("duvidas"));
                    consultorias.add(consultoria);
                }
            }else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return consultorias;
    }

    public ConsultoriaTO findByCodigo (Long id_consultoria){
        ConsultoriaTO consultoria = new ConsultoriaTO();
        String sql = "select * from ddd_consultoria where id_consultoria = ?";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setLong(1, id_consultoria);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                consultoria.setId_consultoria(rs.getLong("id_consultoria"));
                consultoria.setNome_usuario(rs.getString("nome_usuario"));
                consultoria.setEmail_usuario(rs.getString("email_usuario"));
                consultoria.setDuvidas(rs.getString("duvidas"));
            }else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return consultoria;
    }

    public ConsultoriaTO save(ConsultoriaTO consultoria){
        String sql = "insert into ddd_consultoria (nome_usuario, email_usuario, duvidas) values (?, ?, ?)";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setString(1, consultoria.getNome_usuario());
            ps.setString(2, consultoria.getEmail_usuario());
            ps.setString(3, consultoria.getDuvidas
                    ());
            if (ps.executeUpdate() > 0){
                return consultoria;
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

    public boolean delete (Long id_consultoria){
        String sql = "delete from ddd_consultoria where id_consultoria = ?";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setLong(1,id_consultoria);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return false;
    }

    public ConsultoriaTO update(ConsultoriaTO consultoria){
        String sql = "update ddd_consultoria set nome_usuario=?, email_usuario=?, duvidas=? where id_consultoria=?";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setString(1, consultoria.getNome_usuario());
            ps.setString(2, consultoria.getEmail_usuario());
            ps.setString(3, consultoria.getDuvidas());
            ps.setLong(4, consultoria.getId_consultoria());
            if (ps.executeUpdate() > 0){
                return consultoria;
            }else{
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return null;
    }
}
