package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractImmediateOperandBuilder;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class clx extends AbstractImmediateOperandBuilder implements clw {
   public static final clx q = new clx(
      AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, 4194304, 0, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(0, 26), 2)
   );
   public static final clx RF = new clx(
         AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 2097152, 0, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(0, 16), 2)
      )
      .q();
   public static final clx xK = new clx(
      AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 2097152, 0, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(0, 18), 3)
   );
   public static final clx Dw = new clx(
      AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 2097152, 0, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(0, 19), 2)
   );
   public static final clx Uv = new clx(
         AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 2097152, 0, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(0, 21), 2)
      )
      .q();
   public static final clx oW = new clx(
         AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 2097152, 0, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(0, 26), 2)
      )
      .q();
   public static final clw gO = new clx(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, DirectEncodedMemoryArea.get(7, 9));
   public static final clw nf = new clx(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, DirectEncodedMemoryArea.get(0, 11));
   public static final clx gP = new clx(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, DirectEncodedMemoryArea.get(0, 16));
   public static final clw za = new clx(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, DirectEncodedMemoryArea.get(6, 2));
   public static final clw lm = new clx(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, 1048576, 0, DirectEncodedMemoryArea.get(6, 2));
   public static final clw zz = new clx(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, DirectEncodedMemoryArea.get(0, 3));
   public static final clw JY = new clx(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, DirectEncodedMemoryArea.get(6, 3));
   public static final clw HF = new clx(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, 268435456, 0, DirectEncodedMemoryArea.get(18, 3));
   public static final clw LK = new clx(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, DirectEncodedMemoryArea.get(6, 5));
   public static final clw io = new clx(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, 268435456, 0, DirectEncodedMemoryArea.get(6, 5));
   public static final clw qa = new clx(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, DirectEncodedMemoryArea.get(11, 5));
   public static final clw Hk = new clx(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, DirectEncodedMemoryArea.get(16, 5));
   public static final clw Me = new clx(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, 268435456, 0, DirectEncodedMemoryArea.get(16, 5));
   public static final clw PV = new clx(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, 268435456, 0, DirectEncodedMemoryArea.get(6, 10));
   public static final clx oQ = new clx(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtend32, DirectEncodedMemoryArea.get(0, 11));
   public static final clx xW = new clx(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtend32, DirectEncodedMemoryArea.get(0, 16));
   public static final clw KT = new clx(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, 268435456, 0, DirectEncodedMemoryArea.get(6, 20));
   public static final clx Gf = new clx(AbstractImmediateOperandBuilder.ImmediateType.SignExtend64, DirectEncodedMemoryArea.get(0, 16));
   public static final clx Ef = new clx(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtend64, DirectEncodedMemoryArea.get(0, 16));
   public static final clw cC = new clx(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, DirectEncodedMemoryArea.get(16, 10));
   public static final clw sH = new clx(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, DirectEncodedMemoryArea.get(16, 8));
   public static final clw CE = new clx(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, DirectEncodedMemoryArea.get(21, 3));
   public static final clw wF = new clx(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, DirectEncodedMemoryArea.get(21, 4));
   public static final clw If = new clx(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, DirectEncodedMemoryArea.get(21, 5));
   public static final clw Dz = new clx(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, DirectEncodedMemoryArea.get(11, 2));
   public static final clw mI = new clx(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, DirectEncodedMemoryArea.get(11, 3));
   public static final clw jq = new clx(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, DirectEncodedMemoryArea.get(20, 6));
   public static final clw ui = new clx(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, DirectEncodedMemoryArea.get(19, 7));
   public static final clw TX = new clx(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, 268435456, 31, 63, DirectEncodedMemoryArea.get(11, 10));
   public static final clw Rr = new clx(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, 268435456, 31, 63, DirectEncodedMemoryArea.get(16, 10));

   private clx(AbstractImmediateOperandBuilder.ImmediateType var1, IEncodedMemoryArea var2) {
      super(var1, 0, 0, var2);
   }

   private clx(AbstractImmediateOperandBuilder.ImmediateType var1, int var2, int var3, IEncodedMemoryArea var4) {
      super(var1, var2, var3, var4);
   }

   private clx(AbstractImmediateOperandBuilder.ImmediateType var1, int var2, int var3, int var4, IEncodedMemoryArea var5) {
      super(var1, var2, var3, var4, var5);
   }

   public clx q() {
      this.flags |= 8388608;
      return this;
   }

   protected clv q(int var1, long var2) {
      byte var4 = 1;
      if ((this.flags & 2097152) != 0) {
         var4 = 3;
      } else if ((this.flags & 4194304) != 0) {
         var4 = 2;
      }

      return (this.flags & 8388608) != 0
         ? new clv(var4, 65536, this.getSize(), var2, this.isSigned())
         : new clv(var4, 0, this.getSize(), var2, this.isSigned());
   }
}
