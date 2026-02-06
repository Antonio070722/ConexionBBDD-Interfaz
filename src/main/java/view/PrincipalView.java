package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PrincipalView extends JFrame {
    private final JButton btnAdd = new JButton("Añadir");
    private final JButton btnBuscar = new JButton("Buscar");
    private final JButton btnBorrar = new JButton("Borrar");
    private final JButton btnRefrescar = new JButton("Refrescar");

    private final  JLabel lblEstado = new JLabel("Aucorsa");

    private DefaultTableModel modeloTabla;
    private JTable vistaTabla;

    public PrincipalView() {
        this.setTitle("Aucorsa - Ventana Principal");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(850, 450);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));
        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        top.add(btnAdd);

        top.add(btnBorrar);
        top.add(btnRefrescar);
        add(top, BorderLayout.NORTH);

        JPanel west = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        west.setSize(80,80);
        west.add(btnBuscar);
        west.add(btnBorrar);
        add(west, BorderLayout.WEST);

        top.add(btnBuscar);

        JPanel bot = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bot.add(lblEstado);
        add(bot, BorderLayout.SOUTH);

        modeloTabla = new DefaultTableModel(new String[]{"numeroConductor", "nombre", "apellidos"}, 0);

        vistaTabla = new JTable(modeloTabla);

        add(vistaTabla, BorderLayout.CENTER);

    }
}
