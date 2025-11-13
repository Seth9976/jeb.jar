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
import com.pnfsoftware.jebglobal.Ux;
import com.pnfsoftware.jebglobal.VO;
import com.pnfsoftware.jebglobal.bjo;
import com.pnfsoftware.jebglobal.ch;
import com.pnfsoftware.jebglobal.ct;
import java.util.List;

public class qa extends ct {
   private static final ILogger q = GlobalLog.getLogger(qa.class);
   private com.pnfsoftware.jeb.corei.parsers.dex.bK RF;

   qa(CI ci, com.pnfsoftware.jeb.corei.parsers.dex.bK rf) {
      super(ci, "JDB Registers");
      this.RF = rf;
   }

   @Override
   public boolean isTarget(IUnit unit) {
      return UnitUtil.isAncestorOf(unit, this.RF);
   }

   @Override
   public TypedContent getItemInformation(IAddressableUnit addressableUnit, long n, String s, List list) {
      if (!this.isTarget(addressableUnit)) {
         return null;
      } else {
         Object objectById = this.RF.getObjectById(n);
         if (!(objectById instanceof bjo) && (n & -72057594037927936L) != 648518346341351424L) {
            return null;
         } else if (((CI)this.getPrimaryTarget()).RF() && !((CI)this.getPrimaryTarget()).q()) {
            kY iq = ((CI)this.getPrimaryTarget()).nf();
            if (iq != null && iq.RF()) {
               LR ug = null;
               if ((n & -72057594037927936L) == 648518346341351424L) {
                  int n2 = (int)(n & 65535L);
                  Xa lc = iq.xK();
                  if (lc != null) {
                     ug = lc.q(n2);
                  }
               } else if (objectById instanceof bjo bjo) {
                  ug = this.q(bjo);
               }

               if (ug == null) {
                  return null;
               } else {
                  String htmlEscape = Formatter.htmlEscape(ug.getName());
                  ITypedValue typedValue = ug.getTypedValue();
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

   LR q(bjo bjo) {
      try {
         Ux ux = (Ux)((CI)this.getPrimaryTarget()).oW();
         long id = ((CI)this.getPrimaryTarget()).nf().getId();
         VO xk = ux.zz().xK(id, ux.zz().q(id, 0, 1).q[0].q);
         long q = ux.HF().q(xk.RF);
         if (q < 0L) {
            return null;
         } else {
            long uv = ux.JY().Uv(q, bjo.getName(false), null);
            if (uv < 0L) {
               return null;
            } else {
               ITypedValue q2 = ((CI)this.getPrimaryTarget()).q((ch)ux.zz().RF(xk.RF, uv).get(0));
               return q2 == null ? null : new LR(bjo.getName(true), q2, HA.q(0), (CI)this.getPrimaryTarget(), null);
            }
         }
      } catch (Exception var11) {
         qa.q.catchingSilent(var11);
         return null;
      }
   }
}
