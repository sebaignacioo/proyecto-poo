package aplicacion.models;

import java.util.Random;

/**
 * Clase que registra la asistencia de un alumno, para un año escolar completo.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */
public class RegistroAsistencia {

    private float[][] asistencia; // Matriz que registra asistencia (dia y mes).
    private int[] contadorDias; // Arreglo que contiene el total de días a considerar en la asistencia

    /**
     * Constructor que permite crear un objeto para registrar la asistencia. No recibe parámetros.
     */
    public RegistroAsistencia() {
        // Considera 31 días máximo por mes, desde Marzo (índice 0) a Diciembre (índice 9).
        this.asistencia = new float[31][10];
        this.contadorDias = new int[10];
    }

    /**
     * Permite registrar la asistencia del alumno en un día y mes determinado.
     *
     * @param valor El valor de la asistencia (entre 0 y 1)
     * @param dia El día para el que se quiere registrar la asistencia
     * @param mes El mes para el que se quiere registrar la asistencia
     */
    public void registrarAsistencia(float valor, int dia, int mes) {
        if ((mes >= 3 && mes <= 12) && (dia >= 1 && dia <= 31))
            this.asistencia[dia][mes] = valor;
        this.contadorDias[mes]++;
    }

    /**
     * Permite obtener la asistencia del alumno, calculada al día de realizada la consulta.
     *
     * @return Porcentaje de asistencia (entre 0 y 1).
     */
    public float obtenerAsistencia() {
        float totalDiasAsistidos = 0;

        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 31; j++){
                if (Float.compare(this.asistencia[j][i], 1.0f) == 0 || Float.compare(this.asistencia[j][i], 0.5f) == 0){
                    totalDiasAsistidos++;
                }
            }
        }
        if (totalDiasAsistidos != 0)
            return totalDiasAsistidos / 310;
        return 0.0f;
    }

    /**
     * Permite obtener la asistencia del alumno en un mes determinado.
     *
     * @param mes Número de mes (1 = marzo, 10 = diciembre)
     * @return Porcentaje de asistencia (entre 0 y 1).
     */
    public float obtenerAsistencia(int mes) {
        return 0.0f;
    }

    /**
     * Permite obtener la asistencia del alumno, calculada al día de realizada la consulta.
     *
     * @param dia Dia a buscar
     * @param mes Número de mes (1 = marzo, 10 = diciembre)
     * @return Porcentaje de asistencia (entre 0 y 1).
     */
    public float obtenerAsistencia(int dia, int mes) {
        return 0.0f;
    }

    /**
     * Permite obtener los retiros del alumno, calculada al día de realizada la consulta.
     *
     * @return Porcentaje de retiros (entre 0 y 1).
     */

    public float obtenerRetiros() {
        float totalDiasRetirado = 0;

        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 31; j++){
                if (Float.compare(this.asistencia[j][i], 0.5f) == 0){
                    totalDiasRetirado++;
                }
            }
        }
        if (totalDiasRetirado != 0)
            return totalDiasRetirado / 310;
        return 0.0f;
    }
}