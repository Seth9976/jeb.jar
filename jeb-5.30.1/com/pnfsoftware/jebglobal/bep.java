package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;

public class bep extends ber {
   public String q;
   public beu RF;
   public List xK = new ArrayList();
   public Long Dw;

   bep(bew var1) {
      super(var1);
      this.q = var1.Uv("name");
      bew var2 = var1.Dw("code");
      if (var2 != null && var2.RF("entryPoint")) {
         this.Dw = var1.q.nf + ((Number)var2.q("entryPoint", Number.class)).longValue();
      }
   }

   @Override
   void q(bev var1) {
   }

   @Override
   public String toString() {
      String var1 = "";
      if (this.RF == null) {
         var1 = var1 + this.q;
      } else {
         var1 = var1 + Strings.ff("%s %s(%s)", this.RF, this.q, Strings.join(", ", this.xK));
      }

      if (this.Dw != null) {
         var1 = var1 + Strings.ff(" @ 0x%X", this.Dw);
      }

      return var1;
   }
}
