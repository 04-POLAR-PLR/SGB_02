public class Main {

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {

            TelaBiblioteca tela =
                    new TelaBiblioteca();

            tela.setVisible(true);

        });
    }
}