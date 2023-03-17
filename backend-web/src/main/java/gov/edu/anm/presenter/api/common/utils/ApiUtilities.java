package gov.edu.anm.presenter.api.common.utils;

public class ApiUtilities {
    public static String convertStringPath(String path) {
        return path.replace("%20", " ");
    }
}
