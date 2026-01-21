import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient {

    public static void main(String argv[]) throws Exception {

        Socket clientSocket = new Socket("localhost", 6789);
        System.out.println("Cliente conectado ao servidor");

        BufferedReader ler = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream sai = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader entra = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        while (true) {
        	// recebimento de mensages do servidor
            String mensagem = entra.readLine();
            // caso o servidor feche
            if (mensagem == null) break;
            System.out.println(mensagem);
            // comfirmação
            if (mensagem.toLowerCase().contains("confirmar")) {
                String resp = ler.readLine();
                sai.writeBytes(resp + "\n");
            }
            // controle de turno do jogador
            if (mensagem.contains("Digite uma letra")) {
                String palpite = ler.readLine();
                sai.writeBytes(palpite + "\n");
            }
            // controle de nova rodada
            if (mensagem.contains("novo jogo?")) {
            	String palpite = ler.readLine();
                sai.writeBytes(palpite + "\n");
            }
        }

        clientSocket.close();
        System.out.println("Conexão encerrada.");
    }
}
