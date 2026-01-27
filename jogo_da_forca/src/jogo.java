import java.util.ArrayList;
import java.util.Random;

public class jogo {

	 //arry para armezenar as respostas
	  ArrayList<String> resposta = new ArrayList<>();
	  
	  String palavra_da_rodada = "";
	 //metodo para gerar a palavra
	 public  String palavra () {
		 
		 String palavra_na_forca[] = {
				 
				    "princesinha sofia","elena de avalor",
				    "bob esponja", "hora de aventura", 
				    "o incrivel mundo de gumball", "peppa pig",
				    "pica pau", "tom e jerry",
				    "os simpsons", "futurama",
				    "rick and morty", "gravity falls",
				    "steven universo", "jovens titas",
				    "ben dez", "dragon ball",
				    "naruto", "pokemon",
				    "one piece", "toy story",
				    "a era do gelo", "procurando nemo"
				    
				};

		 Random index = new Random();
		 
		 return palavra_na_forca[index.nextInt(20)];
		 
	 }
	 // metodo para verificar se o palpite do participante está correto
	 public  boolean verificar (String palpite, String palavra) {
		 
		 boolean acerto = palavra.toLowerCase().indexOf(palpite) >= 0;
	     if (acerto) {
	    	 resposta.add(palpite);
	     }
		 return acerto;
		 
	 }
	 // metodo para montar a palavra mediante as respostas anteriores
	 public  String palavra_pos_resposta(String palavra) {
	
		 String concluido = "";
		 palavra_da_rodada = "";
		 char[] letras = palavra.toCharArray();

		 // estrutura para verificar e montar a palavra mediante respostas anteriores
		 for (char indentificacao: letras) {
			boolean possue = false;
			 for(int i = 0; i < resposta.size(); i++) {
				 //variaveis para identificar letras
				 char [] temp = resposta.get(i).toCharArray();
								  
				  if(temp[0] == indentificacao) {
						 
					  possue = true;
						 break;
						 
					 }

				 }
			 
			 if(possue) {
				 
				 concluido+= indentificacao;
				 palavra_da_rodada += indentificacao;
				 
			 }
			 
			 else if (indentificacao == ' ') {
				 
				 concluido+= "   ";
				 palavra_da_rodada += " ";
				 
			 }
			 
			 else {
				 
				 concluido += "_ ";
				 
			 }
				  
			 }
			 
		 return concluido;
	 
	 }
	 // verificar se ganhou
	 boolean jogo_ganho(String palavra) {
		 
		 if(palavra_da_rodada.equals(palavra)) {
			 
			 palavra_da_rodada = "";
			 return true;
		 }
		 
		 else return false;
		 
	 }
}
