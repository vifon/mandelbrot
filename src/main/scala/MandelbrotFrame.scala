
import java.awt.event.{InputEvent, KeyEvent, KeyListener}
import javax.swing.JFrame

class MandelbrotFrame(canvas_width: Int, canvas_height: Int, setChecker: ComplexSet) extends JFrame {
  setTitle("Bitmap")
  setSize(canvas_width, canvas_height)
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

  val mandelbrot = new MandelbrotCanvas(canvas_width, canvas_height, setChecker)
  add(mandelbrot)

  val keyboardListener = new KeyListener {
    override def keyTyped(e: KeyEvent) = ()
    override def keyReleased(e: KeyEvent) = ()
    override def keyPressed(e: KeyEvent) = {
      e.getModifiers() match {
        case InputEvent.SHIFT_MASK => {
          e.getKeyCode() match {
            case KeyEvent.VK_SEMICOLON => mandelbrot.precision( 1)
            case KeyEvent.VK_QUOTE     => mandelbrot.precision(-1)
            case _ => ()
          }
        }
        case _ => {
          e.getKeyCode() match {
            case KeyEvent.VK_UP    => mandelbrot.pan( 0.0 , 0.2)
            case KeyEvent.VK_LEFT  => mandelbrot.pan(-0.2 , 0.0)
            case KeyEvent.VK_RIGHT => mandelbrot.pan( 0.2 , 0.0)
            case KeyEvent.VK_DOWN  => mandelbrot.pan( 0.0 ,-0.2)
            case KeyEvent.VK_COMMA  => mandelbrot.magnify(2.0)
            case KeyEvent.VK_PERIOD => mandelbrot.magnify(0.5)
            case KeyEvent.VK_SEMICOLON  => mandelbrot.precision( 50)
            case KeyEvent.VK_QUOTE      => mandelbrot.precision(-50)
            case KeyEvent.VK_SLASH      => mandelbrot.nextMode()
            case KeyEvent.VK_Q => System.exit(0)
            case _ => ()
          }
        }
      }
    }
  }
  addKeyListener(keyboardListener)

  setVisible(true)
}
