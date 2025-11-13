package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.IAddressableUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.debug.ITypedValue;
import com.pnfsoftware.jeb.core.units.code.debug.impl.AbstractValueComposite;
import com.pnfsoftware.jeb.util.base.TypedContent;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.Bv;
import com.pnfsoftware.jebglobal.bA;
import com.pnfsoftware.jebglobal.bft;
import com.pnfsoftware.jebglobal.rG;
import com.pnfsoftware.jebglobal.uG;
import java.util.List;

public class nA extends Bv {
   private static final ILogger pC = GlobalLog.getLogger(nA.class);
   private com.pnfsoftware.jeb.corei.parsers.dex.vi A;

   nA(Tb tb, com.pnfsoftware.jeb.corei.parsers.dex.vi a) {
      super(tb, "JDB Registers");
      this.A = a;
   }

   @Override
   public boolean isTarget(IUnit unit) {
      return UnitUtil.isAncestorOf(unit, this.A);
   }

   @Override
   public TypedContent getItemInformation(IAddressableUnit addressableUnit, long n, String s, List list) {
      if (!this.isTarget(addressableUnit)) {
         return null;
      } else {
         Object objectById = this.A.getObjectById(n);
         if (!(objectById instanceof bft) && (n & -72057594037927936L) != 648518346341351424L) {
            return null;
         } else if (((Tb)this.getPrimaryTarget()).gy() && !((Tb)this.getPrimaryTarget()).x()) {
            Mh dr = ((Tb)this.getPrimaryTarget()).sY();
            if (dr != null && dr.A()) {
               Kr aj = null;
               if ((n & -72057594037927936L) == 648518346341351424L) {
                  int n2 = (int)(n & 65535L);
                  uX hw = dr.kS();
                  if (hw != null) {
                     aj = hw.pC(n2);
                  }
               } else if (objectById instanceof bft bfz) {
                  aj = this.x(bfz);
               }

               if (aj == null) {
                  return null;
               } else {
                  String htmlEscape = Formatter.htmlEscape(aj.getName());
                  ITypedValue typedValue = aj.getTypedValue();
                  String htmlEscape2 = Formatter.htmlEscape(typedValue.getTypeName());
                  String s2;
                  if (typedValue instanceof AbstractValueComposite abstractValueComposite) {
                     s2 = Formatter.htmlEscape(Strings.safe2(abstractValueComposite.format(), S.L("The object value cannot be determined")));
                  } else {
                     s2 = Formatter.htmlEscape(Strings.safe2(typedValue, S.L("The value cannot be determined")));
                  }

                  StringBuilder sb = new StringBuilder();
                  sb.append("<pre>\n");
                  Strings.ff(sb, "%s - %s\n", htmlEscape, htmlEscape2);
                  Strings.ff(sb, "%s\n", s2);
                  sb.append("</pre>\n");
                  return TypedContent.html(sb.toString());
               }
            } else {
               return TypedContent.text(S.L("This operation cannot be performed while the thread is running"));
            }
         } else {
            return TypedContent.text(S.L("The operation cannot be performed now"));
         }
      }
   }

   Kr x(bft bfz) {
      try {
         bA u = (bA)((Tb)this.getPrimaryTarget()).UT();
         long id = ((Tb)this.getPrimaryTarget()).sY().getId();
         uG hw = u.gp().A(id, u.gp().pC(id, 0, 1).pC[0].pC);
         long x = u.fI().pC(hw.A);
         if (x < 0L) {
            return null;
         } else {
            long vi = u.oT().UT(x, bfz.getName(false), null);
            if (vi < 0L) {
               return null;
            } else {
               ITypedValue x2 = ((Tb)this.getPrimaryTarget()).pC((rG)u.gp().gy(hw.A, vi).get(0));
               return x2 == null ? null : new Kr(bfz.getName(true), x2, gb.pC(0), (Tb)this.getPrimaryTarget(), null);
            }
         }
      } catch (Exception var11) {
         pC.catchingSilent(var11);
         return null;
      }
   }
}
