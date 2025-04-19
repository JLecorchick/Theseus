package projfxmldemo.helpers;

import javafx.stage.Stage;
import projfxmldemo.AppUtil;

public class NavigationManager {
    public static void goTo(String fxmlPath, String title) {
        Stage stage = AppUtil.loadFxml(fxmlPath, title);
        if (stage != null) {
            stage.show();
        }
    }
}
