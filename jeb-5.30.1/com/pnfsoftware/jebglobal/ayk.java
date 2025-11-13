package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class ayk {
   public ayj q(long var1) {
      if ((var1 & -72057594037927936L) != 0L) {
         return null;
      } else {
         long var3 = -9079256848778919936L | var1;
         return new ayj(var3, var1);
      }
   }

   public ayj RF(long var1) {
      if ((var1 & -72057594037927936L) != -9079256848778919936L) {
         return null;
      } else {
         long var3 = var1 & 72057594037927935L;
         return new ayj(var1, var3);
      }
   }
}
