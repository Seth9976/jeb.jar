package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.IAddressableUnit;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerUnit;
import com.pnfsoftware.jeb.core.units.code.debug.impl.DebuggerUtil;
import com.pnfsoftware.jeb.util.base.TypedContent;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class vN extends Bv {
   private static final ILogger pC = GlobalLog.getLogger(vN.class);
   private List A;
   private Map kS = null;

   vN(ia ia, List a) {
      super(ia, "GDB Registers");
      this.A = a;
   }

   @Override
   public boolean isTarget(IUnit unit) {
      Iterator iterator = this.A.iterator();

      while (iterator.hasNext()) {
         if (unit == iterator.next()) {
            return true;
         }
      }

      return false;
   }

   @Override
   public TypedContent getItemInformation(IAddressableUnit addressableUnit, long l, String s, List list) {
      if (!this.isTarget(addressableUnit)) {
         return null;
      } else if ((l & -72057594037927936L) != -9079256848778919936L) {
         return null;
      } else {
         Long value = null;
         Object itemObject = addressableUnit.getItemObject(l);
         if (itemObject instanceof avn avn) {
            value = avn.kS();
         } else if (itemObject instanceof Long n2) {
            value = n2;
         }

         if (value == null) {
            return null;
         } else {
            HX gp = ((ia)this.getPrimaryTarget()).gp();
            if (gp == null) {
               return null;
            } else {
               LD x = gp.pC(false);
               if (x == null) {
                  return null;
               } else {
                  int intValue = (int)value.longValue();
                  if (intValue > x.size()) {
                     if (this.kS == null) {
                        this.kS = new HashMap();

                        for (int i = 0; i < x.size(); i++) {
                           this.kS.put(x.getName(i).toLowerCase(), i);
                        }
                     }

                     Integer n = null;
                     Iterator iterator = this.A.iterator();

                     while (iterator.hasNext()) {
                        n = (Integer)this.kS.get(((INativeCodeUnit)iterator.next()).getProcessor().getRegisterName(intValue).toLowerCase());
                        if (n != null) {
                           break;
                        }
                     }

                     if (n == null) {
                        pC.error(S.L("Unable to determine itemId for item: %xh"), l);
                        return null;
                     }

                     intValue = n;
                  }

                  String name = x.getName(intValue);
                  if (name == null) {
                     return null;
                  } else {
                     Long valueAsLong = x.getValueAsLong(intValue);
                     if (valueAsLong == null) {
                        return null;
                     } else {
                        byte[] memoryStringSafe = DebuggerUtil.readMemoryStringSafe((IDebuggerUnit)this.getPrimaryTarget(), valueAsLong, 8);
                        StringBuilder sb = new StringBuilder("<table>");
                        Strings.ff(
                           sb,
                           "<tr><td style='padding:0 5px;'>%s</td><td style='padding:0 5px;'>%Xh</td><td style='padding:0 5px;'>%d</td></tr>",
                           Formatter.htmlEscape(name),
                           valueAsLong,
                           valueAsLong
                        );
                        if (memoryStringSafe != null) {
                           String s2 = Formatter.byteArrayToHex(memoryStringSafe, 0, Math.min(8, memoryStringSafe.length)) + "";
                           int asciiLength = Strings.getAsciiLength(memoryStringSafe);
                           String decodeLocal = "";
                           if (asciiLength > 0) {
                              decodeLocal = Strings.decodeLocal(memoryStringSafe, 0, asciiLength);
                           }

                           Strings.ff(
                              sb,
                              "<tr><td style='padding:0 5px;'>@%s</td><td style='padding:0 5px;'>%s</td><td style='padding:0 5px;'>%s</td></tr>",
                              Formatter.htmlEscape(name),
                              Formatter.htmlEscape(s2),
                              Formatter.htmlEscape(decodeLocal)
                           );
                        }

                        sb.append("</table>");
                        return TypedContent.html(sb.toString());
                     }
                  }
               }
            }
         }
      }
   }
}
