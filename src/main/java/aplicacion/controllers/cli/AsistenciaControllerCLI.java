package aplicacion.controllers.cli;

import aplicacion.models.Alumno;
import aplicacion.models.Curso;
import aplicacion.views.cli.AsistenciaViewCli;
import aplicacion.views.cli.MenuCLI;
import aplicacion.views.cli.UtilsCLI;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase controladora del menú de gestión de Asistencia de la interfaz de linea de comandos.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 3.0
 */

public class AsistenciaControllerCLI {

    private final MenuCLI menuCLI;

    /**
     * Objeto controlador de Asistencia en la interfaz de línea de comandos
     *
     * @param menuCLI Instancia del origen de datos de alumnos.
     */
    public AsistenciaControllerCLI(MenuCLI menuCLI) {
        this.menuCLI = menuCLI;
    }

    /**
     * Selecciona el alumno con mejor asistencia o más retiros dependiendo del caso
     *
     * @param value Valor por defecto que designa la opción del dato a retornar; 1 si es asistencia, 2 si es retiros
     * @return Alumno
     */
    public Alumno getAlumnoRequerido(int value){
        Alumno alumnoReq = null;
        List<Curso> cursos = this.menuCLI.getCursoData().getCursos();

        for (Curso curso: cursos) {
            for (Alumno alumno: curso.getAlumnos().values()) {
                if (value == 1 && (alumnoReq == null || alumno.getPromAsistencia() > alumnoReq.getPromAsistencia())) {
                    alumnoReq = alumno;
                }
                if (value == 0 && (alumnoReq == null || alumno.promRetiros() > alumnoReq.promRetiros()))
                    alumnoReq = alumno;
            }
        }
        return alumnoReq;
    }

    /**
     * Selecciona los alumnos con asistencia o retiros dentro de los rangos dados por usuario
     *
     * @param percent1 Primer porcentaje del rango
     * @param percent2 Segundo porcentaje del rango
     * @param value Valor por defecto que designa la opción del dato a retornar; 1 si es asistencia, 2 si es retiros
     * @return HashMap con los alumnos seleccionados
     */
    public Map<String, Alumno> getEntrePorcentajes(int percent1, int percent2, int value){
        List<Curso> cursos = this.menuCLI.getCursoData().getCursos();
        Map<String, Alumno> alumnos = new HashMap<>();
        int alumnoReq = -1;

        for (Curso curso: cursos) {
            for (Alumno alumno: curso.getAlumnos().values()) {
                if (value == 1)
                    alumnoReq = (int) Math.round((alumno.getPromAsistencia() * 100.0));
                if (value == 0)
                    alumnoReq = (int) Math.round((alumno.promRetiros() * 100.0));
                if (alumnoReq >= percent1 && alumnoReq <= percent2)
                    alumnos.put(alumno.getRut(), alumno);
            }
        }
        return alumnos;
    }

    /**
     * Permite mostrar por pantalla el alumno con mejor registro de asistencia.
     */
    public void alumnoMejorAsistencia() {
        Alumno alumno;

        alumno = getAlumnoRequerido(1);
        if (alumno != null)
            System.out.println(alumno.toString("del alumno con mejor asistencia"));
        else
            UtilsCLI.mensajeErrorNoEncontrado("registro de asistencia", 0);
    }

    /**
     * Permite mostrar por pantalla una tabla con los alumnos que tengan una asistencia promedio entre los
     * porcentajes especificados por el usuario.
     *
     * @throws IOException Errores de entrada/salida de datos.
     */
    public void alumnosEntrePorcentajesAsistencia() throws IOException {
        int porcentaje1, porcentaje2;
        Map<String, Alumno> alumnosEnPorcentaje;

        UtilsCLI.imprimirSolicitar("porcentaje 1 ", "número de 0 a 100");
        porcentaje1 = Integer.parseInt(this.menuCLI.getLector().readLine());
        UtilsCLI.imprimirSolicitar("porcentaje 2 ", "número de 0 a 100");
        porcentaje2 = Integer.parseInt(this.menuCLI.getLector().readLine());
        alumnosEnPorcentaje = getEntrePorcentajes(porcentaje1, porcentaje2, 1);
        AsistenciaViewCli.mostrarTablaAlumnosAsistencia(alumnosEnPorcentaje, "porcentaje dado ASISTENCIA");
    }

    /**
     * Permite mostrar por pantalla al alumno que registre más retiros.
     *
     * @throws IOException Errores de entrada/salida de datos.
     */
    public void alumnoConMasRetiros() throws IOException {
        Alumno alumno;

        System.out.println("Alumno con mas retiros: ");
        alumno = getAlumnoRequerido(0);
        System.out.println(alumno.toString("del alumno con mas retiros"));
    }

    /**
     * Permite mostrar una tabla de alumnos que posean un porcentaje de retiros entre límites especificados por el
     * usuario.
     *
     * @throws IOException Errores de entrada/salida de datos.
     */
    public void alumnoEntrePorcentajesRetiros() throws IOException {
        int porcentaje1, porcentaje2;
        Map<String, Alumno> alumnosEnPorcentaje;

        UtilsCLI.imprimirSolicitar("porcentaje 1 ", "número de 0 a 100");
        porcentaje1 = Integer.parseInt(this.menuCLI.getLector().readLine());
        UtilsCLI.imprimirSolicitar("porcentaje 2 ", "número de 0 a 100");
        porcentaje2 = Integer.parseInt(this.menuCLI.getLector().readLine());
        alumnosEnPorcentaje = getEntrePorcentajes(porcentaje1, porcentaje2, 0);
        AsistenciaViewCli.mostrarTablaAlumnosAsistencia(alumnosEnPorcentaje, "porcentaje dado RETIROS");
    }

}
