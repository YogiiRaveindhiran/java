
public class ParallaxVeriticalSquareDemo extends JFrame implements ActionListener {
    
    private SquareLayer background, midground, foreground;
    private Timer timer;
    
    public ParallaxVeriticalSquareDemo() {
        super("Parallax Vertical Square Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        background = new SquareLayer(2.0, 3, 10,30, 0.3);
        midground = new SquareLayer(3.0, 3, 20, 0, 0.2);
        foreground = new SquareLayer(4.0, 3, 30, 0, 0.1);
        
        timer = new Timer(3, this);
        timer.start();
        
        getContentPane().setBackground(Color.BLACK);

        setSize(500, 400);
        setResizable(false);
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
        new ParallaxVeriticalSquareDemo();
    }
    
    private class SquareLayer {
        private Square[] squares;
        private int[] x, y;
        private double speed;
        private double rotationAngle;
        private double rotationSpeed;
        
        public SquareLayer(double speed, int numSquares, int side, double rotationAngle, double rotationSpeed) {
            this.speed = speed;
            this.squares = new Square[numSquares];
            this.x = new int[numSquares];
            this.y = new int[numSquares];
            for (int i = 0; i < numSquares; i++) {
                squares[i] = new Square(side);
                double rand = Math.random();
                x[i] = (int) (rand * (500));
                y[i] = (int) (rand * (400));
            }
            this.rotationAngle = rotationAngle;
            this.rotationSpeed = rotationSpeed;
        }
        
        public void move() {
            for (int i = 0; i < squares.length; i++) {
                y[i] += speed;
                if (y[i] > 400) {
                    y[i] = -150;
                }
            }
        }
        
        public void paint(Graphics g) {
            for (int i = 0; i < squares.length; i++) {
                this.rotationAngle += this.rotationSpeed;
                squares[i].draw(g, x[i], y[i], this.rotationAngle);
            }
        }
    }
    
    private class Square {
        private int side;
        private double rotationAngle;
        
        public Square(int side) {
            this.side = side;
        }

        
        public int getDiameter() {
            return side * 2;
        }
        
        public void draw(Graphics g, int x, int y, double rotationAngle) {

            Graphics2D g2d = (Graphics2D) g;
            if (this.side == 10) {
                g2d.setColor(Color.CYAN);
            } else if (this.side == 20) {
                g2d.setColor(Color.YELLOW);
            } else {
                g2d.setColor(Color.GRAY);
            }
            g2d.rotate(Math.toRadians(rotationAngle), x + side / 2, y + side / 2);
            g2d.fillRect(x, y, getDiameter(), getDiameter());
            g2d.rotate(Math.toRadians(-rotationAngle), x + side / 2, y + side / 2);
        }
    }
}
