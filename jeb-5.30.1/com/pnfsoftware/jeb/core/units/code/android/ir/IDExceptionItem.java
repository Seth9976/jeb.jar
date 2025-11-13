package com.pnfsoftware.jeb.core.units.code.android.ir;

import java.util.List;

public interface IDExceptionItem {
   int getTryAddress();

   int getTrySize();

   List getHandlers();
}
