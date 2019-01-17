package br.com.alura.leilao.ui.activity;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alura.leilao.api.retrofit.client.LeilaoWebClient;
import br.com.alura.leilao.api.retrofit.client.RespostaListener;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaLeilaoAdapter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class AtualizadorDeLeiloesTest {
    @Mock
    private ListaLeilaoAdapter adapter;

    @Mock
    private LeilaoWebClient client;

    @Mock
    private Context context;

    @Test
    public void deve_AtualizarListaDeLeiloes_QuandoBuscarLeiloesDaAPI() {
        AtualizadorDeLeiloes atualizador = new AtualizadorDeLeiloes();

        doAnswer(new Answer() { /*Simulando a chamada ao método*/
            @Override
            public Object answer(InvocationOnMock invocation) {
                RespostaListener<List<Leilao>> argument = invocation.getArgument(0);
                argument.sucesso(new ArrayList<>(Arrays.asList(
                        new Leilao("Computador"),
                        new Leilao("Carro")
                )));
                return null;
            }
        }).when(client).todos(any(RespostaListener.class));

        atualizador.buscaLeiloes(adapter, client, context);

        /*Verificando se os métodos que devem ser executados, são executados*/
        Mockito.verify(client).todos(Mockito.any(RespostaListener.class));
        Mockito.verify(adapter).atualiza(new ArrayList<>(Arrays.asList(
                new Leilao("Computador"),
                new Leilao("Carro")
        )));
    }

    @Test
    public void deve_ApresentarMensagemDeFalha_QuandoFalharBuscaDeLeiloesDaAPI() {
        AtualizadorDeLeiloes atualizador = Mockito.spy(new AtualizadorDeLeiloes());
        doNothing().when(atualizador).mostraMensagemDeFalha(context);

        doAnswer(new Answer() { /*Simulando a chamada ao método*/
            @Override
            public Object answer(InvocationOnMock invocation) {
                RespostaListener<List<Leilao>> argument = invocation.getArgument(0);
                argument.falha(anyString());
                return null;
            }
        }).when(client).todos(any(RespostaListener.class));

        atualizador.buscaLeiloes(adapter, client, context);

        Mockito.verify(atualizador).mostraMensagemDeFalha(context);
    }


}