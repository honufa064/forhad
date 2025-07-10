
public class HelloEnemy extends Enemy {
	public HelloEnemy(double x,double y,
            double vx,double vy) {
    super(x,y,vx,vy);
   life = 5+GameWorld.stage;
  }
  public void draw(MyFrame f) {
    f.setColor(255,0, 0);
    f.fillOval(x, y, 20, 20);


}

}
