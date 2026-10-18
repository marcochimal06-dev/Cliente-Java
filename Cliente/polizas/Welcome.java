
package polizas;

import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Welcome extends JFrame implements ActionListener {
    
    
    JMenuBar bm; // barra de menu
    JMenu mArchivo, mBD; //menus
    JMenuItem mBD_C, mBD_R; // submenus

    
    JLabel lb1, lb1d, lbnombre, lbapellido, lbtelefono, lbedad, lbsexo;
    JTextField txtid, txtnombre, txtapellido, txttelefono, txtedad, txtsexo;
    JButton enviar, agregar, actualizar, eliminar;
    Persona persona;

    Welcome() {
        this.setBounds(150, 50, 800, 450);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        
        bm = new JMenuBar();
        this.setJMenuBar(bm);
        //PARA MENUS
        mArchivo = new JMenu("Archivo");
        bm.add(mArchivo);
        //menu
        mBD = new JMenu("Base de datos");
        bm.add(mBD);
        
        //SUBMENUS
        this.mBD_C = new JMenuItem("Crear");
        mBD.add(mBD_C);
        
        this.mBD_R = new JMenuItem("Consulta");
        mBD.add(mBD_R);
        
        this.mBD_R = new JMenuItem("Actualizar");
        mBD.add(mBD_R);
        
        this.mBD_R = new JMenuItem("Eliminar");
        mBD.add(mBD_R);
        
        this.mBD_R.addActionListener(e -> {
            JOptionPane.showMessageDialog(rootPane, "Evento capturado");
    consultar();
                    
        });

       
        this.lb1 = new JLabel("Registro de Clientes");
        this.lb1.setBounds(230, 15, 200, 30);
        this.add(lb1);

        //datos requeridos/se piden obligatoriamente
        this.lb1d = new JLabel("ID");
        this.lb1d.setBounds(100, 45, 80, 25);
        this.add(this.lb1d);

        this.txtid = new JTextField("");
        this.txtid.setBounds(180, 45, 270, 25);
        this.add(this.txtid);

       
        this.lbnombre = new JLabel("Nombre");
        this.lbnombre.setBounds(100, 75, 80, 25);
        this.add(lbnombre);
        // falta el txt aqui
        this.txtnombre = new JTextField("");
        this.txtnombre.setBounds(180, 75, 270, 25);
        this.add(txtnombre);
        
        this.lbapellido = new JLabel("Apellido");
        this.lbapellido.setBounds(100, 105, 80, 25);
        this.add(lbapellido);
        
        this.txtapellido = new JTextField("");
        this.txtapellido.setBounds(180, 105, 270, 25);
        this.add(txtapellido);
        
        this.lbtelefono = new JLabel("Telefono");
        this.lbtelefono.setBounds(100, 135, 80, 25);
        this.add(lbtelefono);

        this.txttelefono = new JTextField("");
        this.txttelefono.setBounds(180, 135, 270, 25);
        this.add(txttelefono);
        
        this.lbedad = new JLabel("Edad");
        this.lbedad.setBounds(100, 165, 80, 25);
        this.add(lbedad);
         this.txtedad = new JTextField("");
        this.txtedad.setBounds(180, 165, 270, 25);
        this.add(txtedad);
        
        this.lbsexo = new JLabel("Sexo");
        this.lbsexo.setBounds(100, 195, 270, 25);
        this.add(lbsexo);
         this.txtsexo = new JTextField("");
        this.txtsexo.setBounds(180, 195, 270, 25);
        this.add(txtsexo);

       //botones
        this.enviar = new JButton("Consultar");
        this.enviar.setBounds(45, 300, 120, 30);
        this.add(enviar);

        this.agregar = new JButton("Agregar");
        this.agregar.setBounds(175, 300, 120, 30);
        this.add(this.agregar);

        this.actualizar = new JButton("Actualizar");
        this.actualizar.setBounds(305, 300, 120, 30);
        this.add(this.actualizar);

        this.eliminar = new JButton("Eliminar");
        this.eliminar.setBounds(435, 300, 120, 30);
        this.add(this.eliminar);

       
        this.enviar.addActionListener(this);
        this.agregar.addActionListener(this);
        this.actualizar.addActionListener(this);
        this.eliminar.addActionListener(this);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        Welcome polizas = new Welcome();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.enviar) {
            System.out.println("Consultar");
            consultar();
        }

        if (e.getSource() == this.agregar) {
            System.out.println("Agregar");
            agregar();
        }

        if (e.getSource() == this.actualizar) {
            System.out.println("Actualizar");
            actualizar();
        }

        if (e.getSource() == this.eliminar) {
            System.out.println("Eliminar");
            eliminar();
        }
    }

    
    public void agregar() {
        try {
            Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost/polizas", "root", "");
            PreparedStatement ps = cnx.prepareStatement("INSERT INTO cliente (id, nombre, apellido, telefono, edad, sexo) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, Integer.parseInt(this.txtid.getText()));
            ps.setString(2, this.txtnombre.getText());
            ps.setString(3, this.txtapellido.getText());
            ps.setString(4, this.txttelefono.getText());
            ps.setInt(5, Integer.parseInt(txtedad.getText()));
            ps.setString(6, this.txtsexo.getText());
            ps.execute();
            
            System.out.println("Registro insertado correctamente.");
            cnx.close();
        } catch (SQLException ex) {
            System.out.println("Error al agregar: " + ex.getMessage());
        } catch (NumberFormatException nfe) {
            System.out.println("El ID debe ser un numero.");
        }
    }

   
    public void consultar() {
        try {
            Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost/polizas", "root", "");
            PreparedStatement ps = cnx.prepareStatement("SELECT * FROM cliente");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " | Nombre: " + rs.getString("nombre"));
            }

            System.out.println("Conexion exitosa");
            cnx.close();
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getSQLState() + " : " + ex.getMessage());
        }
    }

    
    public void actualizar() {
        String nuevoNombre = JOptionPane.showInputDialog("Ingresa el nuevo nombre:");
        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
            try {
                Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost/polizas", "root", "");
                PreparedStatement ps = cnx.prepareStatement("UPDATE cliente SET nombre = ? WHERE id = ?");
                ps.setString(1, nuevoNombre);
                ps.setInt(2, Integer.parseInt(this.txtid.getText()));

                int filas = ps.executeUpdate();
                if (filas > 0) {
                    System.out.println("Registro actualizado exitosamente.");
                    this.txtnombre.setText(nuevoNombre);
                } else {
                    System.out.println("No se encontro el ID para actualizar.");
                }
                cnx.close();
            } catch (SQLException ex) {
                System.out.println("Error al actualizar: " + ex.getMessage());
            } catch (NumberFormatException nfe) {
                System.out.println("Por favor escribe un numero valido en el ID.");
            }
        }
    }

   
    public void eliminar() {
        try {
            Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost/polizas", "root", "");
            PreparedStatement ps = cnx.prepareStatement("DELETE FROM cliente WHERE id = ?");
            ps.setInt(1, Integer.parseInt(this.txtid.getText()));

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Registro con ID " + this.txtid.getText() + " eliminado exitosamente.");
                this.txtid.setText("");
                this.txtnombre.setText("");
            } else {
                System.out.println("No se encontro el ID para eliminar.");
            }
            cnx.close();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar: " + ex.getMessage());
        } catch (NumberFormatException nfe) {
            System.out.println("Por favor escribe un numero en la casilla de ID.");
        }
    }
}

