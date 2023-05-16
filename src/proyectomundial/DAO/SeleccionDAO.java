/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomundial.DAO;
import java.sql.Connection;
import java.awt.Color;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import proyectomundial.model.Seleccion;
import proyectomundial.util.BasedeDatos;

/**
 *
 * @author miguelropero
 */
public class SeleccionDAO {

    public class Barra {

        private String etiqueta;
        private int valor;

        public Barra(String etiqueta, int valor) {
            this.etiqueta = etiqueta;
            this.valor = valor;
        }

        public String getEtiqueta() {
            return etiqueta;
        }

        public int getValor() {
            return valor;
        }
    }

    public SeleccionDAO() {
        BasedeDatos.conectar();
    }

    public boolean registrarSeleccion(Seleccion seleccion) {

        String sql = "INSERT INTO j_galvis16.seleccion (nombre, continente, dt, nacionalidad) values("
                + "'" + seleccion.getNombre() + "', "
                + "'" + seleccion.getContinente() + "', "
                + "'" + seleccion.getDt() + "', "
                + "'" + seleccion.getNacionalidad() + "')";

        //BasedeDatos.conectar();
        boolean registro = BasedeDatos.ejecutarActualizacionSQL(sql);
        //BasedeDatos.desconectar();
        return registro;
    }

