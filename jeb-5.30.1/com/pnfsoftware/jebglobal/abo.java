package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICommentManager;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryModelEventType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Ser
public class abo implements ICommentManager {
   @SerId(1)
   private Bm q;
   @SerId(2)
   private Map RF = new ConcurrentHashMap();

   public abo(Bm var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.q = var1;
      }
   }

   @Override
   public Map getComments() {
      return this.RF;
   }

   @Override
   public String getComment(long var1) {
      return (String)this.RF.get(var1);
   }

   @Override
   public boolean setComment(long var1, String var3) {
      String var4 = (String)this.RF.get(var1);
      if ((var4 != null || var3 != null) && !Strings.equals(var4, var3)) {
         if (var3 != null && !var3.isEmpty()) {
            this.RF.put(var1, var3);
         } else {
            this.RF.remove(var1);
         }

         this.q.notifyListenersOfModelChange(MemoryModelEventType.COMMENT_UPDATE, var1);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean addComment(long var1, String var3) {
      String var4 = (String)this.RF.get(var1);
      if (var4 != null) {
         var3 = var4 + "\n" + var3;
      }

      return this.setComment(var1, var3);
   }
}
