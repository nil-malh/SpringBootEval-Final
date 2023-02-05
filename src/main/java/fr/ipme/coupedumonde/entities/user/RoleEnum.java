package fr.ipme.coupedumonde.entities.user;

public enum RoleEnum {
    USER("user"), ADMIN("admin");

    private String role;


    RoleEnum(String role) {

        this.role = role;

    }
    public String getRole() {
        return role;
    }
}
