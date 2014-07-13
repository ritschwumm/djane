package djane.gui.util

import java.awt.{ List=>AwtList, _ }
import javax.swing._

final class PaintedComponent(paintFunc:Graphics2D=>Unit) extends JComponent {
	// NOTE Further, if you do not invoker super's implementation you must honor the opaque property,
	// that is if this component is opaque, you must completely fill in the background in a non-opaque color. 
	// super.paintComponent(graphics);
	final override def paintComponent(graphics:Graphics) {
		paintFunc(graphics.asInstanceOf[Graphics2D])
	}
}
