import java.awt.*;
import javax.swing.*;

public class TelaBiblioteca extends JFrame {

    private JTextField txtCodigo;
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtAno;
    private JLabel lblQuantidadeLivros;
    private JTextField txtBuscarLivro;

    private JTextArea areaLivros;

    private Biblioteca biblioteca;

    private Livro livroSelecionado;

    public TelaBiblioteca() {

        biblioteca = new Biblioteca();

        biblioteca.adicionarUsuario(
            new Usuario(1, "Joao"));

        biblioteca.adicionarUsuario(
            new Usuario(2, "Maria"));

        biblioteca.adicionarUsuario(
            new Usuario(3, "Pedro"));

        setTitle("Sistema Gerencial de Biblioteca");
        setSize(700,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(9,2,10,10));

        painel.add(new JLabel("Código"));
        txtCodigo = new JTextField();
        painel.add(txtCodigo);

        painel.add(new JLabel("Título"));
        txtTitulo = new JTextField();
        painel.add(txtTitulo);

        painel.add(new JLabel("Autor"));
        txtAutor = new JTextField();
        painel.add(txtAutor);

        painel.add(new JLabel("Ano"));
        txtAno = new JTextField();
        painel.add(txtAno);

        JButton btnCadastrar = new JButton("Cadastrar Livro");
        JButton btnListar = new JButton("Listar Livros");
        JButton btnLimpar = new JButton("Limpar Campos");
        JButton btnBuscar = new JButton("Buscar Livro");

        painel.add(btnCadastrar);
        painel.add(btnListar);
        painel.add(btnLimpar);
        painel.add(btnBuscar);

        painel.add(new JLabel("Buscar por Titulo"));
        txtBuscarLivro = new JTextField();
        painel.add(txtBuscarLivro);
        
        JButton btnRemover = new JButton("Remover Livro");
        painel.add(btnRemover);

        JButton btnEmprestar = new JButton("Emprestar Livro");
        painel.add(btnEmprestar);

        JButton btnDevolver = new JButton("Devolver Livro");
        painel.add(btnDevolver);

        JButton btnRelatorio = new JButton("Relatorio: ");
        painel.add(btnRelatorio);

        areaLivros = new JTextArea();
        areaLivros.setEditable(false);

        JScrollPane scroll = new JScrollPane(areaLivros);

        lblQuantidadeLivros = new JLabel("Quantidade de livros: 0");
        
        add(painel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(lblQuantidadeLivros, BorderLayout.SOUTH);

        btnCadastrar.addActionListener(e -> cadastrarLivro());

        btnListar.addActionListener(e -> listarLivros());

        btnLimpar.addActionListener(e -> limparCampos());

        btnBuscar.addActionListener(e -> buscarLivro());

        btnRemover.addActionListener(e -> removerLivro());

        btnEmprestar.addActionListener(e -> emprestarLivro());

        btnDevolver.addActionListener(e -> devolverLivro());

        btnRelatorio.addActionListener(e -> listarEmprestimos());
    }


    private void cadastrarLivro() {

        int codigo = Integer.parseInt(txtCodigo.getText());
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        int ano = Integer.parseInt(txtAno.getText());

        Livro livro = new Livro(
                codigo,
                titulo,
                autor,
                ano
        );

        biblioteca.adicionarLivro(livro);

        JOptionPane.showMessageDialog(
                this,
                "Livro cadastrado com sucesso!"
        );

        atualizarContador();

        limparCampos();
    }

    private void listarLivros() {

        areaLivros.setText("");

        for (Livro livro : biblioteca.getLivros()) {
            areaLivros.append(livro + "\n");
        }

    }

    private void atualizarContador() {
        lblQuantidadeLivros.setText(
            "Quantidade de livros: " + 
            biblioteca.getQuantidadeLivros());
    }

    private void limparCampos() {
        txtCodigo.setText("");
        txtTitulo.setText("");
        txtAutor.setText("");
        txtAno.setText("");
    }

    private void buscarLivro() {

        String titulo = txtBuscarLivro.getText();

        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Digite um título primeiro."
            );
            return;
        }

        Livro livro = biblioteca.buscarLivro(titulo);
        if (livro != null) {
            livroSelecionado = livro;
            areaLivros.setText("Livro encontrado:\n" + livro);

        } else {
            livroSelecionado = null;
            areaLivros.setText("Livro não encontrado.");
        }

    }

    private void removerLivro() {
        if (livroSelecionado.isEmprestado()) {

            JOptionPane.showMessageDialog(
                this,
                "Não é possível remover um livro emprestado."
            );
            return;
        }

        if (livroSelecionado != null) {
            biblioteca.removerLivro(livroSelecionado);
            atualizarContador();
            areaLivros.setText("Livro removido com sucesso.");
            livroSelecionado = null;
            txtBuscarLivro.setText("");
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Nenhum livro selecionado para remoção."
            );

        }

    }

    private void emprestarLivro() {

        if (livroSelecionado == null) {

            JOptionPane.showMessageDialog(
                this,
                "Busque um livro primeiro."
            );
            return;
        }

        if (livroSelecionado.isEmprestado()) {

            JOptionPane.showMessageDialog(
                this,
                "Livro já está emprestado."
            );
            return;
        }

        String nome = JOptionPane.showInputDialog(
            this,
            "Nome do usuário:"
        );

        Usuario usuario =
            biblioteca.buscarUsuario(nome);

        if (usuario == null) {

            JOptionPane.showMessageDialog(
                this,
                "Usuário não encontrado."
            );
            return;
        }

        biblioteca.realizarEmprestimo(
            usuario,
            livroSelecionado
        );

        JOptionPane.showMessageDialog(
            this,
            "Empréstimo realizado com sucesso."
        );
    }

    private void devolverLivro() {
        if (livroSelecionado == null) {
            
            JOptionPane.showMessageDialog(
                this,
                "Busque um livro primeiro."
            );
            return;
        }

        if (!livroSelecionado.isEmprestado()) {
            JOptionPane.showMessageDialog(
            this,
            "Livro não está emprestado"
            );
            return;
        }

        biblioteca.devolverLivro(livroSelecionado);

        JOptionPane.showMessageDialog(
            this,
            "Livro devolvido com sucesso."
        );
    }

    private void listarEmprestimos() {

        if (biblioteca.getEmprestimos().isEmpty()) {

            areaLivros.setText(
                "Nenhum empréstimo registrado."
            );

            return;
        }

        areaLivros.setText("");

        for (Emprestimo e : biblioteca.getEmprestimos()) {

            areaLivros.append(
                e + "\n"
            );
        }

    }
    
}