package com.pnfsoftware.jebglobal;

import java.util.Comparator;

class cns implements Comparator {
   cns(cnr var1) {
      this.q = var1;
   }

   public int q(ads var1, ads var2) {
      return (Integer)var1.RF("IndexInheritanceAttr") - (Integer)var2.RF("IndexInheritanceAttr");
   }
}
