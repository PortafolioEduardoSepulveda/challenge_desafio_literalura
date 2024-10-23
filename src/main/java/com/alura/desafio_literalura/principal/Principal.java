package com.alura.desafio_literalura.principal;

import com.alura.desafio_literalura.model.Autor;
import com.alura.desafio_literalura.model.DatosGutendex;
import com.alura.desafio_literalura.model.Libro;
import com.alura.desafio_literalura.repository.AutorRepository;
import com.alura.desafio_literalura.repository.LibroRepository;
import com.alura.desafio_literalura.service.ConsumoAPI;
import com.alura.desafio_literalura.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "http://gutendex.com/books/?search=";
    //private final String API_KEY = "&languages=";
   // private String idioma = "es";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository repositoryLibro;
    private AutorRepository repositoryAutor;
    private List<Libro> libros;
    private List<Autor> mostrar_autor;
    public Principal(LibroRepository repository1,AutorRepository repository2 ) {
        this.repositoryLibro =  repository1;
        this.repositoryAutor =  repository2;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    Elija La Opción Através De Su Número:
                    1 - Listar Libros Por Titulo
                    2 - Listar Libros Registrados
                    3 - Listar Autores Registrados
                    4 - Listar Autores Vivos en un Determinado Año
                    5 - Listar Libros Por Idioma
                    6 - Listar 10 Libros Mas Descargados
                    7 - Buscar Autores Por Nombre
                    8 - Listar Autores Fallecidos desde un Determinado Año
                    9 - Generando Estadisticas de Libros Descargados
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    mostrarLibrosRegistrados();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    mostarAutoresPorFecha();
                    break;
                case 5:
                    busquedaPorLenguaje();
                    break;
                case 6:
                    buscar10LibosMAsDescargados();
                    break;
                case 7:
                    buscarAutoresPorNombre();
                    break;
                case 8:
                    buscarAutoresFallecidosDesdeDeteminadoAnio();
                    break;
                case 9:
                    generandoEstadisticasDescargas();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }


    }




    private void guardaDatosBd(DatosGutendex datos){
        int contador = Integer.parseInt(datos.cantidad());
        for (int i = 0; i < contador; i++) {
            Libro libro = new Libro(datos.libros().get(i));
            List<Autor> AutorRepo = new ArrayList<>();
            List<Autor> Autores = datos.libros().get(i).autores().stream().map(a -> new Autor(a.nombre(), a.fecha_nacimiento(), a.fecha_fallecimiento())).toList();

            List<Autor> autor = repositoryAutor.buscarPorNombre(Autores.get(0).getNombre());
            if (autor.isEmpty()){
                Autor autor_no_encon = new Autor(Autores.get(0).getNombre(),Autores.get(0).getFecha_nacimiento(),Autores.get(0).getFecha_fallecimiento());
                repositoryAutor.save(autor_no_encon);
                AutorRepo.add(autor_no_encon);
                libro.setAutores(AutorRepo);
            }else{
                AutorRepo.add(autor.get(0));
                libro.setAutores(AutorRepo);
            }
            System.out.println(libro);
            repositoryLibro.save(libro);

        }
    }

    private void buscarLibroWeb() {
        System.out.println("Escribe el nombre del Libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        DatosGutendex datos = getDatosLibros(nombreLibro);
        System.out.println("cantidad %s "+datos.cantidad()+"\n");
        if(datos.cantidad().equals("0")){
            System.out.println("Libro no Encontrado");
        }else{
            List<Libro> LibroBd = repositoryLibro.buscarLibroPorNombre(nombreLibro);
            if (LibroBd.isEmpty()){
                guardaDatosBd(datos);
            }else{
                System.out.println("Libro Encontrado en BD");
            }
            System.out.println(datos);
        }

    }

    private DatosGutendex getDatosLibros(String nombreLibro) {

        var json = consumoApi.obtenerDatos(URL_BASE+ nombreLibro.replace(" ","+") );
        DatosGutendex datos = conversor.obtenerDatos(json, DatosGutendex.class);
        return datos;
    }
    private void mostrarLibrosRegistrados() {
        libros = repositoryLibro.findAll();
        libros.stream()
              .forEach(System.out::println);
    }
    private void mostrarAutoresRegistrados() {
        mostrar_autor = repositoryAutor.findAll();
        mostrar_autor.stream()
                .forEach(System.out::println);
    }
    private void mostarAutoresPorFecha(){
        System.out.println("ingrese el año vivo de autor(es) que desea buscar");
        var fecha = teclado.nextLine();
        mostrar_autor = repositoryAutor.buscarPorUnDerminadoAnio(fecha);
        mostrar_autor.stream()
                .forEach(System.out::println);
    }
    private void busquedaPorLenguaje(){
        var idioma = "";
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    Elija La Opción Através De Su Número:
                    1 - es - español
                    2 - en - ingles
                    3 - fr - frances
                    4 - pt - portugues
                    5 - fi - finés
                    6 - hu - hungaro
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    idioma = "es";
                    opcion = 0;
                    break;
                case 2:
                    idioma = "en";
                    opcion = 0;
                    break;
                case 3:
                    idioma = "fr";
                    opcion = 0;
                    break;
                case 4:
                    idioma = "pt";
                    opcion = 0;
                    break;
                case 5:
                    idioma = "fi";
                    opcion = 0;
                    break;
                case 6:
                    idioma = "hu";
                    opcion = 0;
                    break;
                default:
                    System.out.println("Opción inválida");
            }

        }
        libros = repositoryLibro.busquedaPorLenguaje(idioma);
        libros.stream()
                .forEach(System.out::println);
    }
    private void buscar10LibosMAsDescargados() {
        libros = repositoryLibro.busqueda10LibrosMasDescargados();
        libros.stream()
                .forEach(System.out::println);
    }
    private void buscarAutoresPorNombre() {
        System.out.println("ingrese el Nombre del Autor A Buscar");
        var nombre = teclado.nextLine();
        mostrar_autor = repositoryAutor.buscarPorNombre(nombre);
        mostrar_autor.stream()
                .forEach(System.out::println);
    }
    private void buscarAutoresFallecidosDesdeDeteminadoAnio() {
        System.out.println("Ingrese año de fallecimiento desde el que se desea buscar");
        var fecha = teclado.nextLine();
        mostrar_autor = repositoryAutor.buscarPorFechadeFallecimiento(fecha);
        mostrar_autor.stream()
                .forEach(System.out::println);
    }
    private void generandoEstadisticasDescargas() {
        libros = repositoryLibro.findAll();
        DoubleSummaryStatistics est = libros.stream()
                .filter(e -> e.getCantidad_descargas() > 0)
                .collect(Collectors.summarizingDouble(Libro::getCantidad_descargas));
        System.out.println("\nMedia de la Cantidad de Descarga: " +String.format("%.2f", est.getAverage()) );
        System.out.println("Libro Mejor Descargado: " + est.getMax());
        System.out.println("Libro Peor Descargado: " + est.getMin()+"\n");



    }
}
