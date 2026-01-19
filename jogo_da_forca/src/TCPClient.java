import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient {

	public static void main(String argv[]) throws Exception {
		
		// informações de porta e ip
		String serverAddress = "localhost";
		int serverPort = 6789;
		// criação do socket que irá conectar o cliente ao servidor
		Socket clientSocket = new Socket(serverAddress, serverPort);
		
		System.out.println(" Cliente conectado ao TCP server " + serverAddress+":"+serverPort);
		// bloco primordial para a comunicação e leitura de dados
		BufferedReader ler = new BufferedReader(new InputStreamReader(System.in)); // leitor do teclado do cliente
		DataOutputStream sai = new DataOutputStream(clientSocket.getOutputStream()); //output
		BufferedReader entra = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //input
		
        // primeira mensagem do servidor
        String mensagem_inicial = entra.readLine();
        System.out.println(mensagem_inicial);
        //regra 1
        System.out.println(entra.readLine());
        //regra 2
        System.out.println(entra.readLine());
        //regra 3
        System.out.println(entra.readLine());
        // loop do jogo
        while (true) {

            String mensagem = entra.readLine();
            // servidor encerrou
            if (mensagem == null) break;
            System.out.println(mensagem);
            // pedido do servidor
            if (mensagem.contains("Digite uma letra")) {
                String palpite = ler.readLine();
                sai.writeBytes(palpite + "\n");
            }
        }
	

        clientSocket.close();
        System.out.println("Conexão encerrada.");
    }
}
