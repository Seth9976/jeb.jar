package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTypeFactory;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DTypeInfo {
   IDMethodContext ctx;
   List conflicts = new ArrayList();
   int unchanged;
   int changed;

   public DTypeInfo(IDMethodContext var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.ctx = var1;
      }
   }

   public IDMethodContext getContext() {
      return this.ctx;
   }

   public IJavaTypeFactory getTypeFactory() {
      return this.ctx.getTypeFactory();
   }

   public void recordConflict(String var1, IDExpression var2, IJavaType var3, IJavaType var4) {
      DTypeInfo.TypingConlict var5 = new DTypeInfo.TypingConlict(var1, var2, var3, var4);
      this.conflicts.add(var5);
   }

   public void resetCounters() {
      this.changed = 0;
      this.unchanged = 0;
   }

   public void reset() {
      this.resetCounters();
      this.conflicts.clear();
   }

   public int recordChanged() {
      this.changed++;
      return this.changed;
   }

   public int getChangedCounter() {
      return this.changed;
   }

   public int recordUnchanged() {
      this.unchanged++;
      return this.unchanged;
   }

   public int getUnchangedCounter() {
      return this.unchanged;
   }

   public List getConflicts() {
      return Collections.unmodifiableList(this.conflicts);
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "TI:mods=%d,moot=%d", this.changed, this.unchanged);

      for (DTypeInfo.TypingConlict var3 : this.conflicts) {
         Strings.ff(var1, "\n- CONFLICT: %s", var3);
      }

      return var1.toString();
   }

   public static class TypingConlict {
      String msg;
      IDExpression exp;
      IJavaType currentType;
      IJavaType wantedType;

      public TypingConlict(String var1, IDExpression var2, IJavaType var3, IJavaType var4) {
         this.msg = var1;
         this.exp = var2;
         this.currentType = var3;
         this.wantedType = var4;
      }

      public String getMessage() {
         return this.msg;
      }

      public IDExpression getExpression() {
         return this.exp;
      }

      public IJavaType getCurrentType() {
         return this.currentType;
      }

      public IJavaType getWantedType() {
         return this.wantedType;
      }

      @Override
      public String toString() {
         return Strings.ff("%s: target=\"%s\", current_type=%s, wanted_type=%s", this.msg, this.exp, this.currentType, this.wantedType);
      }
   }
}
