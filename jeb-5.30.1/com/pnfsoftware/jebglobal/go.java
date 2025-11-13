package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractImmediateOperandBuilder;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class go extends AbstractImmediateOperandBuilder implements Ef {
   public static final int q = 65536;
   public static final int RF = 16777216;
   public static final int xK = 33554432;
   public static final int Dw = 67108864;
   public static final int Uv = 134217728;
   public static final int oW = 524288;
   public static final int gO = 65536;
   public static final Ef nf = new go(DirectEncodedMemoryArea.get(8, 1));
   public static final Ef gP = new go(DirectEncodedMemoryArea.get(5, 3));
   public static final Ef za = new go(DirectEncodedMemoryArea.get(6, 3));
   public static final Ef lm = new go(DirectEncodedMemoryArea.get(16, 3));
   public static final Ef zz = new go(DirectEncodedMemoryArea.get(0, 4));
   public static final Ef JY = new go(DirectEncodedMemoryArea.get(4, 4));
   public static final Ef HF = new go(1048576, DirectEncodedMemoryArea.get(0, 4));
   public static final Ef LK = new go(DirectEncodedMemoryArea.get(8, 4));
   public static final Ef io = new go(DirectEncodedMemoryArea.get(10, 4));
   public static final Ef qa = new go(DirectEncodedMemoryArea.get(16, 4));
   public static final Ef Hk = new go(1048576, DirectEncodedMemoryArea.get(16, 4));
   public static final Ef Me = new go(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 65536, DirectEncodedMemoryArea.get(16, 4));
   public static final Ef PV = new go(DirectEncodedMemoryArea.get(0, 5));
   public static final Ef oQ = new go(1048576, DirectEncodedMemoryArea.get(0, 5));
   public static final Ef xW = new go(DirectEncodedMemoryArea.get(16, 5));
   public static final Ef KT = new go(1048576, DirectEncodedMemoryArea.get(16, 5));
   public static final Ef Gf = new go(524288, DirectEncodedMemoryArea.get(6, 5));
   public static final Ef Ef = new go(524288, DirectEncodedMemoryArea.get(7, 5));
   public static final Ef cC = new go(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 65536, DirectEncodedMemoryArea.get(5, 5));
   public static final Ef sH = new go(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 65536, DirectEncodedMemoryArea.get(16, 5));
   public static final Ef CE = new go(DirectEncodedMemoryArea.get(0, 6));
   public static final Ef wF = new go(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 65536, DirectEncodedMemoryArea.get(5, 6));
   public static final Ef If = new go(DirectEncodedMemoryArea.get(10, 6));
   public static final Ef Dz = new go(DirectEncodedMemoryArea.get(15, 6));
   public static final Ef mI = new go(DirectEncodedMemoryArea.get(16, 6));
   public static final Ef jq = new go(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 65536, DirectEncodedMemoryArea.get(16, 6));
   public static final Ef ui = new go(DirectEncodedMemoryArea.get(16, 6).shift(4));
   public static final Ef TX = new go(524288, DirectEncodedMemoryArea.get(16, 6));
   public static final Ef Rr = new go(DirectEncodedMemoryArea.get(14, 7));
   public static final Ef EB = new go(DirectEncodedMemoryArea.get(0, 7).shift(2));
   public static final Ef Xo = new go(35651584, DirectEncodedMemoryArea.get(0, 8).shift(2));
   public static final Ef Bu = new go(DirectEncodedMemoryArea.get(0, 8).shift(2));
   public static final Ef IN = new go(DirectEncodedMemoryArea.get(0, 8));
   public static final Ef rL = new go(DirectEncodedMemoryArea.get(5, 8));
   public static final Ef eJ = new go(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 65536, DirectEncodedMemoryArea.get(5, 8));
   public static final Ef YN = new go(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 65536, DirectEncodedMemoryArea.get(5, 8).shift(3));
   public static final Ef Rv = new go(DirectEncodedMemoryArea.get(10, 8));
   public static final Ef zx = new go(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 65536, DirectEncodedMemoryArea.get(10, 8));
   public static final Ef ZT = new go(DirectEncodedMemoryArea.get(0, 16));
   public static final Ef Ri = new go(VirtualEncodedMemoryArea._0);
   public static final go GY = new go(
      AbstractImmediateOperandBuilder.ImmediateType.SignExtend32,
      65536,
      new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(16, 6), DirectEncodedMemoryArea.get(10, 3))
   );
   public static final go Wx = new go(new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(8, 12), DirectEncodedMemoryArea.get(0, 4)));
   public static final go AB = new go(new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(16, 4), DirectEncodedMemoryArea.get(0, 12)));
   public static final go CY = new go(new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(16, 5), DirectEncodedMemoryArea.get(10, 3)));

   public go(IEncodedMemoryArea var1) {
      this(65536, var1);
   }

   public go(int var1, IEncodedMemoryArea var2) {
      this(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, var1, var2);
   }

   public go(int var1, int var2, IEncodedMemoryArea var3) {
      this(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, var1, var2, var3);
   }

   public go(AbstractImmediateOperandBuilder.ImmediateType var1, int var2, IEncodedMemoryArea var3) {
      this(var1, var2, 0, var3);
   }

   private go(AbstractImmediateOperandBuilder.ImmediateType var1, int var2, int var3, IEncodedMemoryArea var4) {
      this(var1, var2, var3, var4, true);
   }

   private go(AbstractImmediateOperandBuilder.ImmediateType var1, int var2, int var3, IEncodedMemoryArea var4, boolean var5) {
      super(var1, var2 | (var5 ? 65536 : 0), var3, var4);
   }

   public static go q(IEncodedMemoryArea var0) {
      return new go(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, 0, 0, var0, false);
   }

   protected CW q(int var1, long var2) {
      if ((this.flags & 2097152) != 0) {
         if ((this.flags & 134217728) != 0) {
            var2 = -var2;
         }

         return new fp(var2, var1, (this.flags & 33554432) != 0, (this.flags & 16777216) != 0);
      } else {
         CW.CU var4 = CW.CU.q;
         if ((this.flags & 134217728) != 0) {
            var4 = CW.CU.xK;
         }

         if ((this.flags & 67108864) != 0) {
            var2 *= 90L;
         }

         int var5 = this.flags & 589824;
         return CW.q(this.getSize(), var4, var2, var5);
      }
   }
}
