package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EEquationMatcher {
   INode t0;
   INode t1;
   List templates = new ArrayList();
   Map map;

   public EEquationMatcher(INode var1, INode var2) {
      this.t0 = var1;
      this.t1 = var2;
   }

   public Map getMatchMap() {
      return this.map;
   }

   public boolean isMatch(IEGeneric var1, IEGeneric var2) {
      EExpressionMatcher var3 = new EExpressionMatcher(this.t0);
      if (var3.isMatch(var1)) {
         this.map = var3.getMatchMap();
         EExpressionMatcher var4 = new EExpressionMatcher(this.t1, this.map);
         if (var4.isMatch(var2)) {
            return true;
         }
      } else {
         var3.reset();
         if (var3.isMatch(var2)) {
            this.map = var3.getMatchMap();
            EExpressionMatcher var5 = new EExpressionMatcher(this.t1, this.map);
            if (var5.isMatch(var1)) {
               return true;
            }
         }
      }

      return false;
   }
}
