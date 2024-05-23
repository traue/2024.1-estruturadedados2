package map.test;

import java.util.Map;
import java.util.HashMap;


public class MapTest {
	
	public static void main(String[] args) {
		
		//vide: https://docs.oracle.com/javase/8/docs/api/java/util/Map.html
		
		Map<Integer, String> map = new HashMap<>();
		
		map.put(1, "Estrutura de Dados I");
		map.put(2, "Estrutura de Dados II");
		map.put(3, "Modelagem Matemática");
		map.put(1, "Bla"); //substitui
		
		map.forEach((key, value) -> {
		    System.out.println("Key: " + key + "Value: " + value);
		});

	}
	
}
