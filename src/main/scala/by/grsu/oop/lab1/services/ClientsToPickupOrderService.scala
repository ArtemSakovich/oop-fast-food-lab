package by.grsu.oop.lab1.services

import by.grsu.oop.lab1.entities.Order

import java.util.concurrent.atomic.AtomicInteger

trait ClientsToPickupOrderService  {
    def addClientToPickupOrder(): Unit
    def getNumOfClientsWaitingToGetDoneOrder: Int
    def giveClientDoneOrder(order: Order): Unit
}


