package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractImmediateOperandBuilder;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class IV extends AbstractImmediateOperandBuilder implements Hu {
   public static final Hu pC = new IV(DirectEncodedMemoryArea.get(8, 1));
   public static final Hu A = new IV(DirectEncodedMemoryArea.get(5, 3));
   public static final Hu kS = new IV(DirectEncodedMemoryArea.get(6, 3));
   public static final Hu wS = new IV(DirectEncodedMemoryArea.get(16, 3));
   public static final Hu UT = new IV(DirectEncodedMemoryArea.get(0, 4));
   public static final Hu E = new IV(DirectEncodedMemoryArea.get(4, 4));
   public static final Hu sY = new IV(1048576, DirectEncodedMemoryArea.get(0, 4));
   public static final Hu ys = new IV(DirectEncodedMemoryArea.get(8, 4));
   public static final Hu ld = new IV(DirectEncodedMemoryArea.get(10, 4));
   public static final Hu gp = new IV(DirectEncodedMemoryArea.get(16, 4));
   public static final Hu oT = new IV(1048576, DirectEncodedMemoryArea.get(16, 4));
   public static final Hu fI = new IV(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 65536, DirectEncodedMemoryArea.get(16, 4));
   public static final Hu WR = new IV(DirectEncodedMemoryArea.get(0, 5));
   public static final Hu NS = new IV(1048576, DirectEncodedMemoryArea.get(0, 5));
   public static final Hu vP = new IV(DirectEncodedMemoryArea.get(16, 5));
   public static final Hu xC = new IV(1048576, DirectEncodedMemoryArea.get(16, 5));
   public static final Hu ED = new IV(524288, DirectEncodedMemoryArea.get(6, 5));
   public static final Hu Sc = new IV(524288, DirectEncodedMemoryArea.get(7, 5));
   public static final Hu ah = new IV(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 65536, DirectEncodedMemoryArea.get(5, 5));
   public static final Hu eP = new IV(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 65536, DirectEncodedMemoryArea.get(16, 5));
   public static final Hu UO = new IV(DirectEncodedMemoryArea.get(0, 6));
   public static final Hu Ab = new IV(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 65536, DirectEncodedMemoryArea.get(5, 6));
   public static final Hu rl = new IV(DirectEncodedMemoryArea.get(10, 6));
   public static final Hu z = new IV(DirectEncodedMemoryArea.get(15, 6));
   public static final Hu Ek = new IV(DirectEncodedMemoryArea.get(16, 6));
   public static final Hu hK = new IV(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 65536, DirectEncodedMemoryArea.get(16, 6));
   public static final Hu Er = new IV(DirectEncodedMemoryArea.get(16, 6).shift(4));
   public static final Hu FE = new IV(524288, DirectEncodedMemoryArea.get(16, 6));
   public static final Hu Aj = new IV(DirectEncodedMemoryArea.get(14, 7));
   public static final Hu EX = new IV(DirectEncodedMemoryArea.get(0, 7).shift(2));
   public static final Hu LM = new IV(35651584, DirectEncodedMemoryArea.get(0, 8).shift(2));
   public static final Hu mv = new IV(DirectEncodedMemoryArea.get(0, 8).shift(2));
   public static final Hu sO = new IV(DirectEncodedMemoryArea.get(0, 8));
   public static final Hu os = new IV(DirectEncodedMemoryArea.get(5, 8));
   public static final Hu Cu = new IV(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 65536, DirectEncodedMemoryArea.get(5, 8));
   public static final Hu hZ = new IV(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 65536, DirectEncodedMemoryArea.get(5, 8).shift(3));
   public static final Hu UW = new IV(DirectEncodedMemoryArea.get(10, 8));
   public static final Hu PR = new IV(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 65536, DirectEncodedMemoryArea.get(10, 8));
   public static final Hu cX = new IV(DirectEncodedMemoryArea.get(0, 16));
   public static final Hu DQ = new IV(VirtualEncodedMemoryArea._0);
   public static final IV ZN = new IV(
      AbstractImmediateOperandBuilder.ImmediateType.SignExtend32,
      65536,
      new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(16, 6), DirectEncodedMemoryArea.get(10, 3))
   );
   public static final IV OB = new IV(new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(8, 12), DirectEncodedMemoryArea.get(0, 4)));
   public static final IV pF = new IV(new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(16, 4), DirectEncodedMemoryArea.get(0, 12)));
   public static final IV Bc = new IV(new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(16, 5), DirectEncodedMemoryArea.get(10, 3)));

   public IV(IEncodedMemoryArea var1) {
      this(65536, var1);
   }

   public IV(int var1, IEncodedMemoryArea var2) {
      this(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, var1, var2);
   }

   public IV(int var1, int var2, IEncodedMemoryArea var3) {
      this(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, var1, var2, var3);
   }

   public IV(AbstractImmediateOperandBuilder.ImmediateType var1, int var2, IEncodedMemoryArea var3) {
      this(var1, var2, 0, var3);
   }

   private IV(AbstractImmediateOperandBuilder.ImmediateType var1, int var2, int var3, IEncodedMemoryArea var4) {
      this(var1, var2, var3, var4, true);
   }

   private IV(AbstractImmediateOperandBuilder.ImmediateType var1, int var2, int var3, IEncodedMemoryArea var4, boolean var5) {
      super(var1, var2 | (var5 ? 65536 : 0), var3, var4);
   }

   public static IV pC(IEncodedMemoryArea var0) {
      return new IV(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, 0, 0, var0, false);
   }

   protected Yg pC(int var1, long var2) {
      if ((this.flags & 2097152) != 0) {
         if ((this.flags & 134217728) != 0) {
            var2 = -var2;
         }

         return new cv(var2, var1, (this.flags & 33554432) != 0, (this.flags & 16777216) != 0);
      } else {
         Yg.Sv var4 = Yg.Sv.pC;
         if ((this.flags & 134217728) != 0) {
            var4 = Yg.Sv.kS;
         }

         if ((this.flags & 67108864) != 0) {
            var2 *= 90L;
         }

         int var5 = this.flags & 589824;
         return Yg.pC(this.getSize(), var4, var2, var5);
      }
   }
}
