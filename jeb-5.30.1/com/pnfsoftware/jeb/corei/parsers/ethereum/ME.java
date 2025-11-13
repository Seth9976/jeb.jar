package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ME {
   int q = 0;
   int RF = 1;
   int xK = 2;

   default boolean q() {
      return this.xK() == 0;
   }

   default boolean RF() {
      return this.Dw() == 0;
   }

   int xK();

   int Dw();
}
