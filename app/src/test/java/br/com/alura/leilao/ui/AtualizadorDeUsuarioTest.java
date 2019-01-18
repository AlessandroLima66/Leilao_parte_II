package br.com.alura.leilao.ui;

import android.support.v7.widget.RecyclerView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.alura.leilao.database.dao.UsuarioDAO;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaUsuarioAdapter;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AtualizadorDeUsuarioTest {
    @Mock
    private UsuarioDAO dao;
    @Mock
    private ListaUsuarioAdapter adapter;
    @Mock
    private RecyclerView recyclerView;

    @Test
    public void deve_AtualizarListaDeUsuarios_QuandoSalvarUsuario(){
        AtualizadorDeUsuario atualizadorDeUsuario = new AtualizadorDeUsuario(
                dao,
                adapter,
                recyclerView);

        Usuario usuario = new Usuario("Alessandro");
        when(dao.salva(usuario)).thenReturn(new Usuario(1, "Alessandro"));
        when(adapter.getItemCount()).thenReturn(1);
        atualizadorDeUsuario.salva(usuario);

        verify(dao).salva(new Usuario("Alessandro"));
        verify(adapter).adiciona(new Usuario(1, "Alessandro"));
        verify(recyclerView).smoothScrollToPosition(0);
    }

}