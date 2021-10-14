package by.grsu.oop.lab1

import by.grsu.oop.lab1.services.impl._
import by.grsu.oop.lab1.services._
import by.grsu.oop.lab1.ui.AppWindow

import java.util.concurrent.atomic.AtomicBoolean

object Application extends App {
    init()

    def init(): Unit ={
        val mainWindow: AppWindow = AppWindow()

        val isSimulationIsWorking: AtomicBoolean = new AtomicBoolean(true)
        val cookService: CookService =
            new CookServiceImpl {
                override def updateView(): Unit = {
                    mainWindow.kitchenArea
                      .numOrdersIsBeingPrepared
                      .setText(getCurrentCookingOrder.num.toString)

                    mainWindow.kitchenArea.numOrdersIsBeingPrepared.repaint()
                }
            }

        val clientsToPickupOrderService: ClientsToPickupOrderService =
            new ClientsToPickUpOrderServiceImpl {
                override def updateView(): Unit = {
                    mainWindow.pickupArea
                      .numWaitingCustomersInServingRoom
                      .setText(getNumOfClientsWaitingToGetDoneOrder.toString)
                    mainWindow.kitchenArea.numOrdersIsBeingPrepared.repaint()
                }
            }

        val ordersToPickupQueueService: OrdersToPickupQueueService =
            new OrdersToPickupQueueServiceImpl(clientsToPickupOrderService,isSimulationIsWorking) {
                override def updateView(): Unit = {
                    mainWindow.pickupArea
                      .availableOrderCount
                      .setText(getAvailableOrdersCounts.toString)
                    mainWindow.kitchenArea.numOrdersIsBeingPrepared.repaint()

                }
            }

        val ordersToCookQueueService: OrdersToCookQueueService =
            new OrdersToCookQueueServiceImpl(cookService,ordersToPickupQueueService,isSimulationIsWorking) {
                override def updateView(): Unit = {
                    mainWindow.orderArea
                      .orders
                      .setListData(getOrdersToCookArray.map(order=>order.num.toString))
                    mainWindow.kitchenArea.numOrdersIsBeingPrepared.repaint()
                }

            }

        val clientsToMakeOrderService: ClientToMakeOrderService =
            new ClientToMakeOrderServiceImpl(ordersToCookQueueService,isSimulationIsWorking) {
                override def updateView(): Unit = {
                    mainWindow.orderLineArea
                      .numOfCustomersWaitToPlaceOrder
                      .setText(getNumOfClientsWaitingToPlaceOrder.toString)
                    mainWindow.kitchenArea.numOrdersIsBeingPrepared.repaint()

                }
            }

        val simulationService: SimulationService =new SimulationServiceImpl(
            clientsToMakeOrderService,
            ordersToCookQueueService,
            ordersToPickupQueueService,
            isSimulationIsWorking
        )

        simulationService.startSimulation()
    }
}
