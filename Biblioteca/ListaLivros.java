public class ListaLivros {
    public static void exibirLivrosRevistas(Biblioteca biblioteca) {
        System.out.println("\n===== LIVROS DISPONÍVEIS =====");
        for (Livro livro : biblioteca.getLivros()) {
            if (livro.isDisponivel()) {
                System.out.println(livro.getTitulo() + " - " + livro.getAutor());
            }
        }

        System.out.println("\n===== LIVROS INDISPONÍVEIS =====");
        for (Livro livro : biblioteca.getLivros()) {
            if (!livro.isDisponivel()) {
                System.out.println(livro.getTitulo() + " - " + livro.getAutor());
            }
        }

        System.out.println("\n===== REVISTAS DISPONÍVEIS =====");
        for (Revista revista : biblioteca.getRevistas()) {
            if (revista.isDisponivel()) {
                System.out.println(revista.getTitulo() + " - " + revista.getEditora());
            }
        }

        System.out.println("\n===== REVISTAS INDISPONÍVEIS =====");
        for (Revista revista : biblioteca.getRevistas()) {
            if (!revista.isDisponivel()) {
                System.out.println(revista.getTitulo() + " - " + revista.getEditora());
            }
        }
    }
}
