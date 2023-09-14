
public class ParallaxVerticalBallDemo extends JFrame implements ActionListener {
    
    private BallLayer background, midground, foreground;
    private Timer timer;
    
    public ParallaxVerticalBallDemo() {
        super("Parallax Vertical Ball Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        background = new BallLayer(1.0, 5, 10);
        midground = new BallLayer(2.0, 5, 20);
        foreground = new BallLayer(3.0, 5, 30);
        
        timer = new Timer(50, this);
        timer.start();
        
        setSize(800, 600);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        background.move();
        midground.move();
        foreground.move();
        repaint();
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        background.paint(g);
        midground.paint(g);
        foreground.paint(g);
    }
    
    public static void main(String[] args) {
        new ParallaxVerticalBallDemo();
    }
    
    private class BallLayer {
        private Ball[] balls;
        private int[] x, y;
        private double speed;
        
        public BallLayer(double speed, int numBalls, int radius) {
            this.speed = speed;
            this.balls = new Ball[numBalls];
            this.x = new int[numBalls];
            this.y = new int[numBalls];
            for (int i = 0; i < numBalls; i++) {
                balls[i] = new Ball(radius);
                x[i] = (int) (Math.random() * (getWidth() + 200));
                y[i] = (int) (Math.random() * (getHeight() - 100) + 50);
            }
        }
        
        public void move() {
            for (int i = 0; i < balls.length; i++) {
                y[i] += speed;
                if (y[i] > getHeight()) {
                    y[i] = -100;
                }
            }
        }
        
        public void paint(Graphics g) {
            for (int i = 0; i < balls.length; i++) {
                balls[i].draw(g, x[i], y[i]);
            }
        }
    }
    
    private class Ball {
        private int radius;
        
        public Ball(int radius) {
            this.radius = radius;
        }
        
        public int getDiameter() {
            return radius * 2;
        }
        
        public void draw(Graphics g, int x, int y) {
            g.setColor(Color.RED);
            g.fillOval(x, y, getDiameter(), getDiameter());
        }
    }
}
