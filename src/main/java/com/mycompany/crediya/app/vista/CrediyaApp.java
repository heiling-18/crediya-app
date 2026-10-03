/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.crediya.app.vista;

import com.mycompany.crediya.app.Modelo.Persistencia.ConexionBD;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Heiling
 */
public class CrediyaApp {

    public static void main(String[] args) {
       //Comprobandte de conexion
        
        try {

            Connection conexion = ConexionBD.MysConnection();

            if (conexion != null) {
                System.out.println("Conexion con la base de datos exitosa.");
            } else {
                System.out.println("Error al conectar con la base de datos.");
            }

        } catch (SQLException e) {
            System.out.println("Error de conexion: " + e.getMessage());
        }
        
    }
}
