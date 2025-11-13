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
public class Nt implements IDexDynamic {
   @SerId(1)
   private Map q = new HashMap();

   @Override
   public void registerDynamicJni(String var1, IUnit var2, long var3) {
      Object var5 = (List)this.q.get(var1);
      if (var5 == null) {
         var5 = new ArrayList();
         this.q.put(var1, var5);
      }

      var5.add(new iZ(var2, var3));
   }

   @Override
   public List getJniMethods(String var1) {
      return (List)this.q.get(var1);
   }
}
