package com.renanalencar.metaheuristics.many.taco_mofss;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author: renanalencar
 * @version: 1.0
 * @date: 2018-04-15
 */
public class LogExperiment {

     public static LogExperiment logExperiment;

     public static BufferedWriter f_log_stand_exper;
     public static BufferedWriter f_sols_aco;
     public static BufferedWriter f_day_aco_final_sols;

     //public static BufferedWriter teste;

     public static BufferedWriter f_real_sols;     // soluções reais dos dias para plotagem
     public static BufferedWriter f_longests;
     public static BufferedWriter f_total_costs;
     public static BufferedWriter f_simul_res;    // arquivo com o resumo das simulações
     public static BufferedWriter f_time_execs;


     // dados estatísticos do experimento
     public static BufferedWriter f_m_standard_deviation; // desvião padrão de cada simulação
     public static BufferedWriter f_m_max_cost;           // custo máximo de cada simulação

    public static BufferedWriter f_m_convergency;         // convergência do algoritmo

     public static BufferedWriter f_log_exper;
     static int i = 0;

     private LogExperiment() throws IOException {

     }

    /**
     *
     * @throws IOException
     */
     public void loadBuffersStandardExperiment() throws IOException {
         this.f_sols_aco        = new BufferedWriter(new FileWriter("outs/plot_best_sols_aco.txt"));
         this.f_log_stand_exper = new BufferedWriter(new FileWriter("outs/log_stand_experiment.txt"));

         //this.teste = new BufferedWriter(new FileWriter("outs/teste.txt"));
         this.f_m_standard_deviation    = new BufferedWriter(new FileWriter("outs/f_m_stand_sd.txt"));
         this.f_m_max_cost              = new BufferedWriter(new FileWriter("outs/f_m_stand_max_cost.txt"));
         this.f_m_convergency           = new BufferedWriter(new FileWriter("outs/f_m__stand_statistics.txt"));
     }

    /**
     *
     * @throws IOException
     */
     public void loaBuffersdRealExperiment() throws IOException{
         //this.teste = new BufferedWriter(new FileWriter("outs/teste.txt"));
         this.f_m_standard_deviation    = new BufferedWriter(new FileWriter("outs/f_m_real_sd.txt"));
         this.f_m_max_cost              = new BufferedWriter(new FileWriter("outs/f_m_real_max_cost.txt"));
         this.f_m_convergency           = new BufferedWriter(new FileWriter("outs/f_m_real_statistics.txt"));

         // arquivo para gravação das soluções reais para plotagem
         this.f_real_sols   = new BufferedWriter(new FileWriter("outs/plot_real_sols.txt"));

         this.f_longests    = new BufferedWriter(new FileWriter("outs/longests.txt"));

         this.f_total_costs = new BufferedWriter(new FileWriter("outs/total_costs.txt"));
         this.f_log_exper   = new BufferedWriter(new FileWriter("outs/log_real_experiment.txt"));
         this.f_simul_res   = new BufferedWriter(new FileWriter("outs/simulations_resume.txt"));

         this.f_time_execs  = new BufferedWriter(new FileWriter("outs/time_execs.txt"));

         this.f_day_aco_final_sols = new BufferedWriter(new FileWriter("outs/plot_final_created_sols_day.txt"));
     }

    /**
     *
     * @return
     * @throws IOException
     */
     public static synchronized LogExperiment getInstance() throws IOException {
            if (logExperiment == null)
                logExperiment = new LogExperiment();

            return logExperiment;
     }

    /**
     *
     * @param data
     * @throws IOException
     */
     public void writeF_LONGESTS(String data) throws IOException{
         this.f_longests.write(data);
     }

    /**
     *
     * @param data
     * @throws IOException
     */
     public void writeF_TOTAL_COSTS(String data) throws IOException{
         this.f_total_costs.write(data);
     }

    /**
     *
     * @param data
     * @throws IOException
     */
     public void writeF_REAL_SOLS(String data) throws IOException{
         this.f_real_sols.write(data);

     }

    /**
     *
     * @throws IOException
     */
//    public void flushFilesRealExperiment() throws IOException {
//        this.f_longests.flush();
//        this.f_real_sols.flush();
//        this.f_total_costs.flush();
//        this.f_log_exper.flush();
//        this.f_time_execs.flush();
//        this.f_simul_res.flush();
//        this.f_day_aco_final_sols.flush();
//
//        //this.teste.flush();
//        this.f_m_standard_deviation.flush();
//        this.f_m_max_cost.flush();
//
//    }

    /**
     *
     * @throws IOException
     */
    public void closeFilesRealExperiment() throws IOException {
        this.f_longests.close();
        this.f_real_sols.close();
        this.f_total_costs.close();
        this.f_log_exper.close();
        this.f_time_execs.close();
        this.f_simul_res.close();
        this.f_day_aco_final_sols.close();

        //this.teste.close();
        this.f_m_standard_deviation.close();
        this.f_m_max_cost.close();
        this.f_m_convergency.close();

    }

    /**
     *
     * @throws IOException
     */
    public void flushFilesStandardExperiment() throws IOException {
        this.f_sols_aco.flush();
        this.f_log_stand_exper.flush();

        //this.teste.flush();
        this.f_m_standard_deviation.flush();
        this.f_m_max_cost.flush();
        this.f_m_convergency.flush();

    }

    /**
     *
     * @throws IOException
     */
    public void closeFilesStandardExperiment() throws IOException {
        this.f_sols_aco.close();
        this.f_log_stand_exper.close();

        //this.teste.close();
        this.f_m_standard_deviation.close();
        this.f_m_max_cost.close();
        this.f_m_convergency.close();

    }
 
} // LogExperiment
