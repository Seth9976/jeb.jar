package com.pnfsoftware.jeb.corei.parsers.apk;

import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.code.android.IDexDynamic;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Ser
public class cq implements IDexDynamic {
   @SerId(1)
   private Map pC = new HashMap();

   @Override
   public void registerDynamicJni(String var1, IUnit var2, long var3) {
      Object var5 = (List)this.pC.get(var1);
      if (var5 == null) {
         var5 = new ArrayList();
         this.pC.put(var1, var5);
      }

      var5.add(new DH(var2, var3));
   }

   @Override
   public List getJniMethods(String var1) {
      return (List)this.pC.get(var1);
   }
}
