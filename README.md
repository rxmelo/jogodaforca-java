# jogodaforca-java
Jogo da forca em Java usando TCP com múltiplos jogadores


##  Integrantes da Equipe
- Ana Lúcia de oliveira nascimento.
- Maria Luiza macêdo bento.
- Matheus Martins silva santos.
- Rauane Xavier de melo.
- Rodrigo Neves da silva

  ##  Descrição do Jogo

Este projeto consiste em um jogo da forca multiplayer, desenvolvido em java, utilizando Sockets TCP para permitir a comunicação entre servidor e clientes.
O jogo funciona no modelo cliente-servidor, onde um servidor central é responsável por controlar a lógica do jogo, enquanto três jogadores se conectam como clientes através da rede.
A cada partida, o servidor escolhe aleatoriamente uma palavra relacionada ao tema animações.  
Os jogadores se revezam em turnos, digitando uma letra por vez para tentar descobrir a palavra secreta.
Quando um jogador erra uma letra, uma penalidade da forca é aplicada, sendo compartilhada entre todos os jogadores.  
O jogo termina quando a palavra é completamente revelada ou quando o número máximo de erros é atingido.

## Instruções de Como Jogar

- O jogo da forca é jogado por três jogadores, conectados simultaneamente ao servidor.
- Antes de iniciar a partida, o servidor deve estar em execução.
- Cada jogador entra no jogo executando o programa cliente em um terminal separado.
- O servidor escolhe automaticamente uma palavra secreta relacionada ao tema animações.
- Os jogadores jogam em turnos, e apenas o jogador da vez pode enviar um palpite.
- Em cada turno, o jogador deve digitar uma única letra.
- Se a letra existir na palavra, ela será revelada para todos os jogadores.
- Se a letra não existir, uma penalidade da forca é aplicada.
- As penalidades são compartilhadas entre os jogadores.
- O jogo termina quando:
  - A palavra é completamente descoberta (vitória), ou
  - O número máximo de erros é atingido (derrota).
- Vence o jogador que acertar a última letra da palavra.

##  Instruções de Como Jogar

1. Inicie o servidor executando a classe TCPServer.
2. Em três terminais diferentes, execute a classe TCPClient para que os jogadores se conectem.
3. O servidor escolhe automaticamente uma palavra secreta relacionada ao tema animações.
4. Cada jogador deve aguardar a sua vez para jogar.
5. Quando for sua vez, o jogador deve digitar uma letra e pressionar Enter.
6. Se a letra existir na palavra, ela será revelada para todos os jogadores.
7. Caso a letra não exista, uma penalidade da forca será aplicada.
8. As penalidades são compartilhadas entre todos os jogadores.
9. O jogo continua até que:
   - A palavra seja completamente descoberta, ou
   - O número máximo de erros seja atingido.
10. O jogador que acertar a última letra da palavra é declarado vencedor.
