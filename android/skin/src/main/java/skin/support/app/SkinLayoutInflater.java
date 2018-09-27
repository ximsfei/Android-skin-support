package skin.support.app;

import android.content.Context;
import androidx.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

public interface SkinLayoutInflater {
    View createView(@NonNull Context context, final String name, @NonNull AttributeSet attrs);
}
