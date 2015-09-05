public class Planet {
    public double x;
    public double y;
    public double xVelocity;
    public double yVelocity;
    public double mass;
    public String img;
    public double xNetForce;
    public double yNetForce;
    public double xAccel;
    public double yAccel;
    private double radius;

    public Planet(double x, double y, double vx, double vy, double mass, String img, double radius) {
        this.x = x;
        this.y = y;
        this.xVelocity = vx;
        this.yVelocity = vy;
        this.mass = mass;
        this.img = img;
        this.radius = radius;
    }

    public double getMass() {
        return mass;
    }

    public double getRadius() {
        return radius;
    }

    public double calcDistance(Planet other) {
        return Math.sqrt(Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2));
    }

    public double calcPairwiseForce(Planet other) {
        double G = 6.67E-11;
        double rSquare = Math.pow(this.calcDistance(other), 2);
        return G * this.mass * other.mass / rSquare;
    }
    
    public double calcPairwiseForceX(Planet other) {
        double F = this.calcPairwiseForce(other);
        return F * (other.x - this.x) / this.calcDistance(other);
    }

    public double calcPairwiseForceY(Planet other) {
        double F = this.calcPairwiseForce(other);
        return F * (other.y - this.y) / this.calcDistance(other);
    }

    public void setNetForce(Planet[] planets) {
        this.xNetForce = 0;
        this.yNetForce = 0;
        for (Planet cur : planets) {
            if (cur.x != this.x || cur.y != this.y) {
                this.xNetForce += this.calcPairwiseForceX(cur);
                this.yNetForce += this.calcPairwiseForceY(cur);
            }
        }
    }

    public void update(double dt) {
        this.xAccel = xNetForce / this.mass;
        this.yAccel = yNetForce / this.mass;
        this.xVelocity += dt * this.xAccel;
        this.yVelocity += dt * this.yAccel;
        this.x += dt * this.xVelocity;
        this.y += dt * this.yVelocity;
    }

    public void draw() {
        StdDraw.picture(x, y, "images/" + img);
    }
}
