package by.grsu.oop.lab1.services.impl

import by.grsu.oop.lab1.entities.Order
import by.grsu.oop.lab1.services.{ClientToMakeOrderService, OrdersToCookQueueService}

import java.util.concurrent.atomic.{AtomicBoolean, AtomicInteger}
import java.util.concurrent.locks.{Condition, Lock, ReentrantLock}

abstract class ClientToMakeOrderServiceImpl(ordersToCookQueueService: OrdersToCookQueueService,
                                            isSimulationWorking: AtomicBoolean
                                           ) extends ClientToMakeOrderService {

  private val customersToPlaceOrderCount: AtomicInteger = new AtomicInteger(0)
  private val customersToPlaceOrderProducerLock: Lock = new ReentrantLock()
  private val customersToPlaceOrderProducerCondition: Condition = customersToPlaceOrderProducerLock.newCondition()

  override def addClientToMakeOrder(): Unit = {
    try{
      customersToPlaceOrderProducerLock.lock()
      customersToPlaceOrderCount.incrementAndGet()
      //      customersToPlaceOrderProducerCondition.signal()
    }finally {
      customersToPlaceOrderProducerLock.unlock()
    }

  }

  override def getNumOfClientsWaitingToPlaceOrder: Int = customersToPlaceOrderCount.get()

  //orders producer logic
  override def run(): Unit = {
    var nextOrderId: Int = 0
//    try{
//        customersToPlaceOrderProducerLock.lock()
        while(true){
          while(customersToPlaceOrderCount.get() != 0 ){

            if(isSimulationWorking.get()){
//              if(customersToPlaceOrderCount.get() == 0 ){
//                customersToPlaceOrderProducerCondition.await()
//              }
              Thread.sleep(1000);
              ordersToCookQueueService.addOrderToCook(Order(nextOrderId))
              customersToPlaceOrderCount.decrementAndGet()
              nextOrderId = nextOrderId + 1
              updateView()

            }
          }

        }
//    }finally {
//      customersToPlaceOrderProducerLock.unlock()
//    }
  }

  def updateView(): Unit
}

