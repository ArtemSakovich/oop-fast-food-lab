package by.grsu.oop.lab1.services.impl

import by.grsu.oop.lab1.services.{ClientToMakeOrderService, OrdersToCookQueueService, OrdersToPickupQueueService, SimulationService}

import java.util.concurrent.atomic.AtomicBoolean

class SimulationServiceImpl(clientToMakeOrderService: ClientToMakeOrderService,
                            ordersToCookQueueService: OrdersToCookQueueService,
                            ordersToPickupQueueService: OrdersToPickupQueueService,
                            isSimulationWorking: AtomicBoolean
                           )extends SimulationService{

  override def startSimulation(): Unit = {
    val clientToMakeOrderServiceThread = new Thread(clientToMakeOrderService,"ClientToMakeOrderService")
    val ordersToCookQueueServiceThread = new Thread(ordersToCookQueueService,"OrdersToCookQueueService")
    val ordersToPickupQueueServiceThread = new Thread(ordersToPickupQueueService,"OrdersToPickupQueueService")

    clientToMakeOrderServiceThread.start()
    ordersToCookQueueServiceThread.start()
    ordersToPickupQueueServiceThread.start()

    while(true){
      if(isSimulationWorking.get()){
        val newClient: Thread = new Thread(()=>{
          clientToMakeOrderService.addClientToMakeOrder()
        })
        newClient.start()
        Thread.sleep(500);

      }
    }

  }
}
