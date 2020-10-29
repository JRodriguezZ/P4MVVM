package com.company.p4mvvm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.p4mvvm.databinding.FragmentBatallaBinding;

import org.jetbrains.annotations.Nullable;

public class BatallaFragment extends Fragment {

    private FragmentBatallaBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentBatallaBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final PeleaViewModel peleaViewModel = new ViewModelProvider(this).get(PeleaViewModel.class);

        binding.pelea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int vidaJ1 = Integer.parseInt(binding.vidaJ1.getText().toString());
                int fuerzaJ1 = Integer.parseInt(binding.fuerzaJ1.getText().toString());
                int vidaJ2 = Integer.parseInt(binding.vidaJ2.getText().toString());
                int fuerzaJ2 = Integer.parseInt(binding.fuerzaJ2.getText().toString());

                peleaViewModel.pelear(vidaJ1, fuerzaJ1, vidaJ2, fuerzaJ2);
            }
        });


        peleaViewModel.errorVida.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer vidaMinima) {
                if (vidaMinima != null) {
                    binding.vidaJ1.setError("La vida no puede ser inferor a " + vidaMinima);
                } else {
                    binding.vidaJ1.setError(null);
                }

                if (vidaMinima != null){
                    binding.vidaJ2.setError("La vida no puede ser inferor a " + vidaMinima);
                } else {
                    binding.vidaJ2.setError(null);
                }
            }
        });

        peleaViewModel.errorFuerza.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer fuerzaMinima) {
                if (fuerzaMinima != null) {
                    binding.fuerzaJ1.setError("La fuerza no puede ser inferior a " + fuerzaMinima);
                } else {
                    binding.fuerzaJ1.setError(null);
                }

                if (fuerzaMinima != null){
                    binding.fuerzaJ2.setError("La fuerza no puede ser inferior a " + fuerzaMinima);
                } else {
                    binding.fuerzaJ2.setError(null);
                }
            }
        });

        peleaViewModel.resultadoCombate.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String ganador) {
                binding.resultado.setText(ganador + " es el ganador de la pelea!");
            }
        });


    }
}