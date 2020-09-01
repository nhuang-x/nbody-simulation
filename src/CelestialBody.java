

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

	public double getX() {
		return myXPos;
	}
	public double getY() {
		return myYPos;
	}
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
	
	public double getMass() {
		return myMass;
	}
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

	public double calcForceExertedBy(CelestialBody b) {
		return  (6.67*1e-11*this.getMass()*b.getMass())/(Math.pow(calcDistance(b),2));
	}

	public double calcForceExertedByX(CelestialBody b) {
		return calcForceExertedBy(b)*(b.getX()-this.getX())/calcDistance(b);
	}
	public double calcForceExertedByY(CelestialBody b) {
		return calcForceExertedBy(b)*(b.getY()-this.getY())/calcDistance(b);
	}

	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		double sum = 0.0;
		for(CelestialBody b:bodies) {
			if(!b.equals(this)) {
				sum+=calcForceExertedByX(b);
			}
		}
		return sum;
	}

	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		double sum = 0.0;
		for(CelestialBody b:bodies) {
			if(!b.equals(this)) {
				sum+=calcForceExertedByY(b);
			}
		}
		return sum;
	}

	public void update(double deltaT, 
			           double xforce, double yforce) {
		// TODO: complete method
	}

	/**
	 * Draws this planet's image at its current position
	 */
	public void draw() {
		StdDraw.picture(myXPos,myYPos,"images/"+myFileName);
	}
}
