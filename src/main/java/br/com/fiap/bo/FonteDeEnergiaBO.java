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
                case "AP":
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

                case "BA":
                case "CE":
                case "AL":
                    if (objetivo.equalsIgnoreCase("Rural")) {
                        tp_energia = "Recomendação: Energia Eólica - Região com excelente potencial eólico.";
                    } else if (objetivo.equalsIgnoreCase("Industrial")) {
                        if (orcamento >= 150000) {
                            tp_energia = "Recomendação: Energia Solar Híbrida - Alta demanda e eficiência.";
                        } else if (consumo >= 1200 && consumo <= 1500 && orcamento >= 25000 && orcamento <= 50000){
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica - Melhor opção para para pequenas empresas no Ceará e na Bahia devido à alta incidência de luz solar ao longo do ano. Essa alternativa sustentável oferece um excelente custo-benefício, permitindo uma economia significativa na conta de energia elétrica. Além disso, sistemas solares são de fácil instalação, possuem baixa manutenção e proporcionam autonomia energética, o que é fundamental para pequenas empresas que desejam reduzir custos operacionais. Com um investimento inicial acessível, variando entre R$ 25.000 e R$ 50.000, é possível garantir um retorno financeiro em poucos anos, contribuindo também para a preservação ambiental e para a valorização da marca como um negócio sustentável.";
                        } else if (consumo >= 500000 && consumo <= 5000000 && orcamento >= 200000 && orcamento <= 500000) {
                            tp_energia = "Recomendação: Energia Solar Híbrida - Para grandes empresas localizadas na Bahia, Ceará, Alagoas e Rio Grande do Norte, a energia solar híbrida é a solução mais eficiente e sustentável. Essa tecnologia combina sistemas fotovoltaicos com armazenamento em baterias e, em alguns casos, integração com a rede elétrica. A energia solar híbrida é ideal para atender a grandes demandas energéticas, garantindo uma operação contínua e confiável, mesmo durante a noite ou em dias com baixa insolação.";
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

                case "RN":
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

                case "SP":
                case "MG":
                case "PR":
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

                case "RS":
                case "SC":
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

                case "MT":
                case "MS":
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

                case "GO":
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

                case "RJ":
                case "ES":
                    if (objetivo.equalsIgnoreCase("Rural")) {
                        tp_energia = "Recomendação: Energia Solar - Excelente para áreas rurais devido à alta insolação.";
                    } else if (objetivo.equalsIgnoreCase("Industrial")) {
                        if (orcamento >= 150000) {
                            tp_energia = "Recomendação: Energia Solar Híbrida - Combina eficiência e sustentabilidade.";
                        } else if (consumo >= 1200 && consumo <= 1500 && orcamento >= 30000 && orcamento <= 70000) {
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica - Ideal para pequenas indústrias.";
                        } else if (consumo >= 500000 && consumo <= 5000000 && orcamento >= 300000 && orcamento <= 5000000) {
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica - Opção eficiente para grandes indústrias.";
                        } else {
                            tp_energia = "O orçamento não é suficiente para implementar uma solução sustentável.";
                        }
                    } else {
                        tp_energia = "Recomendação: Energia Solar Residencial - Boa opção para residências com orçamento moderado.";
                    }
                    break;

                case "PA":
                case "TO":
                case "MA":
                    if (objetivo.equalsIgnoreCase("Rural")) {
                        tp_energia = "Recomendação: Energia Solar Fotovoltaica - Melhor opção para regiões remotas com boa insolação.";
                    } else if (objetivo.equalsIgnoreCase("Industrial")) {
                        if (orcamento >= 150000) {
                            tp_energia = "Recomendação: Energia Solar Híbrida - Combina eficiência e sustentabilidade.";
                        } else if (consumo >= 1200 && consumo <= 1500 && orcamento >= 30000 && orcamento <= 70000) {
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica - Boa opção para pequenas indústrias.";
                        } else {
                            tp_energia = "O orçamento não é suficiente para implementar uma solução sustentável.";
                        }
                    } else {
                        tp_energia = "Recomendação: Energia Solar Residencial - Boa opção para residências com orçamento moderado.";
                    }
                    break;

                case "DF":
                    if (objetivo.equalsIgnoreCase("Rural")) {
                        if (orcamento >= 20000) {
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica - Ideal para propriedades rurais no DF, aproveitando a alta incidência solar e permitindo redução de custos a longo prazo.";
                        } else {
                            tp_energia = "Recomendação: Energia Solar Residencial de Pequeno Porte - Alternativa econômica para propriedades rurais com menor demanda energética e orçamento limitado.";
                        }
                    } else if (objetivo.equalsIgnoreCase("Industrial")) {
                        if (orcamento >= 100000) {
                            tp_energia = "Recomendação: Energia Solar Híbrida - Combinação de energia solar com armazenamento para indústrias de médio a grande porte, garantindo fornecimento constante e sustentável.";
                        } else if (consumo >= 1200 && consumo <= 1500 && orcamento >= 25000 && orcamento <= 50000) {
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica - Alternativa eficiente para pequenas empresas no Distrito Federal, especialmente em áreas urbanas com alta demanda e orçamento moderado.";
                        } else if (consumo >= 500000 && consumo <= 5000000 && orcamento >= 200000 && orcamento <= 500000) {
                            tp_energia = "Recomendação: Energia Solar Híbrida - Uma solução robusta e sustentável para indústrias com grande consumo energético.";
                        } else {
                            tp_energia = "O orçamento não é suficiente para implementar energia sustentável no contexto industrial.";
                        }
                    } else {
                        if (orcamento >= 15000) {
                            tp_energia = "Recomendação: Energia Solar Residencial - Excelente opção para residências no DF, aproveitando a alta insolação e gerando economia significativa na conta de energia elétrica.";
                        } else {
                            tp_energia = "Recomendação: Energia Solar de Pequeno Porte - Para residências com menor orçamento, ainda é possível instalar um sistema básico de energia solar.";
                        }
                    }
                    break;

                case "PB":
                    if (objetivo.equalsIgnoreCase("Rural")) {
                        if (orcamento >= 20000) {
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica Off-Grid - Ideal para áreas rurais remotas da Paraíba, sem acesso à rede elétrica, aproveitando a abundância de luz solar.";
                        } else {
                            tp_energia = "Recomendação: Energia Solar Residencial Básica - Para propriedades rurais com menor orçamento, um sistema básico pode atender demandas de iluminação e pequenos aparelhos.";
                        }
                    } else if (objetivo.equalsIgnoreCase("Industrial")) {
                        if (orcamento >= 80000) {
                            tp_energia = "Recomendação: Energia Solar Híbrida - Excelente para pequenas e médias indústrias urbanas ou rurais na Paraíba, garantindo eficiência energética e sustentabilidade.";
                        } else if (consumo >= 1000 && consumo <= 5000 && orcamento >= 30000 && orcamento <= 60000) {
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica Conectada à Rede - Ideal para pequenas indústrias urbanas com orçamento moderado.";
                        } else {
                            tp_energia = "O orçamento não é suficiente para implementar energia sustentável no contexto industrial.";
                        }
                    } else {
                        if (orcamento >= 15000) {
                            tp_energia = "Recomendação: Energia Solar Residencial - Ótima opção para residências urbanas ou rurais na Paraíba, aproveitando o alto índice de insolação para reduzir custos na conta de luz.";
                        } else {
                            tp_energia = "Recomendação: Energia Solar Residencial Compacta - Para residências com orçamento reduzido, é possível instalar um sistema compacto que atenda às necessidades básicas.";
                        }
                    }
                    break;

                case "PE":
                    if (objetivo.equalsIgnoreCase("Rural")) {
                        if (orcamento >= 25000) {
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica Off-Grid - Ideal para propriedades rurais no interior pernambucano, aproveitando a alta insolação e permitindo independência energética.";
                        } else {
                            tp_energia = "Recomendação: Energia Solar Residencial Básica - Um sistema compacto pode atender às demandas essenciais em áreas rurais, considerando o orçamento limitado.";
                        }
                    } else if (objetivo.equalsIgnoreCase("Industrial")) {
                        if (orcamento >= 100000) {
                            tp_energia = "Recomendação: Energia Solar Híbrida - Ideal para indústrias em polos econômicos como Suape, permitindo uso eficiente de energia solar e da rede elétrica.";
                        } else if (consumo >= 1500 && consumo <= 5000 && orcamento >= 40000) {
                            tp_energia = "Recomendação: Energia Solar Conectada à Rede - Excelente para indústrias de pequeno e médio porte, principalmente no litoral, reduzindo custos com alta eficiência.";
                        } else {
                            tp_energia = "O orçamento não é suficiente para implementar energia sustentável no setor industrial.";
                        }
                    } else if (objetivo.equalsIgnoreCase("Comercial")) {
                        if (orcamento >= 50000) {
                            tp_energia = "Recomendação: Energia Solar para Estabelecimentos Comerciais - Perfeito para hotéis, pousadas e comércios no litoral, aproveitando a alta densidade solar para atrair economia e sustentabilidade.";
                        } else {
                            tp_energia = "Recomendação: Energia Solar Compacta para Pequenos Negócios - Para comércios menores, é possível implementar sistemas compactos que atendam às demandas básicas.";
                        }
                    } else {
                        if (orcamento >= 20000) {
                            tp_energia = "Recomendação: Energia Solar Residencial - Uma solução ideal para residências urbanas ou litorâneas, considerando o potencial de redução nos custos com energia.";
                        } else {
                            tp_energia = "Recomendação: Energia Solar Residencial Básica - Um sistema simples pode ser instalado para reduzir o consumo de energia da rede, considerando o orçamento reduzido.";
                        }
                    }
                    break;

                case "PI":
                    if (objetivo.equalsIgnoreCase("Rural")) {
                        if (orcamento >= 20000) {
                            tp_energia = "Recomendação: Energia Solar Fotovoltaica Off-Grid - Excelente para áreas rurais isoladas, aproveitando a alta insolação para garantir energia autônoma.";
                        } else {
                            tp_energia = "Recomendação: Energia Solar Básica com Bateria - Ideal para atender necessidades básicas em áreas rurais com orçamento limitado.";
                        }
                    } else if (objetivo.equalsIgnoreCase("Industrial")) {
                        if (orcamento >= 80000) {
                            tp_energia = "Recomendação: Energia Solar Conectada à Rede - Ideal para indústrias em crescimento no estado, reduzindo custos operacionais e aumentando a eficiência.";
                        } else if (orcamento >= 50000 && consumo >= 1500) {
                            tp_energia = "Recomendação: Energia Solar Híbrida - Adequada para pequenas indústrias com consumo médio-alto, oferecendo maior confiabilidade energética.";
                        } else {
                            tp_energia = "O orçamento não é suficiente para implementar energia sustentável no setor industrial.";
                        }
                    } else if (objetivo.equalsIgnoreCase("Comercial")) {
                        if (orcamento >= 40000) {
                            tp_energia = "Recomendação: Energia Solar para Estabelecimentos Comerciais - Perfeito para lojas, hotéis e serviços no estado, aproveitando a economia gerada pela energia renovável.";
                        } else {
                            tp_energia = "Recomendação: Energia Solar Compacta para Pequenos Negócios - Um sistema acessível que pode atender comércios menores e reduzir custos.";
                        }
                    } else {
                        if (orcamento >= 15000) {
                            tp_energia = "Recomendação: Energia Solar Residencial - Uma solução ideal para famílias urbanas e rurais no Piauí, aproveitando o potencial solar e reduzindo custos de energia.";
                        } else {
                            tp_energia = "Recomendação: Energia Solar Residencial Básica - Sistemas compactos podem ser instalados para atender necessidades energéticas essenciais.";
                        }
                    }
                    break;

                case "SE":
                    if (objetivo.equalsIgnoreCase("Rural")) {
                        if (orcamento >= 18000) {
                            tp_energia = "Recomendação: Energia Solar Off-Grid - Perfeito para propriedades rurais no interior, permitindo autossuficiência energética em áreas remotas.";
                        } else {
                            tp_energia = "Recomendação: Energia Solar Compacta com Bateria - Ideal para pequenas propriedades rurais com necessidades energéticas básicas.";
                        }
                    } else if (objetivo.equalsIgnoreCase("Industrial")) {
                        if (orcamento >= 70000) {
                            tp_energia = "Recomendação: Energia Solar Conectada à Rede - Uma ótima opção para indústrias em Sergipe, especialmente em polos urbanos e áreas industriais.";
                        } else if (orcamento >= 45000 && consumo >= 1400) {
                            tp_energia = "Recomendação: Energia Solar Híbrida - Ideal para pequenas indústrias no estado, reduzindo custos e oferecendo maior confiabilidade.";
                        } else {
                            tp_energia = "O orçamento é insuficiente para implementar um sistema sustentável no setor industrial.";
                        }
                    } else if (objetivo.equalsIgnoreCase("Comercial")) {
                        if (orcamento >= 35000) {
                            tp_energia = "Recomendação: Energia Solar para Pequenos e Médios Negócios - Excelente para lojas e serviços que desejam economia e sustentabilidade.";
                        } else {
                            tp_energia = "Recomendação: Energia Solar Básica para Comércio - Ideal para pequenos negócios que desejam economizar e contribuir com o meio ambiente.";
                        }
                    } else {
                        if (orcamento >= 14000) {
                            tp_energia = "Recomendação: Energia Solar Residencial - Solução viável para famílias urbanas e rurais, aproveitando a alta incidência solar e reduzindo custos de energia.";
                        } else {
                            tp_energia = "Recomendação: Energia Solar Residencial Compacta - Um sistema simples para atender às necessidades básicas de consumo.";
                        }
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
