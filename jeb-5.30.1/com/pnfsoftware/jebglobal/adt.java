package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.ConcurrentHashSet;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

@Ser
public class adt {
   @SerId(1)
   private Map q = new ConcurrentHashMap();
   @SerId(2)
   private Map RF = new ConcurrentHashMap();

   public boolean q(Object var1) {
      boolean var2 = false;
      Set var3 = (Set)this.q.get(var1);
      if (var3 == null) {
         ConcurrentHashSet var5 = new ConcurrentHashSet();
         this.q.put(var1, var5);
         var2 = true;
      }

      Set var4 = (Set)this.RF.get(var1);
      if (var4 == null) {
         ConcurrentHashSet var6 = new ConcurrentHashSet();
         this.RF.put(var1, var6);
         var2 = true;
      }

      return var2;
   }

   public Set q() {
      HashSet var1 = new HashSet(this.q.keySet());
      var1.addAll(this.RF.keySet());
      return var1;
   }

   public Set RF(Object var1) {
      return (Set)this.q.get(var1);
   }

   public Set xK(Object var1) {
      return (Set)this.RF.get(var1);
   }

   public boolean q(ads var1) {
      boolean var2 = false;
      Object var3 = var1.RF();
      Object var4 = (Set)this.q.get(var3);
      if (var4 == null) {
         var4 = new ConcurrentHashSet();
         this.q.put(var3, var4);
      }

      if (var4.add(var1)) {
         var2 = true;
      }

      Object var5 = var1.xK();
      Object var6 = (Set)this.RF.get(var5);
      if (var6 == null) {
         var6 = new ConcurrentHashSet();
         this.RF.put(var5, var6);
      }

      if (var6.add(var1)) {
         var2 = true;
      }

      return var2;
   }

   public int Dw(Object var1) {
      Set var2 = (Set)this.RF.get(var1);
      return var2 != null ? var2.size() : 0;
   }

   public List Uv(Object var1) {
      return this.q(var1, (String[])null);
   }

   public List q(Object var1, String... var2) {
      ArrayList var3 = new ArrayList();
      Set var4 = (Set)this.RF.get(var1);
      if (var4 != null) {
         Iterator var5 = var4.iterator();

         while (true) {
            ads var6;
            boolean var7;
            do {
               if (!var5.hasNext()) {
                  return var3;
               }

               var6 = (ads)var5.next();
               if (var2 == null || var2.length <= 0) {
                  break;
               }

               var7 = false;

               for (String var11 : var2) {
                  if (var6.RF(var11) != null) {
                     var7 = true;
                     break;
                  }
               }
            } while (!var7);

            var3.add(var6.RF());
         }
      } else {
         return var3;
      }
   }

   public int oW(Object var1) {
      Set var2 = (Set)this.q.get(var1);
      return var2 != null ? var2.size() : 0;
   }

   public List gO(Object var1) {
      return this.RF(var1, (String[])null);
   }

   public List RF(Object var1, String... var2) {
      ArrayList var3 = new ArrayList();
      Set var4 = (Set)this.q.get(var1);
      if (var4 != null) {
         Iterator var5 = var4.iterator();

         while (true) {
            ads var6;
            boolean var7;
            do {
               if (!var5.hasNext()) {
                  return var3;
               }

               var6 = (ads)var5.next();
               if (var2 == null || var2.length <= 0) {
                  break;
               }

               var7 = false;

               for (String var11 : var2) {
                  if (var6.RF(var11) != null) {
                     var7 = true;
                     break;
                  }
               }
            } while (!var7);

            var3.add(var6.xK());
         }
      } else {
         return var3;
      }
   }

   public boolean q(Object var1, Object var2) {
      Set var4 = (Set)this.q.get(var1);
      if (var4 == null) {
         return false;
      } else {
         var4.removeIf(var1x -> var1x.xK().equals(var2));
         Set var5 = (Set)this.RF.get(var2);
         return var5 == null ? false : var5.removeIf(var1x -> var1x.RF().equals(var1));
      }
   }

   public boolean nf(Object var1) {
      Set var2 = (Set)this.q.remove(var1);
      if (var2 == null) {
         return false;
      } else {
         for (ads var4 : var2) {
            Set var5 = (Set)this.RF.get(var4.xK());
            if (var5 != null) {
               var5.remove(var4);
            }
         }

         return true;
      }
   }

   public boolean gP(Object var1) {
      Set var2 = (Set)this.RF.remove(var1);
      if (var2 == null) {
         return false;
      } else {
         for (ads var4 : var2) {
            Set var5 = (Set)this.q.get(var4.RF());
            if (var5 != null) {
               var5.remove(var4);
            }
         }

         return true;
      }
   }

   public boolean za(Object var1) {
      boolean var2 = false;
      if (this.nf(var1)) {
         var2 = true;
      }

      if (this.gP(var1)) {
         var2 = true;
      }

      return var2;
   }

   @Override
   public String toString() {
      return "DirectedGraph [outEdges=" + this.q + ", inEdges=" + this.RF + "]";
   }

   public String RF() {
      StringBuilder var1 = new StringBuilder();
      TreeMap var2 = new TreeMap(this.q);

      for (Entry var4 : var2.entrySet()) {
         var1.append("vertex ");
         var1.append(var4.getKey().toString());
         var1.append(" out edges:");
         ArrayList var5 = new ArrayList();

         for (ads var7 : (Set)var4.getValue()) {
            var5.add(var7.toString());
         }

         Collections.sort(var5);
         var1.append(var5);
         var1.append(" ");
      }

      TreeMap var9 = new TreeMap(this.RF);

      for (Entry var11 : var9.entrySet()) {
         var1.append("vertex");
         var1.append(var11.getKey().toString());
         var1.append(" in edges:");
         ArrayList var12 = new ArrayList();

         for (ads var8 : (Set)var11.getValue()) {
            var12.add(var8.toString());
         }

         Collections.sort(var12);
         var1.append(var12);
         var1.append(" ");
      }

      return var1.toString();
   }

   public boolean xK() {
      return this.RF.isEmpty() && this.q.isEmpty();
   }
}
