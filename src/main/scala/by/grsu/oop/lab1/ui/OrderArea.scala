package by.grsu.oop.lab1.ui

import java.awt.Color
import javax.swing.{BorderFactory, JLabel, JList, JPanel}

class OrderArea(val orders: JList[String]) extends JPanel{
    def this() = this(new JList[String])
}
object OrderArea{
    def apply():OrderArea = new OrderArea{
        setName("Order Area")
        setBorder(BorderFactory.createLineBorder(Color.BLACK))
        add(orders)
        add(new JLabel("Taken Orders"))
    }
}