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
        definirRecomendacao(fonte);
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

    private void definirRecomendacao(FonteDeEnergiaTO fonte) {
        String estado = fonte.getLocalizacao_geografica();
        String objetivo = fonte.getObj_implementacao();
        double orcamento = fonte.getOrcamento();
        double consumo = fonte.getEnergia_mensal();
        String tp_energia = "Recomendação não disponível.";


        try {
            if (estado == null || estado.isEmpty() || objetivo == null || objetivo.isEmpty()) {
                throw new DadosInsuficientesException("Dados insuficientes para a recomendação. Estado ou objetivo não podem ser nulos ou vazios.");
            }

            if (orcamento < 15000) {
                tp_energia = "Recomendação: Energia Solar Residencial - Baixo orçamento, indicada para residências pequenas.";
            }

            switch (estado) {
                case "Acre":
                case "Amazonas":
                case "Roraima":
                case "Rondônia":
                    if (objetivo.equalsIgnoreCase("Rural")) {
                        if (orcamento >= 30000) {
                            tp_energia = "Recomendação: Energia Eólica - Ideal para áreas com ventos constantes e poucas alternativas.";
                        } else {
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica - Melhor opção para regiões remotas com boa insolação.";
                        }
                    } else if (objetivo.equalsIgnoreCase("Industrial")) {
                        if (orcamento >= 100000) {
                            tp_energia = "Recomendação: Energia Solar Híbrida - Alta eficiência para indústrias com alta demanda energética.";
                        } else if (consumo >= 1200 && consumo <= 1500 && orcamento >= 20000 && orcamento <= 50000){
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica - Melhor opção para pequenas empresas em regiões com alta incidência solar, como o Acre, Amazonas, Roraima e Rondônia. A energia solar é uma solução econômica e sustentável, ideal para áreas remotas, pois aproveita a abundante luz solar, reduzindo custos com eletricidade e trazendo alta eficiência energética. Além disso, com o baixo custo de operação e a possibilidade de personalização do sistema de acordo com as necessidades da empresa, a energia solar se torna a alternativa mais viável para garantir a sustentabilidade e a redução de gastos operacionais.";
                        } else if (consumo >= 500000 && consumo <= 5000000 && orcamento >= 5000000 && orcamento <= 7000000) {
                            tp_energia = "Recomendação: Energia Eólica - Ideal para atender grandes demandas energéticas de empresas, aproveitando os padrões de vento constantes e a escalabilidade dos sistemas, enquanto promove uma operação sustentável e econômica no longo prazo.";
                        } else if (consumo >= 1200 && consumo <= 1500 && orcamento >= 15000 && orcamento <= 20000){
                            tp_energia = "Recomendação: Energia Solar Residencial de Pequeno Porte - Alternativa econômica e eficiente para pequenas empresas, aproveitando o alto índice de insolação na região para reduzir custos com energia elétrica, mesmo com um orçamento limitado.";
                        } else if (consumo >= 500000 && consumo <= 5000000 && orcamento >= 3000000 && orcamento < 5000000) {
                            tp_energia = "Recomendação: Energia Solar Híbrida - Uma solução eficiente e sustentável para grandes empresas que necessitam de fornecimento contínuo e confiável, aproveitando os recursos naturais da região amazônica dentro de um orçamento moderado.";
                        } else {
                            tp_energia = "O orçamento não é suficiente para instalar energia sustentável";
                        }
                    } else {
                        tp_energia = "Recomendação: Energia Solar Residencial - Boa opção para residências com orçamento moderado.";
                    }
                    break;

                case "Bahia":
                case "Ceará":
                case "Rio grande do norte":
                    if (objetivo.equalsIgnoreCase("Rural")) {
                        tp_energia = "Recomendação: Energia Eólica - Região com excelente potencial eólico.";
                    } else if (objetivo.equalsIgnoreCase("Industrial")) {
                        if (orcamento >= 150000) {
                            tp_energia = "Recomendação: Energia Solar Híbrida - Alta demanda e eficiência.";
                        } else {
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica - Opção viável para menor orçamento.";
                        }
                    } else {
                        tp_energia = "Recomendação: Energia Solar Residencial - Ideal para residências aproveitando a alta insolação.";
                    }
                    break;

                case "São paulo":
                case "Minas gerais":
                case "Paraná":
                    if (objetivo.equalsIgnoreCase("Rural")) {
                        tp_energia = "Recomendação: Biomassa - Aproveite resíduos agrícolas para geração de energia.";
                    } else if (objetivo.equalsIgnoreCase("Industrial")) {
                        if (orcamento >= 200000) {
                            tp_energia = "Recomendação: Hidroenergia (PCH) - Opção sustentável e eficiente.";
                        } else {
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica - Opção mais econômica para indústrias.";
                        }
                    } else {
                        tp_energia = "Recomendação: Energia Solar Residencial - Alta eficiência para áreas urbanas.";
                    }
                    break;

                case "Rio grande do sul":
                case "Santa catarina":
                    if (objetivo.equalsIgnoreCase("Rural")) {
                        tp_energia = "Recomendação: Energia Eólica - Aproveite o potencial de ventos na região.";
                    } else if (objetivo.equalsIgnoreCase("Industrial")) {
                        if (orcamento >= 100000) {
                            tp_energia = "Recomendação: Hidroenergia (PCH) - Ideal para indústrias com alto consumo.";
                        } else {
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica - Alternativa viável para menor orçamento.";
                        }
                    } else {
                        tp_energia = "Recomendação: Energia Solar Residencial - Solução eficiente para residências.";
                    }
                    break;

                case "Mato grosso":
                case "Mato grosso do sul":
                case "Goiás":
                    if (objetivo.equalsIgnoreCase("Rural")) {
                        tp_energia = "Recomendação: Biomassa - Utilize resíduos agrícolas abundantes na região.";
                    } else if (objetivo.equalsIgnoreCase("Industrial")) {
                        if (orcamento >= 150000)
                            tp_energia = "Recomendação: Energia Solar Híbrida - Alta eficiência para grandes indústrias.";
                        else {
                            tp_energia = "Recomendação: Biomassa - Alternativa sustentável para menor orçamento.";
                        }
                    } else {
                        tp_energia = "Recomendação: Energia Solar Residencial - Boa escolha para residências urbanas.";
                    }
                    break;

                default:
                    throw new IllegalArgumentException("Estado não reconhecido. Por favor, verifique o nome do estado.");
            }

        }catch (ArithmeticException e) {
            System.out.println("Erro de cálculo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro de conversão: o valor do orçamento deve ser numérico. " + e.getMessage());
        } catch (IllegalArgumentException e) {
             System.out.println("Erro de entrada: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        } finally {
            System.out.println("Recomendação de energia sustentável concluída.");
        }

        fonte.setTp_energia(tp_energia);

    }

}
