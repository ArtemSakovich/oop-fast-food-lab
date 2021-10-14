package by.grsu.oop.lab1.ui

import java.awt.{Color, GridLayout}
import javax.swing.{BorderFactory, JLabel, JList, JPanel}

class KitchenArea(val numOrdersIsBeingPrepared: JLabel,
                  val waitingOrders: JList[String],
                  val numWaitingOrders: JLabel) extends JPanel {
    def this() = this(
        new JLabel {
            setBorder(BorderFactory.createLineBorder(Color.GREEN))
            setText("0")
        },
        new JList[String] {
            setBorder(BorderFactory.createLineBorder(Color.RED))
        },
        new JLabel {
            setBorder(BorderFactory.createLineBorder(Color.GREEN))
            setText("0")
        }
    )
}

object KitchenArea {
    def apply(): KitchenArea = new KitchenArea {
        setName("Kitchen Area")
        setBorder(BorderFactory.createLineBorder(Color.BLACK))
        setLayout(new GridLayout(3, 1))
        add(new JPanel {
            add(numOrdersIsBeingPrepared)
            add(new JLabel("Num Of Order that is being prepared"))
        })
        add(new JPanel {
            add(waitingOrders)
            add(new JLabel("Waiting Orders"))
        })
        add(new JPanel {
            add(numWaitingOrders)
            add(new JLabel("Num of waiting orders"))
        })
    }
}
