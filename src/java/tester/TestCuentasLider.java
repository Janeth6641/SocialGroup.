/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import java.util.List;
import master.logica.entidades.Usuario;
import master.logica.funciones.FUsuario;

/**
 *
 * @author Geovanny Cudco
 */
public class TestCuentasLider {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            List<Usuario> lst = FUsuario.obtenerUsuariosDadoLider(1);
            System.out.println("Total " + lst.size());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
