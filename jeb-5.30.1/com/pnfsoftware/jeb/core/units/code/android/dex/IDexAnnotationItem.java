package com.pnfsoftware.jeb.core.units.code.android.dex;

public interface IDexAnnotationItem {
   int VISIBILITY_BUILD = 0;
   int VISIBILITY_RUNTIME = 1;
   int VISIBILITY_SYSTEM = 2;

   int getVisibility();

   String formatVisibility();

   default boolean isSystemLevelAnnotation() {
      return this.getVisibility() == 2;
   }

   IDexAnnotation getAnnotation();
}
