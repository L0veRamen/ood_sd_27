package ca.bytetube._09_pizza;

public class Main {
    interface Component {
        void draw();
    }

    static class Button implements Component {
        public void draw() {
            System.out.println("Drawing Button");
        }
    }

    static class BorderDecorator implements Component {
        private Component wrappedComponent;

        public BorderDecorator(Component comp) {
            this.wrappedComponent = comp;
        }

        public void draw() {
            wrappedComponent.draw();
            drawBorder();
        }

        private void drawBorder() {
            System.out.println("Drawing Border");
        }
    }

    static public class GUI {
        public static void main(String[] args) {
            Component myButton = new Button();
            myButton.draw();
            Component decoratedButton = new BorderDecorator(myButton);
            decoratedButton.draw(); // Draws Button and then draws Border
        }
    }
}
