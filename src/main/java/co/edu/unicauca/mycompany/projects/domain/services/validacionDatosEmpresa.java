/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.mycompany.projects.domain.services;

import co.edu.unicauca.mycompany.projects.infra.Messages;

/**
 *
 * @author lopez
 */
public class validacionDatosEmpresa {
    
    public boolean validarRegistroEmpresa(String nit,String email,String telefono,String clave){
        if((validarNit(nit) && validarCorreo(email) && validarClave(clave) && validarNumero(telefono))){
            return true;
        }
        return false;
    };
    
    public boolean validarNit(String nit){
        if (nit.length() < 9) {
         Messages.showMessageDialog("Nit invalido. Este debe ser de 9 caracteres", "nitInvalido");
         return false;   
        } 
        return true;
    };
    
    public boolean validarCorreo(String email){
        if (!email.matches(".+[@]{1}.+[.]{1}.+")) {
         Messages.showMessageDialog("Correo invalido. Siga el ejemplo:\n micorreo@gmail.com", "email invalido");
         return false;   
        } 
        return true;
    }
    
    public boolean validarNumero(String numero){
        try {
            Integer.valueOf(numero);
            
            return true;
        } catch (NumberFormatException e) {
            Messages.showMessageDialog("Error en telefono. Ingrese un número", "Atención");
            return false;
        }
    };
      
    public boolean validarClave(String clave){
        if(!clave.matches(".{6,}"))return false;
        else if(!clave.matches(".*[A-Z]+.*")) return false;
        else if(!clave.matches(".*[a-z]+.*")) return false;
        else if(!clave.matches(".*[0-9]+.*")) return false;
        else if(!clave.matches(".*[\\*\\+\\-\\_\\.\\:\\,\\;\\¿\\?\\!\\¡\\#\\$\\%\\&\\/\\=\\@\\<\\>]+.*")) return false;
        return true;
    }
    
    public void mostrarEjemploClave(){
        Messages.showMessageDialog("Error al registrar la clave.Esta debe tener: \n"
                + "6 caracteres minimos \n"
                + "Una mayuscula y miniscula \n"
                + "Un caracter especial (.,@,?)", "Atención");
    }
    };
    

