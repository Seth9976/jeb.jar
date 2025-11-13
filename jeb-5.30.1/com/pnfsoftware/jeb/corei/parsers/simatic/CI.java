package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.code.simatic.IS7Block;

public class CI {
   public static boolean q(byte[] var0) {
      return tl.q(var0) ? true : Nz.q(var0);
   }

   public static boolean q(IInput var0) {
      return tl.q(var0) ? true : Nz.q(var0);
   }

   public static IS7Block RF(byte[] var0) {
      if (tl.q(var0)) {
         return new tl(var0);
      } else {
         return Nz.q(var0) ? new Nz(var0) : null;
      }
   }

   private CI() {
   }
}
