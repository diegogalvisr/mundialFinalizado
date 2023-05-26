/**/

package proyectomundial;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.table.*;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import proyectomundial.DAO.ResultadosDAO;
import proyectomundial.DAO.SeleccionDAO;
import proyectomundial.model.Resultados;
import proyectomundial.model.Seleccion;

public class GUIManual extends JFrame {

    SeleccionDAO seleccionDAO = new SeleccionDAO();
    ResultadosDAO resultadosDAO = new ResultadosDAO();

    // Matrix que permite almancenar la información de las selecciones futbol cargadas
    public String[][] selecciones = null;

    // Matriz que permite almacenar los resultados de los partidos cargardos
    public String[][] resultados = null;

    // Elementos de bara Lateral
    private JPanel jPanelLeft;

    private int contClickSelecciones = 0;
    private int contClickResultados = 0;
    private int contClickDashSelecciones = 0;
    private int contClickDashResultados = 0;
    private int contClickHome = 0;

    private JPanel jPanelIconFIFA;
    private JLabel iconFIFA;

    // Elementos de opciones de Menú
    private JPanel jPanelMenu;

    private JPanel jPanelMenuHome;
    private JLabel btnHome;

    private JPanel jPanelMenuSelecciones;
    private JLabel btnSelecciones;

    private JPanel jPanelMenuResultados;
    private JLabel btnResultados;

    private JPanel jPanelMenuDashboardSel;
    private JLabel btnDashboardSel;

    private JPanel jPanelMenuDashboardRes;
    private JLabel btnDashboardRes;

    private JPanel jPanelMenuAuditoria;
    private JLabel btnAuditoria;

    // Elementos de panel de contenido  
    private JPanel jPanelRight;
    private JPanel jPanelLabelTop;
    private JLabel jLabelTop;

    private JPanel jPanelMain;

    public GUIManual() {

        // Se inician los componentes gráficos
        initComponents();
        setTitle("Jeison Leal - Diego Galvis");
        // Se configuran propiedades de nuestra Ventana
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        // Se llama la función home para que al momento de iniciar la aplicacoón, por defecto se muestre el home
        accionHome();

    }

    private void initComponents() {

        // Inicializamos componentes del Menu Lateral
        jPanelLeft = new JPanel();

        jPanelIconFIFA = new JPanel();
        iconFIFA = new JLabel();
        jPanelMenu = new JPanel();

        jPanelMenuHome = new JPanel();
        btnHome = new JLabel();

        jPanelMenuSelecciones = new JPanel();
        btnSelecciones = new JLabel();

        jPanelMenuResultados = new JPanel();
        btnResultados = new JLabel();

        jPanelMenuDashboardSel = new JPanel();
        btnDashboardSel = new JLabel();

        jPanelMenuDashboardRes = new JPanel();
        btnDashboardRes = new JLabel();

        jPanelMenuAuditoria = new JPanel();
        btnAuditoria = new JLabel();

        // Pinta el logo de la aplicación
        pintarLogo();

        // Pinta la opción de menú del Home
        pintarMenuHome();

        // Pinta la opción de Menú de las Selecciones
        pintarMenuSelecciones();

        // Pinta la opción de Menú de los resultados
        pintarMenuResultados();

        // Pinta la opción de Menú del dashboard de equipo
        pintarMenuDashboardSel();

        // Pinta la opción de Menú del dahboard de resultados
        pintarMenuDashboardRes();

        // Pinta y ajuste diseño del contenedor del panel izquierdo
        pintarPanelIzquierdo();
        ////
        pintarMenuAuditoria();

        // Inicializa los componentes del panel derecho de los contenidos
        jPanelRight = new JPanel();
        jPanelLabelTop = new JPanel();
        jPanelMain = new JPanel();

        // Pinta la barra superrior de color azul claro, del panel de contenido
        pintarLabelTop();

        // Pinta y ajusta diseño del contenedor de contenidos
        pintarPanelDerecho();

        setTitle("Mundial");
        pack();
        setVisible(true);
    }

