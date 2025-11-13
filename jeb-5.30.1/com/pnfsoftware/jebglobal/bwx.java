package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class bwx {
   IDMethodContext q;
   bxj RF;
   bxj xK;
   List Dw = new ArrayList();
   Map Uv;

   public bwx(IDMethodContext var1, bxj var2, bxj var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
   }

   public Map q() {
      return this.Uv;
   }

   public boolean q(IDExpression var1, IDExpression var2) {
      bxa var3 = new bxa(this.q, this.RF);
      if (var3.q(var1)) {
         this.Uv = var3.xK();
         bxa var4 = new bxa(this.q, this.xK, this.Uv);
         if (var4.q(var2)) {
            return true;
         }
      } else {
         var3.Dw();
         if (var3.q(var2)) {
            this.Uv = var3.xK();
            bxa var5 = new bxa(this.q, this.xK, this.Uv);
            if (var5.q(var1)) {
               return true;
            }
         }
      }

      return false;
   }
}
