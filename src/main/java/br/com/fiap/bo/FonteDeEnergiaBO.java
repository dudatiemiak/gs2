package br.com.fiap.bo;

import br.com.fiap.dao.FonteDeEnergiaDAO;
import br.com.fiap.exception.DadosInsuficientesException;
import br.com.fiap.to.FonteDeEnergiaTO;

import java.text.Normalizer;
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
        String recomendacao = definirRecomendacao(fonte);
        fonte.setTp_energia(recomendacao);
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

    private String definirRecomendacao(FonteDeEnergiaTO fonte) {
        String estado = fonte.getLocalizacao_geografica();
        String objetivo = fonte.getObj_implementacao();
        double orcamento = fonte.getOrcamento();


        try {
            if (estado == null || estado.isEmpty() || objetivo == null || objetivo.isEmpty()) {
                throw new DadosInsuficientesException("Dados insuficientes para a recomendação. Estado ou objetivo não podem ser nulos ou vazios.");
            }

            if (orcamento < 15000) {
                return "Recomendação: Energia Solar Residencial - Baixo orçamento, indicada para residências pequenas.";
            }

            switch (estado) {
                case "Acre":
                case "Amazonas":
                case "Roraima":
                case "Rondônia":
                    if (objetivo.equalsIgnoreCase("Rural")) {
                        if (orcamento >= 30000) {
                            return "Recomendação: Energia Eólica - Ideal para áreas com ventos constantes e poucas alternativas.";
                        } else {
                            return "Recomendação: Energia Solar Fotovoltaica - Melhor opção para regiões remotas com boa insolação.";
                        }
                    } else if (objetivo.equalsIgnoreCase("Industrial")) {
                        if (orcamento >= 100000) {
                            return "Recomendação: Energia Solar Híbrida - Alta eficiência para indústrias com alta demanda energética.";
                        } else {
                            return "Recomendação: Biomassa - Utilize resíduos florestais disponíveis na região.";
                        }
                    } else {
                        return "Recomendação: Energia Solar Residencial - Boa opção para residências com orçamento moderado.";
                    }

                case "Bahia":
                case "Ceará":
                case "Rio grande do norte":
                    if (objetivo.equalsIgnoreCase("Rural")) {
                        return "Recomendação: Energia Eólica - Região com excelente potencial eólico.";
                    } else if (objetivo.equalsIgnoreCase("Industrial")) {
                        if (orcamento >= 150000) {
                            return "Recomendação: Energia Solar Híbrida - Alta demanda e eficiência.";
                        } else {
                            return "Recomendação: Energia Solar Fotovoltaica - Opção viável para menor orçamento.";
                        }
                    } else {
                        return "Recomendação: Energia Solar Residencial - Ideal para residências aproveitando a alta insolação.";
                    }

                case "São paulo":
                case "Minas gerais":
                case "Paraná":
                    if (objetivo.equalsIgnoreCase("Rural")) {
                        return "Recomendação: Biomassa - Aproveite resíduos agrícolas para geração de energia.";
                    } else if (objetivo.equalsIgnoreCase("Industrial")) {
                        if (orcamento >= 200000) {
                            return "Recomendação: Hidroenergia (PCH) - Opção sustentável e eficiente.";
                        } else {
                            return "Recomendação: Energia Solar Fotovoltaica - Opção mais econômica para indústrias.";
                        }
                    } else {
                        return "Recomendação: Energia Solar Residencial - Alta eficiência para áreas urbanas.";
                    }

                case "Rio grande do sul":
                case "Santa catarina":
                    if (objetivo.equalsIgnoreCase("Rural")) {
                        return "Recomendação: Energia Eólica - Aproveite o potencial de ventos na região.";
                    } else if (objetivo.equalsIgnoreCase("Industrial")) {
                        if (orcamento >= 100000) {
                            return "Recomendação: Hidroenergia (PCH) - Ideal para indústrias com alto consumo.";
                        } else {
                            return "Recomendação: Energia Solar Fotovoltaica - Alternativa viável para menor orçamento.";
                        }
                    } else {
                        return "Recomendação: Energia Solar Residencial - Solução eficiente para residências.";
                    }

                case "Mato grosso":
                case "Mato grosso do sul":
                case "Goiás":
                    if (objetivo.equalsIgnoreCase("Rural")) {
                        return "Recomendação: Biomassa - Utilize resíduos agrícolas abundantes na região.";
                    } else if (objetivo.equalsIgnoreCase("Industrial")) {
                        if (orcamento >= 150000) {
                            return "Recomendação: Energia Solar Híbrida - Alta eficiência para grandes indústrias.";
                        } else {
                            return "Recomendação: Biomassa - Alternativa sustentável para menor orçamento.";
                        }
                    } else {
                        return "Recomendação: Energia Solar Residencial - Boa escolha para residências urbanas.";
                    }

                default:
                    throw new IllegalArgumentException("Estado não reconhecido. Por favor, verifique o nome do estado.");
            }
        }catch (ArithmeticException e) {
            return "Erro de cálculo: " + e.getMessage();
        } catch (NumberFormatException e) {
            return "Erro de conversão: o valor do orçamento deve ser numérico. " + e.getMessage();
        } catch (IllegalArgumentException e) {
            return "Erro de entrada: " + e.getMessage();
        } catch (Exception e) {
            return "Erro inesperado: " + e.getMessage();
        } finally {
            System.out.println("Recomendação de energia sustentável concluída.");
        }

    }

}
