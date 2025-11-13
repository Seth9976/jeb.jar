package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;

public class wJ extends jD {
   private final String[] xK;
   private final String Dw;
   private final IEncodedMemoryArea Uv;
   public static final wJ q = new wJ(mZ.q, DirectEncodedMemoryArea.get(4, 4));
   public static final wJ RF = new wJ(mZ.q, DirectEncodedMemoryArea.get(12, 4));

   public wJ(String[] var1, IEncodedMemoryArea var2) {
      this(jD.eo.q, var1, var2);
   }

   public wJ(jD.eo var1, String[] var2, IEncodedMemoryArea var3) {
      this(var1, var2, var3, null);
   }

   public wJ(jD.eo var1, String[] var2, IEncodedMemoryArea var3, String var4) {
      super(var1);
      this.xK = var2;
      this.Uv = var3;
      this.Dw = var4;
   }

   @Override
   public CW buildOperand(byte[] var1, int var2) throws ProcessorException {
      int var3 = k.RF(var1, this.Uv);
      if (!this.q(var3) && this.xK != null) {
         String var4 = this.q(var3, var1);
         if (var4 == null) {
            if (this.Dw == null) {
               throw new ProcessorException("Unknown value");
            }

            var4 = String.format(this.Dw, var3);
         }

         return new sJ(var3, this.q(), var4);
      } else {
         return null;
      }
   }

   private String q(int var1, byte[] var2) {
      return this.xK != null ? (String)ArrayUtil.getSafe(this.xK, var1, null) : null;
   }
}
