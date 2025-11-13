package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.tree.ICodeNode;
import com.pnfsoftware.jeb.core.units.code.ICodeHierarchy;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bbk implements ICodeHierarchy {
   @SerId(1)
   abg q;

   public bbk(abg var1) {
      this.q = var1;
   }

   public bbo q() {
      return this.q.RF().JY();
   }

   @Override
   public ICodeNode findNode(String var1, boolean var2) {
      long var3 = this.q.getCanonicalMemoryAddress(var1);
      if (var3 == -1L) {
         return null;
      } else {
         axp var5 = this.q.q(var3, !var2);
         return var5 == null ? null : this.q.RF().q((axj)var5);
      }
   }
}
