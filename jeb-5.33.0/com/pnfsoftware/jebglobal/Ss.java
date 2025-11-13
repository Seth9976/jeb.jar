package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Ser
public class Ss {
   @SerId(1)
   long pC;
   @SerId(2)
   List A = new ArrayList();
   @SerId(3)
   CFG kS;
   @SerId(4)
   Set wS;
   @SerId(5)
   boolean UT;

   public Ss(long var1) {
      this.pC = var1;
   }

   public CFG pC() {
      return this.kS;
   }

   public int A() {
      int var1 = 0;

      for (ON var3 : this.A) {
         var1 += (int)(var3.getEndAddress() - var3.getFirstAddress());
      }

      return var1;
   }

   @Override
   public String toString() {
      return Strings.ff("%Xh(%d)", this.pC, this.A.size());
   }
}
