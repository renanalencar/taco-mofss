package com.renanalencar.metaheuristics.taco_modified;

/**
 * @author renanalencar
 * @version 1.0
 * @since 2017-11-01
 *
 */
public interface ControlExperiment {

    // Configurações gerais para os experimentos
    int MODE_EXECUTION         = 2;  // 1: experimento com instâncias modelo; 2: experimento com dados reais
    int TYPE_EUCLID_MATRIX     = 0;  // tipo de dados da matriz de distâncias euclidianas: (0)double, (1)int
    int DEPOT_INDEX            = 0;  // índice da instância que corresponde ao depósito
    int FLOAT_PRECISION        = 2;  // precisão decimal de impressão de pontos flutuantes

    // Configurações das variáveis e objetivos os experimento
    int N_VARIABLES            = 4;  // Número de variáveis
    int N_OBJECTIVES           = 2;  // Número de objetivos observáveis
    int OBJECTIVE_MODE         = 0;  // 0: mono-objetivo; 1: multi-objetivo
    int MINIMIZATION_TYPE      = 2;  // 1: minimizar rota mais longa (workload balance); 2: minimizar total da solução (soma das rotas)

    // Configurações de arquivos
    String DATA_FILE            ="data/many_data.txt";
    String COST_FILE            ="data/many_costs.txt";
    String ORDER_FILE           ="data/many_orders.txt";


    // Parâmetros para experimentos com dados reais:
    //TODO Verificar NullPointerException para valor < 1
    int STATIC_SIMULATION      = 1;  // simulação com instâncias estáticas com todos os serviços dos dias de trabalho
    int NEAREST_TEAM           = 0;  // não usa ACO: despacha o serviço que estiver mais próximo da equipe
    int N_SIMULATIONS_BY_DAY   = 1;  // número de execuções para cada dia de trabalho
    int TYPE_COST_MATRIX       = 6;  // 1:euclidiana, 2: distâncias reais, 3: tempos reais, 4:tempo + deslocamento, 5: pesos das entregas, 6: distância + peso (multi-objetivo)
    int DESPATCH_REAL_SOL      = 1;  // definindo o horário de despacho do serviço a partir do momento que uma equipe inicia o deslocamento para ele na solução real calculada
    int INDEX_DAY_TEST         = -1;  // index do dia de trabalho para testes. Se -1, todos os dias serão simulados

    // parametros de resultados opcionais a salvar:
    int SAVE_DAY_STATE_CHANGES      = 1;  // salva todas as mudanças de estado dos dias de trabalho no log do experimento
    int SAVE_PARTIAL_DAY_SOLS_PLOT  = 1;  // salva todas as soluções ACO geradas durante as simulações

    // Parâmetros de impressão:
    int PRINT_COSTS_MATRIX    = 0;  // imprimir as três matrizes logo após sua construção
    int PRINT_DAY_SERVICES    = 0;  // imprimir os serviços do dia de trabalho
    int PRINT_EMERGENCY_CARE  = 0;  // imprimir a melhora dos atendimentos de emergência


    // Parâmetros para experimentos com instâncias modelo:
    int N_EXECUTIONS         = 30;  // número de execuções independentes do algorimto ACO
    int TYPE_MTSP_SOLS       = 1;  // 1: finais fechados; 2: finais abertos (determina o tipo da solução alterando a posição inicial das formigas)
    int MODEL_INSTANCE       = 1;  // 1: eil51, 2: eil76, 3: eil101, 4: pr76, 5: pr1002, 6: sgb128, 0: grids para testes
    int N_SALESMEN           = 4;  // número de equipes do experimento
    int TEST                 = -1;  // para diminuir o número de nós da instância em testes. -1 = todos os nós

    // parâmetros de impressão:
    int PISO    = 0;  // imprimir todas as soluções melhoradas em cada execução
    int PDMT    = 0;  // imprimir a matriz de distâncias no início da execução
    int PASO    = 0;  // imprimir todas as melhores soluções de cada ciclo
    int PDCR    = 0;  // imprimir detalhes da construção das rotas
    int PART    = 0;  // imprimir todas as rotas geradas em todas as soluções

    int PRLS    = 0;  // imprimir melhoramento pelas buscas locais
    int PDNO    = 0;  // imprimir detalhes da busca local no cross
    int PDLS    = 0;  // imprimir detalhes das buscas locais 2 e 3-opt

    int PPOE    = 0;  // imprimir a matriz de feromônio no início e no final da execução
    int PPLU    = 0;  // imprimir a matriz de feromônio a cada atualização local
    int PPGU    = 0;  // imprimir a matriz de feromônio a cada atualização global

    int PRM     = 0;  // imprimir métricas

} // ControlExperiment
