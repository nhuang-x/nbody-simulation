

/**
 * Celestial Body class for NBody
 * @author ola
 *
 */
public class CelestialBody {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;

	/**
	 * Create a Body from parameters	
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */
	public CelestialBody(double xp, double yp, double xv,
			             double yv, double mass, String filename){
		this.myXPos = xp;
		this.myYPos = yp;
		this.myXVel = xv;
		this.myYVel = yv;
		this.myMass = mass;
		this.myFileName = filename;

	}

	/**
	 * Copy constructor: copy instance variables from one
	 * body to this body
	 * @param b used to initialize this body
	 */
	public CelestialBody(CelestialBody b){
		this.myXPos = b.myXPos;
		this.myYPos = b.myYPos;
		this.myXVel = b.myXVel;
		this.myYVel = b.myYVel;
		this.myMass = b.myMass;
		this.myFileName = b.myFileName;
	}

	/**
	 * Return x position of this Body.
	 * @return the x position of the CelestialBody instance
	 */
	public double getX() {
		return myXPos;
	}
	/**
	 * Return y position of this Body.
	 * @return the y position of the CelestialBody instance
	 */
	public double getY() {
		return myYPos;
	}
	/**
	 * Return x-velocity of this Body.
	 * @return value of x-velocity.
	 */
	public double getXVel() {
		return myXVel;
	}
	/**
	 * Return y-velocity of this Body.
	 * @return value of y-velocity.
	 */
	public double getYVel() {
		return myYVel;
	}
	/**
	 * Return mass of this Body.
	 * @return value of mass.
	 */
	public double getMass() {
		return myMass;
	}
	/**
	 * Return filename of this Body.
	 * @return name of file for Body animation
	 */
	public String getName() {
		return myFileName;
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(CelestialBody b) {
		return Math.sqrt((this.getX()-b.getX())*(this.getX()-b.getX())+(this.getY()-b.getY())*(this.getY()-b.getY()));
	}

	/**
	 * Calculates the force exerted on this CelestialBody by another CelestialBody b
	 * @param b the other body which exerts force on this CelestialBody
	 * @return the amount of force exerted on this CelestialBody by CelestialBody B
	 */
	public double calcForceExertedBy(CelestialBody b) {
		return  (6.67*1e-11*this.getMass()*b.getMass())/(Math.pow(calcDistance(b),2));
	}
	/**
	 * Calculates the x-component of the force exerted on this CelestialBody by another CelestialBody b
	 * @param b the other body which exerts force on this CelestialBody
	 * @return the x-component of the force exerted on this CelestialBody by CelestialBody b
	 */
	public double calcForceExertedByX(CelestialBody b) {
		return calcForceExertedBy(b)*(b.getX()-this.getX())/calcDistance(b);
	}
	/**
	 * Calculates the y-component of the force exerted on this CelestialBody by another CelestialBody b
	 * @param b the other body which exerts force on this CelestialBody
	 * @return the y-component of the force exerted on this CelestialBody by CelestialBody b
	 */
	public double calcForceExertedByY(CelestialBody b) {
		return calcForceExertedBy(b)*(b.getY()-this.getY())/calcDistance(b);
	}
	/**
	 * Calculates the x-component of the net force force exerted on this CelestialBody by all surrounding bodies
	 * @param bodies an array of all other CelestialBodies surrounding this CelestialBody
	 * @return x-component of the net force exerted on this CelestialBody by all surrounding bodies
	 */
	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		double sum = 0.0;
		for(CelestialBody b:bodies) {
			if(!b.equals(this)) {
				sum+=calcForceExertedByX(b);
			}
		}
		return sum;
	}
	/**
	 * Calculates the y-component of the net force force exerted on this CelestialBody by all surrounding bodies
	 * @param bodies an array of all other CelestialBodies surrounding this CelestialBody
	 * @return y-component of the net force exerted on this CelestialBody by all surrounding bodies
	 */
	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		double sum = 0.0;
		for(CelestialBody b:bodies) {
			if(!b.equals(this)) {
				sum+=calcForceExertedByY(b);
			}
		}
		return sum;
	}
	/**
	 * Updates the state of CelestialBody variables myXPos, myYPos, myXVel, myYVel
	 * @param deltaT a measure of the time passed
	 * @param xforce net force exerted on this CelestialBody in the x direction
	 * @param yforce net force exerted on this CelestialBody in the y direction
	 */
	public void update(double deltaT, 
			           double xforce, double yforce) {
		double accelX = xforce/getMass();
		double accelY = yforce/getMass();
		double nvx = myXVel + deltaT*accelX;
		double nvy = myYVel + deltaT*accelY;
		double nx = myXPos + deltaT*nvx;
		double ny = myYPos + deltaT*nvy;
		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;
	}

	/**
	 * Draws this planet's image at its current position
	 */
	public void draw() {
		StdDraw.picture(myXPos,myYPos,"images/"+myFileName);
	}
}
