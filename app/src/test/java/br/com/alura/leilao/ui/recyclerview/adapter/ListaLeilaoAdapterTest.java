package br.com.alura.leilao.ui.recyclerview.adapter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.alura.leilao.model.Leilao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ListaLeilaoAdapterTest {

    @Test
    public void deve_AtualizarListaDeLeiloes_QuandoREceberListaDeLeiloes() {
        ListaLeilaoAdapter adapter = new ListaLeilaoAdapter(null);

        adapter.atualiza(new ArrayList<Leilao>(Arrays.asList(
                new Leilao("Console"),
                new Leilao("Computador")
        )));

        int quantidadeDevolvida = adapter.getItemCount();

        assertThat(quantidadeDevolvida, is(2));
    }
}