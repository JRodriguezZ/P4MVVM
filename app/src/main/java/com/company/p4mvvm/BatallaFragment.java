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

        peleaViewModel.resultadoCombate.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String ganador) {
                binding.resultado.setText(ganador + " es el ganador de la pelea!");
            }
        });
    }
}