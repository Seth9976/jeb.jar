package com.pnfsoftware.jeb.corei.parsers.dex;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.android.dex.DalvikParserErrorType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jebglobal.bif;
import com.pnfsoftware.jebglobal.bir;
import com.pnfsoftware.jebglobal.bjp;

class Nz implements bir {
   Nz(bK var1) {
      this.q = var1;
   }

   @Override
   public void q(DalvikParserErrorType var1, int var2, int var3, int var4, int var5, bif.eo var6) {
      bjp var9 = (bjp)this.q.PV().q(var5);
      String var7;
      String var8;
      if (var9 != null) {
         var7 = Strings.ff(S.L("Parsing error at %s (+%Xh/@%Xh): %s"), var9.q(false), var4, var4 / 2, var1);
         var8 = Strings.ff("%s+%Xh", var9.getSignature(false), var4);
      } else {
         var7 = Strings.ff(S.L("Parsing error at %Xh (+%Xh/@%Xh) : %s"), var2, var4, var4 / 2, var1);
         var8 = null;
      }

      if (var6 != null) {
         var7 = var7 + Strings.ff(" (%s)", var6.xK);
      }

      UnitUtil.logError(this.q, var8, true, bK.ui, var7);
   }
}
