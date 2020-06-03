
public class Prog1 {
	
	static int[] tableau = {0,1,2,3,4,5,6,7,8,9};
	
	public static void main(String[] args) {
		exercice0(tableau);
		minAndMax(tableau);
		moyenne(tableau);
		ecartType(tableau);
	}
	
	
	public static void exercice0(int[] tab ) {
		System.out.println("Exercice 0 \n");
		for(int i = 0; i < tab.length; i++) {
			System.out.println(tab[i]);
		}
	}
	
	public static void minAndMax(int[] tab) {
		int min = tab[0];
		int max = tab[0];
		
		for(int i = 0; i < tab.length; i++) {
			if (tab[i] < min) min = tab[i];
			
			if (tab[i] > max) max = tab[i];
		}
		System.out.println("Minimum : " + min + "\nMaximum : " + max);
	}
	
	public static double moyenne(int[] tab ) {
		int somme = 0; 
		double moyenne = 0.0;
		for(int i = 0; i < tab.length; i++) {
			somme += tab[i];
		}
		moyenne =  somme / tab.length;
		System.out.println("\nMoyenne " + moyenne);
		return moyenne;
	}
	
	public static double moyenne(double[] tab ) {
		double somme = 0.0; 
		double moyenne = 0.0;
		for(int i = 0; i < tab.length; i++) {
			somme += tab[i];
		}
		moyenne =  somme / tab.length;
		System.out.println("\nMoyenne " + moyenne);
		return moyenne;
	}
	
	public static void ecartType(int[] tab) {
		double moyenne = moyenne(tab);
		double variance = 0;
		double ecartType = 0;
		double [] ecarts = new double [tab.length];
		
		for(int i = 0; i < tab.length; i++) {
			ecarts[i] = (moyenne - tab[i]) * (moyenne - tab[i]);
		}
		variance = moyenne(ecarts);
		ecartType = Math.sqrt(variance);
		
		System.out.println("Ecart Type : " + ecartType);
	}
	
	

}
