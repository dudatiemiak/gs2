package br.com.fiap.dao;

import br.com.fiap.to.FonteDeEnergiaTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FonteDeEnergiaDAO extends Repository{
    public ArrayList<FonteDeEnergiaTO> findAll(){
        ArrayList<FonteDeEnergiaTO> fontes = new ArrayList<>();
        String sql = "select * from ddd_fonte_energia order by id_es";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            if (rs !=null) {
                while (rs.next()){
                    FonteDeEnergiaTO fonte = new FonteDeEnergiaTO();
                    fonte.setId_es(rs.getLong("id_es"));
                    fonte.setTp_energia(rs.getString("tp_energia"));
                    fonte.setLocalizacao_geografica(rs.getString("localizacao_geografica"));
                    fonte.setEnergia_mensal(rs.getDouble("energia_mensal"));
                    fonte.setObj_implementacao(rs.getString("obj_implementacao"));
                    fonte.setOrcamento(rs.getDouble("orcamento"));
                    fonte.setNecessidade_atendimento(rs.getString("necessidade_atendimento"));
                    fonte.setUsuario_es(rs.getString("usuario_es"));
                    fonte.setPreferencia_contato(rs.getString("preferencia_contato"));
                    fonte.setContato(rs.getString("contato"));
                    fontes.add(fonte);
                }
            }else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return fontes;
    }

    public FonteDeEnergiaTO findByCodigo (Long id_es){
        FonteDeEnergiaTO fonte = new FonteDeEnergiaTO();
        String sql = "select * from ddd_fonte_energia where id_es = ?";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setLong(1, id_es);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                fonte.setId_es(rs.getLong("id_es"));
                fonte.setTp_energia(rs.getString("tp_energia"));
                fonte.setLocalizacao_geografica(rs.getString("localizacao_geografica"));
                fonte.setEnergia_mensal(rs.getDouble("energia_mensal"));
                fonte.setObj_implementacao(rs.getString("obj_implementacao"));
                fonte.setOrcamento(rs.getDouble("orcamento"));
                fonte.setNecessidade_atendimento(rs.getString("necessidade_atendimento"));
                fonte.setUsuario_es(rs.getString("usuario_es"));
                fonte.setPreferencia_contato(rs.getString("preferencia_contato"));
                fonte.setContato(rs.getString("contato"));
            }else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return fonte;
    }

    public FonteDeEnergiaTO save(FonteDeEnergiaTO fonte){
        String sql = "insert into ddd_fonte_energia (tp_energia, localizacao_geografica, energia_mensal, obj_implementacao, orcamento, necessidade_atendimento, usuario_es, preferencia_contato, contato) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setString(1, fonte.getTp_energia());
            ps.setString(2, fonte.getLocalizacao_geografica());
            ps.setDouble(3, fonte.getEnergia_mensal());
            ps.setString(4, fonte.getObj_implementacao());
            ps.setDouble(5, fonte.getOrcamento());
            ps.setString(6, fonte.getNecessidade_atendimento());
            ps.setString(7, fonte.getUsuario_es());
            ps.setString(8, fonte.getPreferencia_contato());
            ps.setString(9, fonte.getContato());
            if (ps.executeUpdate() > 0){
                return fonte;
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

    public boolean delete (Long id_es){
        String sql = "delete from ddd_fonte_energia where id_es = ?";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setLong(1,id_es);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return false;
    }

    public FonteDeEnergiaTO update(FonteDeEnergiaTO fonte){
        String sql = "update ddd_fonte_energia set tp_energia=?, localizacao_geografica=?, energia_mensal=?, obj_implementacao=?, orcamento=?, necessidade_atendimento=?, usuario_es=?, preferencia_contato=?, contato=? where id_es=?";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)){
            ps.setString(1, fonte.getTp_energia());
            ps.setString(2, fonte.getLocalizacao_geografica());
            ps.setDouble(3, fonte.getEnergia_mensal());
            ps.setString(4, fonte.getObj_implementacao());
            ps.setDouble(5, fonte.getOrcamento());
            ps.setString(6, fonte.getNecessidade_atendimento());
            ps.setString(7, fonte.getUsuario_es());
            ps.setString(8, fonte.getPreferencia_contato());
            ps.setString(9, fonte.getContato());
            ps.setLong(10, fonte.getId_es());
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }finally {
            closeConnection();
        }
        return null;
    }
}
