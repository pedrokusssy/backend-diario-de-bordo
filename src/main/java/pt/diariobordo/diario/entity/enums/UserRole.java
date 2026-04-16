package pt.diariobordo.diario.entity.enums;

public enum UserRole {
    FORMANDO("formando"),
    TUTOR("tutor");

    private String role;
    UserRole(String role) { this.role = role; }
    public String getRole() { return role; }
}