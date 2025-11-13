package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.util.collect.Maps;
import java.util.Map;

public class OO extends FB {
   private int E = 0;
   private final boolean sY;
   private final Map ys;
   public static final Hu pC = new OO(0, 16, true);
   public static final Hu A = new OO(0, 8, false);
   public static final Hu kS = new OO(0, 16, false);
   public static final Hu wS = new OO(0, 9, Maps.toMap(8, 14));
   public static final Hu UT = new OO(0, 9, Maps.toMap(8, 15));

   public OO(int var1, int var2, boolean var3) {
      super(DirectEncodedMemoryArea.get(var1, var2));
      this.sY = var3;
      this.ys = null;
   }

   public OO(int var1, int var2, Map var3) {
      super(DirectEncodedMemoryArea.get(var1, var2));
      this.sY = false;
      this.ys = var3;
   }

   @Override
   protected int pC(byte[] var1, int var2) {
      return (this.sY ? (var1[1] & 64) == 0 : !this.sY) ? 0 : 524288;
   }

   @Override
   protected Yg pC(int var1, int var2) {
      int var3 = var1;
      if (this.ys != null) {
         Integer var4 = (Integer)this.ys.get(var1);
         if (var4 != null) {
            var3 = var4;
         }
      }

      return LC.kS(this.E, var3, var2);
   }
}
