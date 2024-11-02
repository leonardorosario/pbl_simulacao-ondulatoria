package org.example;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.awt.*;

import java.math.BigDecimal;

public class Calculos {

    Onda obj_onda = new Onda();
    Simulacao obj_simulacao = new Simulacao();

    private double wavelength;
    private double epsilon;
    private double frequency;

    public Calculos(){
    }

    public Calculos(Onda onda, Simulacao simulacao) {
        this.obj_onda = onda;
        this.obj_simulacao = simulacao;
        this.wavelength = obj_onda.getComprimentoOnda();
        this.frequency = obj_onda.getFrequencia();
        this.epsilon = obj_simulacao.getErro_max();
    }

    void init_vars(){
        this.wavelength = obj_onda.getComprimentoOnda();
        this.epsilon = obj_simulacao.getErro_max();
        this.frequency = obj_onda.getFrequencia();
    }

    public void ping_vars(){
        init_vars();
        System.out.print(frequency + " " + wavelength);
        Main.Menu();
    }

    public void ping_ts(){
        double ping_ts = TaylorSeries.sin(5, 0.001);
        System.out.print(ping_ts);
        Main.Menu();
    }

    public void ping_angulo(double x, double t){
        init_vars();
        System.out.println(wavelength);
        System.out.println(frequency);
        x = 0.1;
        t = 1;
        double angle = 2 * Math.PI * ((frequency * t) - (x / wavelength));
        System.out.println(angle);
        double y = TaylorSeries.sin(angle, epsilon);
        System.out.print(y);

    }

    public void ping_y(){
        double x = 0;
        double t = 0;
        System.out.println("Frequency: " + frequency);
        System.out.println("Wavelength: " + wavelength);
        System.out.println("x: " + x);
        System.out.println("t: " + t);
        double y = calculateY(x, t);
        System.out.println(y);
        Main.Menu();
    }

    public double calculateY(double x, double t) {
        double y;
        init_vars();
        double angle = (2 * Math.PI * (frequency * t) - (x / wavelength));
        y = TaylorSeries.sin(angle, epsilon);

        return y;
    }

    public double arredondar(double x, double casas){
        double fator = Math.pow(10, casas);
        return Math.round(x * fator) / fator;
    }

    public double convert_m_cm(double x){
        x = x/100;
        return x;
    }

    public static class TaylorSeries {

        public static double sin(double x, double epsilon) {

            x = normalizeAngle(x); // Normaliza o ângulo entre -π e π
            double term = x;
            double sum = x;
            int n = 1;

            while (Math.abs(term) > epsilon) {
                term *= -x * x / ((2 * n) * (2 * n + 1));
                sum += term;
                n++;
            }
            return sum;
        }

        private static double normalizeAngle(double x) {
            // Implementação para normalizar o ângulo
            while (x > Math.PI) x -= 2 * Math.PI;
            while (x < -Math.PI) x += 2 * Math.PI;
            return x;
        }
    }

}
