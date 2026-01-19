import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TCPServer {

    static String penalidade(int consequencias, String palpite) {
    	//mensagem de erro
        if (consequencias == 1)
            return "Não possui a letra: " + palpite + " 0 Cabeça desenhada";
        if (consequencias == 2)
            return "Não possui a letra: " + palpite + " . Pescoço desenhado";
        if (consequencias == 3)
            return "Não possui a letra: " + palpite + " D Tronco desenhado";
        if (consequencias == 4)
            return "Não possui a letra: " + palpite + " // Perna esquerda";
        if (consequencias == 5)
            return "Não possui a letra: " + palpite + " \\  Perna direita";
        if (consequencias == 6)
            return "Não possui a letra: " + palpite + " // Braço esquerdo";
        return "estão livres da forca por enqunto";
    }

    public static void main(String[] args) throws Exception {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        ServerSocket serverSocket = new ServerSocket(6789);
        System.out.println("Servidor da Forca iniciado...");

        // esquema de controle dos jogadores
        Socket[] jogadores = new Socket[3];
        BufferedReader[] entradas = new BufferedReader[3];
        PrintWriter[] saidas = new PrintWriter[3];
       
        // conectando com os tres jogadores
        for (int i = 0; i < 3; i++) {

            System.out.println("[" + dtf.format(LocalDateTime.now()) + "] "
                    + "Aguardando conexão do jogador " + (i + 1));

            jogadores[i] = serverSocket.accept();

            entradas[i] = new BufferedReader(new InputStreamReader(jogadores[i].getInputStream()));

            saidas[i] = new PrintWriter(jogadores[i].getOutputStream(), true);

            System.out.println("Jogador " + (i + 1) + " conectado");

            saidas[i].println("Você é o jogador " + (i + 1));
        }

        // variaveis do jogo
        jogo jogo = new jogo();
        String palavra = jogo.palavra();
        int consequencias = 0;
        boolean fimDeJogo = false;
        int jogadorAtual = 0;

        // jogo iniciado
        for (PrintWriter out : saidas) {
            out.println("Bem vindo ao jogo da forca! o tema será animações!");
            //regra 1
            out.println(" Regra:1-> Cada jogador deve esperar a sua vez de chutar uma letra da palavra. Só pode erra o chute até 7 vezes");
            //regra 2
            out.println(" Regra:2-> Os jogadores terão as penalidades compartilhadas. "
            		+ "Para o jogo ser emocionante apenas o jogador que errou vai saber qual parte foi desnhada");
            //regra 3
            out.println(" Regra:3-> A palavra atual tem: "+ palavra.length()+" letras. Ganha quem acertar a ultima letra da palavra");
        }
        // loop de turnos
        while (!fimDeJogo) {
        	// inicialização dos meios de envio e entrada de dados: out-> sai in-> entra
            PrintWriter out = saidas[jogadorAtual];
            BufferedReader in = entradas[jogadorAtual];

            out.println("Sua vez! jogador: "+ (jogadorAtual+1) + " Digite uma letra:");
            String palpite = in.readLine();

            if (!jogo.verificar(palpite, palavra)) {
                consequencias++;
                
                out.println(penalidade(consequencias, palpite));
                
            } else {
            	// mensagem para dizer qual é o jogador do proximo turno
            	if(jogadorAtual == 2) out.println("Letra correta! Agora o jogador: "+(jogadorAtual-1) +" Vai responder");
            	else out.println("Letra correta! Agora o jogador: "+(jogadorAtual+2) +" Vai responder");
            	
            }

            String estado = jogo.palavra_pos_resposta(palavra);

            // Envia o estado da palavra para todos
            for (PrintWriter s : saidas) {
                s.println("Palavra: " + estado);
            }

            // Verificar se os jogadores erraram acima do limite
            if (consequencias >= 7) {
                for (PrintWriter s : saidas) {
                    s.println("Não possui a letra: " + palpite + " \\ Braço direito; perderam o jogo");
                }
                
                fimDeJogo = true;
                
            }
            // verifica se a palavra foi completa
            fimDeJogo = jogo.jogo_ganho(estado, palavra);
            
            if (fimDeJogo == true) {
                for (PrintWriter s : saidas) {
                    s.println("palavra completa! jogador: "+ (jogadorAtual+1) +" ganhou");
                }
                
                fimDeJogo = true;
            }

            jogadorAtual++;
            
            if (jogadorAtual > 2) jogadorAtual = 0;
            
        }
        // Fim do jogo
        for (int i = 0; i < 3; i++) {
            jogadores[i].close();
        }
        // fecha o servidor sem aparecer mensagem de erro na tela 
        serverSocket.close();
        System.out.println("Servidor encerrado.");
    }
}
