package br.edu.ifrs.projetoexemplomd.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifrs.projetoexemplomd.R;
import br.edu.ifrs.projetoexemplomd.dao.SettingsFirebase;
import br.edu.ifrs.projetoexemplomd.data.Database;
import br.edu.ifrs.projetoexemplomd.model.Pergunta;

import static androidx.databinding.DataBindingUtil.inflate;

public class HomeFragment extends Fragment { //implements View.OnClickListener{ //está dizendo eu sou um fragmento

    private HomeViewModel homeViewModel;
    private CardView card_quiz;
    private CardView card_dicas;
    private CardView card_telefones;
    private CardView card_mapa;
    private CardView card_ong;
    private NavController navController;

    //método que caracteriza o fragmento onCreateView
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflate(inflater, R.layout.fragment_home, container, false).getRoot();
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind(view);
        setClick();
    }

    private void bind(View view) {
        card_quiz = view.findViewById(R.id.card_one);
        card_dicas = view.findViewById(R.id.card_two);
        card_mapa = view.findViewById(R.id.card_three);
        card_telefones = view.findViewById(R.id.card_four);
        card_ong = view.findViewById(R.id.card_five);
    }

    private void setClick() {

        card_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsFirebase.getNo("perguntas").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<Pergunta> perguntas = new ArrayList<>();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            Pergunta pergunta = postSnapshot.getValue(Pergunta.class);
                            perguntas.add(pergunta);
                        }
                        Database.salvarPerguntas(perguntas);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        databaseError.getMessage();
                    }
                });
                navController.navigate(R.id.nav_onboard);
            }
        });
        card_dicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_listar_dicas);
            }
        });
        card_telefones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_phone);
            }
        });
        card_mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_maps);
            }
        });
        card_ong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_ong);
            }
        });
    }
}