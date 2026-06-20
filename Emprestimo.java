public class Emprestimo {
    private Usuario usuario;
    private Livro livro;

    public Emprestimo(Usuario usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    @Override
    public String toString() {
        return usuario.getNome() + " pegou " + livro.getTitulo();
    }
}
