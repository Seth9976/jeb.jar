package com.pnfsoftware.jeb.corei.parsers.dex;

import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMap;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Ser
public class PY implements IDexMap {
   private static final ILogger q = GlobalLog.getLogger(PY.class);
   @SerId(1)
   private Map RF = new HashMap();

   @Override
   public Collection getEntries() {
      return Collections.unmodifiableCollection(this.RF.values());
   }

   @Override
   public IDexMap.Entry getEntry(int var1) {
      return (IDexMap.Entry)this.RF.get(var1);
   }

   public static PY q(byte[] var0, int var1) {
      PY var2 = new PY();
      int var3 = DexUtil.bytearrayULEInt32ToInt(var0, var1);
      var1 += 4;

      for (int var4 = 0; var4 < var3; var4++) {
         int var5 = DexUtil.bytearrayULEInt16ToInt(var0, var1);
         DexUtil.bytearrayULEInt16ToInt(var0, var1 + 2);
         int var6 = DexUtil.bytearrayULEInt32ToInt(var0, var1 + 4);
         int var7 = DexUtil.bytearrayULEInt32ToInt(var0, var1 + 8);
         var1 += 12;
         switch (var5) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 4096:
            case 4097:
            case 4098:
            case 4099:
            case 8192:
            case 8193:
            case 8194:
            case 8195:
            case 8196:
            case 8197:
            case 8198:
            case 61440:
               var2.RF.put(var5, new IDexMap.Entry(var5, var7, var6));
               break;
         }
      }

      return var2;
   }
}
