package by.grsu.oop.lab1.services.impl

import by.grsu.oop.lab1.entities.Order
import by.grsu.oop.lab1.services.ClientsToPickupOrderService

import java.util.concurrent.atomic.AtomicInteger

abstract class ClientsToPickUpOrderServiceImpl extends ClientsToPickupOrderService {
  private val customersToPickupOrderCount: AtomicInteger = new AtomicInteger(0)

  override def addClientToPickupOrder(): Unit = customersToPickupOrderCount.incrementAndGet()

  override def getNumOfClientsWaitingToGetDoneOrder: Int = customersToPickupOrderCount.get()

  override def giveClientDoneOrder(order: Order): Unit = customersToPickupOrderCount.decrementAndGet()

  def updateView(): Unit
}