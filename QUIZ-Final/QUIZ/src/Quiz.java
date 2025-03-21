import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Quiz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] quiz = new String[100];
        int contador = 0;
        int incorrectas = 0;
        int correctas = 0;
        double porcentaje = 0;
        boolean jugarnuevo = false;
        boolean incorrecto = false;
        String nombre = "";

        do {
            String nombre2 = nombre(nombre);

            lecturaGos(quiz);
            lecturaGato(quiz);
            escogermascota(scanner);
            int numPreguntes = numPreguntas();


            for (int i = 0; i < numPreguntes; i++) {
                String[] preguntesSeparades2 = preguntasTest(quiz, numPreguntes);
                int resultadoPregunta = respuestaCorrecta(preguntesSeparades2);
                if (resultadoPregunta == 1) {
                    contador++;
                } else if (resultadoPregunta == 0) {
                    incorrectas++;
                }
            }

            System.out.println("Tienes " + contador + " respuestas correctas!!");
            System.out.println("Tienes " + incorrectas + " respuestas incorrectas");

            porcentaje = (double) contador / numPreguntes * 100.00;
            porcentaje = (int) (porcentaje * 100.00) / 100.00;
            System.out.println("El porcentaje que tienes es del: " + porcentaje + "%");

            do {
                String respuesta = "";
                String respuestafinal = volverjugar();
                if (respuestafinal.equals("si") || respuestafinal.equals("s")) {
                    incorrecto = false;
                    jugarnuevo = true;
                } else if (respuestafinal.equals("no") || respuestafinal.equals("n")) {
                    incorrecto = false;
                    jugarnuevo = false;
                    System.out.println("HASTA LUEGO!! ❤️❤️ ");
                } else {
                    System.out.println("OPERACION INCORRECTA. INTRODUCE DE NUEVO EL VALOR");
                    incorrecto = true;
                }
            } while (incorrecto);

            escribirGato(quiz, contador, nombre2, incorrectas, correctas);
            escribirPerro(quiz, contador, nombre2, incorrectas, correctas);
        } while (jugarnuevo);
    }

    private static void escogermascota(Scanner scanner) {
        int numPreguntes = 0;
        String[] quiz = new String[100];
        boolean salir = false;
        do {
            System.out.println("Introduce que mascota quieres (gato o perro): ");
            String mascota = scanner.nextLine().toLowerCase();
            if (mascota.equals("gato")) {
                numPreguntes = lecturaGato(quiz);
                salir = false;
            } else if (mascota.equals("perro")) {
                numPreguntes = lecturaGos(quiz);
                salir = false;
            } else {
                System.out.println("No has introducido bien la mascota");
                salir = true;
            }
        } while (salir);
    }

    private static void escribirPerro(String[] quiz, int contador, String nombre2, int incorrectas, int correctas) {
        String archivoperro = "src/quizperro";
        LocalDateTime fecha = LocalDateTime.now();
        DateTimeFormatter fechaperro = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String fechaperro2 = fecha.format(fechaperro);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivoperro, true));
            writer.write("Nombre: " + nombre2 + ";");
            writer.write("Respuestas Correctas: " + correctas + ";");
            writer.write("Respuestas Incorrectas: " + incorrectas + ";");
            writer.write("Fecha: " + fechaperro2 + ";");
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void escribirGato(String[] quiz, int contador, String nombre2, int incorrectas, int correctas) {
        String archivogato = "src/quizgato";
        LocalDateTime fecha = LocalDateTime.now();
        DateTimeFormatter fechagato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String fechagato2 = fecha.format(fechagato);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivogato, true));
            writer.write("Nombre: " + nombre2 + ";");
            writer.write("Respuestas Correctas: " + correctas + ";");
            writer.write("Respuestas Incorrectas: " + incorrectas + ";");
            writer.write("Fecha: " + fechagato2 + ";");
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String volverjugar() {
        System.out.println("Quieres volver a jugar? (si - no // s - n)");
        Scanner scanner = new Scanner(System.in);
        String respuesta = scanner.nextLine().toLowerCase();
        return respuesta;
    }

    private static int respuestaCorrecta(String[] preguntesSeparades2) {

        boolean continuar = false;
        int resultados = 0;
        String respuestausuario = "";
        do {
            System.out.println("Introduce la respuesta: ");
            Scanner scanner = new Scanner(System.in);
            respuestausuario = scanner.nextLine().toUpperCase();

            if (respuestausuario.charAt(0) == 'A' || respuestausuario.charAt(0) == 'B' || respuestausuario.charAt(0) == 'C') {
                if (respuestausuario.equals(preguntesSeparades2[4])) {
                    System.out.println("La respuesta es correcta");
                    resultados++;
                    continuar = false;
                } else {
                    System.out.println("La respuesta es incorrecta");
                    continuar = false;
                }
            } else {
                System.out.println("No has introducido bien la respuesta");
                System.out.println(preguntesSeparades2[0]);
                System.out.println(preguntesSeparades2[1]);
                System.out.println(preguntesSeparades2[2]);
                System.out.println(preguntesSeparades2[3]);
                continuar = true;
            }
        } while (continuar);
        return resultados;
    }

    private static String[] preguntasTest(String[] quiz, int numPreguntes) {
        int PreguntaRandom = random(0, numPreguntes - 1);
        String[] preguntesSeparades = quiz[PreguntaRandom].split(";");
        System.out.println(preguntesSeparades[0]);
        System.out.println(preguntesSeparades[1]);
        System.out.println(preguntesSeparades[2]);
        System.out.println(preguntesSeparades[3]);
        return preguntesSeparades;
    }

    private static int numPreguntas() {
        Scanner scanner = new Scanner(System.in);
        int preguntas = 0;
        boolean entradaValida = false;

        do {
            System.out.println("Introduce cuántas preguntas quieres (entre 5 y 20): ");
            if (scanner.hasNextInt()) {
                preguntas = scanner.nextInt();
                scanner.nextLine();
                if (preguntas >= 5 && preguntas <= 20) {
                    entradaValida = true;
                } else {
                    System.out.println("El número debe estar entre 5 y 20.");
                }
            } else {
                System.out.println("Por favor, introduce un número válido.");
                scanner.nextLine();
            }
        } while (!entradaValida);

        return preguntas;
    }

    private static String nombre(String nombre) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce tu nombre: ");
        nombre = scanner.nextLine();
        System.out.println("Hola " + nombre + "!!");
        return nombre;
    }

    private static int lecturaGos(String[] quiz) {
        int i = 0;

        String fitxer = "src/perro.txt";
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(fitxer));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String linea;
        while (true) {
            try {
                if ((!((linea = br.readLine()) != null))) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            quiz[i] = linea;
            i++;
        }
        return i;
    }

    private static int lecturaGato(String[] quiz) {
        int i = 0;

        String fitxer = "src/gato.txt";
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(fitxer));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String linea;
        while (true) {
            try {
                if ((!((linea = br.readLine()) != null))) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            quiz[i] = linea;
            i++;
        }
        return i;
    }

    private static int random(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}