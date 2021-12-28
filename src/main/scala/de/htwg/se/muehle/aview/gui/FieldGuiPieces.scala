package de.htwg.se.muehle


package aview

package gui


import scala.swing._
import de.htwg.se.muehle.util.Observer
import java.awt.Dimension
import java.awt.{Color, Font} 
import javax.swing.border.LineBorder
import de.htwg.se.muehle.controller.ControllerComponent.ControllerInterface
import controller.ControllerComponent._
import controller.ControllerComponent.ControllerBaseImplementation._
import model.FieldComponent.Piece


case class GuiPieces(controller: ControllerInterface) {


    def tile(x: Int, y: Int) = new Tiles(x, y, controller)
    var top = Array.tabulate[Tiles](3, 3) { (row, col) => new Tiles(row, col, controller) }
    var mid = Array.tabulate[Tiles](6) { (col) => new Tiles(3, col, controller) }
    var bot = Array.tabulate[Tiles](3, 3) { (row, col) => new Tiles(row + 4, col, controller) }

    val textp1 = new TextField{columns = 10}

    val textp2 = new TextField{columns = 10}

    val player1 = new FlowPanel() {    
            contents += new Label("Spieler1: ")
            textp1.editable = false
            contents += textp1
        }
    val player2 = new FlowPanel() {    
            contents += new Label("Spieler2: ")    
            textp2.editable = false 
            contents += textp2   
        }
    val player = new FlowPanel() {
        contents += player1
        contents += player2
    }

    val p1stones = new TextField{columns = 2}

    val p2stones = new TextField{columns = 2}

    val stonesleftp1 = new FlowPanel {
        contents += new Label("stones left: ")
        p1stones.editable = false
        p1stones.text = controller.field.player.p1stones.toString
        contents += p1stones
    }
    val stonesleftp2 = new FlowPanel {
        contents += new Label("stones left: ")
        p2stones.editable = false
        p2stones.text = controller.field.player.p2stones.toString
        contents += p2stones
    }   
    val stones = new FlowPanel {
        contents += stonesleftp1
        contents += stonesleftp2
        border = Swing.EmptyBorder(10, 10, 50, 10)
        hGap = 150
    }
    val showturn = new TextField(columns = 50)

    val turn = new FlowPanel {
        contents+= showturn
        showturn.editable = false
        showturn.text = 
            if(controller.field.playerstatus.equals(Piece.player1)) {
                "Player1: " + controller.field.gamestatus.toString
            } else {
                "Player2: " + controller.field.gamestatus.toString
            }
        border = Swing.EmptyBorder(0, 0, 25, 0)

    }
    val field1 = new FlowPanel() {
        hGap = 150
        contents += top(0)(0)
        contents += top(0)(1)
        contents += top(0)(2)
    }
    val field11= new FlowPanel() {
        hGap = 150
        contents += bot(2)(0)
        contents += bot(2)(1)
        contents += bot(2)(2)
    }
    val field2 = new FlowPanel() {
        hGap = 100
        border = Swing.EmptyBorder(25, 0, 25, 0)
        contents += top(1)(0)
        contents += top(1)(1)
        contents += top(1)(2)
    }
    val field22 = new FlowPanel() {
        hGap = 100
        border = Swing.EmptyBorder(25, 0, 25, 0)
        contents += bot(1)(0)
        contents += bot(1)(1)
        contents += bot(1)(2)
    }
    val field3 = new FlowPanel {
        hGap = 50
        contents += top(2)(0)
        contents += top(2)(1)
        contents += top(2)(2)
    }
    val field33 = new FlowPanel {
        hGap = 50
        contents += bot(0)(0)
        contents += bot(0)(1)
        contents += bot(0)(2)
    }
    val fieldmid = new FlowPanel {
        hGap = 75
        border = Swing.EmptyBorder(25, 0, 25, 0)
        contents += new FlowPanel {
            hGap = 25
            contents += mid(0)
            contents += mid(1)
            contents += mid(2)
        }
        contents += new FlowPanel {
            hGap = 25
            contents += mid(3)
            contents += mid(4)
            contents += mid(5)
        }
    }
    val finalfield = new BoxPanel(Orientation.Vertical) {
        background = Color.PINK
        contents += field1
        contents += field2
        contents += field3
        contents += fieldmid
        contents += field33
        contents += field22
        contents += field11

        visible = true

    }
    val finalBox = new BoxPanel(Orientation.Vertical) {

        contents += player
            contents += stones
            contents += turn
            contents += finalfield
    }
}

