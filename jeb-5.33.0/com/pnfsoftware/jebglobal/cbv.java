package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;

class cbv implements bst {
   @Override
   public boolean pC(bsq var1, bsq.Av var2) {
      IDImm var3 = (IDImm)var2.pC(1, IDImm.class);
      IDImm var4 = (IDImm)var2.pC(2, IDImm.class);
      IDImm var5 = (IDImm)var2.pC(3, IDImm.class);
      IDImm var6 = (IDImm)var2.pC(4, IDImm.class);
      IDImm var7 = (IDImm)var2.pC(5, IDImm.class);
      IDImm var8 = (IDImm)var2.pC(6, IDImm.class);
      return var4._and(var6._xor(var8)).isZero()
         && var3._xor(var4)._and(var5._and(var6)._or(var7)._xor(var8)).isZero()
         && var4._add(var6._xor(var8))._xor(var3._xor(var4)._add(var5._and(var6)._or(var7)._xor(var8))).isZero();
   }
}
