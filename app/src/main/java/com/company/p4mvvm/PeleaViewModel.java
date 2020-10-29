package com.company.p4mvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PeleaViewModel extends AndroidViewModel {

    Executor executor;
    SimuladorPelea simulador;
    MutableLiveData<String> resultadoCombate = new MutableLiveData<>();
    MutableLiveData<Integer> errorVida = new MutableLiveData<>();
    MutableLiveData<Integer> errorFuerza = new MutableLiveData<>();

    public PeleaViewModel(@NonNull Application application) {
        super(application);
        executor = Executors.newSingleThreadExecutor();
        simulador = new SimuladorPelea();
    }

    public void pelear(int vidaJ1, int fuerzaJ1, int vidaJ2, int fuerzaJ2) {

        final SimuladorPelea.Batalla batalla = new SimuladorPelea.Batalla(vidaJ1, fuerzaJ1, vidaJ2, fuerzaJ2);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                simulador.pelear(batalla, new SimuladorPelea.Callback() {
                    @Override
                    public void resultadoCombate(String ganador) {
                        resultadoCombate.postValue(ganador);
                        errorVida.postValue(null);
                        errorFuerza.postValue(null);
                    }

                    @Override
                    public void cuandoHayaErrorDeVidaInferiorAlMinimo(int vidaMinima) {
                        errorVida.postValue(vidaMinima);
                    }

                    @Override
                    public void cuandoHayaErrorDeFuerzaInferiorAlMinimo(int fuerzaMinima) {
                        errorFuerza.postValue(fuerzaMinima);
                    }
                });
            }
        });
    }
}