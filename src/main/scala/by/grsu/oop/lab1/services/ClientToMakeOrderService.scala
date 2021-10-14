package by.grsu.oop.lab1.services

trait ClientToMakeOrderService extends Runnable{
    def addClientToMakeOrder(): Unit
    def getNumOfClientsWaitingToPlaceOrder: Int
}