    public int totalSelecciones() {
        int totalSelecciones = 0;
        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT COUNT(*) AS total_selecciones FROM j_galvis16.seleccion");
            if (result.next()) {
                totalSelecciones = result.getInt("total_selecciones");
            }
        } catch (Exception e) {
            // Manejar la excepción o propagarla hacia arriba
        }
        return totalSelecciones;
    }
    public JFreeChart Auditoria() {
    List<Barra> barras = new ArrayList<Barra>();
    try {
        ResultSet result = BasedeDatos.ejecutarSQL("SELECT pagina, contador FROM j_galvis16.auditoria;");
        while (result.next()) {
            String etiqueta = result.getString("pagina");
            int valor = result.getInt("contador");
            barras.add(new Barra(etiqueta, valor));
        }
    } catch (Exception e) {
        // Manejar la excepción cerrando la conexión a la base de datos
    }

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    for (Barra barra : barras) {
        dataset.setValue(barra.getValor(), barra.getEtiqueta(), barra.getEtiqueta());
    }

    JFreeChart chart = ChartFactory.createBarChart("", "Páginas", "Contador", dataset, PlotOrientation.VERTICAL, false, true, false);

    // Personalización del gráfico
    CategoryPlot plot = chart.getCategoryPlot();
    plot.setBackgroundPaint(Color.getHSBColor(0.5f, 0.7f, 0.9f));
    plot.setRangeGridlinePaint(Color.BLACK);
    plot.getRenderer().setSeriesPaint(0, Color.BLUE);

    return chart;
}

    
  public void ingresarClick(int home, int dashSelecciones, int dashResultados, int selecciones, int resultados) {
    try {
        Connection conexion = BasedeDatos.conexion; // Obtener conexión a la base de datos
        PreparedStatement statement;

        // Actualizar la página Home
        statement = conexion.prepareStatement("UPDATE j_galvis16.auditoria SET contador = contador + ? WHERE pagina = ?");
        statement.setInt(1, home); // Establecer el valor a sumar al contador existente
        statement.setString(2, "Home"); // Establecer la condición para actualizar los registros
        int filasActualizadas = statement.executeUpdate();
        System.out.println("Filas actualizadas para Home: " + filasActualizadas);

        // Actualizar la página Dash Selecciones
        statement = conexion.prepareStatement("UPDATE j_galvis16.auditoria SET contador = contador + ? WHERE pagina = ?");
        statement.setInt(1, dashSelecciones);
        statement.setString(2, "Dash Selecciones");
        filasActualizadas = statement.executeUpdate();
        System.out.println("Filas actualizadas para Dash Selecciones: " + filasActualizadas);

        // Actualizar la página Dash Resultados
        statement = conexion.prepareStatement("UPDATE j_galvis16.auditoria SET contador = contador + ? WHERE pagina = ?");
        statement.setInt(1, dashResultados);
        statement.setString(2, "Dash Resultados");
        filasActualizadas = statement.executeUpdate();
        System.out.println("Filas actualizadas para Dash Resultados: " + filasActualizadas);

        // Actualizar la página Selecciones
        statement = conexion.prepareStatement("UPDATE j_galvis16.auditoria SET contador = contador + ? WHERE pagina = ?");
        statement.setInt(1, selecciones);
        statement.setString(2, "Selecciones");
        filasActualizadas = statement.executeUpdate();
        System.out.println("Filas actualizadas para Selecciones: " + filasActualizadas);

        // Actualizar la página Resultados
        statement = conexion.prepareStatement("UPDATE j_galvis16.auditoria SET contador = contador + ? WHERE pagina = ?");
        statement.setInt(1, resultados);
        statement.setString(2, "Resultados");
        filasActualizadas = statement.executeUpdate();
        System.out.println("Filas actualizadas para Resultados: " + filasActualizadas);

        //statement.close();
        //conexion.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}




    
    

    public int totalNacionalidades() {
        int totalNacionalidades = 0;
        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT COUNT(DISTINCT nacionalidad) AS total_nacionalidades FROM j_galvis16.seleccion;");
            if (result.next()) {
                totalNacionalidades = result.getInt("total_nacionalidades");
            }
        } catch (Exception e) {
            // Manejar la excepción o propagarla hacia arriba
        }
        return totalNacionalidades;
    }
    
    public JFreeChart seleccionXcontinentes() {
        List<Barra> barras = new ArrayList<Barra>();
        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT continente as continentes, SUM(cantidad_selecciones) AS total_selecciones FROM (SELECT continente, nombre, COUNT(*) AS cantidad_selecciones FROM j_galvis16.seleccion GROUP BY continente, nombre) AS subconsulta GROUP BY continente");
            while (result.next()) {
                String etiqueta = result.getString("continentes");
                int valor = result.getInt("total_selecciones");
                barras.add(new Barra(etiqueta, valor));
            }
        } catch (Exception e) {
            // Manejar la excepción cerrando la conexión a la base de datos
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Barra barra : barras) {
            dataset.setValue(barra.getValor(), barra.getEtiqueta(), ""); // agregamos el nombre de la nacionalidad como etiqueta de la columna
        }
        JFreeChart chart = ChartFactory.createBarChart("", "Contienntes", "Cantidad Selecciones", dataset, PlotOrientation.VERTICAL, false, true, false);
        // Personalización del gráfico
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.getRenderer().setSeriesPaint(0, Color.BLUE);

        return chart;
    }

    public DefaultTableModel seleccionXcontinente() {
        String[] columnas = {"Continente", "Total Selecciones"};
        Object[][] matrizResultados = new Object[0][columnas.length];
        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT continente as continentes, SUM(cantidad_selecciones) AS total_selecciones FROM (SELECT continente, nombre, COUNT(*) AS cantidad_selecciones FROM j_galvis16.seleccion GROUP BY continente, nombre) AS subconsulta GROUP BY continente");
            while (result.next()) {
                String continente = result.getString("continentes");
                int totalSelecciones = result.getInt("total_selecciones");
                Object[] fila = {continente, totalSelecciones};
                matrizResultados = Arrays.copyOf(matrizResultados, matrizResultados.length + 1);
                matrizResultados[matrizResultados.length - 1] = fila;
            }
        } catch (Exception e) {
            // Manejar la excepción cerrando la conexión a la base de datos
        }
        DefaultTableModel modeloTabla = new DefaultTableModel(matrizResultados, columnas);
        return modeloTabla;
    }

    public JFreeChart rankingDirectores() {
        List<Barra> barras = new ArrayList<Barra>();
        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT nacionalidad, COUNT(*) as cantidad_entrenadores FROM j_galvis16.seleccion WHERE dt IS NOT NULL GROUP BY nacionalidad ORDER BY cantidad_entrenadores DESC;");
            while (result.next()) {
                String etiqueta = result.getString("nacionalidad");
                int valor = result.getInt("cantidad_entrenadores");
                barras.add(new Barra(etiqueta, valor));
            }
        } catch (Exception e) {
            // Manejar la excepción cerrando la conexión a la base de datos
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Barra barra : barras) {
            dataset.setValue(barra.getValor(), barra.getEtiqueta(), ""); // agregamos el nombre de la nacionalidad como etiqueta de la columna
        }

        JFreeChart chart = ChartFactory.createBarChart("", "Nacionalidades", "Cantidad Entrenadores", dataset, PlotOrientation.VERTICAL, false, true, false);

        // Personalización del gráfico
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.getHSBColor(0.5f, 0.7f, 0.9f));
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.getRenderer().setSeriesPaint(0, Color.BLUE);

        return chart;
    }

    public List<Seleccion> getSelecciones() {

        String sql = "SELECT nombre, continente, dt, nacionalidad FROM j_galvis16.seleccion";
        List<Seleccion> selecciones = new ArrayList<Seleccion>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Seleccion seleccion = new Seleccion(result.getString("nombre"), result.getString("continente"), result.getString("dt"), result.getString("nacionalidad"));
                    selecciones.add(seleccion);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }

        return selecciones;
    }


   public List<Seleccion> getSeleccionesBusqueda(String nombreSeleccion) {
    System.out.println("LLEGAMOS HASTA GETSELECCIONESBUSQUEDA");
    String sql = "SELECT nombre, continente, dt, nacionalidad FROM j_galvis16.seleccion WHERE nombre LIKE ? OR continente LIKE ? OR dt LIKE ? OR nacionalidad LIKE ?";
    List<Seleccion> selecciones = new ArrayList<Seleccion>();

    try {
        if (BasedeDatos.conexion == null) {
            // Mostrar un mensaje de error o lanzar una excepción
            System.out.println("No hay conexión a la base de datos");
            return selecciones;
        }
        // Preparar la consulta SQL y establecer el parámetro
        PreparedStatement stmt = BasedeDatos.conexion.prepareStatement(sql);
        String parametro = "%" + nombreSeleccion + "%";
        stmt.setString(1, parametro);
        stmt.setString(2, parametro);
        stmt.setString(3, parametro);
        stmt.setString(4, parametro);

        // Ejecutar la consulta y obtener el resultado
        ResultSet result = stmt.executeQuery();

        if (result != null) {
            while (result.next()) {
                Seleccion seleccion = new Seleccion(result.getString("nombre"), result.getString("continente"), result.getString("dt"), result.getString("nacionalidad"));
                selecciones.add(seleccion);
            }
        }
    } catch (Exception e) {
        System.out.println(e.toString());
        System.out.println("Error consultando selecciones");
    }

    return selecciones;
}


    public String[][] getSeleccionesMatriz() {

        String[][] matrizSelecciones = null;
        List<Seleccion> selecciones = getSelecciones();

        if (selecciones != null && selecciones.size() > 0) {

            matrizSelecciones = new String[selecciones.size()][4];

            int x = 0;
            for (Seleccion seleccion : selecciones) {

                matrizSelecciones[x][0] = seleccion.getNombre();
                matrizSelecciones[x][1] = seleccion.getContinente();
                matrizSelecciones[x][2] = seleccion.getDt();
                matrizSelecciones[x][3] = seleccion.getNacionalidad();
                x++;
            }
        }

        return matrizSelecciones;
    }
}
