package es.uma.taw.arkhammovies.controller;

import jakarta.servlet.http.HttpSession;

public class BaseController {
    protected boolean isAuthenticated(HttpSession session) {
        return session.getAttribute("user") != null;
    }
}
