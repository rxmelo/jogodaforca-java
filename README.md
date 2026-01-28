# jogodaforca-java
Jogo da forca em Java usando TCP com múltiplos jogadores


##  Integrantes da Equipe
- Ana Lúcia de Oliveira Nascimento.
- Maria Luiza Macêdo Nento.
- Matheus Martins Silva Santos.
- Rauane Xavier de Melo.
- Rodrigo Neves da Silva


## Objetivo

Este projeto tem por objetivo de desenvolvimento a obtenção da nota final da disciplina de __Redes de Computadores__, ministrada pelo professor __David Alain do Nascimento__ à turma do __terceiro ano integrado ao curso técnico em informática__ na Instituição __IFPE - Campus Garanhuns__. O trabalho tem como função implementar um jogo em rede multiplayer usando a linguagem de programação Java, explorando conceitos como comunicação por sockets TCP e multithreading, entre outros conhecimentos adquiridos durante o período de lecionamento das matérias referentes ao curso.


  ##  Descrição do Jogo

O jogo funciona no modelo cliente-servidor, onde um servidor central é responsável por controlar a lógica do jogo, enquanto três jogadores se conectam como clientes através da rede.
A cada partida, o servidor escolhe aleatoriamente uma palavra relacionada ao tema animações.  
Os jogadores se revezam em turnos, digitando uma letra por vez para tentar descobrir a palavra secreta.
Quando um jogador erra uma letra, uma penalidade da forca é aplicada, sendo compartilhada entre todos os jogadores.  
O jogo termina quando a palavra é completamente revelada ou quando o número máximo de erros é atingido.

## Dinâmica do jogo

- O jogo da forca é jogado por exatamente três jogadores, conectados simultaneamente ao servidor.
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

## Como instalar na máquina (Linux)

1. Na tela inicial deste perfil, basta clicar no botão "<> Code", depois em "Download ZIP".
2. Quando o download for concluído, deve-se abrir o app "arquivos" e buscar o arquivo instalado.
3. Quando encontrar o arquivo, clique com o botão direiro do mouse e depois em "extrair", salvando na pasta desejada.
4. Clique com o botão direito no arquivo extraído e selecione a opção "abrir com outra aplicação" е selecione a opção "Eclipse IDE".
5. Pronto! Seu jogo já foi instalado na sua máquina!


## Como instalar na máquina (windows)

1. Na tela inicial deste repositório/perfil, clique no botão “<> Code” e depois em “Download ZIP”.
2. Após a conclusão do download, abra o Explorador de Arquivos e localize o arquivo .zip baixado.
3. Clique com o botão direito do mouse sobre o arquivo e selecione “Extrair tudo”, escolhendo a pasta onde deseja salvar o projeto.
4. Abra o Eclipse IDE.
5. No menu superior, clique em File > Import.
6. Selecione General > Existing Projects into Workspace e clique em Next.
7. Em Select root directory, clique em Browse e escolha a pasta onde o projeto foi extraído.
8. Marque o projeto desejado e clique em Finish para concluir a importação.


## Como preparar a máquina para a partida começar

Com todos os arquivos no Eclipse IDE, deve-se:

1. Clicar 1 vez em "Run" (bolinha verde com triângulo na parte de cima do app) no arquivo "TCPServer.java".
2. Clicar 3 vezes em "Run" (bolinha verde com triângulo na parte de cima do app) no arquivo "TCPClient.java".
3. Pronto! Já pode jogar!
