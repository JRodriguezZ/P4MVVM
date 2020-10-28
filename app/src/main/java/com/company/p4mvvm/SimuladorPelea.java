package com.company.p4mvvm;

public class SimuladorPelea {

    String ganador = null;

    public static class Batalla {
        public int vidaJ1;
        public int fuerzaJ1;
        public int vidaJ2;
        public int fuerzaJ2;


        public Batalla(int vidaJ1, int fuerzaJ1, int vidaJ2, int fuerzaJ2) {
            this.vidaJ1 = vidaJ1;
            this.fuerzaJ1 = fuerzaJ1;
            this.vidaJ2 = vidaJ2;
            this.fuerzaJ2 = fuerzaJ2;
        }
    }

    interface Callback {
        void resultadoCombate(String ganador);
        void cuandoHayaErrorDeVidaInferiorAlMinimo(int vidaMinima);
        void cuandoHayaErrorDeFuerzaInferiorAlMinimo(int fuerzaMinima);
    }

    public void pelear(Batalla batalla, Callback callback){
        int resultado = 0; //resultado -1 = J1 wins / resultado 1 = J2 wins
        double vidaMinima = 0;
        int fuerzaMinima = 0;

        try {
            Thread.sleep(10000);
            vidaMinima = 1;
            fuerzaMinima = 1;
        } catch (InterruptedException e) {}

        boolean error = false;

        if (batalla.vidaJ1 < vidaMinima) {
            callback.cuandoHayaErrorDeVidaInferiorAlMinimo((int) vidaMinima);
            error = true;
        }

        if (batalla.vidaJ2 < vidaMinima) {
            callback.cuandoHayaErrorDeVidaInferiorAlMinimo((int) vidaMinima);
            error = true;
        }

        if (batalla.fuerzaJ1 < fuerzaMinima) {
            callback.cuandoHayaErrorDeFuerzaInferiorAlMinimo(fuerzaMinima);
            error = true;
        }

        if (batalla.fuerzaJ2 < fuerzaMinima) {
            callback.cuandoHayaErrorDeFuerzaInferiorAlMinimo(fuerzaMinima);
            error = true;
        }

        if (!error){
            do {
                if (batalla.fuerzaJ1 > batalla.vidaJ2){
                    resultado = -1;
                } else if (batalla.fuerzaJ1 < batalla.vidaJ2){
                    batalla.vidaJ2 -= batalla.fuerzaJ1;
                }
                if (batalla.fuerzaJ2 > batalla.vidaJ1){
                    resultado = 1;
                } else if (batalla.fuerzaJ2 < batalla.vidaJ1){
                    batalla.vidaJ1 -= batalla.fuerzaJ2;
                }
            } while (resultado == 0);

            if (resultado == -1){
                ganador = "Jugador 1";
            } else if (resultado == 1){
                ganador = "Jugador 2";
            }

            callback.resultadoCombate(ganador);

        }

    }
}