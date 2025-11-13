package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExceptionHandler;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

public class btn implements IDTryData {
   private static final ILogger q = GlobalLog.getLogger(btn.class);
   private SortedMap RF = new TreeMap();
   private bix xK;

   public btn() {
   }

   public btn q() {
      btn var1 = new btn();

      for (Entry var3 : this.RF.entrySet()) {
         int var4 = (Integer)var3.getKey();
         List var5 = (List)var3.getValue();
         if (var5 != null && !var5.isEmpty()) {
            var1.RF.put(var4, biv.q(var5));
         }
      }

      var1.xK = null;
      return var1;
   }

   public void q(int var1, int var2, int var3) {
      Object var4 = (List)this.RF.get(var1);
      if (var4 == null) {
         var4 = new ArrayList();
         this.RF.put(var1, var4);
      }

      var4.add(new biv(var2, var3));
   }

   public btn(bix var1, SortedSet var2, Map var3) {
      Object[] var10000 = new Object[]{var1};
      TreeSet var4 = null;

      for (biw var6 : var1.RF()) {
         Integer var7 = (Integer)var3.get(var6.getTryAddress());
         if (var7 == null) {
            if (var4 == null) {
               var4 = new TreeSet(var3.keySet());
            }

            Integer var8 = (Integer)var4.ceiling(var6.getTryAddress());
            if (var8 == null) {
               RuntimeException var15 = new RuntimeException(Strings.ff("Cannot find matching IR offset for: 0x%X (try-start)", var6.getTryAddress()));
               com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(var15);
               continue;
            }

            var7 = (Integer)var3.get(var8);
            Assert.a(var7 != null);
         }

         Integer var13 = (Integer)var3.get(var6.RF());
         if (var13 == null) {
            if (var4 == null) {
               var4 = new TreeSet(var3.keySet());
            }

            Integer var9 = (Integer)var4.floor(var6.RF());
            if (var9 == null) {
               RuntimeException var17 = new RuntimeException(Strings.ff("Cannot find matching IR offset for: 0x%X (try-end)", var6.RF()));
               com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(var17);
               continue;
            }

            var13 = (Integer)var3.get(var9);
            Assert.a(var13 != null);
         }

         if (var7 < var13) {
            ArrayList var14 = new ArrayList();

            for (biv var11 : var6.getHandlers()) {
               Integer var12 = (Integer)var3.get(var11.getAddress());
               if (var12 == null) {
                  throw new RuntimeException(Strings.ff("Cannot find matching IR offset for: 0x%X (try-catch)", var11.getAddress()));
               }

               var14.add(new biv(var11.RF(), var12));
            }

            if (!var14.isEmpty()) {
               int var16 = 0;

               for (Integer var19 : var2.subSet(var7, var13)) {
                  if (var16 == 0) {
                     this.RF.put(var19, var14);
                  } else {
                     this.RF.put(var19, biv.q(var14));
                  }

                  var16++;
               }
            }
         }
      }
   }

   @Override
   public boolean isEmpty() {
      return this.RF.isEmpty();
   }

   @Override
   public boolean hasProtectedBlocks() {
      return !this.isEmpty();
   }

   @Override
   public List getProtectedBlocks() {
      ArrayList var1 = new ArrayList(this.RF.size());

      for (Entry var3 : this.RF.entrySet()) {
         if (var3.getValue() != null && !((List)var3.getValue()).isEmpty()) {
            var1.add((Integer)var3.getKey());
         }
      }

      return var1;
   }

   @Override
   public boolean isProtectedBlock(int var1) {
      List var2 = (List)this.RF.get(var1);
      return var2 != null && !var2.isEmpty();
   }

   @Override
   public List getHandlers() {
      ArrayList var1 = new ArrayList();

      for (List var3 : this.RF.values()) {
         if (var3 != null && !var3.isEmpty()) {
            var1.addAll(var3);
         }
      }

      return Collections.unmodifiableList(var1);
   }

   @Override
   public List getHandlers(int var1) {
      ArrayList var2 = new ArrayList();

      for (List var4 : this.RF.values()) {
         if (var4 != null && !var4.isEmpty()) {
            for (IDExceptionHandler var6 : var4) {
               if (var6.getAddress() == var1) {
                  var2.add(var6);
               }
            }
         }
      }

      return Collections.unmodifiableList(var2);
   }

