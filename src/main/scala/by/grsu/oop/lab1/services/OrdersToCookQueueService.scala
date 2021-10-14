package by.grsu.oop.lab1.services

import by.grsu.oop.lab1.entities.Order

trait OrdersToCookQueueService extends Runnable{
    def addOrderToCook(order: Order): Unit
    def getOrdersToCookArray: Array[Order]
}

