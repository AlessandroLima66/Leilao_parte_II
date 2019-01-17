package br.com.alura.leilao.ui.recyclerview.adapter;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.alura.leilao.model.Leilao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class ListaLeilaoAdapterTest {
    @Mock
    private Context contextMock; /* mock = Não executa nada do código fonte */
    //Context contextMock = Mockito.mock(Context.class);
    
    @Spy
    private ListaLeilaoAdapter adapter = (new ListaLeilaoAdapter(contextMock)); /* spy = Executa os métodos reais */

    @Test
    public void deve_AtualizarListaDeLeiloes_QuandoReceberListaDeLeiloes() {
        //MockitoAnnotations.initMocks(this);

        Mockito.doNothing().when(adapter).atualizaLista();

        adapter.atualiza(new ArrayList<>(Arrays.asList(
                new Leilao("Console"),
                new Leilao("Computador")
        )));

        int quantidadeDevolvida = adapter.getItemCount();

        Mockito.verify(adapter).atualizaLista();
        assertThat(quantidadeDevolvida, is(2));
    }
}