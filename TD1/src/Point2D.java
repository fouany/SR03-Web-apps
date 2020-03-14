
public class Point2D {
	
	protected double abscisse;
	protected double ordonnee;
	
	public Point2D(double abscisse, double ordonnee) {
		this.abscisse = abscisse;
		this.ordonnee = ordonnee;
	}
	
	public static double calculerDistance(Point2D p1, Point2D p2) {
		double x = Math.pow(p1.getAbscisse() + p2.getAbscisse(), 2);
		double y = Math.pow(p1.getOrdonnee() + p2.getOrdonnee(), 2);
		
		return Math.sqrt(x + y);

		
	}
	

	public double getAbscisse() {
		return abscisse;
	}

	public void setAbscisse(double abscisse) {
		this.abscisse = abscisse;
	}

	public double getOrdonnee() {
		return ordonnee;
	}

	public void setOrdonnee(double ordonnee) {
		this.ordonnee = ordonnee;
	}
	
	@Override
	public String toString() {
		return "Point2D [abscisse=" + abscisse + ", ordonnee=" + ordonnee + "]";
	}

	
	
	
	
	

}
