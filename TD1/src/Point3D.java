
public class Point3D extends Point2D{
	
	protected double z;

	public Point3D(double abscisse, double ordonnee) {
		super(abscisse, ordonnee);
		this.z = z;
		// TODO Auto-generated constructor stub
	}
	
	public static double calculerDistance(Point3D p1, Point3D p2) {
		double x = Math.pow(p1.getAbscisse() + p2.getAbscisse(), 2);
		double y = Math.pow(p1.getOrdonnee() + p2.getOrdonnee(), 2);
		double z = Math.pow(p1.getZ() + p2.getZ(), 2);

		return Math.sqrt(x + y);	
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	@Override
	public String toString() {
		return "Point3D [z=" + z + ", abscisse=" + abscisse + ", ordonnee=" + ordonnee + "]";
	}


	
	
	
	

}