    private void pintarLogo() {
        jPanelIconFIFA.add(iconFIFA);
        jPanelIconFIFA.setOpaque(false);
        jPanelIconFIFA.setPreferredSize((new java.awt.Dimension(220, 80)));
        jPanelIconFIFA.setMaximumSize(jPanelIconFIFA.getPreferredSize());
        iconFIFA.setIcon(new ImageIcon(getClass().getResource("/resources/Easports_fifa_logo.svg.png")));
        jPanelLeft.add(jPanelIconFIFA, BorderLayout.LINE_START);

    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación del HOME Define estilos, etiquetas, iconos que
     * decoran la opción del Menú. Esta opción de Menu permite mostrar la página
     * de bienvenida de la aplicación
     */
    private void pintarMenuHome() {
        btnHome.setIcon(new ImageIcon(getClass().getResource("/resources/icons/home.png"))); // NOI18N
        btnHome.setText("Home");
        btnHome.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioHome = new JLabel();
        jPanelMenuHome.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuHome.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuHome.setLayout(new BorderLayout(15, 0));
        jPanelMenuHome.add(vacioHome, BorderLayout.WEST);
        jPanelMenuHome.add(btnHome, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuHome);

        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contClickHome++;
                System.out.println("Home");
                accionHome();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hacer click sobre la opción de
     * navegación Home Permite modificar la etiqueta de Navegación en Home,
     * remover los elementos que hay en el panel de contenidos y agregar la
     * imagen de inicio de la aplicación
     */
    private void accionHome() {
        jLabelTop.setText("Home");
        //jLabelTopDescription.setText("Bievenido al sistema de gestión de mundiales de fútbol");

        jPanelMain.removeAll();
        JPanel homePanel = new JPanel();
        JLabel imageHome = new JLabel();

        imageHome.setIcon(new ImageIcon(getClass().getResource("/resources/home.jpg"))); // NOI18N
        //imageHome.setPreferredSize(new java.awt.Dimension(810, 465));
        homePanel.add(imageHome);

        jPanelMain.add(homePanel, BorderLayout.CENTER);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de SELECCIONES Define estilos, etiquetas, iconos
     * que decoran la opción del Menú. Esta opción de Menu permite mostrar las
     * selecciones de futbol cargadas en la aplicación
     */
    private void pintarMenuSelecciones() {
        btnSelecciones.setIcon(new ImageIcon(getClass().getResource("/resources/icons/selecciones.png"))); // NOI18N
        btnSelecciones.setText("Selecciones");
        btnSelecciones.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioSelecciones = new JLabel();
        jPanelMenuSelecciones.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuSelecciones.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuSelecciones.setLayout(new BorderLayout(15, 0));
        jPanelMenuSelecciones.add(vacioSelecciones, BorderLayout.WEST);
        jPanelMenuSelecciones.add(btnSelecciones, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuSelecciones);

        btnSelecciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contClickSelecciones++;
                System.out.println("Selecciones");
                accionSelecciones();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hace click sobre la opción de
     * navegación Selecciones Permite ver la lista de selecciones que se
     * encuentran cargadas en la aplicación. Si la lista de selecciones en
     * vacía, muestra un botón que permite cargar un archivo CSV con la
     * información de las selelecciones
     */
    private void accionSelecciones() {
        jLabelTop.setText("Selecciones");
        selecciones = seleccionDAO.getSeleccionesMatriz();

        // Si no hay selecciones cargadas, muestra el botón de carga de selecciones
        if (selecciones == null) {
            jPanelMain.removeAll();
            JPanel seleccionesPanel = new JPanel();

            JLabel notSelecciones = new JLabel();
            notSelecciones.setText("No hay selecciones cargadas, por favor cargue selecciones \n\n");
            seleccionesPanel.add(notSelecciones);

            JButton cargarFile = new JButton();
            cargarFile.setText("Seleccione el archivo");
            seleccionesPanel.add(cargarFile);
            cargarFile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    cargarFileSelecciones();
                }
            });

            jPanelMain.add(seleccionesPanel);
            jPanelMain.repaint();
            jPanelMain.revalidate();
        } // Si hay selecciones cargadas, llama el método que permite pintar la tabla de selecciones
        else {
            pintarTablaSelecciones();
        }
    }

    private void accionAuditoria() {
        jLabelTop.setText("Auditoria");
        seleccionDAO.ingresarClick(contClickHome, contClickDashSelecciones, contClickDashResultados, contClickSelecciones, contClickResultados);
        contClickHome = 0;
        contClickDashSelecciones = 0;
        contClickDashResultados = 0;
        contClickSelecciones = 0;
        contClickResultados = 0;
        JPanel panelAuditoria = new JPanel();
        panelAuditoria.setBackground(Color.WHITE);
        jPanelMain.removeAll();
        panelAuditoria.setPreferredSize(new Dimension(1020, 800));

        JFreeChart chart = seleccionDAO.Auditoria();
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 400)); // Establecer el tamaño preferido del gráfico
        panelAuditoria.add(chartPanel, BorderLayout.CENTER);
        JFrame ventanaPrincipal = new JFrame("Auditoria");
        ventanaPrincipal.getContentPane().add(panelAuditoria, BorderLayout.CENTER);

        System.out.println("Clicks Home: " + contClickHome);
        //int home, int dashSelecciones, int dashResultados, int selecciones, int resultados

        jPanelMain.add(panelAuditoria);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    //pintarTablaSelecciones();
    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de RESULTADOS Define estilos, etiquetas, iconos
     * que decoran la opción del Menú. Esta opción de Menu permite mostrar los
     * diferentes resultados de los partidos de la fase de grupos de un mundial
     */
    private void pintarMenuResultados() {
        btnResultados.setIcon(new ImageIcon(getClass().getResource("/resources/icons/resultados.png"))); // NOI18N
        btnResultados.setText("Resultados");
        btnResultados.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioResultados = new JLabel();
        jPanelMenuResultados.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuResultados.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuResultados.setLayout(new BorderLayout(15, 0));
        jPanelMenuResultados.add(vacioResultados, BorderLayout.WEST);
        jPanelMenuResultados.add(btnResultados, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuResultados);

        btnResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contClickResultados++;
                accionResultados();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hace click sobre la opción de
     * navegación Resultados Permite ver la lista de resultados que se
     * encuentran cargadas en la aplicación. Si la lista de resultados en vacía,
     * muestra un botón que permite cargar un archivo CSV con la información de
     * los resultados
     */
    private void accionResultados() {
        jLabelTop.setText("Resultados");
        resultados = resultadosDAO.getResultadosMatriz();

        // Si no hay resultados cargados, muestra el botón de carga de resultados
        if (resultados == null) {
            jPanelMain.removeAll();
            JPanel resultadosPanel = new JPanel();

            if (resultados == null) {

                JLabel notResultados = new JLabel();
                notResultados.setText("No hay resultados, por favor cargue resultados \n\n");
                resultadosPanel.add(notResultados);

                JButton cargarFile = new JButton();
                cargarFile.setText("Seleccione el archivo");
                resultadosPanel.add(cargarFile);
                cargarFile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        cargarFileResultados();
                    }
                });
            }

