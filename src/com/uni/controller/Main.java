
package com.uni.controller;
import com.uni.controller.ControladorMenuPrincipal;
import com.uni.view.MenuPrincipal;

/**
 *
 * @author diego
 */
public class Main {
    public static void main(String[] args){
        MenuPrincipal menu = new MenuPrincipal();
        ControladorMenuPrincipal cPrincipal = new ControladorMenuPrincipal(menu);
        menu.setVisible(true);
    }
}
