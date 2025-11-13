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
public class kf implements IRegistersResolution {
   public static final kf pC = new kf();
   @SerId(1)
   Map A;
   @SerId(2)
   Map kS;

   @Override
   public boolean isEmpty() {
      return (this.A == null || this.A.isEmpty()) && (this.kS == null || this.kS.isEmpty());
   }

   private Map pC(boolean var1, int var2) {
      if (var1) {
         if (this.kS == null) {
            if (var2 == 1) {
               this.kS = new ConcurrentHashMap();
            } else if (var2 == 2) {
               return Collections.emptyMap();
            }
         }

         return this.kS;
      } else {
         if (this.A == null) {
            if (var2 == 1) {
               this.A = new ConcurrentHashMap();
            } else if (var2 == 2) {
               return Collections.emptyMap();
            }
         }

         return this.A;
      }
   }

   private List pC(Map var1, long var2, int var4) {
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

   boolean pC(IUnitLock var1, boolean var2, long var3, long var5) {
      var1.verifyLocked();
      Map var7 = this.pC(var2, 1);
      List var8 = this.pC(var7, var3, 1);
      if (var8.contains(var5)) {
         return false;
      } else {
         var8.add(var5);
         return true;
      }
   }

   @Override
   public Map getAll(boolean var1) {
      return this.pC(var1, 2);
   }
}