            jPanelMain.add(resultadosPanel);
            jPanelMain.repaint();
            jPanelMain.revalidate();
        } // Si hay ressultados cargados, llama el método que permite pintar la tabla de resultados
        else {
            pintarTablaResultados();
        }
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de Dashboard de Selecciones Define estilos,
     * etiquetas, iconos que decoran la opción del Menú. Esta opción de Menu
     * permite mostrar los diferentes datos que será extraidos de la información
     * de las selecciones de futbol que fueron cargadas
     */
    private void pintarMenuDashboardSel() {
        btnDashboardSel.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_selecciones.png")));
        btnDashboardSel.setText("Dash Selecciones");
        btnDashboardSel.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioDashboardSelecciones = new JLabel();
        jPanelMenuDashboardSel.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuDashboardSel.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuDashboardSel.setLayout(new BorderLayout(15, 0));
        jPanelMenuDashboardSel.add(vacioDashboardSelecciones, BorderLayout.WEST);
        jPanelMenuDashboardSel.add(btnDashboardSel, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuDashboardSel);

        btnDashboardSel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contClickDashSelecciones++;
                System.out.println("Dashboard Selecciones");
                accionDashboardSel();
            }
        });
    }

    private void accionDashboardSel() {
        jLabelTop.setText("Dashboard Selecciones");
        jPanelMain.setBackground(Color.white);//JPanel principal
        JLabel TParCar = new JLabel();

        JPanel TParCars = new JPanel();//JPanel Partidos Cargados
        TParCars.add(TParCar, BorderLayout.CENTER);
        TParCars.setPreferredSize(new Dimension(400, 125));
        TParCars.setBackground(Color.getHSBColor(0.5f, 0.7f, 0.9f));
        TParCar.setText("<html><div style='text-align: center; font-size: 15pt;'><span style='font-size: 48;'>" + seleccionDAO.totalSelecciones() + "</span><br>Selecciones Cargadas</div></html>");
        ///////////////////////////////cantidad nacionalidades///////////////////
        JLabel nacion = new JLabel();

        JPanel Pnacion = new JPanel();//JPanel Partidos Cargados
        Pnacion.add(nacion, BorderLayout.CENTER);
        Pnacion.setPreferredSize(new Dimension(400, 125));
        Pnacion.setBackground(Color.getHSBColor(0.5f, 0.7f, 0.9f));
        nacion.setText("<html><div style='text-align: center; font-size: 15pt;'><span style='font-size: 48;'>" + seleccionDAO.totalNacionalidades() + "</span><br>Total Nacionalidades</div></html>");
        /////////////////////////////////////
///////////////////////SELECCIONES X CONTINENTE/////////////////
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.getHSBColor(0.5f, 0.7f, 0.9f));
        panel2.setLayout(new BorderLayout());
        JFreeChart chart2 = seleccionDAO.seleccionXcontinentes();
        ChartPanel chartPanel2 = new ChartPanel(chart2);
        chartPanel2.setPreferredSize(new Dimension(600, 200));
        JLabel titleLabel2 = new JLabel("Selecciones Por Continentes");
        titleLabel2.setFont(new Font("Arial", Font.PLAIN, 15));
        titleLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        panel2.add(titleLabel2, BorderLayout.NORTH);
        panel2.add(chartPanel2, BorderLayout.CENTER);
        ///////////////////////////////////
        ///////////////////////RANKING DIRECTORES/////////////////
        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.getHSBColor(0.5f, 0.7f, 0.9f));
        panel3.setLayout(new BorderLayout());
        JFreeChart chart = seleccionDAO.rankingDirectores();
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 200));
        JLabel titleLabel = new JLabel("Ranking Directores");
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel3.add(titleLabel, BorderLayout.NORTH);
        panel3.add(chartPanel, BorderLayout.CENTER);
        ///////////////////////////////////
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER); // centramos el texto

        ///////////
        jPanelMain.removeAll();
        jPanelMain.add(TParCars);
        jPanelMain.add(panel2);
        jPanelMain.add(Pnacion);
        jPanelMain.add(panel3);
        // Actualizar la interfaz
        jPanelMain.repaint();
        jPanelMain.revalidate();

    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de Dashboard de Resultados Define estilos,
     * etiquetas, iconos que decoran la opción del Menú. Esta opción de Menu
     * permite mostrar los diferentes datos que será extraidos de la información
     * de los resultados de los partidos que fueron cargados
     */
    private void pintarMenuDashboardRes() {
        btnDashboardRes.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_resultados.png")));
        btnDashboardRes.setText("Dash Resultados");
        btnDashboardRes.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioDashboardResultados = new JLabel();
        jPanelMenuDashboardRes.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuDashboardRes.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuDashboardRes.setLayout(new BorderLayout(15, 0));
        jPanelMenuDashboardRes.add(vacioDashboardResultados, BorderLayout.WEST);
        jPanelMenuDashboardRes.add(btnDashboardRes, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuDashboardRes);

        btnDashboardRes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contClickDashResultados++;
                System.out.println("Dashboard Resultados");
                accionDashboardRes();
            }
        });
    }

    /**
     * TRABAJO DEL ESTUDIANTE Se debe módificar este método para poder calcular
     * y pintar las diferentes informaciones que son solicitadas Revise el
     * proceso que se siguen en los demás métodos para poder actualizar la
     * información de los paneles
     */
    private void accionDashboardRes() {
        jLabelTop.setText("Dashboard Resultados");
        JLabel TParCar = new JLabel();
        TParCar.setText("<html><div style='text-align: center; font-size: 15pt;'><span style='font-size: 48;'>" + resultadosDAO.totalPartidos() + "</span><br>Partidos Cargados</div></html>");
        JPanel TParCars = new JPanel();//JPanel Partidos Cargados
        TParCars.add(TParCar, BorderLayout.CENTER);
        TParCars.setPreferredSize(new Dimension(250, 125));
        TParCars.setBackground(Color.getHSBColor(0.5f, 0.7f, 0.9f));
        JPanel Panel1 = new JPanel();//JPanel2
        Panel1.setPreferredSize(new Dimension(200, 125));
        Panel1.setBackground(Color.cyan);
        JLabel PGoPorPart = new JLabel();
        PGoPorPart.setText("<html><div style='text-align: center; font-size: 15pt;'><span style='font-size: 48;'>" + resultadosDAO.promedioGolePorPatidos() + "%</span><br>Promedio Goles</div></html>");
        Panel1.add(PGoPorPart, BorderLayout.CENTER);
//  dashboardPanel.setPreferredSize(new Dimension(610, 400));
        jPanelMain.setBackground(Color.white);//JPanel principal

        /// PANEL PARTIDO MAS GOLES
        JPanel Panel2 = new JPanel();//JPanel3
        Panel2.setPreferredSize(new Dimension(300, 125));
        Panel2.setBackground(Color.getHSBColor(0.5f, 0.7f, 0.9f));
        JTable table = new JTable();
        table.setBackground(Color.getHSBColor(0.5f, 0.7f, 0.9f));
        table.setShowGrid(false);
        Font font = new Font("Arial", Font.PLAIN, 14);
        table.setFont(font);
        table.setEnabled(false);
        table.setCellSelectionEnabled(false);
        table.setModel(resultadosDAO.partidosMasGoles());
        JLabel panel2 = new JLabel();
        panel2.setText("<html><div style='text-align: center; font-size: 15pt;'><span style='font-size: 20;'>Partidos Mas Goles</span></div></html>");
        Panel2.add(panel2);
        Panel2.add(table);
        ///////////
        //// PANEL4 PARTIDOS GANADOS --- PANEL 5PARTIDOS EMPATADOS
        JPanel Panel4 = new JPanel();
        JLabel panel4 = new JLabel();
        Panel4.setPreferredSize(new Dimension(250, 125));
        Panel4.setBackground(Color.getHSBColor(0.5f, 0.7f, 0.9f));
        JPanel Panel5 = new JPanel();
        JLabel panel5 = new JLabel();
        Panel5.setBackground(Color.cyan);
        Panel5.setPreferredSize(new Dimension(200, 125));
        int[] vector = resultadosDAO.partidoGanEmp();
        int partidosGanados = vector[0], partidosEmpatados = vector[1];
        panel4.setText("<html><div style='text-align: center; font-size: 15pt;'><span style='font-size: 48;'>" + partidosGanados + "</span><br>Partidos Ganados</div></html>");
        panel5.setText("<html><div style='text-align: center; font-size: 15pt;'><span style='font-size: 48;'>" + partidosEmpatados + "</span><br>Partidos Empatados</div></html>");
        Panel4.add(panel4);
        Panel5.add(panel5);
/////////////////////////////////////////////////////////

        /// PANEL PARTIDO MENOS GOLES
        JPanel Panel3 = new JPanel();//JPanel3
        Panel3.setPreferredSize(new Dimension(300, 125));
        Panel3.setBackground(Color.cyan);
        JTable tablePanel3 = new JTable();
        tablePanel3.setBackground(Color.cyan);
        tablePanel3.setShowGrid(false);
        Font fontPanel3 = new Font("Arial", Font.PLAIN, 14);
        tablePanel3.setFont(fontPanel3);
        tablePanel3.setEnabled(false);
        tablePanel3.setCellSelectionEnabled(false);
        tablePanel3.setModel(resultadosDAO.partidosMenosGoles());
        JLabel panel3 = new JLabel();
        panel3.setText("<html><div style='text-align: center; font-size: 15pt;'><span style='font-size: 20;'>Partidos Menos Goles</span></div></html>");
        Panel3.add(panel3);
        Panel3.add(tablePanel3);
        ///////////

////////////Panel MAS Goles//////////////////////////
        JPanel Panel6 = new JPanel();
        Panel6.setPreferredSize(new Dimension(300, 125));

// Obtener resultados y agregarlos al panel
        String[] seleccionesConMasGoles = resultadosDAO.seleccionesMasGoles();
        Panel6.setLayout(new GridLayout(seleccionesConMasGoles.length + 1, 1));
        JLabel titulo = new JLabel("<html><div style='text-align: center; font-size: 15pt;'><span style='font-size: 20;'>Selecciones con más goles</span><br></div></html>");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        Panel6.add(titulo);
        Panel6.setBackground(Color.getHSBColor(0.5f, 0.7f, 0.9f));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < seleccionesConMasGoles.length; i++) {
            sb.append(seleccionesConMasGoles[i]);
            if (i < seleccionesConMasGoles.length - 1) {
                sb.append("  ");
            }
        }
        JLabel label23 = new JLabel(sb.toString());
        label23.setHorizontalAlignment(SwingConstants.CENTER);
        Font fontPanel6 = new Font("Arial", Font.PLAIN, 18);
        label23.setFont(fontPanel6);
        Panel6.add(label23);

        ////////////////////////////////////////////
