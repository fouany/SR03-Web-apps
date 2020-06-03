
public class Main {
	
	public static void main(String[] args) {
		
		Point2D p1 = new Point2D(0, 3.5);
		Point2D p2 = new Point2D(1, 4.5);
		Point2D p3 = new Point2D(2, 5.5);
		
		Point2D[] points = {p1, p2, p3};
		
		for(int i = 0; i < points.length; i++) {
			points[i].getAbscisse();
			points[i].setOrdonnee(0);
			System.out.println(points[i]);
			
		}
		
		System.out.println(Point2D.calculerDistance(points[1], points[2]));
		
		//------------------ en 3D
		
		Point3D p4 = new Point3D(0, 1);
		Point3D p5 = new Point3D(0, 2);
		Point3D p6 = new Point3D(0, 3);
		
		Point3D [] points3D = {p4, p5, p6};
		
		System.out.println(Point3D.calculerDistance(points3D[1], points3D[2]));

		

	}
	
	
	

}
