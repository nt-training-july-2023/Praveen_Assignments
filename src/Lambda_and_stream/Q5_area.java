package Lambda_and_stream;

interface shape{
	 float area(float v1,float v2);
}

public class Q5_area {
	 public static void main(String[] args) {
			
		  // Area of rectangle
		  shape rect =(l,b)->{
			return l*b;  
		  };
		  float rectArea = rect.area(3,7);
		  System.out.println("The area of rectangle - " + rectArea);
		  
		  // Area of square
		  shape squr = (a1,a2)->{
			  return a1*a2;  
		  };
		  float squareArea = squr.area(5,5);
		  System.out.println("The area of square is  - " + squareArea);
		  
		  //Area of circle
		  shape circ = (a3,a4)->{
			  return 3.14f*a3*a4;
		  };
		  float circleArea = circ.area(5, 10);
		  System.out.println("Area of  circle - " + circleArea);
		  
		  //Area of cylinder
		  shape cycl = (rad,height)->{
			  return 3.14f*rad*rad*height;
		  };
		  System.out.println("The area of cylinder is - " + cycl.area(5,8));
		  
		  // Area of sphere
		  shape spr = (rad1,rad2)->{
			  return 4*3.14f*rad1*rad2;
		  };
		  System.out.println("The Area of sphere - " + spr.area(9,9));
	}

}
