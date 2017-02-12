package shapes2;

import javafx.scene.canvas.GraphicsContext;

/**
 * Абстрактный класс Shape содержит основные общие методы, используемые фигурами
 */
public abstract class Shape {
    public boolean select=false;

    public boolean isSelected(Point findingPoint){
        return select;//проверяет выбрана ли данная фигура
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