   @Override
   public Set getHandledExceptionTypesAt(int var1) {
      LinkedHashSet var2 = new LinkedHashSet();

      for (List var4 : this.RF.values()) {
         if (var4 != null && !var4.isEmpty()) {
            for (biv var6 : var4) {
               if (var6.getAddress() == var1) {
                  var2.add(var6.RF());
               }
            }
         }
      }

      return var2;
   }

   @Override
   public List getBlockHandlers(int var1) {
      List var2 = (List)this.RF.get(var1);
      return var2 != null && !var2.isEmpty() ? Collections.unmodifiableList(var2) : Collections.emptyList();
   }

   @Override
   public void setBlockHandlers(int var1, Collection var2) {
      if (var2.isEmpty()) {
         this.RF.remove(var1);
      } else {
         Object var3 = (List)this.RF.get(var1);
         if (var3 == null) {
            var3 = new ArrayList();
            this.RF.put(var1, var3);
         } else {
            var3.clear();
         }

         for (IDExceptionHandler var5 : var2) {
            var3.add(new biv(var5.getTypeIndex(), var5.getAddress()));
         }
      }
   }

   @Override
   public boolean compareHandlers(int var1, int var2) {
      List var3 = (List)this.RF.get(var1);
      List var4 = (List)this.RF.get(var2);
      if ((var3 != null || var4 != null) && (var3 != null || !var4.isEmpty()) && (var4 != null || !var3.isEmpty())) {
         return var3 != null && var4 != null ? var3.equals(var4) : false;
      } else {
         return true;
      }
   }

   @Override
   public boolean replaceHandler(int var1, IDExceptionHandler var2, IDExceptionHandler var3) {
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else {
         List var4 = (List)this.RF.get(var1);
         if (var4 != null && !var4.isEmpty()) {
            for (int var5 = 0; var5 < var4.size(); var5++) {
               if (var4.get(var5) == var2) {
                  if (var3 != null) {
                     var4.set(var5, (biv)var3);
                  } else {
                     var4.remove(var5);
                     if (var4.isEmpty()) {
                        this.RF.remove(var1);
                     }
                  }

                  return true;
               }
            }

            return false;
         } else {
            return false;
         }
      }
   }

   @Override
   public boolean protectBlock(int var1, int var2, int var3, int var4) {
      return this.q(var1, var2, var3, var4, false);
   }

   public boolean q(int var1, int var2, int var3, int var4, boolean var5) {
      boolean var6 = false;
      Object var7 = (List)this.RF.get(var1);
      if (var7 == null) {
         var7 = new ArrayList();
         var6 = true;
      }

      if (var4 < 0) {
         var4 += var7.size() + 1;
      }

      if (var4 >= 0 && var4 <= var7.size()) {
         if (!var5) {
            int var8 = 0;

            for (biv var10 : var7) {
               if (var8 >= var4) {
                  break;
               }

               if (var10.getTypeIndex() == var2) {
                  return false;
               }

               var8++;
            }
         }

         var7.add(var4, new biv(var2, var3));
         if (var6) {
            this.RF.put(var1, var7);
         }

         return true;
      } else {
         throw new IllegalArgumentException("Illegal insertion index argument");
      }
   }

