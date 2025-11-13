package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractImmediateOperandBuilder;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class cfl extends AbstractImmediateOperandBuilder implements cfk {
   public static final cfl pC = new cfl(
      AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, 4194304, 0, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(0, 26), 2)
   );
   public static final cfl A = new cfl(
         AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 2097152, 0, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(0, 16), 2)
      )
      .pC();
   public static final cfl kS = new cfl(
      AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 2097152, 0, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(0, 18), 3)
   );
   public static final cfl wS = new cfl(
      AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 2097152, 0, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(0, 19), 2)
   );
   public static final cfl UT = new cfl(
         AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 2097152, 0, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(0, 21), 2)
      )
      .pC();
   public static final cfl E = new cfl(
         AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 2097152, 0, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(0, 26), 2)
      )
      .pC();
   public static final cfk sY = new cfl(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, DirectEncodedMemoryArea.get(7, 9));
   public static final cfk ys = new cfl(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, DirectEncodedMemoryArea.get(0, 11));
   public static final cfl ld = new cfl(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, DirectEncodedMemoryArea.get(0, 16));
   public static final cfk gp = new cfl(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, DirectEncodedMemoryArea.get(6, 2));
   public static final cfk oT = new cfl(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, 1048576, 0, DirectEncodedMemoryArea.get(6, 2));
   public static final cfk fI = new cfl(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, DirectEncodedMemoryArea.get(0, 3));
   public static final cfk WR = new cfl(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, DirectEncodedMemoryArea.get(6, 3));
   public static final cfk NS = new cfl(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, 268435456, 0, DirectEncodedMemoryArea.get(18, 3));
   public static final cfk vP = new cfl(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, DirectEncodedMemoryArea.get(6, 5));
   public static final cfk xC = new cfl(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, 268435456, 0, DirectEncodedMemoryArea.get(6, 5));
   public static final cfk ED = new cfl(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, DirectEncodedMemoryArea.get(11, 5));
   public static final cfk Sc = new cfl(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, DirectEncodedMemoryArea.get(16, 5));
   public static final cfk ah = new cfl(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, 268435456, 0, DirectEncodedMemoryArea.get(16, 5));
   public static final cfk eP = new cfl(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, 268435456, 0, DirectEncodedMemoryArea.get(6, 10));
   public static final cfl UO = new cfl(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtend32, DirectEncodedMemoryArea.get(0, 11));
   public static final cfl Ab = new cfl(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtend32, DirectEncodedMemoryArea.get(0, 16));
   public static final cfk rl = new cfl(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, 268435456, 0, DirectEncodedMemoryArea.get(6, 20));
   public static final cfl z = new cfl(AbstractImmediateOperandBuilder.ImmediateType.SignExtend64, DirectEncodedMemoryArea.get(0, 16));
   public static final cfl Ek = new cfl(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtend64, DirectEncodedMemoryArea.get(0, 16));
   public static final cfk hK = new cfl(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, DirectEncodedMemoryArea.get(16, 10));
   public static final cfk Er = new cfl(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, DirectEncodedMemoryArea.get(16, 8));
   public static final cfk FE = new cfl(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, DirectEncodedMemoryArea.get(21, 3));
   public static final cfk Aj = new cfl(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, DirectEncodedMemoryArea.get(21, 4));
   public static final cfk EX = new cfl(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, DirectEncodedMemoryArea.get(21, 5));
   public static final cfk LM = new cfl(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, DirectEncodedMemoryArea.get(11, 2));
   public static final cfk mv = new cfl(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, DirectEncodedMemoryArea.get(11, 3));
   public static final cfk sO = new cfl(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, DirectEncodedMemoryArea.get(20, 6));
   public static final cfk os = new cfl(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, DirectEncodedMemoryArea.get(19, 7));
   public static final cfk Cu = new cfl(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, 268435456, 31, 63, DirectEncodedMemoryArea.get(11, 10));
   public static final cfk hZ = new cfl(AbstractImmediateOperandBuilder.ImmediateType.ZeroExtendLength, 268435456, 31, 63, DirectEncodedMemoryArea.get(16, 10));

   private cfl(AbstractImmediateOperandBuilder.ImmediateType var1, IEncodedMemoryArea var2) {
      super(var1, 0, 0, var2);
   }

   private cfl(AbstractImmediateOperandBuilder.ImmediateType var1, int var2, int var3, IEncodedMemoryArea var4) {
      super(var1, var2, var3, var4);
   }

   private cfl(AbstractImmediateOperandBuilder.ImmediateType var1, int var2, int var3, int var4, IEncodedMemoryArea var5) {
      super(var1, var2, var3, var4, var5);
   }

   public cfl pC() {
      this.flags |= 8388608;
      return this;
   }

   protected cfj pC(int var1, long var2) {
      byte var4 = 1;
      if ((this.flags & 2097152) != 0) {
         var4 = 3;
      } else if ((this.flags & 4194304) != 0) {
         var4 = 2;
      }

      return (this.flags & 8388608) != 0
         ? new cfj(var4, 65536, this.getSize(), var2, this.isSigned())
         : new cfj(var4, 0, this.getSize(), var2, this.isSigned());
   }
}
