package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICommentManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class aah implements ICommentManager {
   aah(aaf var1) {
      this.q = var1;
   }

   @Override
   public Map getComments() {
      HashMap var1 = new HashMap();

      for (Entry var3 : this.q.RF.getPrimaryMap2(0).entrySet()) {
         Long var4 = this.q.RF.coordToMemory((ICodeCoordinates)var3.getKey());
         if (var4 != -1L) {
            var1.put(var4, (String)var3.getValue());
         }
      }

      return var1;
   }

   @Override
   public String getComment(long var1) {
      ICodeCoordinates var3 = this.q.RF.memoryToCoord(var1);
      return var3 == null ? null : this.q.RF.getPrimary2(var3, 0);
   }

   @Override
   public boolean setComment(long var1, String var3) {
      ICodeCoordinates var4 = this.q.RF.memoryToCoord(var1);
      return var4 == null ? false : this.q.RF.setPrimary2(var4, var3, 0, true);
   }

   @Override
   public boolean addComment(long var1, String var3) {
      ICodeCoordinates var4 = this.q.RF.memoryToCoord(var1);
      if (var4 == null) {
         return false;
      } else {
         String var5 = this.q.RF.getPrimary2(var4, 0);
         if (var5 != null) {
            var3 = var5 + "\n" + var3;
         }

         return this.q.RF.setPrimary2(var4, var3, 0, true);
      }
   }
}
