package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CodeGapAnalysisStyle;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.DataGapAnalysisStyle;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.NativeAnalyzerException;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Ser
public class BL {
   @SerId(1)
   private final MultiMap pC;
   @SerId(2)
   private final int A;

   public BL(MultiMap var1) {
      this.pC = var1;
      this.A = (Integer)Collections.max(var1.keySet());
   }

   public List pC() {
      ArrayList var1 = new ArrayList();

      for (RP var3 : this.pC.values()) {
         List var4 = var3.wS();
         if (var4 != null) {
            var1.addAll(var4);
         }
      }

      return var1;
   }

   public DataGapAnalysisStyle A() {
      HashSet var1 = new HashSet();

      for (int var2 = this.A; var2 >= 0; var2--) {
         for (RP var4 : this.pC.get(var2)) {
            DataGapAnalysisStyle var5 = var4.UT();
            if (var5 != null) {
               var1.add(var5);
            }
         }

         if (!var1.isEmpty()) {
            break;
         }
      }

      if (var1.size() > 1) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("category conflict"));
      }

      if (var1.isEmpty()) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("no matching categories"));
         return null;
      } else {
         return (DataGapAnalysisStyle)var1.iterator().next();
      }
   }

   public CodeGapAnalysisStyle kS() {
      HashSet var1 = new HashSet();

      for (int var2 = this.A; var2 >= 0; var2--) {
         for (RP var4 : this.pC.get(var2)) {
            CodeGapAnalysisStyle var5 = var4.E();
            if (var5 != null) {
               var1.add(var5);
            }
         }

         if (!var1.isEmpty()) {
            break;
         }
      }

      if (var1.size() > 1) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("category conflict"));
      }

      if (var1.isEmpty()) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("no matching categories"));
         return null;
      } else {
         return (CodeGapAnalysisStyle)var1.iterator().next();
      }
   }

   public iL.Av pC(INativeCodeUnit var1) {
      HashSet var2 = new HashSet();

      for (int var3 = this.A; var3 >= 0; var3--) {
         for (RP var5 : this.pC.get(var3)) {
            iL.Av var6 = var5.A(var1);
            if (var6 != null) {
               var2.add(var6);
            }
         }

         if (!var2.isEmpty()) {
            break;
         }
      }

      if (var2.size() > 1) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("category conflict"));
      }

      if (var2.isEmpty()) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("no matching categories"));
         return null;
      } else {
         return (iL.Av)var2.iterator().next();
      }
   }

   public com.pnfsoftware.jeb.corei.parsers.x86.wn.Av A(INativeCodeUnit var1) {
      HashSet var2 = new HashSet();

      for (int var3 = this.A; var3 >= 0; var3--) {
         for (RP var5 : this.pC.get(var3)) {
            com.pnfsoftware.jeb.corei.parsers.x86.wn.Av var6 = var5.kS(var1);
            if (var6 != null) {
               var2.add(var6);
            }
         }

         if (!var2.isEmpty()) {
            break;
         }
      }

      if (var2.size() > 1) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("category conflict"));
      }

      if (var2.isEmpty()) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("no matching categories"));
         return null;
      } else {
         return (com.pnfsoftware.jeb.corei.parsers.x86.wn.Av)var2.iterator().next();
      }
   }

   public Boolean wS() {
      HashSet var1 = new HashSet();

      for (int var2 = this.A; var2 >= 0; var2--) {
         for (RP var4 : this.pC.get(var2)) {
            Boolean var5 = var4.sY();
            if (var5 != null) {
               var1.add(var5);
            }
         }

         if (!var1.isEmpty()) {
            break;
         }
      }

      if (var1.size() > 1) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("category conflict"));
      }

      if (var1.isEmpty()) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("no matching categories"));
         return null;
      } else {
         return (Boolean)var1.iterator().next();
      }
   }

   public Boolean UT() {
      HashSet var1 = new HashSet();

      for (int var2 = this.A; var2 >= 0; var2--) {
         for (RP var4 : this.pC.get(var2)) {
            Boolean var5 = var4.ys();
            if (var5 != null) {
               var1.add(var5);
            }
         }

         if (!var1.isEmpty()) {
            break;
         }
      }

      if (var1.size() > 1) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("category conflict"));
      }

      if (var1.isEmpty()) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("no matching categories"));
         return null;
      } else {
         return (Boolean)var1.iterator().next();
      }
   }

   public Integer E() {
      HashSet var1 = new HashSet();

      for (int var2 = this.A; var2 >= 0; var2--) {
         for (RP var4 : this.pC.get(var2)) {
            Integer var5 = var4.ld();
            if (var5 != null) {
               var1.add(var5);
            }
         }

         if (!var1.isEmpty()) {
            break;
         }
      }

      if (var1.size() > 1) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("category conflict"));
      }

      if (var1.isEmpty()) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("no matching categories"));
         return null;
      } else {
         return (Integer)var1.iterator().next();
      }
   }

   public Boolean sY() {
      HashSet var1 = new HashSet();

      for (int var2 = this.A; var2 >= 0; var2--) {
         for (RP var4 : this.pC.get(var2)) {
            Boolean var5 = var4.gp();
            if (var5 != null) {
               var1.add(var5);
            }
         }

         if (!var1.isEmpty()) {
            break;
         }
      }

      if (var1.size() > 1) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("category conflict"));
      }

      if (var1.isEmpty()) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("no matching categories"));
         return null;
      } else {
         return (Boolean)var1.iterator().next();
      }
   }

   public Integer ys() {
      HashSet var1 = new HashSet();

      for (int var2 = this.A; var2 >= 0; var2--) {
         for (RP var4 : this.pC.get(var2)) {
            Integer var5 = var4.oT();
            if (var5 != null) {
               var1.add(var5);
            }
         }

         if (!var1.isEmpty()) {
            break;
         }
      }

      if (var1.size() > 1) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("category conflict"));
      }

      if (var1.isEmpty()) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("no matching categories"));
         return null;
      } else {
         return (Integer)var1.iterator().next();
      }
   }

   public Integer ld() {
      HashSet var1 = new HashSet();

      for (int var2 = this.A; var2 >= 0; var2--) {
         for (RP var4 : this.pC.get(var2)) {
            Integer var5 = var4.fI();
            if (var5 != null) {
               var1.add(var5);
            }
         }

         if (!var1.isEmpty()) {
            break;
         }
      }

      if (var1.size() > 1) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("category conflict"));
      }

      if (var1.isEmpty()) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("no matching categories"));
         return null;
      } else {
         return (Integer)var1.iterator().next();
      }
   }

   public Integer gp() {
      HashSet var1 = new HashSet();

      for (int var2 = this.A; var2 >= 0; var2--) {
         for (RP var4 : this.pC.get(var2)) {
            Integer var5 = var4.WR();
            if (var5 != null) {
               var1.add(var5);
            }
         }

         if (!var1.isEmpty()) {
            break;
         }
      }

      if (var1.size() > 1) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("category conflict"));
      }

      if (var1.isEmpty()) {
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("no matching categories"));
         return null;
      } else {
         return (Integer)var1.iterator().next();
      }
   }
}
