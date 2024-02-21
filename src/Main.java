import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Usuario {
    private String nome;
    private String email;
    private String estado;

    public Usuario(String nome, String email, String estado) {
        this.nome = nome;
        this.email = email;
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getEstado() {
        return estado;
    }
}

class Evento {
    private String nome;
    private int vagasDisponiveis;
    private String estado;

    public Evento(String nome, int vagasDisponiveis, String estado) {
        this.nome = nome;
        this.vagasDisponiveis = vagasDisponiveis;
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public int getVagasDisponiveis() {
        return vagasDisponiveis;
    }

    public String getEstado() {
        return estado;
    }

    public void decrementarVagas() {
        vagasDisponiveis--;
    }
}

class GerenciadorUsuarios {
    private List<Usuario> usuarios;

    public GerenciadorUsuarios() {
        usuarios = new ArrayList<>();
    }

    public void cadastrarUsuario(String nome, String email, String estado) {
        Usuario novoUsuario = new Usuario(nome, email, estado);
        usuarios.add(novoUsuario);
        System.out.println("Usuário cadastrado com sucesso!");

        // Recomendar evento
        recomendarEvento(novoUsuario);
    }

    private void recomendarEvento(Usuario usuario) {
        for (Evento evento : GerenciadorEventos.eventosPreexistentes) {
            if (evento.getEstado().equalsIgnoreCase(usuario.getEstado())) {
                System.out.println("Olá " + usuario.getNome() + "! Recomendamos o evento \"" + evento.getNome() + "\" no seu estado.");
                return;
            }else {
                System.out.println("Olá " + usuario.getNome() + "! Não encontramos nenhum evento recomendado para o seu estado.");
                return;
            }
        }
    }
}

class GerenciadorEventos {
    static List<Evento> eventosPreexistentes = new ArrayList<>();

    public void adicionarEventoPreExistente(Evento evento) {
        eventosPreexistentes.add(evento);
    }
}

public class Main {
    private static final GerenciadorUsuarios gerenciadorUsuarios = new GerenciadorUsuarios();
    private static final GerenciadorEventos gerenciadorEventos = new GerenciadorEventos();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Adicionar eventos pré-existentes
        Evento evento1 = new Evento("Evento A", 50, "RN");
        Evento evento2 = new Evento("Evento B", 30, "Rio grande do Norte");
        gerenciadorEventos.adicionarEventoPreExistente(evento1);
        gerenciadorEventos.adicionarEventoPreExistente(evento2);

        // Cadastro de usuário
        System.out.println("Bem-vindo ao sistema de cadastro de usuários!");
        System.out.println("Por favor, cadastre-se para continuar.");
        System.out.println("Digite seu nome:");
        String nomeUsuario = scanner.nextLine();
        System.out.println("Digite seu email:");
        String emailUsuario = scanner.nextLine();
        System.out.println("Digite o estado onde você está:");
        String estadoUsuario = scanner.nextLine();
        gerenciadorUsuarios.cadastrarUsuario(nomeUsuario, emailUsuario, estadoUsuario);
    }
}
