package by.grsu.oop.lab1.ui

import java.awt.GridLayout
import javax.swing.{JButton, JFrame, JLabel, JPanel, JTextField, WindowConstants}

class AppWindow(val orderLineArea: OrderLineArea,
                val orderArea: OrderArea,
                val kitchenArea: KitchenArea,
                val pickupArea: PickupArea,
                val startSimulationBtn: JButton,
                val stopSimulationBtn: JButton,
                val customerArrivalIntervalInput: JTextField,
                val ticketFulfillmentIntervalInput: JTextField,
               ) extends JFrame{

    def this() = this(
      OrderLineArea(),
      OrderArea(),
      KitchenArea(),
      PickupArea(),
      new JButton("Start"),
      new JButton("Stop"),
      new JTextField("0"),
      new JTextField("0")
    )


}


object AppWindow{

  def apply(): AppWindow = new AppWindow {
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    setSize(500, 500)
    add(new JPanel {
      setLayout(new GridLayout(4, 2, 15, 15))
      add(orderLineArea)
      add(orderArea)
      add(kitchenArea)
      add(pickupArea)
      add(startSimulationBtn)
      add(stopSimulationBtn)
      add(new JPanel{
        setLayout(new GridLayout)
        add(customerArrivalIntervalInput)
        add(new JLabel("Customer Arrival Interval"))
      })
      add(new JPanel{
        setLayout(new GridLayout)
        add(ticketFulfillmentIntervalInput)
        add(new JLabel("Ticket fulfillment Interval"))
      })
    })
    setVisible(true)
  }

}

