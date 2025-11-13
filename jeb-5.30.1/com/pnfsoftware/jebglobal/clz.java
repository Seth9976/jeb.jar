package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractOperandBuilder;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class clz extends AbstractOperandBuilder implements clw {
   private static final int Gf = 5;
   private static final int Ef = 3;
   private static final int cC = 2;
   private static final int sH = 4;
   private static final int CE = 5;
   private static final int wF = 6;
   private int If = 0;
   public static clz q = new clz(DirectEncodedMemoryArea.get(21, 5));
   public static clz RF = new clz(3, DirectEncodedMemoryArea.get(21, 5));
   public static clz xK = new clz(DirectEncodedMemoryArea.get(16, 5));
   public static clz Dw = new clz(DirectEncodedMemoryArea.get(16, 5), 0);
   public static clz Uv = new clz(3, DirectEncodedMemoryArea.get(16, 5));
   public static clz oW = new clz(4, DirectEncodedMemoryArea.get(16, 5));
   public static clz gO = new clz(5, DirectEncodedMemoryArea.get(11, 5));
   public static clz nf = new clz(DirectEncodedMemoryArea.get(11, 5));
   public static clz gP = new clz(DirectEncodedMemoryArea.get(11, 5), 31);
   public static clz za = new clz(3, DirectEncodedMemoryArea.get(11, 5));
   public static clz lm = new clz(3, DirectEncodedMemoryArea.get(6, 5));
   public static clz zz = new clz(6, DirectEncodedMemoryArea.get(21, 2));
   public static clz JY = new clz(6, DirectEncodedMemoryArea.get(11, 2));
   public static clz HF = new clz(DirectEncodedMemoryArea.get(11, 5));
   public static clz LK = new clz(DirectEncodedMemoryArea.get(16, 5));
   public static clz io = new clz(DirectEncodedMemoryArea.get(21, 5));
   public static final clw[] qa = new clw[]{q, xK};
   public static final clw[] Hk = new clw[]{io, LK};
   public static final clw[] Me = new clw[]{nf, q, xK};
   public static final clw[] PV = new clw[]{HF, io, LK};
   public static final clz oQ = new clz(2, DirectEncodedMemoryArea.get(18, 3));
   public static final clz xW = new clz(2, DirectEncodedMemoryArea.get(18, 3), 0);
   public static final clz KT = new clz(2, DirectEncodedMemoryArea.get(8, 3), 0);

   public clz(IEncodedMemoryArea var1) {
      super(var1);
   }

   public clz(int var1, IEncodedMemoryArea var2) {
      super(var2);
      this.If = var1;
   }

   public clz(int var1, IEncodedMemoryArea var2, int var3) {
      super(var2, 268435456, var3, 0);
      this.If = var1;
   }

   public clz(IEncodedMemoryArea var1, int var2) {
      super(var1, 268435456, var2, 0);
   }

   public clv q(byte[] var1, int var2) throws ProcessorException {
      int var3 = this.decodeMemoryArea(var1);
      return this.isOptional(var3) ? null : clv.RF(this.If, var3, var2);
   }
}
