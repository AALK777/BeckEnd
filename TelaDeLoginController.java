package controller;

import view.*;
import model.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class TelaDeLoginController extends TelaDeLoginView {
    public static void logarController() {
        ArrayList<String> resultados = new ArrayList<String>(TelaDeLoginModel.logarModel(txtLogin.getText(), String.valueOf(txtSenha.getPassword())));
    }
}
