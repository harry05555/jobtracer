package de.gruppe_D;

import de.gruppe_D.app.configs.AppConfig;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AppConfig config = new AppConfig();
        config.router().showAuth();// Startscreen
    }
}