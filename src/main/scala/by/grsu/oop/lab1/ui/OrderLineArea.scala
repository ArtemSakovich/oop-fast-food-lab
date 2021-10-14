package by.grsu.oop.lab1.ui

import java.awt.Color
import javax.swing.{BorderFactory, JLabel, JPanel}

class OrderLineArea(val numOfCustomersWaitToPlaceOrder: JLabel) extends JPanel{
    def this() = this(new JLabel{
        setBorder(BorderFactory.createLineBorder(Color.GREEN))
        setText("0")
    })
}
object OrderLineArea {
    def apply():OrderLineArea = new OrderLineArea{
        setName("Order Line Area")
        setBorder(BorderFactory.createLineBorder(Color.BLACK))
        add(numOfCustomersWaitToPlaceOrder)
        add(new JLabel("Num Of Customers Waiting To Place Order"))
    }
}
