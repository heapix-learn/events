package com.heapix.events.persistence.model.enums;

/**
 * @author mgergalov
 */
public enum UserRole {

    SYSTEM_SUPER_ADMIN(1L, "Super Administrator", "Can manage any section of a club, modify content and modify permissions of any user"),
    SYSTEM_ADMIN_USER(2L,"Administrator","Can manage any section of a club and modify their content"),
    MODERATOR_USER(3L,"Moderator","Will be able to access any section of a club, but as observers"),
    MEMBER_USER(4L,"Member","Will have limited access to the sections related to himself, as well as to common spaces"),
    ANONYMOUS_USER(5L,"Anonymous","Unregistered external user that can access the public sections of the web page");

    private final Long id;
    private final String role;
    private final String description;

    UserRole(Long id, String role, String description) {
        this.id = id;
        this.role = role;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getDescription() {
        return description;
    }


    public static UserRole getById(Long id) {

        for(UserRole e : values()) {
            if(e.id.equals(id)) {
                return e;
            }
        }
        return ANONYMOUS_USER;
    }
}
