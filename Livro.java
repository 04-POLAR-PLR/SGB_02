public class Livro {

    private int codigo;
    private String titulo;
    private String autor;
    private int ano;
    private boolean emprestado;

    public Livro(int codigo, String titulo, String autor, int ano) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.emprestado = false;
    }
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    @Override
    public String toString() {
        return codigo + " - " + titulo +
                " | " + autor +
                " | " + ano;
    }
}