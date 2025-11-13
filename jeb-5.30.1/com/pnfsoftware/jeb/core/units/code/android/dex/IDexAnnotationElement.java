package com.pnfsoftware.jeb.core.units.code.android.dex;

import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;

public interface IDexAnnotationElement {
   int getNameIndex();

   default String getName(IDexUnit var1) {
      return var1.getString(this.getNameIndex()).getValue(false);
   }

   IDexValue getValue();
}
