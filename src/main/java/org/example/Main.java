package org.example;
import java.util.Scanner;

public class Main {
    static double freq, comp, t, pow_err_m;

    public static void main(String[] args) {
        Menu();
    }

    static void Menu(){

        Onda obj_onda = new Onda(freq, comp);
        Simulacao obj_simulacao = new Simulacao(t, pow_err_m);
        Calculos obj_calculos = new Calculos(obj_onda, obj_simulacao);

        Scanner scn = new Scanner(System.in);

        System.out.print("""
                Selecione a opção desejada:
                1 - Registrar nova onda
                2 - Mostrar informações da onda
                3 - Realizar simulação - nw
                4 - ping calc vars
                5 - ping taylor
                6 - ping y
                7 - ping angulo
                10 - onda
                0 - Sair
                """);
        int opcao = scn.nextInt();

        switch (opcao){
            case 1 -> Registro();
            case 2 -> obj_onda.exibirInformacoes();
            case 3 -> obj_simulacao.realizarSimulacao();
            case 4 -> obj_calculos.ping_vars();
            case 5 -> obj_calculos.ping_ts();
            case 6 -> obj_calculos.ping_y();
            case 7 -> obj_calculos.ping_angulo(0.1, 1);
            case 10 -> ping_onda();
            case 0 -> System.exit(0);
            default -> {
                System.out.print("Opção inválida.");
                Menu();
            }
        }
    }

    static void ping_onda(){
        freq = 1;
        comp = 0.3;
        t = 6;
        pow_err_m = 3;
        Menu();
    }

    static void Registro(){
        Scanner scn = new Scanner(System.in);

        System.out.print("Digite a frequência da onda: ");
        freq = scn.nextDouble();

        System.out.print("Digite o comprimento da onda: ");
        comp = scn.nextDouble();

        System.out.print("Digite o tempo da simulação: ");
        t = scn.nextDouble();

        System.out.print("Digite o erro máximo tolerável: ");
        pow_err_m = scn.nextDouble();

        Menu();
    }
}