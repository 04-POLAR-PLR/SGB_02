import java.util.ArrayList;

public class Biblioteca {

    private ArrayList<Livro> livros;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Emprestimo> emprestimos;

    public Biblioteca() {
        livros = new ArrayList<>();
        usuarios = new ArrayList<>();
        emprestimos = new ArrayList<>();
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario buscarUsuario(String nome) {
        for (Usuario usuario : usuarios) {
            if (nome.equals(usuario.getNome())) {
                return usuario;
            }
        }
        return null;
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public int getQuantidadeLivros() {
            return livros.size();
    } 

    public Livro buscarLivro(String titulo){
        for (Livro livro : livros) {
            if (titulo.equals(livro.getTitulo())) {
                return livro;
            }
        }
        return null;
    }

    public void removerLivro(Livro livro) {
        livros.remove(livro);
    }

    public void realizarEmprestimo(Usuario usuario, Livro livro) {

        if (!livro.isEmprestado()) {

            Emprestimo emprestimo =
                    new Emprestimo(
                            usuario,
                            livro);

            emprestimos.add(emprestimo);

            livro.setEmprestado(true);
        }
    }

    public void devolverLivro(Livro livro) {
        
        Emprestimo remover = null;

        for (Emprestimo e : emprestimos) {
            if (e.getLivro() == livro) {

                remover = e;
                break;
            }
        }

        if (remover != null) {

            emprestimos.remove(remover);

            livro.setEmprestado(false);
        }

    }

}