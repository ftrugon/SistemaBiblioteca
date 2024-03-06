import java.util.Date

data class Libro(val isbn: String, val titulo:String, val autor:String, var estado:Estado = Estado.DISPONIBLE)

enum class Estado(){
    DISPONIBLE,
    PRESTADO
}

class SistemaGestion(){

    val inventario = mutableListOf<Libro>()

    fun agregarLibro(libro: Libro){
        inventario.add(libro)
        println("Se ha agreado '${libro.titulo}' al inventario")
    }

    fun eliminarLibro(isbn: String){
        val libro = inventario.find { it.isbn == isbn }
        if (libro != null) {
            println("Se ha eliminado '${libro.titulo}' al inventario")
        }else println("No se ha encontrado el libro")
    }

    fun estaDisponible(isbn: String):Boolean{
        val libro = inventario.find { it.isbn == isbn }
        if (libro != null){
            if (libro.estado == Estado.DISPONIBLE){
                return true
            }
        }
        return false
    }
}

class Prestamo(val sistemaGestion: SistemaGestion){

    fun prestar(libro: Libro){
        if (sistemaGestion.estaDisponible(libro.isbn)){
            libro.estado = Estado.PRESTADO
            println("Se te prestar8a el libro")
        }else println("El libro esta prestado")
    }

    fun devolver(libro: Libro){
        if (!sistemaGestion.estaDisponible(libro.isbn)){
            libro.estado = Estado.DISPONIBLE
            println("El libro se ha delvuelto")
        }else println("El libro esta prestado")
    }
}

class InformeLibro(val sistemaGestion: SistemaGestion){
    fun generarCompleto(){
        //La logica tal
        println("Generando el informe de libro")
    }

    fun generarDisponibles(){

    }

    fun generarPrestados(){

    }
}

fun main() {

    val libro1 = Libro("123-456-789","Kotl  in para principiantes","Juan Perez")
    val libro2 = Libro("987-654-321","Desarrollo avanzado con kotlin","Ana Lopez")
    val libro3 = Libro("456-789-123","Fundamentos de programacion en kotlin","Carlos Garcia")

    val gestorInv = SistemaGestion()
    gestorInv.agregarLibro(libro1)
    gestorInv.agregarLibro(libro2)
    gestorInv.agregarLibro(libro3)

    val gestorPres = Prestamo(gestorInv)
    gestorPres.prestar(libro1)
    gestorPres.prestar(libro3)
    gestorPres.devolver(libro1)


}