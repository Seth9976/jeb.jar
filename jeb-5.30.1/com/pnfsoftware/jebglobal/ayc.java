package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureTypeField;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class ayc {
   @SerId(1)
   private bby q;

   public ayc(bby var1) {
      this.q = var1;
   }

   public ayb q(IStructureType var1, int var2) {
      IStructureTypeField var3 = var1.getFieldAt(var2);
      if (var3 == null) {
         return null;
      } else if (var2 < 0) {
         return null;
      } else {
         int var4 = var1.getIndex();
         if ((var4 & 0xFF000000) != 0) {
            return null;
         } else {
            long var5 = -8646911284551352320L | (var4 & 16777215L) << 32 | var2 & 4294967295L;
            return new ayb(var5, var1, var3);
         }
      }
   }

   public ayb q(long var1) {
      if ((var1 & -72057594037927936L) != -8646911284551352320L) {
         return null;
      } else {
         int var3 = (int)(var1 >> 32 & 16777215L);
         int var4 = (int)var1;
         bbd var5 = this.q.q(var3);
         if (!(var5 instanceof bbv)) {
            return null;
         } else {
            bbu var6 = ((bbv)var5).oW(var4);
            return var6 == null ? null : new ayb(var1, (bbv)var5, var6);
         }
      }
   }
}
