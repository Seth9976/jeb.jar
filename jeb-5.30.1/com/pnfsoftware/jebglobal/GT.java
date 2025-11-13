package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class GT {
   private static final int lm = 5;
   private static final int zz = 524294;
   private static final int JY = 1048582;
   private static final int HF = 2097158;
   private static final int LK = 4194310;
   private static final int io = 8388614;
   public static final dD q = new GT.eo(DirectEncodedMemoryArea.get(19, 4), 524294, 1048582, 2097158, 4194310);
   public static final dD RF = new GT.eo(DirectEncodedMemoryArea.get(19, 4), 524294, 1048582, 2097158);
   public static final dD xK = new GT.eo(DirectEncodedMemoryArea.get(19, 4), 1048582, 2097158, 4194310);
   public static final dD Dw = new GT.eo(DirectEncodedMemoryArea.get(16, 5), 524294, 1048582, 2097158, 4194310, 8388614);
   public static final XD Uv = new XD(q, DirectEncodedMemoryArea.get(0, 5));
   public static final XD oW = new XD(q, DirectEncodedMemoryArea.get(5, 5));
   public static final XD gO = new XD(q, DirectEncodedMemoryArea.get(16, 5));
   public static final XD nf = new XD(RF, DirectEncodedMemoryArea.get(0, 5));
   public static final XD gP = new XD(xK, DirectEncodedMemoryArea.get(5, 5));
   public static final XD za = new XD(Dw, DirectEncodedMemoryArea.get(5, 5));

   public static class eo extends dD {
      public eo(IEncodedMemoryArea var1, Integer... var2) {
         super(var1, var2);
      }

      @Override
      public int q(byte[] var1) {
         int var2 = super.q(var1);
         return k.q((long)var2);
      }
   }
}