////////////Panel MENOS Goles//////////////////////////
        JPanel Panel7 = new JPanel();
        Panel7.setPreferredSize(new Dimension(450, 150));
        String[] seleccionesConMenosGoles = resultadosDAO.seleccionesMenosGoles();
        JLabel tituloM = new JLabel("<html><div style='text-align: center; font-size: 15pt;'><span style='font-size: 20;'>Selecciones con menos goles</span><br></div></html>");
        tituloM.setHorizontalAlignment(SwingConstants.CENTER);
        Panel7.add(tituloM);
        Panel7.setBackground(Color.cyan);
        StringBuilder sbM = new StringBuilder();
        for (int i = 0; i < seleccionesConMenosGoles.length; i++) {
            sbM.append(seleccionesConMenosGoles[i]);
            if (i < seleccionesConMenosGoles.length - 1) {
                sbM.append(" ");
            }
            if ((i + 1) % 2 == 0 || i == seleccionesConMenosGoles.length - 1) {
                JLabel labelM = new JLabel(sbM.toString());
                labelM.setHorizontalAlignment(SwingConstants.CENTER);
                Font fontPanelm = new Font("Arial", Font.PLAIN, 18);
                labelM.setFont(fontPanelm);
                Panel7.add(labelM);
                sbM = new StringBuilder();
            }
        }

        ////////////////////////////////////////////
        ////////////Panel Seleccion Mayor Puntuacion //////////////////////////
        JPanel Panel8 = new JPanel();
        Panel8.setPreferredSize(new Dimension(450, 150));
        String[] seleccionesConMayorPuntuacion = resultadosDAO.seleccionesMayorPuntuacion();
        JLabel tituloMP = new JLabel("<html><div style='text-align: center; font-size: 15pt;'><span style='font-size: 20;'>Selecciones con Mayor Puntuacion</span><br></div></html>");
        tituloMP.setHorizontalAlignment(SwingConstants.CENTER);
        Panel8.add(tituloMP);
        Panel8.setBackground(Color.getHSBColor(0.5f, 0.7f, 0.9f));
        StringBuilder sbMP = new StringBuilder();
        for (int i = 0; i < seleccionesConMayorPuntuacion.length; i++) {
            sbMP.append(seleccionesConMayorPuntuacion[i]);
            if (i < seleccionesConMayorPuntuacion.length - 1) {
                sbMP.append(" ");
            }
            if ((i + 1) % 2 == 0 || i == seleccionesConMayorPuntuacion.length - 1) {
                JLabel labelMP = new JLabel(sbMP.toString());
                labelMP.setHorizontalAlignment(SwingConstants.CENTER);
                Font fontPanellabelMP = new Font("Arial", Font.PLAIN, 18);
                labelMP.setFont(fontPanellabelMP);
                Panel8.add(labelMP);
                sbMP = new StringBuilder();
            }
        }

        ////////////////////////////////////////////
        ////////////Panel Seleccion Menor Puntuacion //////////////////////////
        JPanel Panel9 = new JPanel();
        Panel9.setPreferredSize(new Dimension(400, 100));
        String[] seleccionesConMenorPuntuacion = resultadosDAO.seleccionesMenorPuntuacion();
        JLabel tituloMPk = new JLabel("<html><div style='text-align: center; font-size: 15pt;'><span style='font-size: 20;'>Selecciones con Menor Puntuacion</span><br></div></html>");
        tituloMPk.setHorizontalAlignment(SwingConstants.CENTER);
        Panel9.add(tituloMPk);
        Panel9.setBackground(Color.cyan);
        StringBuilder sbMPk = new StringBuilder();
        for (int i = 0; i < seleccionesConMenorPuntuacion.length; i++) {
            sbMPk.append(seleccionesConMenorPuntuacion[i]);
            if (i < seleccionesConMenorPuntuacion.length - 1) {
                sbMPk.append(" ");
            }
            if ((i + 1) % 2 == 0 || i == seleccionesConMenorPuntuacion.length - 1) {
                JLabel labelMPk = new JLabel(sbMPk.toString());
                labelMPk.setHorizontalAlignment(SwingConstants.CENTER);
                Font fontPanellabelMPk = new Font("Arial", Font.PLAIN, 18);
                labelMPk.setFont(fontPanellabelMPk);
                Panel9.add(labelMPk);
                sbMPk = new StringBuilder();
            }
        }

        ////////////////////////////////////////////
        ////////////Panel Continente Mayor Puntuacion //////////////////////////00011111
        JPanel Panel10 = new JPanel();
        Panel10.setLayout(new BoxLayout(Panel10, BoxLayout.Y_AXIS)); // set vertical layout
        String[] continenteMayorPuntuacion = resultadosDAO.continentesMayorPuntos();
        Panel10.setBackground(Color.getHSBColor(0.5f, 0.7f, 0.9f));
        StringBuilder sbMPx = new StringBuilder();
        for (int i = 0; i < continenteMayorPuntuacion.length; i++) {
            sbMPx.append(continenteMayorPuntuacion[i]);
            if (i < continenteMayorPuntuacion.length - 1) {
                sbMPx.append(" <br>");
            }
            if ((i + 1) % 2 == 0 || i == continenteMayorPuntuacion.length - 1) {
                JLabel labelMPx = new JLabel("\n" + sbMPx.toString());
                labelMPx.setHorizontalAlignment(SwingConstants.CENTER);
                Font fontPanellabelMP = new Font("Arial", Font.PLAIN, 18);
                labelMPx.setFont(fontPanellabelMP);
                labelMPx.setAlignmentX(Component.CENTER_ALIGNMENT); // center the component
                Panel10.add(labelMPx);
                sbMPx = new StringBuilder();
            }
        }

        JLabel tituloMPx = new JLabel("<html><div style='text-align: center; font-size: 15pt;'><span style='font-size: 20;'>Continentes Mayor Puntuacion</span><br></div></html>");
        tituloMPx.setHorizontalAlignment(SwingConstants.CENTER);
        tituloMPx.setAlignmentX(Component.CENTER_ALIGNMENT); // center the component
        Panel10.add(tituloMPx, 0); // add the title label at index 0

        Panel10.setPreferredSize(new Dimension(300, 100)); // set the preferred size of the panel

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////Panel Continente Menor Puntuacion //////////////////////////////////////////////
        JPanel Panel11 = new JPanel();
        Panel11.setLayout(new BoxLayout(Panel11, BoxLayout.Y_AXIS));
        Panel11.setPreferredSize(new Dimension(300, 100));
        Panel11.setBackground(Color.cyan);
        String[] continenteMenorPuntuacion = resultadosDAO.continentesMenorPuntos();
        StringBuilder sbMPxx = new StringBuilder();
        for (int i = 0; i < continenteMenorPuntuacion.length; i++) {
            sbMPxx.append(continenteMenorPuntuacion[i]);
            if (i < continenteMenorPuntuacion.length - 1) {
                sbMPxx.append(" ");
            }
            if ((i + 1) % 2 == 0 || i == continenteMenorPuntuacion.length - 1) {
                JLabel labelMPxx = new JLabel("\n" + sbMPxx.toString());
                labelMPxx.setHorizontalAlignment(SwingConstants.CENTER);
                Font fontPanellabelMPx = new Font("Arial", Font.PLAIN, 18);
                labelMPxx.setFont(fontPanellabelMPx);
                labelMPxx.setAlignmentX(Component.CENTER_ALIGNMENT); // 
                Panel11.add(labelMPxx);
                sbMPxx = new StringBuilder();
            }
        }
        JLabel tituloMPxx = new JLabel("<html><div style='text-align: center; font-size: 15pt;'><span style='font-size: 20;'>Continentes con menor Puntuacion</span><br></div></html>");
        tituloMPxx.setHorizontalAlignment(SwingConstants.CENTER);
        tituloMPxx.setAlignmentX(Component.CENTER_ALIGNMENT);
        Panel11.add(tituloMPxx, 0);
        ////////////////////////////////////////////
        jPanelMain.removeAll();
        jPanelMain.add(TParCars);
        jPanelMain.add(Panel1);
        jPanelMain.add(Panel2);
        jPanelMain.add(Panel4);
        jPanelMain.add(Panel3);
        jPanelMain.add(Panel5);
        jPanelMain.add(Panel6);

        jPanelMain.add(Panel7);
        jPanelMain.add(Panel8);
        jPanelMain.add(Panel9);
        jPanelMain.add(Panel10);
        jPanelMain.add(Panel11);

        // Actualizar la interfaz
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    private void pintarMenuAuditoria() {
        btnAuditoria.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_resultados.png")));
        btnAuditoria.setText("Auditoria");
        btnAuditoria.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacionAuditoria = new JLabel();
        jPanelMenuAuditoria.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuAuditoria.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuAuditoria.setLayout(new BorderLayout(15, 0));
        jPanelMenuAuditoria.add(vacionAuditoria, BorderLayout.WEST);
        jPanelMenuAuditoria.add(btnAuditoria, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuAuditoria);

        btnAuditoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Dashboard Auditoria");
                accionAuditoria();
            }
        });
    }

    /**
     * Función que permite darle estilos y agregar los componentes gráficos del
     * contendor de la parte izquierda de la interfaz, dónde se visulaiza el
     * menú de navegaación
     */
    private void pintarPanelIzquierdo() {
        // Se elimina el color de fondo del panel del menú
        jPanelMenu.setOpaque(false);

        // Se agrega un border izquierdo, de color blanco para diferenciar el panel de menú del panel de contenido
        jPanelLeft.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.WHITE));

        // Se define un BoxLayot de manera vertical para los elementos del panel izquierdo
        jPanelLeft.setLayout(new BoxLayout(jPanelLeft, BoxLayout.Y_AXIS));
        jPanelLeft.setBackground(new java.awt.Color(0, 24, 47));
        getContentPane().add(jPanelLeft, java.awt.BorderLayout.LINE_START);
        jPanelLeft.add(jPanelMenu);
        jPanelLeft.setPreferredSize((new java.awt.Dimension(220, 540)));
        jPanelLeft.setMaximumSize(jPanelLeft.getPreferredSize());
    }

    /**
     * Función que permite leer un archivo y procesar el contenido que tiene en
     * cada una de sus líneas El contenido del archivo es procesado y cargado en
     * la matriz de selecciones. Una vez la información se carga en la atriz, se
     * hace un llamado a la función pintarTablaSelecciones() que se encarga de
     * pintar en la interfaz una tabla con la información almacenada en la
     * matriz de selecciones
     */
    public void cargarFileSelecciones() {

        JFileChooser cargarFile = new JFileChooser();
        cargarFile.showOpenDialog(cargarFile);

        Scanner entrada = null;
        try {

            // Se obtiene la ruta del archivo seleccionado
            String ruta = cargarFile.getSelectedFile().getAbsolutePath();

            // Se obtiene el archivo y se almancena en la variable f
            File f = new File(ruta);
            entrada = new Scanner(f);

            // Permite que el sistema se salte la léctura de los encabzados del archivo CSV
            entrada.nextLine();

            // Se leen cada unas de las líneas del archivo
            while (entrada.hasNext()) {
                String line = entrada.nextLine();
                String[] columns = line.split(",");

                Seleccion seleccion = new Seleccion(columns[1], columns[2], columns[3], columns[4]);
                if (seleccionDAO.registrarSeleccion(seleccion)) {
                    System.out.println("Seleccion " + seleccion.getNombre() + " registrada");
                } else {
                    System.out.println("Error " + seleccion.getNombre());
                }
            }

            selecciones = seleccionDAO.getSeleccionesMatriz();
            pintarTablaSelecciones();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }

    /**
     * Función que se encarga de pinta la tabla con la información de las
     * selelceciones que fue cargada previamente La tabla tiene definido un
     * encabezado con las siguentes columnas: {"ID","Selección", "Continente",
     * "DT", "Nacionalidad DT"} Columnas que se corresponden son la información
     * que fue leida desde el archivo csv
     */
    public void pintarTablaSelecciones() {

        String[] columnNames = {"Selección", "Continente", "DT", "Nacionalidad DT"};
        JTable table = new JTable(selecciones, columnNames);
        table.setRowHeight(30);

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 0));

        JLabel label = new JLabel();
        label.setText("Busqueda de Equipos");
        form.add(label);

        JTextField field = new JTextField();
        form.add(field);

        JPanel panelBotones = new JPanel();
        // panelBotones.setLayout(new GridLayout(1, 2, 30, 0));

        JButton buscar = new JButton();
        buscar.setText("Buscar");
        panelBotones.add(buscar);

        // Agregar ActionListener al botón "buscar"
        buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Código a ejecutar cuando se detecte el evento de clic en el botón "buscar"
                // Obtener la lista de selecciones que coinciden con la búsqueda
                List<Seleccion> seleccionesBusqueda = seleccionDAO.getSeleccionesBusqueda(field.getText());
                /* List<Resultados>resultadosBusqueda=resultadosDAO.getResultadosBusqueda(field.getText());*/

                // Crear un nuevo modelo de tabla
                DefaultTableModel modeloTabla = new DefaultTableModel();
                modeloTabla.addColumn("Nombre");
                modeloTabla.addColumn("Continente");
                modeloTabla.addColumn("DT");
                modeloTabla.addColumn("Nacionalidad");

