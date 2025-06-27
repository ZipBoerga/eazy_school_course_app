package com.zip_boerga.eazy_school.aspects.audit;

public class AuditContext {
    private static final ThreadLocal<String> context = new ThreadLocal<>();

    public static void set(String username) {
        context.set(username);
    }

    public static String get() {
        return context.get();
    }

    public static void clear() {
        context.remove();
    }
}
