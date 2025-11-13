package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.code.simatic.IS7Block;

public class uX {
   public static boolean pC(byte[] var0) {
      return oP.pC(var0) ? true : vi.pC(var0);
   }

   public static boolean pC(IInput var0) {
      return oP.pC(var0) ? true : vi.pC(var0);
   }

   public static IS7Block A(byte[] var0) {
      if (oP.pC(var0)) {
         return new oP(var0);
      } else {
         return vi.pC(var0) ? new vi(var0) : null;
      }
   }
}
