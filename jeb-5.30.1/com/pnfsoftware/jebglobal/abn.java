package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.IUnitLock;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IRegistersResolution;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Ser
public class abn implements IRegistersResolution {
   public static final abn q = new abn();
   private static final int Dw = 1;
   private static final int Uv = 2;
   @SerId(1)
   Map RF;
   @SerId(2)
   Map xK;

   @Override
   public boolean isEmpty() {
      return (this.RF == null || this.RF.isEmpty()) && (this.xK == null || this.xK.isEmpty());
   }

   private Map q(boolean var1, int var2) {
      if (var1) {
         if (this.xK == null) {
            if (var2 == 1) {
               this.xK = new ConcurrentHashMap();
            } else if (var2 == 2) {
               return Collections.emptyMap();
            }
         }

         return this.xK;
      } else {
         if (this.RF == null) {
            if (var2 == 1) {
               this.RF = new ConcurrentHashMap();
            } else if (var2 == 2) {
               return Collections.emptyMap();
            }
         }

         return this.RF;
      }
   }

   private List q(boolean var1, int var2, int var3) {
      int var4 = var3 == 1 ? 1 : 0;
      Map var5 = this.q(var1, var4);
      if (var5 == null) {
         return var3 == 2 ? Collections.emptyList() : null;
      } else {
         return this.q(var5, var2, var3);
      }
   }

   private List q(Map var1, long var2, int var4) {
      Object var5 = (List)var1.get(var2);
      if (var5 == null) {
         if (var4 == 1) {
            var5 = new CopyOnWriteArrayList();
            var1.put(var2, var5);
         } else if (var4 == 2) {
            return Collections.emptyList();
         }
      }

      return (List)var5;
   }

   boolean q(IUnitLock var1, boolean var2, long var3, long var5) {
      var1.verifyLocked();
      Map var7 = this.q(var2, 1);
      List var8 = this.q(var7, var3, 1);
      if (var8.contains(var5)) {
         return false;
      } else {
         var8.add(var5);
         return true;
      }
   }

   @Override
   public Map getAll(boolean var1) {
      return this.q(var1, 2);
   }
}
