package com.pnfsoftware.jeb.core.units.code.android.dex;

import java.util.List;

public interface IDexExceptionItem {
   int getTryAddress();

   int getTrySize();

   default int getTryAddressEnd() {
      return this.getTryAddress() + this.getTrySize();
   }

   List getHandlers();
}
