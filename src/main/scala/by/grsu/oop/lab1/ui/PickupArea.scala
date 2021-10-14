package by.grsu.oop.lab1.ui

import java.awt.{Color, GridLayout}
import javax.swing.{BorderFactory, JLabel, JPanel}

class PickupArea(val availableOrderCount: JLabel,
                 val numWaitingCustomersInServingRoom: JLabel) extends JPanel{
    def this() = this(
        new JLabel {
            setBorder(BorderFactory.createLineBorder(Color.GREEN))
            setText("0")
        },
        new JLabel {
            setBorder(BorderFactory.createLineBorder(Color.GREEN))
            setText("0")
        }
    )
}
object PickupArea {
    def apply():PickupArea = new PickupArea{
        setName("Pickup Area")
        setBorder(BorderFactory.createLineBorder(Color.BLACK))
        setLayout(new GridLayout(2,1))
        add(new JPanel{
            add(availableOrderCount)
            add(new JLabel("Order count that's currently available for pick up by the customer"))
        })
        add(new JPanel{
            add(numWaitingCustomersInServingRoom)
            add(new JLabel("Number of customers waiting in the serving line"))
        })
    }

}
