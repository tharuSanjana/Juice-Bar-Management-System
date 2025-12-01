
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author user
 */
import ControllerForm.LoginFormC;

public class AppInitializer {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new LoginFormC().setVisible(true);
        });
    }

}
