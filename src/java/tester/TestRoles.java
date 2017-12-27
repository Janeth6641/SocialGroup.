/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import java.util.List;
import master.logica.entidades.RolUsuario;
import master.logica.funciones.FRolUsuario;

/**
 *
 * @author Geovanny
 */
public class TestRoles {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            List<RolUsuario> lst = FRolUsuario.obtenerRolesDadoUsuario(1);
            for (int i = 0; i < lst.size(); i++) {
                System.out.println("\n Rol: " + lst.get(i).getRol().getRol()
                        + "\n Usuario: " + lst.get(i).getUsuario().getIdPersona());
            }
        } catch (Exception e) {
            System.out.println("error: "+e.getMessage());
        }
    }

}
