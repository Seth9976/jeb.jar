package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class iv extends rR {
   private static final int zz = 3;
   private static final IEncodedMemoryArea JY = DirectEncodedMemoryArea.get(0, 3);
   private static final IEncodedMemoryArea HF = DirectEncodedMemoryArea.get(3, 3);
   private static final IEncodedMemoryArea LK = DirectEncodedMemoryArea.get(6, 3);
   private static final IEncodedMemoryArea io = DirectEncodedMemoryArea.get(8, 3);
   private final iv.eo qa;
   public static final iv Dw = new iv(JY);
   public static final iv Uv = new iv(HF);
   public static final iv oW = new iv(LK);
   public static final iv gO = new iv(io);
   public static final iv nf = new iv(io, jD.eo.RF);
   public static final iv gP = new iv(io, iv.eo.RF);
   public static final rR za = new iv(DirectEncodedMemoryArea.get(3, 4));
   public static final rR lm = new iv(new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(7, 1), DirectEncodedMemoryArea.get(0, 3)));

   public iv(IEncodedMemoryArea var1) {
      this(0, var1, jD.eo.q, iv.eo.q);
   }

   public iv(IEncodedMemoryArea var1, jD.eo var2) {
      this(0, var1, var2, iv.eo.q);
   }

   public iv(IEncodedMemoryArea var1, iv.eo var2) {
      this(0, var1, jD.eo.q, var2);
   }

   public iv(int var1, IEncodedMemoryArea var2, jD.eo var3, iv.eo var4) {
      super(var1, var3, var2, 15);
      this.qa = var4;
   }

   @Override
   public CW buildOperand(byte[] var1, int var2) throws ProcessorException {
      if (this.qa == iv.eo.RF) {
         int var3 = this.RF(var1);
         int var4 = var1[1] & 255;
         boolean var5 = (var4 & 1 << var3) == 0;
         return GC.q(this.Uv(var1), var3, var2, var5 ? 131072 : 0);
      } else {
         return super.buildOperand(var1, var2);
      }
   }

   @Override
   public boolean oW(byte[] var1) {
      return this.Uv(var1) == 0 && this.RF(var1) == 15;
   }

   private static enum eo {
      q,
      RF;
   }
}
