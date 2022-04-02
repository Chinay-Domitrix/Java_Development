package projects.juliaSet

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.awt.BorderLayout
import java.awt.BorderLayout.*
import java.awt.Color.HSBtoRGB
import java.awt.Graphics
import java.awt.GridLayout
import java.awt.event.AdjustmentEvent
import java.awt.event.AdjustmentListener
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_INT_RGB
import java.text.DecimalFormat
import javax.swing.*
import javax.swing.JFrame.EXIT_ON_CLOSE
import javax.swing.JScrollBar.HORIZONTAL

fun main() {
	class Julia(
		private var frameName: String = "Julia Set",
		private var frame: JFrame = JFrame(frameName),
		private var a: Double = 0.0,
		private var b: Double = 0.0,
		private var radius: Double = 0.0,
		private var multiplier: Double = 1.0,
		private var zoom: Double = 1.0,
		private var maxIter: Int = 100,
		private var hue: Float = 1f,
		private var saturation: Float = 1f,
		private var brightness: Float = 1f
	) : JPanel(), AdjustmentListener, MouseListener {
		private var aBar: JScrollBar = JScrollBar(HORIZONTAL)
		private var bBar: JScrollBar = JScrollBar(HORIZONTAL)
		private var radiusBar: JScrollBar = JScrollBar(HORIZONTAL)
		private var multiplierBar: JScrollBar = JScrollBar(HORIZONTAL)
		private var zoomBar: JScrollBar = JScrollBar(HORIZONTAL)
		private var maxIterBar: JScrollBar = JScrollBar(HORIZONTAL)
		private var hueBar: JScrollBar = JScrollBar(HORIZONTAL)
		private var saturationBar: JScrollBar = JScrollBar(HORIZONTAL)
		private var brightnessBar: JScrollBar = JScrollBar(HORIZONTAL)
		private var aLabel: JLabel
		private var bLabel: JLabel
		private var rLabel: JLabel
		private var mLabel: JLabel
		private var zLabel: JLabel
		private var maxIterLabel: JLabel
		private var hueLabel: JLabel
		private var saturationLabel: JLabel
		private var brightnessLabel: JLabel
		private var decForm = DecimalFormat("0.000")
		private lateinit var image: BufferedImage
		private var pixelSize = 1

		init {
			frame.apply {
				add(this@Julia)
				setSize(1000, 600)
				defaultCloseOperation = EXIT_ON_CLOSE
				isVisible = true
				fun instantiateScrollBar(value: Int = 0, min: Int = 0, max: Int = 100) =
					JScrollBar(HORIZONTAL, value, 0, min, max).apply {
						addAdjustmentListener(this@Julia)
						addMouseListener(object : MouseListener {
							override fun mouseClicked(e: MouseEvent) = Unit
							override fun mousePressed(e: MouseEvent) {
								pixelSize = 4
								repaint()
							}

							override fun mouseReleased(e: MouseEvent) {
								pixelSize = 1
								repaint()
							}

							override fun mouseEntered(e: MouseEvent) = Unit
							override fun mouseExited(e: MouseEvent) = Unit
						})
					}
				aBar = instantiateScrollBar(min = -2000, max = 2000)
				a = (aBar.value / 1000).toDouble()
				bBar = instantiateScrollBar(min = -2000, max = 2000)
				b = (bBar.value / 1000).toDouble()
				radiusBar = instantiateScrollBar(max = 100000, value = 1000)
				radius = radiusBar.value / 1000.0
				multiplierBar = instantiateScrollBar(max = 100000, value = 1000)
				multiplier = multiplierBar.value / 1000.0
				zoomBar = instantiateScrollBar(value = (zoom * 1000).toInt(), max = 2000)
				zoom = zoomBar.value / 1000.0
				maxIterBar = instantiateScrollBar(max = 300, value = maxIter)
				maxIter = maxIterBar.value
				hueBar = instantiateScrollBar(max = 1000, value = (hue * 1000).toInt())
				hue = hueBar.value / 1000f
				saturationBar = instantiateScrollBar(max = 1000, value = (saturation * 1000).toInt())
				saturation = saturationBar.value / 1000f
				brightnessBar = instantiateScrollBar(max = 1000, value = (brightness * 1000).toInt())
				brightness = brightnessBar.value / 1000f
				aLabel = JLabel("A: ${decForm.format(a)}")
				bLabel = JLabel("B: ${decForm.format(b)}")
				rLabel = JLabel("Radius: ${decForm.format(radius)}")
				mLabel = JLabel("Multiplier: ${decForm.format(multiplier)}")
				zLabel = JLabel("Zoom: ${decForm.format(zoom)}")
				maxIterLabel = JLabel("Max Iterations: $maxIter")
				hueLabel = JLabel("Hue: $hue")
				saturationLabel = JLabel("Saturation: $saturation")
				brightnessLabel = JLabel("Brightness: $brightness")
				add(JPanel().apply {
					layout = BorderLayout()
					fun initializeJPanel(vararg components: JComponent) = JPanel().apply {
						layout = GridLayout(9, 1)
						components.forEach(::add)
					}
					add(
						initializeJPanel(
							aLabel,
							bLabel,
							rLabel,
							mLabel,
							zLabel,
							maxIterLabel,
							hueLabel,
							saturationLabel,
							brightnessLabel
						), WEST
					)
					add(
						initializeJPanel(
							aBar,
							bBar,
							radiusBar,
							multiplierBar,
							zoomBar,
							maxIterBar,
							hueBar,
							saturationBar,
							brightnessBar
						), CENTER
					)
					add(JButton("Reset").apply {
						addActionListener {
							Thread {
								a = 0.0
								b = 0.0
								radius = 0.0
								aBar.value = 0
								bBar.value = 0
								radiusBar.value = 0
								multiplierBar.value = 1000
								zoomBar.value = 1000
								maxIterBar.value = 100
								hueBar.value = 1000
								saturationBar.value = 1000
								brightnessBar.value = 1000
								repaint()
							}.start()
						}
					}, EAST)
				}, SOUTH)
			}
		}

		public override fun paintComponent(g: Graphics) {
			super.paintComponent(g)
			fun drawJulia(): BufferedImage {
				val w = frame.width
				val h = frame.height
				image = BufferedImage(w, h, TYPE_INT_RGB)
				var x = 0
				runBlocking {
					launch {
						while (x < w) {
							var y = 0
//						Thread {
							while (y < h) {
								var i = maxIter.toFloat()
								var zx = (1.5 * (x - (w * .5))) / (.5 * w * zoom)
								var zy = (y - (h * 0.5)) / (0.5 * h * zoom)
								while ((((zx * zx) + (zy * zy)) < 6) && (i > 0)) {
									val temp = ((zx * zx) - (zy * zy)) + a
									zy = (multiplier * zx * zy) + b
									zx = temp
									i--
								}
								println("$x $y")
								image.setRGB(
									x, y, if (i > 0) HSBtoRGB(
										(hue * (maxIter / i)) % 1, saturation * 1f, brightness * 1f
									) else HSBtoRGB(hue * maxIter.toFloat(), saturation * 1f, brightness * 0f)
								)
								y += pixelSize
								if (y > h)
									y = h;
							}
//						}.start()
							x += pixelSize
						}
					}
				}
				return image
			}
			g.drawImage(drawJulia(), 0, 0, null)
		}

		override fun adjustmentValueChanged(e: AdjustmentEvent) {
			if (e.source === aBar) {
				a = aBar.value / 1000.0
				aLabel.text = "A: ${decForm.format(a)}"
			} else if (e.source === bBar) {
				b = bBar.value / 1000.0
				bLabel.text = "B: ${decForm.format(b)}"
			} else if (e.source === radiusBar) {
				radius = radiusBar.value / 1000.0
				rLabel.text = "Radius: $radius"
			} else if (e.source === multiplierBar) {
				multiplier = multiplierBar.value / 1000.0
				mLabel.text = "Multiplier: $multiplier"
			} else if (e.source === zoomBar) {
				zoom = zoomBar.value / 1000.0
				zLabel.text = "Zoom: ${decForm.format(zoom)}"
			} else if (e.source === maxIterBar) {
				maxIter = maxIterBar.value
				maxIterLabel.text = "Max Iterations: $maxIter"
			} else if (e.source === hueBar) {
				hue = hueBar.value / 1000f
				hueLabel.text = "Hue: $hue"
			} else if (e.source === saturationBar) {
				saturation = saturationBar.value / 1000f
				saturationLabel.text = "Saturation: $saturation"
			} else if (e.source === brightnessBar) {
				brightness = brightnessBar.value / 1000f
				brightnessLabel.text = "Brightness: $brightness"
			}
			repaint()
		}

		override fun mouseClicked(e: MouseEvent) {}
		override fun mouseEntered(e: MouseEvent) {}
		override fun mouseExited(e: MouseEvent) {}
		override fun mousePressed(e: MouseEvent) {
			pixelSize = 4
			repaint()
		}

		override fun mouseReleased(e: MouseEvent) {
			pixelSize = 1
			repaint()
		}
	}
	Julia()
}
