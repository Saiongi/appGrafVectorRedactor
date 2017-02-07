package shapes2;

import javafx.scene.canvas.GraphicsContext;

/**
 * Абстрактный класс Shape содержит основные общие методы, используемые фигурами
 */
public abstract class Shape {

    public void setPosition(){
        //устанавливает позиции
    }
    public void changePosition(){
        //изменяет позицию
    }
    public void changeSize(){
        //изменяет размер
    }
    public void deleteShape(){
        //удаляет фигуру

    }
    public void paintShape(GraphicsContext gc){
        //рисует фигуру
    }
}
