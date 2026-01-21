import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TCPServer {

    static String penalidade(int consequencias, String palpite) {
        if (consequencias == 1) return "Não possui a letra: " + palpite + " 0 Cabeça desenhada";
        if (consequencias == 2) return "Não possui a letra: " + palpite + " . Pescoço desenhado";
        if (consequencias == 3) return "Não possui a letra: " + palpite + " D Tronco desenhado";
        if (consequencias == 4) return "Não possui a letra: " + palpite + " // Perna esquerda";
        if (consequencias == 5) return "Não possui a letra: " + palpite + " \\  Perna direita";
        if (consequencias == 6) return "Não possui a letra: " + palpite + " // Braço esquerdo";
        return "estão livres da forca por enquanto";
    }

    public static void main(String[] args) throws Exception {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        ServerSocket serverSocket = new ServerSocket(6789);
        System.out.println("Servidor da Forca iniciado...");

        Socket[] jogadores = new Socket[3];
        BufferedReader[] entradas = new BufferedReader[3];
        PrintWriter[] saidas = new PrintWriter[3];

        for (int i = 0; i < 3; i++) {
            System.out.println("[" + dtf.format(LocalDateTime.now()) + "] Aguardando jogador " + (i + 1));
            jogadores[i] = serverSocket.accept();
            entradas[i] = new BufferedReader(new InputStreamReader(jogadores[i].getInputStream()));
            saidas[i] = new PrintWriter(jogadores[i].getOutputStream(), true);
            saidas[i].println("Você é o jogador " + (i + 1));
        }

        int cont = 1;
        boolean encerrarServidor = false;

        // boas vindas e regras
        for (PrintWriter out : saidas) {
            out.println("Bem vindo ao jogo da forca! o tema será animações!");
            out.println("Regra 1 -> Cada jogador deve esperar a sua vez");
            out.println("Regra 2 -> Penalidades compartilhadas");
            out.println("Regra 3 -> Só o jogador que errou sabe qual foi a parte desnhada");
            out.println("Epere todos os jogadores comfirmarem sim");
        }
        
        // confirmação inicio
        boolean[] confirmou = new boolean[3];
        int confirmados = 0;
        
        while (confirmados < 3) {
            for (int i = 0; i < 3; i++) {
                if (!confirmou[i]) {
                    saidas[i].println("Digite 'sim' para confirmar o inicio do jogo; ou 'nao' caso não esteja pronto:");
                    String comf = entradas[i].readLine();
                    if (comf != null && comf.equalsIgnoreCase("sim")) {
                        confirmou[i] = true;
                        confirmados++;
                        saidas[i].println("Confirmado! Aguarde os outros jogadores...");
                    }
                }
            }
        }

        //loops de rodadas 
        while (!encerrarServidor) {

            // reset de rodadas
            jogo jogo = new jogo();
            String palavra = jogo.palavra();
            int consequencias = 0;
            boolean fimDeJogo = false;
            int jogadorAtual = 0;
            // aviso de novas rodadas
            if (cont > 1) {
                for (PrintWriter s : saidas) {
                    s.println("Nova rodada!");
                }
            }
            // envio da regra 3
            for (PrintWriter s : saidas) {
                s.println("Regra 4 -> A palavra tem: " + palavra.length() + " letras");
            }

            // loop de turnos
            while (!fimDeJogo) {

                PrintWriter out = saidas[jogadorAtual];
                BufferedReader in = entradas[jogadorAtual];

                out.println("Sua vez! jogador: " + (jogadorAtual + 1) + " Digite uma letra:");
                String palpite = in.readLine();

                if (!jogo.verificar(palpite, palavra)) {
                    consequencias++;
                    out.println(penalidade(consequencias, palpite));
                } else {
                    out.println("Letra correta!");
                }

                String estado = jogo.palavra_pos_resposta(palavra);
                for (PrintWriter s : saidas) {
                    s.println("Palavra: " + estado);
                }

                if (consequencias >= 7) {
                    for (PrintWriter s : saidas) {
                        s.println("Perderam o jogo!");
                    }
                    fimDeJogo = true; 
                }

                if (jogo.jogo_ganho(estado, palavra)) {
                    for (PrintWriter s : saidas) {
                        s.println("Palavra completa! Jogador " + (jogadorAtual + 1) + " ganhou!");
                    }
                    fimDeJogo = true;
                }

                jogadorAtual++;
                if (jogadorAtual > 2) jogadorAtual = 0;
            }

            // comfirmação de nova rodada
            confirmou = new boolean[3];
            confirmados = 0;
            boolean alguemDisseNao = false;

            while (confirmados < 3) {
                for (int i = 0; i < 3; i++) {
                    if (!confirmou[i]) {
                        saidas[i].println("Deseja iniciar um novo jogo? (sim/nao)[AVISO: caso digite não o servidor será encerrado]");
                        String r = entradas[i].readLine();

                        if (r != null && r.equalsIgnoreCase("sim")) {
                            confirmou[i] = true;
                            confirmados++;
                        } else {
                            alguemDisseNao = true;
                            break;
                        }
                    }
                }
                if (alguemDisseNao) break;
            }

            if (alguemDisseNao) {
                encerrarServidor = true;
            } else {
                cont++;
            }
        }

        for (Socket j : jogadores) j.close();
        serverSocket.close();
        System.out.println("Servidor encerrado.");
    }
}
