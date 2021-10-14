package by.grsu.oop.lab1.services

import by.grsu.oop.lab1.entities.Order

trait OrdersToPickupQueueService extends Runnable{
    def addOrderToPickup(order: Order): Unit
    def getAvailableOrdersCounts: Int
}
