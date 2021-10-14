package by.grsu.oop.lab1.services.impl

import by.grsu.oop.lab1.entities.Order
import by.grsu.oop.lab1.services.CookService

import java.util.concurrent.atomic.AtomicReference

abstract class CookServiceImpl extends CookService {
    private val currentCookingOrder: AtomicReference[Order] = new AtomicReference[Order]()

    override def getCurrentCookingOrder: Order = currentCookingOrder.get()


    override def cookOrder(order: Order): Order = {
      currentCookingOrder.set(order)
      updateView()
      Thread sleep 3000
      currentCookingOrder.set(Order(-1))
      order
    }

    def updateView(): Unit
}