   @Override
   public boolean moveProtectedBlock(int var1, int var2) {
      List var3 = (List)this.RF.remove(var1);
      if (var3 != null && !var3.isEmpty()) {
         this.RF.put(var2, var3);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean copyProtectedBlock(int var1, int var2) {
      List var3 = (List)this.RF.get(var1);
      if (var3 != null && !var3.isEmpty()) {
         this.RF.put(var2, biv.q(var3));
         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean addProtectionFromBlock(int var1, int var2) {
      List var3 = (List)this.RF.get(var1);
      if (var3 != null && !var3.isEmpty()) {
         Object var4 = (List)this.RF.get(var2);
         if (var4 == null) {
            var4 = new ArrayList();
            this.RF.put(var2, var4);
         }

         var4.addAll(biv.q(var3));
         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean unprotectBlock(int var1) {
      List var2 = (List)this.RF.remove(var1);
      return var2 != null && !var2.isEmpty();
   }

   @Override
   public boolean unprotectBlock(int var1, int var2) {
      List var3 = (List)this.RF.get(var1);
      if (var3 != null && !var3.isEmpty()) {
         LinkedList var4 = new LinkedList();
         int var5 = 0;

         for (biv var7 : var3) {
            if (var7.getAddress() == var2) {
               var4.add(0, var5);
            }

            var5++;
         }

         if (var4.isEmpty()) {
            return false;
         } else {
            for (int var9 : var4) {
               var3.remove(var9);
            }

            if (var3.isEmpty()) {
               this.RF.remove(var1);
            }

            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public boolean unprotectBlock(int var1, int var2, int var3) {
      List var4 = (List)this.RF.get(var1);
      if (var4 != null && !var4.isEmpty()) {
         int var5 = 0;

         for (biv var7 : var4) {
            if (var7.getAddress() == var2 && var7.getTypeIndex() == var3) {
               break;
            }

            var5++;
         }

         if (var5 >= var4.size()) {
            return false;
         } else {
            var4.remove(var5);
            if (var4.isEmpty()) {
               this.RF.remove(var1);
            }

            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public int updateTargets(Map var1) {
      return this.updateTargets(var1, false);
   }

   @Override
   public int updateTargets(Map var1, boolean var2) {
      int var3 = 0;
      HashMap var4 = new HashMap();

      for (int var6 : this.RF.keySet()) {
         Integer var7 = (Integer)var1.get(var6);
         if (var7 == null) {
            if (var2) {
               throw new IllegalArgumentException(Strings.ff("Missed entry for old offset 0x%X", var6));
            }

            var7 = var6;
         } else {
            var3++;
         }

         List var8 = (List)this.RF.get(var6);
         if (var8 != null && !var8.isEmpty()) {
            var4.put(var7, var8);
         }
      }

      this.RF.clear();
      this.RF.putAll(var4);

      for (List var11 : this.RF.values()) {
         if (var11 != null && !var11.isEmpty()) {
            for (biv var13 : var11) {
               Integer var9 = (Integer)var1.get(var13.getAddress());
               if (var9 != null) {
                  var13.setAddress(var9);
                  var3++;
               }
            }
         }
      }

      return var3;
   }

   @Override
   public boolean updateHandlerAddress(int var1, int var2) {
      int var3 = 0;

      for (List var5 : this.RF.values()) {
         if (var5 != null && !var5.isEmpty()) {
            for (biv var7 : var5) {
               if (var7.getAddress() == var1) {
                  var7.setAddress(var2);
                  var3++;
               }
            }
         }
      }

      return var3 > 0;
   }

   public bix q(List var1) {
      if (this.xK != null) {
         return this.xK;
      } else {
         ArrayList var2 = new ArrayList();

         for (BasicBlock var4 : var1) {
            if (var4.irroutsize() > 0) {
               int var5 = (int)var4.getFirstAddress();
               if (!this.RF.containsKey(var5)) {
                  throw new RuntimeException();
               }

               List var6 = (List)this.RF.get(var5);
               biw var7 = new biw(var5, (int)(var4.getEndAddress() - var5), var6);
               var2.add(var7);
            }
         }

         this.xK = new bix(var2);
         return this.xK;
      }
   }

   public static bix q(bix var0, List var1) {
      HashMap var2 = new HashMap();
      int var3 = 0;

      for (BasicBlock var5 : var1) {
         int var6 = (int)var5.getFirstAddress();
         var2.put(var6, var3);
         var3++;
      }

      TreeSet var11 = new TreeSet();
      var1.forEach(var1x -> var11.add((int)var1x.getFirstAddress()));
      var1.forEach(var1x -> var11.add((int)var1x.getEndAddress()));
      List var12 = var0.q(var11);

      for (biw var7 : var12) {
         int var8 = var7.getTryAddress();
         var7.q((Integer)var2.get(var8), false);
         var7.q(1);

         for (biv var10 : var7.getHandlers()) {
            var8 = var10.getAddress();
            var10.setAddress((Integer)var2.get(var8));
         }
      }

      return new bix(var12);
   }

   public bix RF(List var1) {
      if (this.xK != null) {
         throw new IllegalStateException("Already transformed");
      } else {
         HashMap var2 = new HashMap();
         int var3 = 0;

         for (BasicBlock var5 : var1) {
            int var6 = (int)var5.getFirstAddress();
            var2.put(var6, var3);
            var3++;
         }

         for (List var13 : this.RF.values()) {
            for (biv var7 : var13) {
               var7.setAddress((Integer)var2.get(var7.getAddress()));
            }
         }

         ArrayList var12 = new ArrayList();
         var3 = 0;

         for (BasicBlock var16 : var1) {
            int var17 = (int)var16.getFirstAddress();
            if (this.RF.containsKey(var17)) {
               List var8 = (List)this.RF.get(var17);
               biw var9 = new biw(var3, 1, var8);
               var12.add(var9);
            }

            var3++;
         }

         this.xK = new bix(var12);
         return this.xK;
      }
   }

   @Override
   public String toString() {
      return this.RF + "";
   }
}
