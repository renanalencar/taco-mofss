package com.renanalencar.metaheuristics.singleObjective.taco;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Classe para realização do experimento com dados reais
 *
 * @author renanalencar
 * @version 1.0
 * @since 2017-11-01
 *
 */
public class RealExperiment implements ControlExperiment, ControlSTACS {
    private int time_ini_experiment; // instante de referẽncia do início do experimento
    private IntList list_id_work_days; // lista com os códigos dos dias de trabalho da base de dados
    private WorkDay current_work_day; // dia de trabalho atual
    private long seed_random; // semente dos randomicos do experimento
    private BufferedWriter f_log_exper; // salvamento em arquivo
    private BufferedWriter f_simul_res; // arquivo com o resumo das simulações
    private BufferedWriter f_time_execs;
    private LogExperiment logExperiment;
    private LogExperiment log;
    public RealExperiment() throws IOException {
    	 this.logExperiment = LogExperiment.getInstance();
         this.logExperiment.writeF_REAL_SOLS("\r\nDia de trabalho: ");
         //this.log.teste.write(++log.i + " aqui");
        // criando apenas estruturas, com tamanhos mínimos
        this.list_id_work_days = new IntList(1);
        this.current_work_day  = new WorkDay(-1);

        // abrindo arquivos de resultados
        //this.log.f_log_exper = new BufferedWriter(new FileWriter("outs/log_real_experiment.txt"));

        // arquivo com os custos gerados para cada dia de trabalho em cada simulação:
        //this.log.fss_simul_res = new BufferedWriter(new FileWriter("outs/simulations_resume.txt"));

        //fss_simul_res << setiosflags (ios::fixed) << setprecision(FLOAT_PRECISION);

        //this.log.f_time_execs = new BufferedWriter(new FileWriter("outs/time_execs.txt"));
        //f_time_execs << setiosflags (ios::fixed) << setprecision(FLOAT_PRECISION);
    }

    public void run_real_experiment() throws IOException {
        this.time_ini_experiment = (int) System.currentTimeMillis();  // instante do início do experimento
        RealData real_data = new RealData();
        int n_work_days = real_data.count_work_days();  // contando número de dias de trabalho na base de dados
        this.list_id_work_days = real_data.load_ids_work_days(n_work_days);  // criado a lista com as ids dos dias de trabalho
        //delete real_data;
        //log.teste.write("workday " + n_work_days);
        for (int current_day = 0; current_day < n_work_days; current_day++) {

            if (INDEX_DAY_TEST != -1){
                current_day = INDEX_DAY_TEST;
            }

            this.log.f_log_exper.write("\r\n------------------------------\r\nDia de Trabalho: " + this.list_id_work_days.value(current_day) + "\r\n");

            for (int counter_day_simulations = 1; counter_day_simulations <= N_SIMULATIONS_BY_DAY; counter_day_simulations++) {

                double time_ini_execution_day = (double) System.currentTimeMillis();  // instante do início do experimento

                this.log.f_log_exper.write("\r\n----------\r\nSimulação: " + counter_day_simulations + "\r\n");

                int current_id_work_day = this.list_id_work_days.value(current_day);
                //delete current_work_day;
                this.current_work_day = new WorkDay(current_id_work_day);

                this.current_work_day.load_data_work_day(counter_day_simulations);

                // a semente é alterada a cada execução de um dia de trabalho
                //seed_random = (long)time(NULL);  // gerando semente aleatória
                this.seed_random = System.currentTimeMillis();
                this.log.f_log_exper.write( "Semente rândomica utilizada: " + this.seed_random + "\r\n");
                this.save_aco_parameters();

                if (STATIC_SIMULATION == 1) {
                    this.current_work_day.execute_static_simulation(this.seed_random, counter_day_simulations, this.log.f_log_exper, this.log.f_simul_res);
                } else {
                    this.current_work_day.execute_simulation(this.seed_random, counter_day_simulations, this.log.f_log_exper, this.log.f_simul_res);
                }

                // gravando o tempo total da simulação:
                double time_simulation = System.currentTimeMillis() - time_ini_execution_day;
                this.log.f_log_exper.write("\r\nTempo total da simulação: " + (int)time_simulation + " milissegundos\r\n");
                this.log.f_simul_res.write((int)time_simulation + "\r\n");

                if (counter_day_simulations == 1) {
                    this.log.f_time_execs.write("\r\n" + current_id_work_day + "\t");
                }
                this.log.f_time_execs.write((int)time_simulation + "\t");
            }

            if (INDEX_DAY_TEST != -1) {
                current_day = n_work_days;
            }
        }
        LogExperiment log = LogExperiment.getInstance();
        
        double time_experiment = System.currentTimeMillis() - time_ini_experiment;
        this.log.f_log_exper.write("\r\n------------------------------\r\nTempo total do experimento: " + (int)time_experiment + " milissegundos\r\n");
    }

    public void save_aco_parameters() throws IOException {
        this.log.f_log_exper.write("Parametros ACO:\r\n");
        this.log.f_log_exper.write( "   Objetivo MTSP:\t");

        if (APP_OBJECTIVE == 1)
            this.log.f_log_exper.write("minimizar maior rota (workload balance)\r\n");
        if (APP_OBJECTIVE == 2)
            this.log.f_log_exper.write("minimizar custo total\r\n");
        this.log.f_log_exper.write("   Critério de parada:\t");
        if (NO_IMP_CYCLES > 0)
            this.log.f_log_exper.write(NO_IMP_CYCLES + " ciclos sem melhora da solução\r\n");
        if (MAX_TIME_EXEC > 0)
            this.log.f_log_exper.write(MAX_TIME_EXEC + " segundos por execução\r\n");
        if (MAX_CYCLES > 0)
            this.log.f_log_exper.write(MAX_CYCLES + " ciclos por execução\r\n");
        this.log.f_log_exper.write("   Tamanho da lista de candidatos:\t" + CL_LENGTH + "\r\n");

        this.log.f_log_exper.write("   N (soluções geradas a cada ciclo):\t" + N + "\r\n");
        this.log.f_log_exper.write("   q0 (nível de determinismo):\t\t" + String.format("%.2f", Q0) + "\r\n");
        this.log.f_log_exper.write("   alfa (peso do feromonio):\t\t" + ALFA + "\r\n");
        this.log.f_log_exper.write("   beta (peso da visibilidade):\t\t" + BETA + "\r\n");
        this.log.f_log_exper.write("   ksi (persitencia do feromonio nas atualizações locais):\t" + String.format("%.2f", KSI) + "\r\n");
        this.log.f_log_exper.write("   ro (persitencia do feromonio nas atualizações globais):\t" + String.format("%.2f", RO) + "\r\n");

        this.log.f_log_exper.write("\r\nBusca Local:\r\n");
        this.log.f_log_exper.write("   2-opt (todas as soluções criadas):\t\t");

        if (LS2O == 1)
            this.log.f_log_exper.write( "ON\r\n");
        else
            this.log.f_log_exper.write("OFF\r\n");
        this.log.f_log_exper.write("   3-opt (somente nas melhores soluções dos ciclos):\t\t");

        if (LS3O == 1)
            this.log.f_log_exper.write("ON\r\n");
        else
            this.log.f_log_exper.write("OFF\r\n");

    }
}
