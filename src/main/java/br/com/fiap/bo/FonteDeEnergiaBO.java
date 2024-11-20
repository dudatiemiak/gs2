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
                case "AC":
                case "AM":
                case "RR":
                case "RO":
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
                    if (objetivo.equalsIgnoreCase("Rural")) {
                        tp_energia = "Recomendação: Energia Eólica - Região com excelente potencial eólico.";
                    } else if (objetivo.equalsIgnoreCase("Industrial")) {
                        if (orcamento >= 150000) {
                            tp_energia = "Recomendação: Energia Solar Híbrida - Alta demanda e eficiência.";
                        } else if (consumo >= 1200 && consumo <= 1500 && orcamento >= 25000 && orcamento <= 50000){
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica - Melhor opção para para pequenas empresas no Ceará e na Bahia devido à alta incidência de luz solar ao longo do ano. Essa alternativa sustentável oferece um excelente custo-benefício, permitindo uma economia significativa na conta de energia elétrica. Além disso, sistemas solares são de fácil instalação, possuem baixa manutenção e proporcionam autonomia energética, o que é fundamental para pequenas empresas que desejam reduzir custos operacionais. Com um investimento inicial acessível, variando entre R$ 25.000 e R$ 50.000, é possível garantir um retorno financeiro em poucos anos, contribuindo também para a preservação ambiental e para a valorização da marca como um negócio sustentável.";
                        } else if (consumo >= 500000 && consumo <= 5000000 && orcamento >= 200000 && orcamento <= 500000) {
                            tp_energia = "Recomendação: Energia Solar Híbrida - Para grandes empresas localizadas na Bahia, Ceará e Rio Grande do Norte, a energia solar híbrida é a solução mais eficiente e sustentável. Essa tecnologia combina sistemas fotovoltaicos com armazenamento em baterias e, em alguns casos, integração com a rede elétrica. A energia solar híbrida é ideal para atender a grandes demandas energéticas, garantindo uma operação contínua e confiável, mesmo durante a noite ou em dias com baixa insolação.";
                        } else if (consumo >= 500000 && consumo <= 5000000 && orcamento > 700000 && orcamento <= 3000000) {
                            tp_energia = "Recomendação: Energia Eólica - É uma excelente alternativa devido ao potencial de ventos constantes nessas regiões. Grandes parques eólicos podem atender às altas demandas energéticas de empresas industriais e comerciais, oferecendo uma solução econômica no longo prazo, com baixos custos operacionais e uma significativa redução de emissões de carbono.";
                        } else if (consumo >= 1200 && consumo <= 1500 && orcamento >= 15000 && orcamento <= 25000){
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica - Alternativa econômica e eficiente para pequenas empresas com orçamento limitado. Em regiões como a Bahia, Ceará e Rio Grande do Norte, que possuem alta incidência solar, a energia solar pode gerar uma economia significativa na conta de luz. Mesmo com um orçamento menor, é possível instalar um sistema solar de pequeno porte que atenda a uma parte ou totalidade da demanda energética da empresa.";
                        } else if (consumo >= 500000 && consumo <= 5000000 && orcamento >= 120000 && orcamento < 200000) {
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica Híbrida (com Bateria) - A instalação de energia solar fotovoltaica híbrida (com sistemas de armazenamento de energia) pode ser uma alternativa. Com esse sistema, a empresa não depende tanto da rede elétrica, já que a energia excedente gerada durante o dia é armazenada em baterias para ser utilizada durante a noite ou em períodos de baixa geração.";
                        } else {
                            tp_energia = "O orçamento não é suficiente para instalar energia sustentável";
                        }
                    } else {
                        tp_energia = "Recomendação: Energia Solar Residencial - Ideal para residências aproveitando a alta insolação.";
                    }
                    break;

                case "Rio grande do norte":
                    if (objetivo.equalsIgnoreCase("Rural")) {
                        tp_energia = "Recomendação: Energia Eólica - Região com excelente potencial eólico.";
                    } else if (objetivo.equalsIgnoreCase("Industrial")) {
                        if (orcamento >= 150000) {
                            tp_energia = "Recomendação: Energia Solar Híbrida - Alta demanda e eficiência.";
                        } else if (consumo >= 1200 && consumo <= 1500 && orcamento >= 25000 && orcamento <= 50000){
                            tp_energia = "Recomendação: Energia Eólica - Melhor opção para para pequenas empresas devido ao alto potencial de ventos constantes na região. Essa alternativa sustentável se destaca por sua alta capacidade de geração, mesmo em pequenos sistemas, garantindo um fornecimento confiável e estável de energia. Embora o investimento inicial seja maior, o sistema eólico oferece benefícios de longo prazo, como a redução significativa dos custos com energia elétrica e a diminuição da pegada de carbono. Além disso, a energia eólica é ideal para empresas que desejam adotar soluções inovadoras e comprometidas com a sustentabilidade em uma das regiões mais favoráveis do Brasil para esse tipo de energia.";
                        } else if (consumo >= 500000 && consumo <= 5000000 && orcamento >= 5000000 && orcamento <= 7000000) {
                            tp_energia = "Recomendação: Energia Solar Híbrida - Para grandes empresas localizadas na Bahia, Ceará e Rio Grande do Norte, a energia solar híbrida é a solução mais eficiente e sustentável. Essa tecnologia combina sistemas fotovoltaicos com armazenamento em baterias e, em alguns casos, integração com a rede elétrica. A energia solar híbrida é ideal para atender a grandes demandas energéticas, garantindo uma operação contínua e confiável, mesmo durante a noite ou em dias com baixa insolação.";
                        } else if (consumo >= 500000 && consumo <= 5000000 && orcamento > 700000 && orcamento <= 3000000) {
                            tp_energia = "Recomendação: Energia Eólica - É uma excelente alternativa devido ao potencial de ventos constantes nessas regiões. Grandes parques eólicos podem atender às altas demandas energéticas de empresas industriais e comerciais, oferecendo uma solução econômica no longo prazo, com baixos custos operacionais e uma significativa redução de emissões de carbono.";
                        }  else if (consumo >= 1200 && consumo <= 1500 && orcamento >= 15000 && orcamento <= 25000){
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica - Alternativa econômica e eficiente para pequenas empresas com orçamento limitado. Em regiões como a Bahia, Ceará e Rio Grande do Norte, que possuem alta incidência solar, a energia solar pode gerar uma economia significativa na conta de luz. Mesmo com um orçamento menor, é possível instalar um sistema solar de pequeno porte que atenda a uma parte ou totalidade da demanda energética da empresa.";
                        }else if (consumo >= 500000 && consumo <= 5000000 && orcamento >= 120000 && orcamento < 200000) {
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica Híbrida (com Bateria) - A instalação de energia solar fotovoltaica híbrida (com sistemas de armazenamento de energia) pode ser uma alternativa. Com esse sistema, a empresa não depende tanto da rede elétrica, já que a energia excedente gerada durante o dia é armazenada em baterias para ser utilizada durante a noite ou em períodos de baixa geração.";
                        } else {
                            tp_energia = "O orçamento não é suficiente para instalar energia sustentável";
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
                        } else if (consumo >= 1200 && consumo <= 1500 && orcamento >= 50000 && orcamento <= 150000){
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica - É uma das melhores opções para pequenas indústrias em todo o Brasil, incluindo São Paulo, Minas Gerais e Paraná, devido à abundância de luz solar em várias partes dessas regiões. Algumas das vantagens são: redução significativa nas contas de energia elétrica, baixo custo operacional após a instalação, e retorno sobre o investimento em 3 a 5 anos. Além disso, a energia solar é altamente escalável.";
                        } else if (consumo >= 1200 && consumo <= 1500 && orcamento > 150000 && orcamento <= 200000){
                            tp_energia = "Recomendação: Energia Eólica de Pequena Escala - Em regiões com boa intensidade e constância de ventos, a energia eólica pode ser uma alternativa viável. Embora a energia eólica em larga escala seja mais comum, sistemas eólicos de pequena escala estão ganhando espaço. Algumas das vantagens são: aprovaitamento dos ventos para geração de energia, especialmente em áreas mais abertas e com boa incidência de vento.";
                        } else if (consumo >= 500000 && consumo <= 5000000 && orcamento >= 500000 && orcamento <= 5000000) {
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica - É uma excelente opção para grandes indústrias, especialmente em regiões como São Paulo, Minas Gerais e Paraná, onde há boa disponibilidade de luz solar durante todo o ano.";
                        } else if (consumo >= 500000 && consumo <= 5000000 && orcamento >= 1000000 && orcamento <= 10000000) {
                            tp_energia = "Recomendação: Biomassa - É uma excelente alternativa para indústrias que já possuem processos térmicos, como fábricas de papel, celulose, alimentos e bebidas. Indústrias localizadas em São Paulo, Minas Gerais e Paraná, com acesso a resíduos agrícolas (bagaço de cana, casca de arroz, resíduos de madeira, etc.), podem utilizar biomassa para gerar energia elétrica e térmica.";
                        }  else if (consumo >= 1200 && consumo <= 1500 && orcamento >= 15000 && orcamento < 50000){
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica de Pequeno Porte - Embora o orçamento seja limitado, a energia solar fotovoltaica ainda é uma das melhores opções, especialmente para pequenas indústrias, pois ela pode reduzir consideravelmente os custos com energia elétrica a longo prazo.";
                        }else if (consumo >= 500000 && consumo <= 5000000 && orcamento >= 200000 && orcamento < 500000) {
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica de Grande Porte - Uma das opções mais viáveis para grandes indústrias, mesmo com um orçamento de até R$ 500.000. Esse valor pode cobrir a instalação de um sistema fotovoltaico de grande porte, adequado para reduzir de forma significativa as contas de energia elétrica.";
                        } else {
                            tp_energia = "O orçamento não é suficiente para instalar energia sustentável";
                        }
                    } else {
                        tp_energia = "Recomendação: Energia Solar Residencial - Alta eficiência para áreas urbanas.";
                    }
                    break;

                case "Rio grande do sul":
                case "Santa catarina":
                    if (objetivo.equalsIgnoreCase("Rural")) {
                        tp_energia = "Recomendação: Biomassa - Utilização de resíduos florestais para geração de energia, ideal para regiões com grande produção de madeira.";
                    } else if (objetivo.equalsIgnoreCase("Industrial")) {
                        if (orcamento >= 200000) {
                            tp_energia = "Recomendação: Hidroenergia (PCH) - Uma opção eficiente para indústrias, aproveitando o grande potencial hidrelétrico da região.";
                        } else if (consumo >= 1200 && consumo <= 1500 && orcamento >= 50000 && orcamento <= 150000){
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica - A energia solar é uma excelente alternativa para pequenas indústrias devido ao alto índice de radiação solar na região. O investimento inicial é acessível e o retorno ocorre em pouco tempo.";
                        } else if (consumo >= 500000 && consumo <= 5000000 && orcamento >= 500000 && orcamento <= 5000000) {
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica - Grande opção para indústrias em regiões com alta incidência solar, garantindo uma grande economia nas contas de energia e uma operação mais sustentável.";
                        } else {
                            tp_energia = "O orçamento não é suficiente para instalar energia sustentável.";
                        }
                    } else {
                        tp_energia = "Recomendação: Energia Solar Residencial - Ideal para residências aproveitando o clima ameno e boa incidência solar.";
                    }
                    break;

                case "Mato grosso":
                case "Mato grosso do sul":
                    if (objetivo.equalsIgnoreCase("Rural")) {
                        tp_energia = "Recomendação: Energia Solar Fotovoltaica - Região com alta incidência solar, ideal para áreas rurais onde o acesso à energia elétrica é limitado.";
                    } else if (objetivo.equalsIgnoreCase("Industrial")) {
                        if (orcamento >= 200000) {
                            tp_energia = "Recomendação: Energia Solar Híbrida - Uma solução eficiente e sustentável para grandes indústrias, que combina energia solar com armazenamento em baterias, ideal para o clima quente e ensolarado do Mato Grosso e Mato Grosso do Sul.";
                        } else if (consumo >= 1200 && consumo <= 1500 && orcamento >= 25000 && orcamento <= 50000){
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica - A energia solar é uma excelente alternativa para pequenas empresas, especialmente em áreas como o Mato Grosso e Mato Grosso do Sul, onde o sol é abundante e o sistema de geração pode ser altamente econômico.";
                        } else if (consumo >= 500000 && consumo <= 5000000 && orcamento >= 500000 && orcamento <= 1000000) {
                            tp_energia = "Recomendação: Energia Eólica - Ideal para grandes empresas em áreas abertas, aproveitando os ventos constantes das regiões do Centro-Oeste para gerar energia.";
                        } else {
                            tp_energia = "O orçamento não é suficiente para instalar energia sustentável.";
                        }
                    } else {
                        tp_energia = "Recomendação: Energia Solar Residencial - Boa opção para residências com alto potencial de geração de energia solar.";
                    }
                    break;

                case "Goiás":
                    if (objetivo.equalsIgnoreCase("Rural")) {
                        tp_energia = "Recomendação: Energia Solar Fotovoltaica - A energia solar é uma excelente opção para áreas rurais, aproveitando a alta incidência de radiação solar.";
                    } else if (objetivo.equalsIgnoreCase("Industrial")) {
                        if (orcamento >= 150000) {
                            tp_energia = "Recomendação: Energia Solar Híbrida - Ideal para indústrias com alta demanda energética e que buscam uma solução sustentável e econômica.";
                        } else if (consumo >= 1200 && consumo <= 1500 && orcamento >= 25000 && orcamento <= 50000){
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica - A energia solar fotovoltaica é uma excelente escolha para empresas menores em Goiás, aproveitando a abundante luz solar da região.";
                        } else if (consumo >= 500000 && consumo <= 5000000 && orcamento >= 200000 && orcamento <= 1000000) {
                            tp_energia = "Recomendação: Energia Eólica - Embora Goiás tenha uma boa incidência solar, também possui um grande potencial para a geração de energia eólica em algumas áreas abertas e propensas a ventos constantes.";
                        } else {
                            tp_energia = "O orçamento não é suficiente para instalar energia sustentável.";
                        }
                    } else {
                        tp_energia = "Recomendação: Energia Solar Residencial - Uma ótima escolha para residências, aproveitando o forte potencial solar de Goiás.";
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
