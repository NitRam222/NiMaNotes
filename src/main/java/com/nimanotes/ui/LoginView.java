package com.nimanotes.ui;

import com.nimanotes.model.User;
import com.nimanotes.repository.UserRepository;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.security.crypto.password.PasswordEncoder;

@Route("login")
public class LoginView extends VerticalLayout {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginView(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

        H1 title = new H1("NiMaNotes Login");
        TextField usernameField = new TextField("Benutzername");
        PasswordField passwordField = new PasswordField("Passwort");
        Button loginButton = new Button("Einloggen");
        Button registerButton = new Button("Konto erstellen");

        loginButton.addClickListener(event -> {
            String username = usernameField.getValue();
            String password = passwordField.getValue();
            User user = userRepository.findByUsername(username).orElse(null);

            if (user != null && passwordEncoder.matches(password, user.getPassword())) {
                Notification.show("Login erfolgreich");
                UI.getCurrent().navigate("");
            } else {
                Notification.show("Login fehlgeschlagen");
            }
        });

        registerButton.addClickListener(event -> UI.getCurrent().navigate("register"));

        add(title, usernameField, passwordField, loginButton, registerButton);
    }
}
