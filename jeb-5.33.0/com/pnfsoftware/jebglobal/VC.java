package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class VC {
   public static final ZW pC = new VC.Av(DirectEncodedMemoryArea.get(19, 4), 524294, 1048582, 2097158, 4194310);
   public static final ZW A = new VC.Av(DirectEncodedMemoryArea.get(19, 4), 524294, 1048582, 2097158);
   public static final ZW kS = new VC.Av(DirectEncodedMemoryArea.get(19, 4), 1048582, 2097158, 4194310);
   public static final ZW wS = new VC.Av(DirectEncodedMemoryArea.get(16, 5), 524294, 1048582, 2097158, 4194310, 8388614);
   public static final ER UT = new ER(pC, DirectEncodedMemoryArea.get(0, 5));
   public static final ER E = new ER(pC, DirectEncodedMemoryArea.get(5, 5));
   public static final ER sY = new ER(pC, DirectEncodedMemoryArea.get(16, 5));
   public static final ER ys = new ER(A, DirectEncodedMemoryArea.get(0, 5));
   public static final ER ld = new ER(kS, DirectEncodedMemoryArea.get(5, 5));
   public static final ER gp = new ER(wS, DirectEncodedMemoryArea.get(5, 5));

   public static class Av extends ZW {
      public Av(IEncodedMemoryArea var1, Integer... var2) {
         super(var1, var2);
      }

      @Override
      public int pC(byte[] var1) {
         int var2 = super.pC(var1);
         return Gq.pC((long)var2);
      }
   }
}