// Agregar las filas correspondientes a la lista de selecciones
                for (Seleccion seleccion : seleccionesBusqueda) {
                    modeloTabla.addRow(new Object[]{seleccion.getNombre(), seleccion.getContinente(), seleccion.getDt(), seleccion.getNacionalidad()});
                }

// Establecer el nuevo modelo de tabla en la JTable
                table.setModel(modeloTabla);
                System.out.println("Se ha hecho clic en el botón 'buscar'");
                // Aquí puedes llamar al método que realiza la búsqueda con el texto ingresado por el usuario en el campo correspondiente
            }
        });

        JButton limpiar = new JButton();
        limpiar.setText("Ver Todos");
        panelBotones.add(limpiar);
        form.add(panelBotones);

        limpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Código a ejecutar cuando se detecte el evento de clic en el botón "buscar"

                // Obtener la lista de selecciones que coinciden con la búsqueda
                field.setText("");
                List<Seleccion> seleccionesBusqueda = seleccionDAO.getSeleccionesBusqueda("");
                // Crear un nuevo modelo de tabla
                DefaultTableModel modeloTabla = new DefaultTableModel();
                modeloTabla.addColumn("Nombre");
                modeloTabla.addColumn("Continente");
                modeloTabla.addColumn("DT");
                modeloTabla.addColumn("Nacionalidad");

