import java.io.*;
import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Usuario> usuarios;
    private ArrayList<Livro> livros;
    private ArrayList<Revista> revistas;

    public Biblioteca() {
        usuarios = new ArrayList<>();
        livros = new ArrayList<>();
        revistas = new ArrayList<>();
        carregarUsuarios();
        carregarLivros();
        carregarRevistas();
    }

    public void adicionarUsuario(Usuario usuario) throws UsuarioJaCadastradoException {
        for (Usuario u : usuarios) {
            if (u.getNome().equals(usuario.getNome())) {
                throw new UsuarioJaCadastradoException("Usuário já cadastrado: " + usuario.getNome());
            }
        }
        usuarios.add(usuario);
    }
    
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public ArrayList<Revista> getRevistas() {
        return revistas;
    }

    public void adicionarUsuarios(Usuario usuario) {
        usuarios.add(usuario);
        salvarUsuarios();
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
        salvarLivros();
    }

    public void adicionarRevista(Revista revista) {
        revistas.add(revista);
        salvarRevistas();
    }

    public Usuario buscarUsuario(String nome) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equalsIgnoreCase(nome)) {
                return usuario;
            }
        }
        return null;
    }

    public Item buscarItem(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        for (Revista revista : revistas) {
            if (revista.getTitulo().equalsIgnoreCase(titulo)) {
                return revista;
            }
        }
        return null;
    }

    public void salvarUsuarios() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt"))) {
            for (Usuario usuario : usuarios) {
                writer.write(usuario.getNome());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os usuários: " + e.getMessage());
        }
    }

    public void carregarUsuarios() {
        try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                usuarios.add(new Usuario(linha));
            }
        } catch (IOException e) {
            System.out.println("Nenhum usuário encontrado.");
        }
    }

    public void salvarLivros() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("livros.txt"))) {
            for (Livro livro : livros) {
                writer.write(livro.getTitulo() + ";" + livro.getAutor() + ";" + livro.isDisponivel());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os livros: " + e.getMessage());
        }
    }

    public void carregarLivros() {
        try (BufferedReader reader = new BufferedReader(new FileReader("livros.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 3) {
                    Livro livro = new Livro(dados[0], dados[1]);
                    livro.setDisponivel(Boolean.parseBoolean(dados[2]));
                    livros.add(livro);
                }
            }
        } catch (IOException e) {
            System.out.println("Nenhum livro encontrado.");
        }
    }

    public void salvarRevistas() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("revistas.txt"))) {
            for (Revista revista : revistas) {
                writer.write(revista.getTitulo() + ";" + revista.getEditora() + ";" + revista.isDisponivel());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar as revistas: " + e.getMessage());
        }
    }

    public void carregarRevistas() {
        try (BufferedReader reader = new BufferedReader(new FileReader("revistas.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 3) {
                    Revista revista = new Revista(dados[0], dados[1]);
                    revista.setDisponivel(Boolean.parseBoolean(dados[2]));
                    revistas.add(revista);
                }
            }
        } catch (IOException e) {
            System.out.println("Nenhuma revista encontrada.");
        }
    }
}