package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractOperandBuilder;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class cfn extends AbstractOperandBuilder implements cfk {
   private int z = 0;
   public static cfn pC = new cfn(DirectEncodedMemoryArea.get(21, 5));
   public static cfn A = new cfn(3, DirectEncodedMemoryArea.get(21, 5));
   public static cfn kS = new cfn(DirectEncodedMemoryArea.get(16, 5));
   public static cfn wS = new cfn(DirectEncodedMemoryArea.get(16, 5), 0);
   public static cfn UT = new cfn(3, DirectEncodedMemoryArea.get(16, 5));
   public static cfn E = new cfn(4, DirectEncodedMemoryArea.get(16, 5));
   public static cfn sY = new cfn(5, DirectEncodedMemoryArea.get(11, 5));
   public static cfn ys = new cfn(DirectEncodedMemoryArea.get(11, 5));
   public static cfn ld = new cfn(DirectEncodedMemoryArea.get(11, 5), 31);
   public static cfn gp = new cfn(3, DirectEncodedMemoryArea.get(11, 5));
   public static cfn oT = new cfn(3, DirectEncodedMemoryArea.get(6, 5));
   public static cfn fI = new cfn(6, DirectEncodedMemoryArea.get(21, 2));
   public static cfn WR = new cfn(6, DirectEncodedMemoryArea.get(11, 2));
   public static cfn NS = new cfn(DirectEncodedMemoryArea.get(11, 5));
   public static cfn vP = new cfn(DirectEncodedMemoryArea.get(16, 5));
   public static cfn xC = new cfn(DirectEncodedMemoryArea.get(21, 5));
   public static final cfk[] ED = new cfk[]{pC, kS};
   public static final cfk[] Sc = new cfk[]{xC, vP};
   public static final cfk[] ah = new cfk[]{ys, pC, kS};
   public static final cfk[] eP = new cfk[]{NS, xC, vP};
   public static final cfn UO = new cfn(2, DirectEncodedMemoryArea.get(18, 3));
   public static final cfn Ab = new cfn(2, DirectEncodedMemoryArea.get(18, 3), 0);
   public static final cfn rl = new cfn(2, DirectEncodedMemoryArea.get(8, 3), 0);

   public cfn(IEncodedMemoryArea var1) {
      super(var1);
   }

   public cfn(int var1, IEncodedMemoryArea var2) {
      super(var2);
      this.z = var1;
   }

   public cfn(int var1, IEncodedMemoryArea var2, int var3) {
      super(var2, 268435456, var3, 0);
      this.z = var1;
   }

   public cfn(IEncodedMemoryArea var1, int var2) {
      super(var1, 268435456, var2, 0);
   }

   public cfj pC(byte[] var1, int var2) throws ProcessorException {
      int var3 = this.decodeMemoryArea(var1);
      return this.isOptional(var3) ? null : cfj.A(this.z, var3, var2);
   }
}
