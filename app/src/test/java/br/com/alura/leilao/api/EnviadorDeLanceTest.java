package br.com.alura.leilao.api;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.alura.leilao.api.retrofit.client.LeilaoWebClient;
import br.com.alura.leilao.exception.LanceMenorQueUltimoLanceException;
import br.com.alura.leilao.exception.UsuarioJaDeuCincoLancesException;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.ui.dialog.AvisoDialogManager;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EnviadorDeLanceTest {
    @Mock
    private LeilaoWebClient cliente;
    @Mock
    private EnviadorDeLance.LanceProcessadoListener listener;
    @Mock
    private Context contex;
    @Mock
    private AvisoDialogManager manager;
    @Mock
    private Leilao leilao;

    @Test
    public void deve_MostrarMensagemDeFalha_QuandoLanceForMenorQueUltimoLance() {
        EnviadorDeLance enviadorDeLance = new EnviadorDeLance(
                cliente,
                listener,
                manager);

        doThrow(LanceMenorQueUltimoLanceException.class)
                .when(leilao).propoe(ArgumentMatchers.any(Lance.class));

        enviadorDeLance.envia(leilao, new Lance(new Usuario("Carolina"), 100.0));

        verify(manager).mostraAvisoLanceMenorQueUltimoLance();
    }

    @Test
    public void deve_MostrarMensagemDeFalha_QuandoUsuarioTentarDarMaisQueCincoLances() {
        EnviadorDeLance enviadorDeLance = new EnviadorDeLance(
                cliente,
                listener,
                manager);


        doThrow(UsuarioJaDeuCincoLancesException.class)
                .when(leilao).propoe(ArgumentMatchers.any(Lance.class));

        enviadorDeLance.envia(leilao, new Lance(new Usuario("Carolina"), 100.0));

        verify(manager).mostraAvisoUsuarioJaDeuCincoLances();
    }

}