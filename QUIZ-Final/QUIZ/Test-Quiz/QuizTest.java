import static org.junit.jupiter.api.Assertions.*;

class QuizTest {

    @org.junit.jupiter.api.Test
    void main() {

    }

    @org.junit.jupiter.api.Test
    void escogermascota() {
        boolean salir = false;
        if (salir) {
            assertTrue(Boolean.parseBoolean("gato"));
            assertTrue(Boolean.parseBoolean("perro"));
        } else {
            System.out.println("Mascota Incorrecta");
        }
    }

    @org.junit.jupiter.api.Test
    void numPreguntes() {
        int preguntas =  6;
        assertTrue(preguntas >= 5 && preguntas <= 20);
        System.out.println("El numero de preguntas que has escogido son: " + preguntas);
    }

    @org.junit.jupiter.api.Test
    void volverjugar() {
        String respuesta = "si";
        assertEquals("si", respuesta);
        System.out.println("Has escogido volver a jugar");
    }

    @org.junit.jupiter.api.Test
    void respuestaCorrecta() {
        String respuestaUsuario = "A";
        boolean continuar = false;
        do {
            if (respuestaUsuario.charAt(0) == 'A' || respuestaUsuario.charAt(0) == 'B' || respuestaUsuario.charAt(0) == 'C') {
                assertFalse(Boolean.parseBoolean("A"));
                assertFalse(Boolean.parseBoolean("B"));
                assertFalse(Boolean.parseBoolean("C"));
                System.out.println("La respuesta es correcta!! ");
                continuar = false;
            } else {
                System.out.println("No has introducido bien la respuesta");
                continuar = false;
            }
        } while(continuar);

    }

}