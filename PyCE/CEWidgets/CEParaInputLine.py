from PySide2.QtCore import *
from PySide2.QtWidgets import *
from PySide2.QtGui import *

class CEParaInputLine(QFrame):
    __i1__ = 1
    __i2__ = 2
    __i3__ = 3
    def __init__(this, parent:QWidget = None):
        super().__init__(parent)
        this.setParent(parent)
        this.setObjectName("CEParaInputLine")
        this.TextLabel = QLabel(this)
        this.InputEdit = QLineEdit(this)
        this.UnitBox = QComboBox(this)
        this.TextLabel.setObjectName("TextLabel")
        this.InputEdit.setObjectName("InputEdit")
        this.UnitBox.setObjectName("UnitBox")
        
    def setText(this, text:str):
        this.TextLabel.setText(text)

    def setTextStyleSheet(this, style:str):
        this.TextLabel.setStyleSheet(style)
    
    def setInputEditStyleSheet(this, style:str):
        this.InputEdit.setStyleSheet(style)

    def setUnitBoxStyleSheet(this, style:str):
        this.UnitBox.setStyleSheet(style)

    def setUnitBox(this, unit:list):
        this.UnitBox.addItems(unit)

    def setCurrentIndex(this, index:int):
        this.UnitBox.setCurrentIndex(index)
    
    def setInputEdit(this, text:str):
        this.InputEdit.setText(text)

    def setInterval(this, i1:int, i2:int, i3:int):
        this.__i1__ = i1
        this.__i2__ = i2
        this.__i3__ = i3

    def resizeEvent(this, event:QResizeEvent):
        this.TextLabel.setGeometry(QRect(0, 0, this.width()*(this.__i1__/(this.__i1__+this.__i2__+this.__i3__)), this.height()))
        this.InputEdit.setGeometry(QRect(this.width()*(this.__i1__/(this.__i1__+this.__i2__+this.__i3__)), 0, this.width()*(this.__i2__/(this.__i1__+this.__i2__+this.__i3__)), this.height()))
        this.UnitBox.setGeometry(QRect(this.width()*((this.__i1__+this.__i2__)/(this.__i1__+this.__i2__+this.__i3__)), 0, this.width()*(this.__i3__/(this.__i1__+this.__i2__+this.__i3__)), this.height()))

    def text(this):
        return this.InputEdit.text()

    def currentIndex(this):
        return this.UnitBox.currentIndex()

    def currentData(this):
        return this.UnitBox.currentData()


    