package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;

public class Ag extends Ll {
   private final String[] kS;
   private final String wS;
   private final IEncodedMemoryArea UT;
   public static final Ag pC = new Ag(zj.pC, DirectEncodedMemoryArea.get(4, 4));
   public static final Ag A = new Ag(zj.pC, DirectEncodedMemoryArea.get(12, 4));

   public Ag(String[] var1, IEncodedMemoryArea var2) {
      this(Ll.Av.pC, var1, var2);
   }

   public Ag(Ll.Av var1, String[] var2, IEncodedMemoryArea var3) {
      this(var1, var2, var3, null);
   }

   public Ag(Ll.Av var1, String[] var2, IEncodedMemoryArea var3, String var4) {
      super(var1);
      this.kS = var2;
      this.UT = var3;
      this.wS = var4;
   }

   @Override
   public Yg buildOperand(byte[] var1, int var2) throws ProcessorException {
      int var3 = Gq.A(var1, this.UT);
      if (!this.pC(var3) && this.kS != null) {
         String var4 = this.pC(var3, var1);
         if (var4 == null) {
            if (this.wS == null) {
               throw new ProcessorException("Unknown value");
            }

            var4 = String.format(this.wS, var3);
         }

         return new fj(var3, this.pC(), var4);
      } else {
         return null;
      }
   }

   private String pC(int var1, byte[] var2) {
      return this.kS != null ? (String)ArrayUtil.getSafe(this.kS, var1, null) : null;
   }
}