// Agregar las filas correspondientes a la lista de selecciones
                for (Seleccion seleccion : seleccionesBusqueda) {
                    modeloTabla.addRow(new Object[]{seleccion.getNombre(), seleccion.getContinente(), seleccion.getDt(), seleccion.getNacionalidad()});
                }

// Establecer el nuevo modelo de tabla en la JTable
                table.setModel(modeloTabla);

                System.out.println("Se ha hecho clic en el botón 'buscar'");
                // Aquí puedes llamar al método que realiza la búsqueda con el texto ingresado por el usuario en el campo correspondiente
            }
        });

        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(900, 500)));
        seleccionesPanel.setMaximumSize(jPanelRight.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(form);
        seleccionesPanel.add(scrollPane);

        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que tiene la lógica que permite leer un archivo CSV de resultados
     * y cargarlo sobre la matriz resultados que se tiene definida cómo variable
     * global. Luego de cargar los datos en la matriz, se llama la función
     * pintarTablaResultados() que se encarga de visulizar el contenido de la
     * matriz en un componente gráfico de tabla
     */
    public void cargarFileResultados() {

        JFileChooser cargarFile = new JFileChooser();
        cargarFile.showOpenDialog(cargarFile);

        Scanner entrada = null;
        try {
            // Se obtiene la ruta del archivo seleccionado
            String ruta = cargarFile.getSelectedFile().getAbsolutePath();

            // Se obtiene el archivo y se almancena en la variable f
            File f = new File(ruta);
            entrada = new Scanner(f);

            // Se define las dimensiones de la matriz de selecciones
            resultados = new String[48][7];
            entrada.nextLine();

            int i = 0;
            // Se iteran cada una de las líneas del archivo
            while (entrada.hasNext()) {
                String line = entrada.nextLine();
                String[] columns = line.split(",");

                Resultados resultados = new Resultados(columns[1], columns[2], columns[3], columns[4], columns[5], columns[6], columns[7]);
                if (resultadosDAO.registrarResultados(resultados)) {
                    System.out.println("Resultado del grupo  " + resultados.getGrupo() + " registrado");
                } else {
                    System.out.println("Error " + resultados.getGrupo());
                }
            }
            resultados = resultadosDAO.getResultadosMatriz();
            pintarTablaResultados();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }

    /**
     * Función que se encarga de pintar la tabla con la información de los
     * resultados que fue cargada previamente La tabla tiene definido un
     * encabezado con las siguentes columnas: {"Grupo","Local", "Visitante",
     * "Continente L", "Continente V", "Goles L", "Goles V"} Columnas que se
     * corresponden son la información que fue leida desde el archivo csv
     */
    public void pintarTablaResultados() {

        String[] columnNames = {"Grupo", "Local", "Visitante", "Continente L", "Continente V", "Goles L", "Goles V"};
        JTable table = new JTable(resultados, columnNames);
        table.setRowHeight(30);

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 0));

        JLabel label = new JLabel();
        label.setText("Busqueda de Resultados");
        form.add(label);

        JTextField field = new JTextField();
        form.add(field);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 30, 0));

        JButton buscar = new JButton();
        buscar.setText("Buscar");
        panelBotones.add(buscar);

        buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Código a ejecutar cuando se detecte el evento de clic en el botón "buscar"
                // Obtener la lista de selecciones que coinciden con la búsqueda
                //  List<Seleccion> seleccionesBusqueda = seleccionDAO.getSeleccionesBusqueda(field.getText());
                List<Resultados> resultadosBusqueda = resultadosDAO.getResultadosBusqueda(field.getText());

                // Crear un nuevo modelo de tabla
                DefaultTableModel modeloTabla = new DefaultTableModel();
                modeloTabla.addColumn("Grupo");
                modeloTabla.addColumn("Local");
                modeloTabla.addColumn("Visitante");
                modeloTabla.addColumn("ContinenteL");
                modeloTabla.addColumn("ContinenteV");
                modeloTabla.addColumn("golesL");
                modeloTabla.addColumn("GolesV");

