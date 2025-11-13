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
public class acb {
   @SerId(1)
   private Map pC = new ConcurrentHashMap();
   @SerId(2)
   private Map A = new ConcurrentHashMap();

   public boolean pC(Object var1) {
      boolean var2 = false;
      Set var3 = (Set)this.pC.get(var1);
      if (var3 == null) {
         ConcurrentHashSet var5 = new ConcurrentHashSet();
         this.pC.put(var1, var5);
         var2 = true;
      }

      Set var4 = (Set)this.A.get(var1);
      if (var4 == null) {
         ConcurrentHashSet var6 = new ConcurrentHashSet();
         this.A.put(var1, var6);
         var2 = true;
      }

      return var2;
   }

   public Set pC() {
      HashSet var1 = new HashSet(this.pC.keySet());
      var1.addAll(this.A.keySet());
      return var1;
   }

   public Set A(Object var1) {
      return (Set)this.pC.get(var1);
   }

   public Set kS(Object var1) {
      return (Set)this.A.get(var1);
   }

   public boolean pC(aca var1) {
      boolean var2 = false;
      Object var3 = var1.pC();
      Object var4 = (Set)this.pC.get(var3);
      if (var4 == null) {
         var4 = new ConcurrentHashSet();
         this.pC.put(var3, var4);
      }

      if (var4.add(var1)) {
         var2 = true;
      }

      Object var5 = var1.A();
      Object var6 = (Set)this.A.get(var5);
      if (var6 == null) {
         var6 = new ConcurrentHashSet();
         this.A.put(var5, var6);
      }

      if (var6.add(var1)) {
         var2 = true;
      }

      return var2;
   }

   public List wS(Object var1) {
      return this.pC(var1, (String[])null);
   }

   public List pC(Object var1, String... var2) {
      ArrayList var3 = new ArrayList();
      Set var4 = (Set)this.A.get(var1);
      if (var4 != null) {
         Iterator var5 = var4.iterator();

         while (true) {
            aca var6;
            boolean var7;
            do {
               if (!var5.hasNext()) {
                  return var3;
               }

               var6 = (aca)var5.next();
               if (var2 == null || var2.length <= 0) {
                  break;
               }

               var7 = false;

               for (String var11 : var2) {
                  if (var6.pC(var11) != null) {
                     var7 = true;
                     break;
                  }
               }
            } while (!var7);

            var3.add(var6.pC());
         }
      } else {
         return var3;
      }
   }

   public int UT(Object var1) {
      Set var2 = (Set)this.pC.get(var1);
      return var2 != null ? var2.size() : 0;
   }

   public List E(Object var1) {
      return this.A(var1, (String[])null);
   }

   public List A(Object var1, String... var2) {
      ArrayList var3 = new ArrayList();
      Set var4 = (Set)this.pC.get(var1);
      if (var4 != null) {
         Iterator var5 = var4.iterator();

         while (true) {
            aca var6;
            boolean var7;
            do {
               if (!var5.hasNext()) {
                  return var3;
               }

               var6 = (aca)var5.next();
               if (var2 == null || var2.length <= 0) {
                  break;
               }

               var7 = false;

               for (String var11 : var2) {
                  if (var6.pC(var11) != null) {
                     var7 = true;
                     break;
                  }
               }
            } while (!var7);

            var3.add(var6.A());
         }
      } else {
         return var3;
      }
   }

   public boolean pC(Object var1, Object var2) {
      Set var4 = (Set)this.pC.get(var1);
      if (var4 == null) {
         return false;
      } else {
         var4.removeIf(var1x -> var1x.A().equals(var2));
         Set var5 = (Set)this.A.get(var2);
         return var5 == null ? false : var5.removeIf(var1x -> var1x.pC().equals(var1));
      }
   }

   public boolean sY(Object var1) {
      Set var2 = (Set)this.pC.remove(var1);
      if (var2 == null) {
         return false;
      } else {
         for (aca var4 : var2) {
            Set var5 = (Set)this.A.get(var4.A());
            if (var5 != null) {
               var5.remove(var4);
            }
         }

         return true;
      }
   }

   public boolean ys(Object var1) {
      Set var2 = (Set)this.A.remove(var1);
      if (var2 == null) {
         return false;
      } else {
         for (aca var4 : var2) {
            Set var5 = (Set)this.pC.get(var4.pC());
            if (var5 != null) {
               var5.remove(var4);
            }
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return "DirectedGraph [outEdges=" + this.pC + ", inEdges=" + this.A + "]";
   }

   public String A() {
      StringBuilder var1 = new StringBuilder();
      TreeMap var2 = new TreeMap(this.pC);

      for (Entry var4 : var2.entrySet()) {
         var1.append("vertex ");
         var1.append(var4.getKey().toString());
         var1.append(" out edges:");
         ArrayList var5 = new ArrayList();

         for (aca var7 : (Set)var4.getValue()) {
            var5.add(var7.toString());
         }

         Collections.sort(var5);
         var1.append(var5);
         var1.append(" ");
      }

      TreeMap var9 = new TreeMap(this.A);

      for (Entry var11 : var9.entrySet()) {
         var1.append("vertex");
         var1.append(var11.getKey().toString());
         var1.append(" in edges:");
         ArrayList var12 = new ArrayList();

         for (aca var8 : (Set)var11.getValue()) {
            var12.add(var8.toString());
         }

         Collections.sort(var12);
         var1.append(var12);
         var1.append(" ");
      }

      return var1.toString();
   }

   public boolean kS() {
      return this.A.isEmpty() && this.pC.isEmpty();
   }
}
