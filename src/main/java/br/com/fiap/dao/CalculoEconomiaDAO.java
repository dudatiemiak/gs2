package br.com.fiap.dao;

import br.com.fiap.to.CalculoEconomiaTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CalculoEconomiaDAO extends Repository{
    public ArrayList<CalculoEconomiaTO> findAll(){
        ArrayList<CalculoEconomiaTO> economias = new ArrayList<>();
        String sql = "select * from ddd_calculo_economia order by id_economia";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            if (rs !=null) {
                while (rs.next()){
                    CalculoEconomiaTO economia = new CalculoEconomiaTO();
                    economia.setId_economia(rs.getLong("id_economia"));
                    economia.setConsumo_mensal_energia(rs.getDouble("consumo_mensal_energia"));
                    economia.setCusto_energia(rs.getDouble("custo_energia"));
                    economia.setEconomia_es(rs.getDouble("economia_es"));
                    economia.setEconomia_total(rs.getDouble("economia_total"));
                    economias.add(economia);
                }
            }else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return economias;
    }

    public CalculoEconomiaTO findByCodigo (Long id_economia){
        CalculoEconomiaTO economia = new CalculoEconomiaTO();
        String sql = "select * from ddd_calculo_economia where id_economia = ?";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setLong(1, id_economia);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                economia.setId_economia(rs.getLong("id_economia"));
                economia.setConsumo_mensal_energia(rs.getDouble("consumo_mensal_energia"));
                economia.setCusto_energia(rs.getDouble("custo_energia"));
                economia.setEconomia_es(rs.getDouble("economia_es"));
                economia.setEconomia_total(rs.getDouble("economia_total"));
            }else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return economia;
    }

    public CalculoEconomiaTO save(CalculoEconomiaTO economia){
        String sql = "insert into ddd_calculo_economia (consumo_mensal_energia, custo_energia, economia_es, economia_total) values (?, ?, ?, ?)";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setDouble(1, economia.getConsumo_mensal_energia());
            ps.setDouble(2, economia.getCusto_energia());
            ps.setDouble(3, economia.getEconomia_es());
            ps.setDouble(4, economia.getEconomia_total());
            if (ps.executeUpdate() > 0){
                return economia;
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

    public boolean delete (Long id_economia){
        String sql = "delete from ddd_calculo_economia where id_economia = ?";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setLong(1,id_economia);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return false;
    }

    public CalculoEconomiaTO update(CalculoEconomiaTO economia){
        String sql = "update ddd_calculo_economia set consumo_mensal_energia=?, custo_energia=?, economia_es=?, economia_total=? where id_economia=?";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setDouble(1, economia.getConsumo_mensal_energia());
            ps.setDouble(2, economia.getCusto_energia());
            ps.setDouble(3, economia.getEconomia_es());
            ps.setDouble(4, economia.getEconomia_total());
            ps.setLong(5, economia.getId_economia());
            if (ps.executeUpdate() > 0){
                return economia;
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
