package com.pnfsoftware.jeb.corei.parsers.dex;

import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.units.UnitChangeEventData;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
@Ser
public class tw {
   @SerId(1)
   bK q;
   @SerId(2)
   Map RF = new ConcurrentHashMap();
   @SerId(3)
   Map xK = new ConcurrentHashMap();

   public tw(bK var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.q = var1;
      }
   }

   public String q(ICodeCoordinates var1) {
      return (String)this.RF.get(var1);
   }

   public Map q() {
      return this.RF;
   }

   public boolean q(ICodeCoordinates var1, String var2, boolean var3) {
      String var4;
      synchronized (this.RF) {
         var4 = (String)this.RF.get(var1);
         if (var4 == null && var2 == null || var4 != null && var4.equals(var2)) {
            return true;
         }

         if (var2 != null && var2.length() == 0) {
            var2 = null;
         }

         if (var2 == null) {
            this.RF.remove(var1);
         } else {
            this.RF.put(var1, var2);
         }
      }

      this.q.q(var3, new UnitChangeEventData(3, this, var2, var4, var1));
      return true;
   }

   public String q(ICodeCoordinates var1, int var2, int var3) {
      String var4 = (String)this.RF.get(var1);
      Collection var5 = this.RF(var1);
      if (var5.isEmpty()) {
         return var4 == null ? "" : var4;
      } else {
         StringBuilder var6 = new StringBuilder(var4 == null ? "" : var4);

         for (tw.eo var8 : var5) {
            if ((var8.RF & var2) == var2 && (var8.RF & var3) == 0) {
               if (var6.length() > 0) {
                  var6.append('\n');
               }

               var6.append(var8.q);
            }
         }

         return var6.toString();
      }
   }

   public Map RF() {
      return this.xK;
   }

   public Collection RF(ICodeCoordinates var1) {
      Collection var2 = (Collection)this.xK.get(var1);
      return (Collection)(var2 == null ? Collections.emptySet() : Collections.unmodifiableCollection(var2));
   }

   public boolean q(ICodeCoordinates var1, tw.eo var2, boolean var3) {
      boolean var4 = Maps.putMulti(this.xK, var1, var2, LinkedHashSet::new);
      this.q.q(var4 && var3, new UnitChangeEventData(3, this.q, var2.q, null, var1));
      return var4;
   }

   public boolean RF(ICodeCoordinates var1, tw.eo var2, boolean var3) {
      boolean var4 = Maps.removeMulti(this.xK, var1, var2);
      this.q.q(var4 && var3, new UnitChangeEventData(3, this.q, var2.q, null, var1));
      return var4;
   }

   @Deprecated
   @Ser
   public static class eo {
      @SerId(1)
      String q;
      @SerId(2)
      int RF;

      public eo(String var1, int var2) {
         if (var1 == null) {
            throw new IllegalArgumentException();
         } else {
            this.q = var1;
            this.RF = var2;
         }
      }

      public eo(String var1) {
         this(var1, 0);
      }

      public String q() {
         return this.q;
      }

      public int RF() {
         return this.RF;
      }

      @Override
      public int hashCode() {
         byte var1 = 1;
         return 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
      }

      @Override
      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (var1 == null) {
            return false;
         } else if (this.getClass() != var1.getClass()) {
            return false;
         } else {
            tw.eo var2 = (tw.eo)var1;
            if (this.q == null) {
               if (var2.q != null) {
                  return false;
               }
            } else if (!this.q.equals(var2.q)) {
               return false;
            }

            return true;
         }
      }

      @Override
      public String toString() {
         return this.q + (this.RF == 0 ? "" : " (" + q(this.RF) + ")");
      }

      public static String q(int var0) {
         return var0 + "";
      }
   }
}
