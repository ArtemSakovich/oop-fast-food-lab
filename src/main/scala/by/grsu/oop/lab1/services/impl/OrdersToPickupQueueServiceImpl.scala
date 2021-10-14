package by.grsu.oop.lab1.services.impl

import by.grsu.oop.lab1.entities.Order
import by.grsu.oop.lab1.services.{ClientsToPickupOrderService, OrdersToPickupQueueService}

import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.locks.{Condition, Lock, ReentrantLock}
import java.util.concurrent.{BlockingQueue, LinkedBlockingQueue}

abstract class OrdersToPickupQueueServiceImpl(clientsToPickUpOrderService: ClientsToPickupOrderService,
                                              isSimulationWorking: AtomicBoolean
                                             ) extends OrdersToPickupQueueService{
  private val orderToPickupQueue: BlockingQueue[Order] = new LinkedBlockingQueue[Order]()
  private val ordersToPickupQueueLock: Lock = new ReentrantLock()
  private val ordersToPickupQueueCondition: Condition = ordersToPickupQueueLock.newCondition()

  override def addOrderToPickup(order: Order): Unit = {
    orderToPickupQueue.add(order)
//    try{
//      ordersToPickupQueueLock.lock()

//      ordersToPickupQueueCondition.signal()
//    }finally {
//      ordersToPickupQueueLock.unlock()
//    }
  }

  override def run(): Unit = {
//    try{
//        ordersToPickupQueueLock.lock()

        while(true){
          while(!orderToPickupQueue.isEmpty ) {

            if (isSimulationWorking.get()) {

//              if (orderToPickupQueue.isEmpty) {
//                ordersToPickupQueueCondition.await()
//              }
              clientsToPickUpOrderService.giveClientDoneOrder(orderToPickupQueue.poll())
              updateView()
            }
          }
        }
//    }finally {
//      ordersToPickupQueueLock.unlock()
//    }
  }

  override def getAvailableOrdersCounts: Int = orderToPickupQueue.size()

  def updateView(): Unit
}
