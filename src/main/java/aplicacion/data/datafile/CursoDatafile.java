package aplicacion.data.datafile;

import aplicacion.data.CursoData;
import aplicacion.models.Curso;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que permite la interacción con el archivo de texto plano que almancena
 * los datos del Curso. Implementa la interfaz CursoData.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 1.0
 */
public class CursoDatafile implements CursoData {

    private final Datafile datafile;
    private final ProfesorDatafile prDatafile;
    private final AlumnoDatafile alDatafile;

    /**
     * Constructor de CursoDatafile. Trabaja con un objeto Datafile.
     */
    public CursoDatafile() {
        this.datafile = new Datafile("curso");
        this.prDatafile = new ProfesorDatafile();
        this.alDatafile = new AlumnoDatafile();
    }

    /**
     * Permite transformar un objeto de tipo Curso, en una línea con formato CSV
     *
     * @param curso Curso
     * @return String con la línea en formato CSV correspondiente al curso
     */
    public String cursoToCSV(Curso curso) {
        List<String> dataList = new ArrayList<>();
        dataList.add(Short.toString(curso.getNivel()));
        dataList.add(Character.toString(curso.getLetra()));
        dataList.add(curso.getProfesorJefe().getRut());
        return Datafile.listToCSV(dataList);
    }

    /**
     * Permite obtener un objeto de tipo Curso, desde un CSV
     *
     * @param csv String con la línea CSV con los datos del curso
     * @return Objeto Curso con los datos del curso.
     */
    public Curso cursoFromCSV(String csv) {
        String[] parts = csv.split(",");
        return new Curso(Short.parseShort(parts[0]), parts[1].charAt(0), prDatafile.getProfesor(parts[2]),
                alDatafile.getAlumnos(Short.parseShort(parts[0]), parts[1].charAt(0)));
    }

    /**
     * Permite obtener todos los cursos almacenados en el archivo CSV
     *
     * @return ArrayList de cursos
     */
    @Override
    public List<Curso> getCursos() {
        List<Curso> cursos = new ArrayList<>();
        List<String> data = this.datafile.getData();
        for (String csv : data)
            cursos.add(cursoFromCSV(csv));
        return cursos;
    }

    /**
     * Permite obtener los cursos de cierto nivel, almacenados en el archivo CSV
     *
     * @param nivel Nivel que se desea filtrar
     * @return ArrayList de cursos del nivel especificado
     */
    @Override
    public List<Curso> getCursos(short nivel) {
        List<Curso> cursos = new ArrayList<>();
        List<String> data = this.datafile.getData();
        for (String csv : data)
            if (Short.parseShort(csv.split(",")[0]) == nivel)
                cursos.add(cursoFromCSV(csv));
        return cursos;
    }

    /**
     * Permite obtener un curso en especifico, almacenado en el archivo CSV.
     *
     * @param nivel Nivel del curso
     * @param letra Letra identificadora de paralelo
     * @return Curso solicitado
     */
    @Override
    public Curso getCurso(short nivel, char letra) {
        List<String> data = this.datafile.getData();
        for (String csv : data)
            if ((Integer.parseInt(csv.split(",")[0]) == nivel) && (csv.split(",")[1].charAt(0) == letra))
                return cursoFromCSV(csv);
        return null;
    }

    /**
     * Agrega un Curso al archivo CSV
     *
     * @param curso Curso que se desea agregar al archivo CSV
     * @return Valor de verdad (boolean) sobre el exito o fracaso de la operacion de inserción
     */
    @Override
    public boolean insertCurso(Curso curso) {
        return this.prDatafile.insertProfesor(curso.getProfesorJefe()) && this.datafile.insertLine(cursoToCSV(curso));
    }

    /**
     * Actualiza los datos de un Curso en el archivo CSV
     *
     * @param curso Curso a actualizar
     * @return Valor de verdad (boolean) sobre el exito o fracaso de la operacion de actualizacion
     */
    @Override
    public boolean updateCurso(Curso curso) {
        String oldLine = cursoToCSV(getCurso(curso.getNivel(), curso.getLetra())), newLine = cursoToCSV(curso);
        return this.datafile.updateLine(oldLine, newLine);
    }

    /**
     * Elimina un curso del archivo CSV
     *
     * @param curso Curso a eliminar
     * @return Valor de verdad (boolean) sobre el exito o fracaso de la operacion de borrado
     */
    @Override
    public boolean deleteCurso(Curso curso) {
        return this.datafile.deleteLine(cursoToCSV(curso));
    }
}