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

public class ny extends ct {
   private static final ILogger q = GlobalLog.getLogger(ny.class);
   private static final String RF = "<td style='padding:0 5px;'>";
   private static final String xK = "<table>";
   private static final String Dw = "<tr><td style='padding:0 5px;'>%s</td><td style='padding:0 5px;'>%Xh</td><td style='padding:0 5px;'>%d</td></tr>";
   private static final String Uv = "<tr><td style='padding:0 5px;'>@%s</td><td style='padding:0 5px;'>%s</td><td style='padding:0 5px;'>%s</td></tr>";
   private static final String oW = "</table>";
   private List gO;
   private Map nf = null;

   ny(um um, List go) {
      super(um, "GDB Registers");
      this.gO = go;
   }

   @Override
   public boolean isTarget(IUnit unit) {
      Iterator iterator = this.gO.iterator();

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
         if (itemObject instanceof ayj ayj) {
            value = ayj.xK();
         } else if (itemObject instanceof Long n2) {
            value = n2;
         }

         if (value == null) {
            return null;
         } else {
            Cg lm = ((um)this.getPrimaryTarget()).lm();
            if (lm == null) {
               return null;
            } else {
               Ht tc = lm.q(false);
               if (tc == null) {
                  return null;
               } else {
                  int intValue = (int)value.longValue();
                  if (intValue > tc.size()) {
                     if (this.nf == null) {
                        this.nf = new HashMap();

                        for (int i = 0; i < tc.size(); i++) {
                           this.nf.put(tc.getName(i).toLowerCase(), i);
                        }
                     }

                     Integer n = null;
                     Iterator iterator = this.gO.iterator();

                     while (iterator.hasNext()) {
                        n = (Integer)this.nf.get(((INativeCodeUnit)iterator.next()).getProcessor().getRegisterName(intValue).toLowerCase());
                        if (n != null) {
                           break;
                        }
                     }

                     if (n == null) {
                        q.error(S.L("Unable to determine itemId for item: %xh"), l);
                        return null;
                     }

                     intValue = n;
                  }

                  String name = tc.getName(intValue);
                  if (name == null) {
                     return null;
                  } else {
                     Long valueAsLong = tc.getValueAsLong(intValue);
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
