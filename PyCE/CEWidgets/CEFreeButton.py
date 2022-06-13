from PySide2.QtCore import  *
from PySide2.QtWidgets import *
from PySide2.QtGui import *

class CEFreeButton(QFrame):
    selected = Signal(str)
    clicked = Signal(None)
    pressed = Signal(None)
    __NAHStyleSheet__ = ""
    __PressedStyleSheet__ = ""
    def __init__(this, parent:QWidget = None, RadioMode:bool = False):
        super().__init__(parent)
        this.setParent(parent)
        this.RadioMode = RadioMode
        this.InsiderImage = QLabel(this)
        this.InsiderImage.setObjectName("INSIDERIMAGE")

        this.InsiderLabel = QLabel(this)
        this.InsiderLabel.setObjectName("INSIDERLABEL")
        this.setAttribute(Qt.WA_Hover, True)

    def setText(this, text:str):
        this.InsiderLabel.setText(text)

    def setTextStyleSheet(this, style:str):
        this.InsiderLabel.setStyleSheet(style)

    def setNAHStyleSheet(this, style:str):
        this.setStyleSheet(style)
        this.__NAHStyleSheet__= style

    def setPressedStyleSheet(this, style:str):
        this.__PressedStyleSheet__= style

    def setPixmapStyleSheet(this, style:str):
        this.InsiderImage.setStyleSheet(style)

    def setTextAlignment(this, Alignment:Qt.Alignment):
        this.InsiderLabel.setAlignment(Alignment)

    def setCentralPixmap(this, url:str):
        this.InsiderImage.setStyleSheet("QLabel#INSIDERIMAGE{background-image: url("+url+");background-position: center;background-repeat: no-repeat;}")

    def click(this):
        this.pressed.emit()
        this.setStyleSheet(this.__PressedStyleSheet__)
        this.clicked.emit()
        if (not this.RadioMode):
            this.setStyleSheet(this.__NAHStyleSheet__)

    def radioModeReleaseButton(this):
        this.setStyleSheet(this.__NAHStyleSheet__)

    def mouseReleaseEvent(this, event:QMouseEvent):
        if (event.button() == Qt.LeftButton):
            this.clicked.emit()
            if (not this.RadioMode):
                this.setStyleSheet(this.__NAHStyleSheet__)

    def mousePressEvent(this, event:QMouseEvent):
        if (event.button() == Qt.LeftButton):
            this.pressed.emit()
            this.setStyleSheet(this.__PressedStyleSheet__)

    def mouseDoubleClickEvent(this, event:QMouseEvent):
        this.selected.emit(this.InsiderLabel.text())

    def text(this)->str:
        return this.InsiderLabel.text()


    