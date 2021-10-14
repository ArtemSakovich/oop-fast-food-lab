package by.grsu.oop.lab1.services

import by.grsu.oop.lab1.entities.Order

import java.util.concurrent.atomic.AtomicReference

trait CookService {
  def getCurrentCookingOrder: Order
  def cookOrder(order: Order): Order
}


