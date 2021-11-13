package aplicacion.data;

import aplicacion.models.Curso;
import aplicacion.models.IDCurso;

import java.util.List;

/**
 * Interfaz que permite interactuar con los datos de los cursos.
 *
 * @author Sebastián García, Guillermo González, Benjamín Navarrete
 * @version 3.0
 */
public interface CursoData {
    /**
     * Obtiene todos los cursos
     *
     * @return ArrayList de cursos
     */
    List<Curso> getCursos();

    /**
     * Obtiene todos los cursos del nivel especificado
     *
     * @param nivel Nivel
     * @return ArrayList de cursos del nivel especificado
     */
    List<Curso> getCursos(short nivel);

    /**
     * Obtiene un curso específico
     *
     * @param nivel Nivel del curso
     * @param letra Letra identificatoria de paralelo del curso
     * @return Curso buscado, o `null` en caso de no encontrarse
     */
    Curso getCurso(short nivel, char letra);

    /**
     * Obtiene un curso específico
     *
     * @param idCurso IDCurso con el nivel y paralelo del curso.
     * @return Curso buscado, o `null` en caso de no encontrarse
     */
    Curso getCurso(IDCurso idCurso);

    /**
     * Agrega un curso
     *
     * @param curso Curso a agregar
     */
    void insertCurso(Curso curso);

    /**
     * Actualiza un curso
     *
     * @param curso Curso a actualizar
     * @return Valor de verdad sobre el éxito o fracaso de la operación
     */
    boolean updateCurso(Curso curso);

    /**
     * Elimina un curso
     *
     * @param curso Curso a eliminar
     * @return Valor de verdad sobre el éxito o fracaso de la operación
     */
    boolean deleteCurso(Curso curso);
}