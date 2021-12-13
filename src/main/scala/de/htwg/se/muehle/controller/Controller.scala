package de.htwg.se.muehle

package controller

import model.Field
import model.Piece
import util.Observable
import scala.swing.Publisher

case class Controller(var field: Field) extends Publisher:
    val invoker = new Invoker
    def put(stone: Option[Piece],x: Int, y: Int) = 
        field = invoker.doStep(PutCommand(stone, x, y, this))
        publish(new fieldchange)

    def undo =
        field = invoker.undoStep.getOrElse(field)
        publish(new fieldchange)

    def redo =
        field = invoker.redoStep.getOrElse(field)
        publish(new fieldchange)

    def move(stone: Option[Piece],x: Int, y: Int, newx: Int, newy: Int) =
        field = invoker.doStep(MoveCommand(stone, x, y, newx, newy, this))
        publish(new fieldchange)


    def take(stone: Option[Piece], x: Int, y: Int) =
        field = invoker.doStep(TakeCommand(stone, x, y,this))
        publish(new fieldchange)


    def select(x: Int, y: Int) =
        field = invoker.doStep(SelectCommand(x, y, this))
        publish(new fieldchange)
