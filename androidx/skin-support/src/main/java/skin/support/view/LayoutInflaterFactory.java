package skin.support.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public interface LayoutInflaterFactory {

    /**
     * Hook you can supply that is called when inflating from a LayoutInflater.
     * You can use this to customize the tag names available in your XML
     * layout files.
     *
     * @param parent The parent that the created view will be placed
     * in; <em>note that this may be null</em>.
     * @param name Tag name to be inflated.
     * @param context The context the view is being created in.
     * @param attrs Inflation attributes as specified in XML file.
     *
     * @return View Newly created view. Return null for the default
     *         behavior.
     */
    View onCreateView(View parent, String name, Context context, AttributeSet attrs);
}
