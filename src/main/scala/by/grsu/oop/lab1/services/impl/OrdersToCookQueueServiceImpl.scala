package by.grsu.oop.lab1.services.impl

import by.grsu.oop.lab1.entities.Order
import by.grsu.oop.lab1.services.{CookService, OrdersToCookQueueService, OrdersToPickupQueueService}

import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.locks.{Condition, Lock, ReentrantLock}
import java.util.concurrent.{BlockingQueue, LinkedBlockingQueue}

abstract class OrdersToCookQueueServiceImpl(cookService: CookService,
                                            ordersToPickupQueueService: OrdersToPickupQueueService,
                                            isSimulationWorking: AtomicBoolean
                                           ) extends OrdersToCookQueueService {

  private val orderToCookQueue: BlockingQueue[Order] = new LinkedBlockingQueue[Order]()
  private val ordersToCookQueueLock: Lock = new ReentrantLock()
  private val ordersToCookQueueCondition: Condition = ordersToCookQueueLock.newCondition()

  override def addOrderToCook(order: Order): Unit = {
    orderToCookQueue.add(order)
//    try{
//      ordersToCookQueueLock.lock()

//      ordersToCookQueueCondition.signal()
//    }finally {
//      ordersToCookQueueLock.unlock()
//    }
  }

  override def run(): Unit = {
//    try{
//      ordersToCookQueueLock.lock()


      while(true){
        while(!orderToCookQueue.isEmpty ) {
          if (isSimulationWorking.get()) {
//            if (orderToCookQueue.isEmpty) {
//              ordersToCookQueueCondition.await()
//            }
            ordersToPickupQueueService.addOrderToPickup(
              cookService.cookOrder(orderToCookQueue.poll())
            )
            updateView()
          }
        }
      }
//    }finally {
//      ordersToCookQueueLock.unlock()
//    }
  }

  override def getOrdersToCookArray: Array[Order] = orderToCookQueue.toArray(new Array[Order](0))

  def updateView(): Unit
}
