import java.util.ArrayList;
import java.util.Random;

public class jogo {

	 //arry para armezenar as respostas
	  ArrayList<String> resposta = new ArrayList<>();
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
		 
		 int acerto = 0;
		 
		 if(palavra.toLowerCase().indexOf(palpite) >= 0) {
			 
			 acerto ++;
			 resposta.add(palpite);
		 }
		
		 if(acerto > 0) return true;
		 
		 else return false;
		 
	 }
	 // metodo para montar a palavra mediante as respostasanteriores
	 public  String palavra_pos_resposta(String palavra) {
		 //veriaveis para a construção da palavra
		 String concluido = "";
		 char[] letras = palavra.toCharArray();
		 boolean verificar = false;
		 // estrutura para verificar e montar a palavra mediante respostas anteriores
		 for (char indentificacao: letras) {
			
			 for(int i = 0; i < resposta.size(); i++) {
				 //variaveis para identificar letras
				 char [] temp = resposta.get(i).toCharArray();
				  verificar = false;
				  
				 if (temp[0] == indentificacao) {
					 
					 verificar = true;
					 break;
					 
				 }
				 
			 }
			 
			 if(verificar == true) {
				 
				 concluido += indentificacao;
				 
			 }
			 
			 else concluido += " ";
		 }
	
		 return concluido;
	 
	 }
	 // verificar se ganhou
	 boolean jogo_ganho(String estado, String palavra) {
		 
		 if(estado.equals(palavra)) return true;
		 else return false;
		 
	 }
}