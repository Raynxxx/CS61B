
public class NBody {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("usgae: java NBody T dt filename");
            System.exit(0);
        }
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        In in = new In(filename);
        int numOfPlanets = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[numOfPlanets];
        for (int i = 0; i < numOfPlanets; ++i) {
            planets[i] = (Planet) NBody.getPlanet(in);
        }
        StdDraw.setScale(-radius, radius);
        double[] audio = StdAudio.read("audio/2001.mid");
        StdAudio.play(audio);
        for (int t = 0; t <= T; t += dt) {
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet cur : planets) {
                cur.setNetForce(planets);
                cur.update(dt);
                cur.draw();
            }
            StdDraw.show(10);
        }
        System.out.println(numOfPlanets);
        System.out.printf("%.2e\n", radius);
        for (Planet cur : planets) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", cur.x, cur.y, cur.xVelocity, cur.yVelocity, cur.mass, cur.img);
        }

    }

    public static Planet getPlanet(In in) {
        Planet p = new Planet(0, 0, 0, 0, 0, "");
        p.x = in.readDouble();
        p.y = in.readDouble();
        p.xVelocity = in.readDouble();
        p.yVelocity = in.readDouble();
        p.mass = in.readDouble();
        p.img = in.readString();
        return p;
    }
}
