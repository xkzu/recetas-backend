package cl.duoc.app.recetas_backend.util;

public class Util {

    private Util() {
    }

    public static boolean isEmptyOrNull(String string) {
        return null == string || string.isEmpty();
    }
}
