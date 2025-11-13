package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class xQ extends PA {
   private static final int nf = 8;
   private static final int gP = 4;
   private static final int za = 2;
   private static final int lm = 1;
   private final bby zz;

   public xQ(aae var1) {
      super(var1);
      this.zz = var1.zz();
      this.q = "data-grp";
   }

   @Override
   protected List RF(long var1, long var3, boolean var5) {
      try {
         List var6 = this.q(var1, var3, 8);
         if (var6 != null) {
            return var6;
         }

         var6 = this.q(var1, var3, 4);
         if (var6 != null) {
            return var6;
         }

         var6 = this.q(var1, var3, 2, -256L);
         if (var6 != null) {
            return var6;
         }

         var6 = this.q(var1, var3, 1, -16L);
         if (var6 != null) {
            return var6;
         }
      } catch (MemoryException var7) {
      }

      return Collections.emptyList();
   }

   private List q(long var1, long var3, int var5) throws MemoryException {
      return this.q(var1, var3, var5, -65536L);
   }

   private List q(long var1, long var3, int var5, long var6) throws MemoryException {
      if (var5 == 1 && (var1 & 1L) != 0L && this.q(var1, 0, var5) == 0L) {
         var1++;
      }

      if (var3 - var1 >= var5 * this.xK(var5)) {
         ArrayList var8 = new ArrayList();
         boolean var9 = false;

         for (int var10 = 0; var1 + var5 * var10 < var3; var10++) {
            long var11 = this.q(var1, var10, var5);
            long var13 = var11 & var6;
            if (var13 != var6 && var13 != 0L) {
               break;
            }

            if (var11 == 0L) {
               if (var5 != 1) {
                  break;
               }

               if (var9) {
                  var8.remove(var10 - 1);
                  break;
               }

               var9 = true;
            } else {
               var9 = false;
            }

            var8.add(var1 + var5 * var10);
         }

         if (var8.size() >= this.xK(var5)) {
            ArrayList var15 = new ArrayList();
            if (var5 == 1) {
               bbq var16 = this.zz.xK().q(var5, true);
               this.zz.RF((INativeType)var16, var8.size());
               var15.add(this.RF.q(var1, var16, -1, true));
            } else {
               bbq var17 = this.zz.xK().q(var5, true);

               for (long var18 : var8) {
                  var15.add(this.RF.q(var18, var17, -1, true));
               }
            }

            return var15;
         }
      }

      return null;
   }

   private long q(long var1, int var3, int var4) throws MemoryException {
      switch (var4) {
         case 1:
            return this.Dw.readByte(var1 + var4 * var3);
         case 2:
            return this.Dw.readShort(var1 + var4 * var3);
         case 3:
         case 5:
         case 6:
         case 7:
         default:
            throw new MemoryException("Invalid size");
         case 4:
            return this.Dw.readInt(var1 + var4 * var3);
         case 8:
            return this.Dw.readLong(var1 + var4 * var3);
      }
   }

   private int xK(int var1) throws MemoryException {
      switch (var1) {
         case 1:
            return 8;
         case 2:
            return 5;
         case 3:
         case 5:
         case 6:
         case 7:
         default:
            throw new MemoryException("Invalid size");
         case 4:
            return 3;
         case 8:
            return 3;
      }
   }
}