// Agregar las filas correspondientes a la lista de selecciones
                for (Resultados resultado : resultadosBusqueda) {
                    modeloTabla.addRow(new Object[]{resultado.getGrupo(), resultado.getLocal(), resultado.getVisitante(), resultado.getContinenteL(), resultado.getContinenteV(), resultado.getGolesL(), resultado.getGolesV()});
                }

// Establecer el nuevo modelo de tabla en la JTable
                table.setModel(modeloTabla);
                System.out.println("Se ha hecho clic en el botón 'buscar' de resultados");
                // Aquí puedes llamar al método que realiza la búsqueda con el texto ingresado por el usuario en el campo correspondiente
            }
        });

        JButton limpiar = new JButton();
        limpiar.setText("Ver Todos");
        panelBotones.add(limpiar);
        form.add(panelBotones);

        limpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                field.setText("");
                List<Resultados> resultadosBusqueda = resultadosDAO.getResultadosBusqueda("");

                // Crear un nuevo modelo de tabla
                DefaultTableModel modeloTabla = new DefaultTableModel();
                modeloTabla.addColumn("Grupo");
                modeloTabla.addColumn("Local");
                modeloTabla.addColumn("Visitante");
                modeloTabla.addColumn("ContinenteL");
                modeloTabla.addColumn("ContinenteV");
                modeloTabla.addColumn("golesL");
                modeloTabla.addColumn("GolesV");

