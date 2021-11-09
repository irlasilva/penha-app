package br.edu.ifrs.projetoexemplomd.data;

import java.util.List;

import br.edu.ifrs.projetoexemplomd.model.Pergunta;

public final class Database {
    public static List<Pergunta> perguntas;
    private static int perguntaAtualIndex = 0;

    public static void salvarPerguntas(List<Pergunta> lista) {
        perguntas = lista;
    }

    public static List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public static Pergunta getProximaPergunta() {
        Pergunta proximaPergunta = perguntas.get(perguntaAtualIndex);
        perguntaAtualIndex++;
        if (perguntaAtualIndex > perguntas.size()) {
            perguntaAtualIndex = 0;
            proximaPergunta = perguntas.get(perguntaAtualIndex);
        }
        return proximaPergunta;
    }
}