// Agregar las filas correspondientes a la lista de selecciones
                    for (Resultados resultado : resultadosBusqueda) {
                    modeloTabla.addRow(new Object[]{resultado.getGrupo(), resultado.getLocal(), resultado.getVisitante(), resultado.getContinenteL(), resultado.getContinenteV(), resultado.getGolesL(), resultado.getGolesV()});
                }

// Establecer el nuevo modelo de tabla en la JTable
                table.setModel(modeloTabla);
                System.out.println("Se ha hecho clic en el botón 'buscar' de resultados");
                // Aquí puedes llamar al método que realiza la búsqueda con el texto ingresado por el usuario en el campo correspondiente
            }
        });

        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(900, 500)));
        seleccionesPanel.setMaximumSize(jPanelRight.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(form);
        seleccionesPanel.add(scrollPane);

        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que permite darle estilos y agregar los componentes gráficos del
     * contendor de la parte derecha de la interfaz, dónde se visulaiza de
     * manera dinámica el contenido de cada una de las funciones que puede
     * realizar el usuario sobre la aplicación.
     */
    private void pintarPanelDerecho() {
        // Define las dimensiones del panel
        jPanelMain.setPreferredSize((new java.awt.Dimension(1020, 660)));
        jPanelMain.setMaximumSize(jPanelLabelTop.getPreferredSize());
        getContentPane().add(jPanelRight, java.awt.BorderLayout.CENTER);
        jPanelRight.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        jPanelRight.add(jPanelLabelTop, BorderLayout.LINE_START);
        jPanelRight.add(jPanelMain);
        jPanelRight.setPreferredSize((new java.awt.Dimension(1020, 660)));
        jPanelRight.setMaximumSize(jPanelRight.getPreferredSize());
    }
    private void pintarLabelTop() {
        jLabelTop = new JLabel();
        jLabelTop.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        jLabelTop.setForeground(new java.awt.Color(241, 241, 241));
        jLabelTop.setText("   Home");

        JLabel vacioTopLabel = new JLabel();
        jPanelLabelTop.setLayout(new BorderLayout(15, 0));
        jPanelLabelTop.add(vacioTopLabel, BorderLayout.WEST);
        jPanelLabelTop.setBackground(new java.awt.Color(18, 119, 217));
        jPanelLabelTop.add(jLabelTop, BorderLayout.CENTER);
        jPanelLabelTop.setPreferredSize((new java.awt.Dimension(1060, 120)));
        jPanelLabelTop.setMaximumSize(jPanelLabelTop.getPreferredSize());
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIManual().setVisible(true);
            }
        });
    }
}
